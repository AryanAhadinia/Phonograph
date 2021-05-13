package com.kabouzeid.gramophone.loader;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;

import com.kabouzeid.gramophone.R;
import com.kabouzeid.gramophone.model.Song;
import com.kabouzeid.gramophone.util.PreferenceUtil;

import java.util.List;

public class LastAddedLoader {

    @NonNull
    public static List<Song> getLastAddedSongs(@NonNull Context context) {
        int defaultLimit = context.getResources().getInteger(R.integer.default_last_added_limit);
        int limit = toIntOrDefault(PreferenceManager.getDefaultSharedPreferences(context)
                .getString("last_added_limit", String.valueOf(defaultLimit)), defaultLimit);
        List<Song> songs = SongLoader.getSongs(makeLastAddedCursor(context));
        return songs.subList(0, Math.min(songs.size(), limit));
    }

    public static int toIntOrDefault(String s, int d) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return d;
        }
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
