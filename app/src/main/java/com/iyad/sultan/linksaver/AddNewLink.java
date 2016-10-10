package com.iyad.sultan.linksaver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iyad.sultan.linksaver.Models.Link;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

/**
 * Created by sultan on 10/10/16.
 */


public class AddNewLink extends AppCompatActivity {
    private Realm realm;
    private RealmAsyncTask anRealmAsyncTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_link);
        realm = Realm.getDefaultInstance();

      //  title = ((EditText)findViewById(R.id.short_des)).getText().toString();


    }

    public void addLink(View view) {
        Toast.makeText(this, ((EditText)findViewById(R.id.short_des)).getText().toString(), Toast.LENGTH_SHORT).show();

      anRealmAsyncTask =  realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Link link = realm.createObject(Link.class);
                link.setTitle(((EditText)findViewById(R.id.short_des)).getText().toString());
                link.setLink(((EditText)findViewById(R.id.link)).getText().toString());
                link.setImportant(false);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                finish();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(AddNewLink.this, getResources().getString(R.string.add_link_error), Toast.LENGTH_SHORT).show();
            }
        });

    }






    @Override
    protected void onStop() {
        super.onStop();
        if(anRealmAsyncTask !=null && !anRealmAsyncTask.isCancelled())
            anRealmAsyncTask.cancel();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(realm != null ) {
            realm.close();
            realm = null;
        }


    }
}
