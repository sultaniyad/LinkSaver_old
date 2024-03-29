package com.iyad.sultan.linksaver.Controller;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iyad.sultan.linksaver.Models.Link;
import com.iyad.sultan.linksaver.R;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by sultan on 9/24/16.
 */

//Adapter RecyclerView

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {


    private RealmResults<Link> linkList ;
    private  AdapterInterface callBackListner;

    public RecyclerViewAdapter(RealmResults<Link> list){
         linkList = list;


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new ViewHolder(v);
    }


    public void setAdapterListener(final AdapterInterface call){
           this.callBackListner = call;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Link link  = linkList.get(position);
        holder.Title.setText(link.getTitle());
        //if Fav do this
        holder.openLinkImg.setImageResource(R.drawable.ic_open_in_browser_black_24dp);
        holder.isImportantImg.setImageResource(R.drawable.important_link_yes);


    }

    @Override
    public int getItemCount() {
        return linkList.size();
    }




    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

        public View cardView  ;
        public ImageView openLinkImg;
        public ImageView isImportantImg;
        public TextView Title;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardview_root);
            openLinkImg = (ImageView) itemView.findViewById(R.id.open_link_img);
            isImportantImg = (ImageView) itemView.findViewById(R.id.is_important_img);
            Title = (TextView) itemView.findViewById(R.id.title);
         /*long Press To delete*/   cardView.setOnLongClickListener(this);
            isImportantImg.setOnClickListener(this);
            openLinkImg.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
           switch (v.getId()){
               case R.id.is_important_img:  callBackListner.getSelectedItem(v.getId(),getAdapterPosition());return;
               case R.id.open_link_img:callBackListner.getSelectedItem(v.getId(),getAdapterPosition());return;
               default:return;
        }}


        @Override
        public boolean onLongClick(View v) {
            callBackListner.deleteInterface(getAdapterPosition());
            return true;
        }
    }

    //Interface
    public interface AdapterInterface{

         void getSelectedItem(int id,int position);
         void deleteInterface(int position);

    }







}

