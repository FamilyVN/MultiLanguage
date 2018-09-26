package com.family.multi.language.activity.changelanguage;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;

import com.family.multi.language.R;
import com.family.multi.language.activity.BaseActivity;
import com.family.multi.language.activity.main.MainActivity;
import com.family.multi.language.databinding.ActivityChangeLanguageBinding;

import java.util.Locale;

public class ChangeLanguageActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChangeLanguageBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_change_language);
        String note = String.format(getString(R.string.text_notification_change_language),
            Locale.getDefault().getDisplayLanguage());
        binding.tvNote.setText(note);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ChangeLanguageActivity.this, MainActivity.class));
                finish();
            }
        }, 5000);
    }
}
