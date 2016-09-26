package com.iyad.sultan.linksaver.Models;

import io.realm.RealmObject;

/**
 * Created by sultan on 9/24/16.
 */

public class Link extends RealmObject {

    private String Title;
    private String Link;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    private boolean isImportant;

}
