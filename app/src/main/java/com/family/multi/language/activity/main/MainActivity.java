package com.family.multi.language.activity.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.family.multi.language.R;
import com.family.multi.language.activity.BaseActivity;
import com.family.multi.language.activity.changelanguage.ChangeLanguageActivity;
import com.family.multi.language.databinding.ActivityMainBinding;
import com.family.multi.language.utils.SpManager;
import com.family.multi.language.utils.language.LanguageUtils;

import java.util.Locale;

public class MainActivity extends BaseActivity {
    private AlertDialog mUnitSettingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.btnChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChangeLanguage();
            }
        });
    }

    public void openChangeLanguage() {
        final String[] arrayName = LanguageUtils.getLanguageNameList(this);
        final String[] array = LanguageUtils.getLanguageList(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.title_settings_change_language)
            .setSingleChoiceItems(arrayName, LanguageUtils.getIndexLanguage(this),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String languageFirst = LanguageUtils.getLanguage();
                        if (which == arrayName.length - 1) {
                            SpManager.getInstance().putString(LanguageUtils.LANGUAGE,
                                LanguageUtils.getLanguageLocal());
                            SpManager.getInstance()
                                .putBoolean(LanguageUtils.IS_AUTO_LANGUAGE, true);
                        } else {
                            SpManager.getInstance().putString(LanguageUtils.LANGUAGE, array[which]);
                            SpManager.getInstance()
                                .putBoolean(LanguageUtils.IS_AUTO_LANGUAGE, false);
                        }
                        mUnitSettingDialog.dismiss();
                        if (!TextUtils.equals(array[which], languageFirst)) {
                            restartActivity();
                        }
                    }
                })
            .setNegativeButton(R.string.text_dialog_ok, null);
        mUnitSettingDialog = builder.create();
        mUnitSettingDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SpManager.getInstance().getBoolean(LanguageUtils.IS_RESTART, false)) {
            SpManager.getInstance().putBoolean(LanguageUtils.IS_RESTART, false);
            restartActivity();
        }
    }

    private void restartActivity() {
        LanguageUtils.changeLanguageType(new Locale(LanguageUtils.getLanguage()));
        Intent intent = new Intent(this, ChangeLanguageActivity.class);
        startActivity(intent);
        finish();
    }
}
