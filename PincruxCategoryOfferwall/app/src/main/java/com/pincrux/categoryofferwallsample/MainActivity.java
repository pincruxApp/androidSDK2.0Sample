package com.pincrux.categoryofferwallsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pincrux.offerwall.PincruxOfferwall;
import com.pincrux.offerwall.util.point.impl.PincruxAdPointImpl;
import com.pincrux.offerwall.util.point.model.PincruxAdPointInfo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    PincruxOfferwall offerwall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(this);

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(this);

        String YOUR_PUBKEY = "911896";

        offerwall = PincruxOfferwall.getInstance();
        offerwall.init(this, YOUR_PUBKEY, "pincrux_test");
        offerwall.setOfferwallType(PincruxOfferwall.BAR_PREMIUM_TYPE);
        offerwall.setDarkMode(PincruxOfferwall.AUTO);
        offerwall.setAdDetail(true);
        offerwall.setOrientationLandscape(false);
        offerwall.setEnableTab(true);
        offerwall.setEnableScrollTopButton(true);

        offerwall.getAdPoint(this, YOUR_PUBKEY, new PincruxAdPointImpl() {
            @Override
            public void onReceivePoint(PincruxAdPointInfo pincruxAdPointInfo) {
                if (pincruxAdPointInfo != null) {
                    String buttonString = getString(R.string.button1) + "\n" + pincruxAdPointInfo.getFinancePoint() + "P";
                    button1.setText(buttonString);

                    buttonString = getString(R.string.button2) + "\n" + pincruxAdPointInfo.getCpaPoint() + "P";
                    button2.setText(buttonString);

                    buttonString = getString(R.string.button3) + "\n" + pincruxAdPointInfo.getSocialPoint() + "P";
                    button3.setText(buttonString);

                    buttonString = getString(R.string.button4) + "\n" + pincruxAdPointInfo.getCpsPoint() + "P";
                    button4.setText(buttonString);

                    buttonString = getString(R.string.button5) + "\n" + pincruxAdPointInfo.getGamePoint() + "P";
                    button5.setText(buttonString);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1: {    // 금융적립
                offerwall.setOfferwallCategory(PincruxOfferwall.CATEGORY_FINANCE);
                offerwall.startPincruxOfferwallActivity(MainActivity.this);
                break;
            }

            case R.id.button2: {    // 참여적립
                offerwall.setOfferwallCategory(PincruxOfferwall.CATEGORY_CPA);
                offerwall.startPincruxOfferwallActivity(MainActivity.this);
                break;
            }

            case R.id.button3: {    // 소셜적립
                offerwall.setOfferwallCategory(PincruxOfferwall.CATEGORY_SOCIAL);
                offerwall.startPincruxOfferwallActivity(MainActivity.this);
                break;
            }

            case R.id.button4: {    // 구매적립
                offerwall.setOfferwallCategory(PincruxOfferwall.CATEGORY_CPS);
                offerwall.startPincruxOfferwallActivity(MainActivity.this);
                break;
            }

            case R.id.button5: {    // 게임적립
                offerwall.setOfferwallCategory(PincruxOfferwall.CATEGORY_GAME);
                offerwall.startPincruxOfferwallActivity(MainActivity.this);
                break;
            }
        }
    }
}