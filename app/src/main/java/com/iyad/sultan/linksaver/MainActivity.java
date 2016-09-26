package com.iyad.sultan.linksaver;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.iyad.sultan.linksaver.Fragments.DetailsFragment;
import com.iyad.sultan.linksaver.Fragments.ImportnatLinksFragment;
import com.iyad.sultan.linksaver.Fragments.LinkFragment;
import com.iyad.sultan.linksaver.Models.Link;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
public class MainActivity extends AppCompatActivity implements LinkFragment.OnFragmentInteractionListener , ImportnatLinksFragment.OnFragmentInteractionListener, DetailsFragment.OnFragmentInteractionListener {
    Realm realmDB;
    RealmAsyncTask realmAsyncTask;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        realmDB = Realm.getDefaultInstance();
        realmDB.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Link l = realm.createObject(Link.class);
                l.setTitle("Yosif 2");
                l.setLink("this is Link");
                l.setImportant(false);
            }
        });*/

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adpater =new ViewPagerAdapter(getSupportFragmentManager());
        adpater.addFragment(new LinkFragment(),"LInks");
        adpater.addFragment(new ImportnatLinksFragment(),"Important LInks");
        viewPager.setAdapter(adpater);
    }


    //Interfaces Fragments
    @Override
    public void onDetailsFragmentInteraction(String uri) {

    }

    @Override
    public void onImportnatLinksFragmentInteraction(String uri) {

    }

    @Override
    public void onLinkFragmentInteraction(String srt) {

    }


    //Adapter Fragment
    class ViewPagerAdapter extends android.support.v4.app.FragmentPagerAdapter{
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentTitleList.size();
        }
        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
