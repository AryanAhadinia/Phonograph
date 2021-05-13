package com.kabouzeid.gramophone.loader;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;

import com.kabouzeid.gramophone.model.Song;
import com.kabouzeid.gramophone.util.PreferenceUtil;

import java.util.ArrayList;
import java.util.List;

public class LastAddedLoader {

    @NonNull
    public static List<Song> getLastAddedSongs(@NonNull Context context) {
        List<Song> songs = SongLoader.getSongs(makeLastAddedCursor(context));

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean isLimitEnable = PreferenceUtil.getInstance(context).isLastAddedItemShowLimitEnable();

        if (!isLimitEnable) {
            return songs;
        }

        int limit = PreferenceUtil.getInstance(context).getLastAddedItemShowLimit();
        return songs.subList(0, Math.min(songs.size(), limit));
    }

    public static Cursor makeLastAddedCursor(@NonNull final Context context) {
        long cutoff = PreferenceUtil.getInstance(context).getLastAddedCutoff();

        return SongLoader.makeSongCursor(
                context,
                MediaStore.Audio.Media.DATE_ADDED + ">?",
                new String[]{String.valueOf(cutoff)},
                MediaStore.Audio.Media.DATE_ADDED + " DESC");
    }
}
