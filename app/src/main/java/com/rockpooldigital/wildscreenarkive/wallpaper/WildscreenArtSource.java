package com.rockpooldigital.wildscreenarkive.wallpaper;

import android.content.Intent;
import android.net.Uri;

import com.google.android.apps.muzei.api.Artwork;
import com.google.android.apps.muzei.api.RemoteMuzeiArtSource;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.rockpooldigital.wildscreenarkive.models.Animal;

import java.util.Date;

/**
 * Thie class provides images for use by the wallpaper extension Muzei
 * Created by nathan on 14/08/2015.
 */
public class WildscreenArtSource extends RemoteMuzeiArtSource {

    private static final String SOURCE_NAME = "WildscreenArtSource";
    private static final int ROTATE_TIME = 1000 * 60 * 60 * 24;

    public WildscreenArtSource() {
        super(SOURCE_NAME);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        setUserCommands(BUILTIN_COMMAND_ID_NEXT_ARTWORK);
    }

    @Override
    protected void onTryUpdate(int reason) throws RetryException {

        ParseQuery<Animal> query = ParseQuery.getQuery(Animal.class);
        query.setLimit(1);
        query.whereLessThanOrEqualTo("activeDate", new Date());
        query.orderByDescending("activeDate");
        try {
            Animal a = query.getFirst();

            // Download image
            a.getImageFile().getData();

            publishArtwork(new Artwork.Builder()
                    .title(a.getName())
                    .byline(a.getLatin())
                    .imageUri(Uri.parse(a.getImageFile().getUrl()))
                    .viewIntent(new Intent(Intent.ACTION_VIEW, Uri.parse(a.getUrl())))
                    .build());

            scheduleUpdate(ROTATE_TIME);

        } catch (ParseException e) {
            e.printStackTrace();

            throw new RetryException(e);
        }
    }
}
