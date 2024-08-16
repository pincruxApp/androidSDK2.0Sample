package com.pincrux.offerwall.javasample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.pincrux.offerwall.PincruxOfferwall;

public class OptionActivity extends AppCompatActivity {

    private PincruxOfferwall offerwall;
    private AppCompatButton barActivity, barActivityLandscape, barView, barViewLandscape;
    private AppCompatButton barPremiumActivity, barPremiumView;
    private AppCompatButton premiumActivity, premiumView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        findId();
        clickAction();
        offerwallInit();
    }

    private void offerwallInit(){
        String pubKey = Preference.getInstance().getPubKey(this);
        offerwall = PincruxOfferwall.getInstance();
        offerwall.init(this, pubKey, "pincrux_test");
    }

    private void offerwallOption() {
        offerwall.setEnableTab(true);
        offerwall.setEnableScrollTopButton(true);
        offerwall.setAdDetail(true);
        offerwall.setDisableCPS(false);
        offerwall.setOrientationLandscape(false);
        offerwall.setOfferwallUnity(false);
        offerwall.setDarkMode(PincruxOfferwall.LIGHT);
    }

    private void findId() {
        barActivity = findViewById(R.id.bar_activity);
        barActivityLandscape = findViewById(R.id.bar_activity_landscape);
        barView = findViewById(R.id.bar_view);
        barViewLandscape = findViewById(R.id.bar_view_andscape);

        barPremiumActivity = findViewById(R.id.bar_premium_activity);
        barPremiumView = findViewById(R.id.bar_premium_view);

        premiumActivity = findViewById(R.id.premium_activity);
        premiumView = findViewById(R.id.premium_view);
    }

    private void clickAction() {
        barActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offerwallOption();
                offerwall.setOfferwallType(PincruxOfferwall.BAR_TYPE);
                offerwall.startPincruxOfferwallActivity(OptionActivity.this);
            }
        });

        barActivityLandscape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offerwallOption();
                offerwall.setOfferwallType(PincruxOfferwall.BAR_TYPE);
                offerwall.setOrientationLandscape(true);
                offerwall.startPincruxOfferwallActivity(OptionActivity.this);
            }
        });

        barPremiumActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offerwallOption();
                offerwall.setOfferwallType(PincruxOfferwall.BAR_PREMIUM_TYPE);
                offerwall.startPincruxOfferwallActivity(OptionActivity.this);
            }
        });


        premiumActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offerwallOption();
                offerwall.setOfferwallType(PincruxOfferwall.PREMIUM_TYPE);
                offerwall.startPincruxOfferwallActivity(OptionActivity.this);
            }
        });

        barView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionActivity.this, OptionViewActivity.class);
                intent.putExtra("type", PincruxOfferwall.BAR_TYPE);
                startActivity(intent);
            }
        });

        barViewLandscape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionActivity.this, OptionViewActivity.class);
                intent.putExtra("type", PincruxOfferwall.BAR_TYPE);
                intent.putExtra("orientation", true);
                startActivity(intent);
            }
        });

        barPremiumView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionActivity.this, OptionViewActivity.class);
                intent.putExtra("type", PincruxOfferwall.BAR_PREMIUM_TYPE);
                startActivity(intent);
            }
        });

        premiumView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionActivity.this, OptionViewActivity.class);
                intent.putExtra("type", PincruxOfferwall.PREMIUM_TYPE);
                startActivity(intent);
            }
        });

    }
}