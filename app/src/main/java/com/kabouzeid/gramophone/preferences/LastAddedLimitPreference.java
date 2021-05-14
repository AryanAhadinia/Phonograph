package com.kabouzeid.gramophone.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;

import androidx.preference.PreferenceManager;

import com.kabouzeid.appthemehelper.common.prefs.supportv7.ATEDialogPreference;
import com.kabouzeid.gramophone.R;
import com.kabouzeid.gramophone.util.PreferenceUtil;

public class LastAddedLimitPreference extends ATEDialogPreference {

    public LastAddedLimitPreference(Context context) {
        super(context);
    }

    public LastAddedLimitPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LastAddedLimitPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LastAddedLimitPreference(Context context, AttributeSet attrs, int defStyleAttr,
                                    int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public CharSequence getSummary() {
        final PreferenceUtil preferenceUtil = PreferenceUtil.getInstance(getContext());
        if (preferenceUtil.isLastAddedItemShowLimitEnable()) {
            return String.valueOf(preferenceUtil.getLastAddedItemShowLimit());
        } else {
            return null;
        }
    }
}
