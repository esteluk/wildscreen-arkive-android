package com.rockpooldigital.wildscreenarkive;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;
import com.rockpooldigital.wildscreenarkive.models.Animal;

public class WildscreenApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        ParseObject.registerSubclass(Animal.class);
        Parse.initialize(this, "fV6QKO7mHUXOixMrdwuq8gNCNC0Cckvp7jLwLY2o", "OtPZuv7O6ojLKU1NKb4VWax5fOyiYRo6EwT50qeW");
    }

}
