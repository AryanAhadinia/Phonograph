package com.kabouzeid.gramophone.preferences;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.preference.PreferenceManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.kabouzeid.gramophone.R;
import com.kabouzeid.gramophone.util.PreferenceUtil;

public class LastAddedLimitPreferenceDialog extends DialogFragment {

    public static LastAddedLimitPreferenceDialog newInstance() {
        return new LastAddedLimitPreferenceDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = requireActivity().getLayoutInflater()
                .inflate(R.layout.preference_dailog_last_added_limit ,null);

        PreferenceUtil preferenceUtil = PreferenceUtil.getInstance(requireContext());

        Switch enableLimit = view.findViewById(R.id.last_added_limit_switch);
        EditText limitEditText = view.findViewById(R.id.last_added_limit);
        enableLimit.setChecked(preferenceUtil.isLastAddedItemShowLimitEnable());

        limitEditText.setText(String.valueOf(preferenceUtil.getLastAddedItemShowLimit()));
        limitEditText.setEnabled(enableLimit.isChecked());

        enableLimit.setOnCheckedChangeListener((compoundButton, b) -> limitEditText.setEnabled(b));

        return new MaterialDialog.Builder(requireContext())
                .title(getResources().getString(R.string.last_added_items_show_limit))
                .autoDismiss(false)
                .customView(view, false)
                .positiveText(android.R.string.ok)
                .negativeText(android.R.string.cancel)
                .neutralText(R.string.reset_action)
                .onPositive((dialog, which) -> {
                    String limitText = limitEditText.getText().toString();
                    int limit;
                    if (limitText.equals("")) {
                        limit = 0;
                    } else {
                        limit = Integer.parseInt(limitText);
                    }
                    preferenceUtil.setLastAddedItemShowLimitEnable(true);
                    preferenceUtil.setLastAddedItemShowLimit(limit);
                    dismiss();
                })
                .onNegative((dialog, which) -> dismiss())
                .onNeutral((dialog, which) -> {
                    limitEditText.setText( String.valueOf(getResources().getInteger(R.integer.default_last_added_show_limit)));
                })
                .build();
    }
}
