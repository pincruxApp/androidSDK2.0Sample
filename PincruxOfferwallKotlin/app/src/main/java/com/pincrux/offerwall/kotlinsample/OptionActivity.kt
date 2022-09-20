package com.pincrux.offerwall.kotlinsample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.pincrux.offerwall.PincruxOfferwall

class OptionActivity : AppCompatActivity() {
    private lateinit var offerwall: PincruxOfferwall
    private lateinit var barActivity: AppCompatButton
    private lateinit var barActivityLandscape: AppCompatButton
    private lateinit var barView: AppCompatButton
    private lateinit var barViewLandscape: AppCompatButton
    private lateinit var barPremiumActivity: AppCompatButton
    private lateinit var barPremiumView: AppCompatButton
    private lateinit var premiumActivity: AppCompatButton
    private lateinit var premiumView: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option)

        findId()
        clickAction()
        offerwallInit()
    }

    private fun offerwallInit() {
        val pubKey = Preference(this).pubkey
        offerwall = PincruxOfferwall.getInstance()
        offerwall.init(this, pubKey, "pincrux_test")
    }

    private fun offerwallOption() {
        offerwall.setEnableTab(true)
        offerwall.setEnableScrollTopButton(true)
        offerwall.setAdDetail(true)
        offerwall.setDisableTermsPopup(false)
        offerwall.setDisableCPS(false)
        offerwall.setOrientationLandscape(false)
        offerwall.setOfferwallUnity(false)
        offerwall.setDarkMode(PincruxOfferwall.LIGHT)
    }

    private fun findId() {
        barActivity = findViewById(R.id.bar_activity)
        barActivityLandscape = findViewById(R.id.bar_activity_landscape)
        barView = findViewById(R.id.bar_view)
        barViewLandscape = findViewById(R.id.bar_view_andscape)

        barPremiumActivity = findViewById(R.id.bar_premium_activity)
        barPremiumView = findViewById(R.id.bar_premium_view)

        premiumActivity = findViewById(R.id.premium_activity)
        premiumView = findViewById(R.id.premium_view)
    }

    private fun clickAction() {
        barActivity.setOnClickListener {
            offerwallOption()
            offerwall.setOfferwallType(PincruxOfferwall.BAR_TYPE)
            offerwall.startPincruxOfferwallActivity(this)
        }

        barActivityLandscape.setOnClickListener {
            offerwallOption()
            offerwall.setOfferwallType(PincruxOfferwall.BAR_TYPE)
            offerwall.setOrientationLandscape(true)
            offerwall.startPincruxOfferwallActivity(this)
        }

        barPremiumActivity.setOnClickListener {
            offerwallOption()
            offerwall.setOfferwallType(PincruxOfferwall.BAR_PREMIUM_TYPE)
            offerwall.startPincruxOfferwallActivity(this)
        }

        premiumActivity.setOnClickListener {
            offerwallOption()
            offerwall.setOfferwallType(PincruxOfferwall.PREMIUM_TYPE)
            offerwall.startPincruxOfferwallActivity(this)
        }

        barView.setOnClickListener {
            val intent = Intent(this@OptionActivity, OptionViewActivity::class.java)
            intent.putExtra("type", PincruxOfferwall.BAR_TYPE)
            startActivity(intent)
        }

        barViewLandscape.setOnClickListener {
            val intent = Intent(this@OptionActivity, OptionViewActivity::class.java)
            intent.putExtra("type", PincruxOfferwall.BAR_TYPE)
            intent.putExtra("orientation", true)
            startActivity(intent)
        }

        barPremiumView.setOnClickListener {
            val intent = Intent(this@OptionActivity, OptionViewActivity::class.java)
            intent.putExtra("type", PincruxOfferwall.BAR_PREMIUM_TYPE)
            startActivity(intent)
        }

        premiumView.setOnClickListener {
            val intent = Intent(this@OptionActivity, OptionViewActivity::class.java)
            intent.putExtra("type", PincruxOfferwall.PREMIUM_TYPE)
            startActivity(intent)
        }
    }
}