package com.iyad.sultan.linksaver.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iyad.sultan.linksaver.Controller.RecyclerViewAdapter;
import com.iyad.sultan.linksaver.Models.Link;
import com.iyad.sultan.linksaver.R;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LinkFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LinkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LinkFragment extends Fragment implements RecyclerViewAdapter.AdapterInterface ,RealmChangeListener{


    private RecyclerView rec;
    private Realm realm;
    private RealmQuery<Link> query;

    private RealmResults<Link> result;

    private RealmAsyncTask realmAsyncTask;
    private RecyclerViewAdapter adapter;

    //


    private Link link;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LinkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LinkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LinkFragment newInstance(String param1, String param2) {
        LinkFragment fragment = new LinkFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_link, container, false);
        rec = (RecyclerView) v.findViewById(R.id.root_recyclerview);

        realm = Realm.getDefaultInstance();
        query = realm.where(Link.class);
        result = query.findAll();
        result.addChangeListener(this);
        adapter = new RecyclerViewAdapter(result);
        adapter.setAdapterListener(this);
        rec.setAdapter(adapter);
        rec.setItemAnimator(new DefaultItemAnimator());
        rec.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Link link) {
        if (mListener != null) {
            mListener.onLinkFragmentInteraction(link);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (realm != null) {
            realm.close();
            realm = null;
        }  }


    @Override
    public void getSelectedItem(int id, int position) {
        mListener.getSelectedItem(id, position);
    }

    @Override
    public void deleteInterface(int position) {
        deleteLink(position);
    }

    @Override
    public void onChange(Object element) {
        adapter.notifyDataSetChanged();
        Toast.makeText(getActivity().getApplicationContext(), "on change called " , Toast.LENGTH_SHORT).show();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onLinkFragmentInteraction(Link link);

        void getSelectedItem(int item, int position);
    }

    @Override
    public void onStop() {
        super.onStop();

        realm.removeAllChangeListeners();
        adapter.notifyDataSetChanged();
        Toast.makeText(getActivity().getApplicationContext(), "remove :Listner", Toast.LENGTH_SHORT).show();

    }

    void deleteLink(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
 // Add the buttons
        builder.setMessage(R.string.are_you_sure_want_delete);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Link l = result.get(position);
                        l.deleteFromRealm();
                    }
                });
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        // adapter.notifyItemRemoved(position);
        Toast.makeText(getActivity().getApplicationContext(), "Deleetttt", Toast.LENGTH_SHORT).show();
  }

}
