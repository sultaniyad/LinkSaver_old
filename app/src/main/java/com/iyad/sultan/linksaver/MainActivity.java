package com.iyad.sultan.linksaver;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.iyad.sultan.linksaver.Fragments.ImportnatLinksFragment;
import com.iyad.sultan.linksaver.Fragments.LinkFragment;
import com.iyad.sultan.linksaver.Models.Link;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LinkFragment.OnFragmentInteractionListener, ImportnatLinksFragment.OnFragmentInteractionListener {

    //


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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adpater = new ViewPagerAdapter(getSupportFragmentManager());
        adpater.addFragment(new LinkFragment(), "Links");
        adpater.addFragment(new ImportnatLinksFragment(), "Important Links");
        viewPager.setAdapter(adpater);
    }


    //Interfaces Fragments


    @Override
    public void onImportnatLinksFragmentInteraction(String uri) {

    }

    @Override
    public void onLinkFragmentInteraction(final Link link) {
        //Start new Fragment with data
        Toast.makeText(this, "Link: " + link.getLink() + " title " + link.getTitle() + " isImpotant " + link.isImportant(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void getSelectedItem(int item, int position) {
        switch (item) {
            case R.id.cardview_root:
                Toast.makeText(this, "Root CardView", Toast.LENGTH_SHORT).show();
                return;
            case R.id.is_important_img:
                Toast.makeText(this, " Is Important" + position, Toast.LENGTH_SHORT).show();
                return;
            case R.id.open_link_img:
                Toast.makeText(this, "Open Link " + position, Toast.LENGTH_SHORT).show();
        }
    }

    //Adapter Fragment
    class ViewPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
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


    /// Interfaces


    //end


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(this, "Add Link", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), AddNewLink.class));

                return true;


            // User chose the "Favorite" action, mark the current item
            // as a favorite...


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Toast.makeText(this, "Back Button clicked Default", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);

        }
    }


    //Interface MainActivity and LinkFragment


}

