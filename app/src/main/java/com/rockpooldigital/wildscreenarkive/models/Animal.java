package com.rockpooldigital.wildscreenarkive.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.util.Date;

@ParseClassName("Animal")
public class Animal extends ParseObject {

    public String getName() {
        return getString("name");
    }

    public String getDescription() {
        return getString("description");
    }

    public Date getActiveDate() {
        return getDate("activeDate");
    }

    public String getUrl() {
        return getString("url");
    }

    public String getLatin() {
        return getString("latinName");
    }

    public ParseFile getImageFile() {
        return getParseFile("image");
    }
}
