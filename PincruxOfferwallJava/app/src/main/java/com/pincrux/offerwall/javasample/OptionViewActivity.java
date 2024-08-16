package com.pincrux.offerwall.javasample;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.pincrux.offerwall.PincruxOfferwall;
import com.pincrux.offerwall.ui.common.impl.PincruxCloseImpl;

public class OptionViewActivity extends AppCompatActivity {

    private PincruxOfferwall offerwall;
    private int type;
    private boolean orientation;
    private LinearLayoutCompat container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_view);
        init();
        findId();
        offerwallInit();
        offerwallView();
    }

    @SuppressLint("SourceLockedOrientationActivity")
    private void init() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            type = getIntent().getExtras().getInt("type");
            orientation = getIntent().getExtras().getBoolean("orientation");
        } else {
            finish();
        }
        if (orientation) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void findId() {
        container = findViewById(R.id.container);
    }

    private void offerwallInit() {
        String pubKey = Preference.getInstance().getPubKey(this);
        offerwall = PincruxOfferwall.getInstance();
        offerwall.init(this, pubKey, "pincrux_test");
    }

    private void offerwallOption() {
        offerwall.setEnableTab(true);
        offerwall.setEnableScrollTopButton(true);
        offerwall.setAdDetail(true);
        offerwall.setDisableCPS(false);
        offerwall.setOfferwallUnity(false);
        offerwall.setDarkMode(PincruxOfferwall.AUTO);

        offerwall.setOfferwallType(type);
        offerwall.setOrientationLandscape(orientation);
    }

    private void offerwallView() {
        offerwallOption();
        View view = offerwall.getPincruxOfferwallView(this,
                new PincruxCloseImpl() {
                    @Override
                    public void onClose() {
                        finish();
                    }

                    @Override
                    public void onPermissionDenied() {
                        finish();
                    }

                    @Override
                    public void onAction() {
                        // 충전소 화면에서 사용자의 액션이 있을때 호출됩니다
                        // 세션 타임아웃을 방지하는 용도로 사용해주세요
                    }
                });
        container.addView(view);
    }

    private boolean isPaused = false;

    @Override
    protected void onResume() {
        super.onResume();
        if (isPaused) {
            offerwall.refreshOfferwall();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPaused = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        offerwall.destroyView();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.clear();
    }
}
