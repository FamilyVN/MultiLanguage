package com.family.multi.language.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.family.multi.language.utils.language.LanguageUtils;
import com.family.multi.language.utils.language.MyContextWrapper;

import java.util.Locale;

public abstract class BaseActivity extends AppCompatActivity {
    // change language of app
    @Override
    protected void attachBaseContext(Context newBase) {
        Locale languageType = LanguageUtils.getLanguageType();
        super.attachBaseContext(MyContextWrapper.wrap(newBase, languageType));
    }
}
