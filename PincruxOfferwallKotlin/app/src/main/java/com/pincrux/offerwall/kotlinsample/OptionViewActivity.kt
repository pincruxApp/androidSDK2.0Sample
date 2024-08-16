package com.pincrux.offerwall.kotlinsample

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import com.pincrux.offerwall.PincruxOfferwall
import com.pincrux.offerwall.ui.common.impl.PincruxCloseImpl

class OptionViewActivity : AppCompatActivity() {

    private lateinit var offerwall: PincruxOfferwall

    private lateinit var container: LinearLayoutCompat

    private var type = 0
    private var orientation = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option_view)
        init()
        findId()
        offerwallInit()
        offerwallView()
    }

    private fun init() {
        if (intent.hasExtra("type")) {
            type = intent.getIntExtra("type", type)
            if (intent.hasExtra("orientation")) {
                orientation = intent.getBooleanExtra("orientation", orientation)
            }
        } else {
            finish()
        }

        requestedOrientation = if (orientation) {
            ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        } else {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    private fun findId() {
        container = findViewById(R.id.container)
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
        offerwall.setDisableCPS(false)
        offerwall.setOfferwallUnity(false)
        offerwall.setDarkMode(PincruxOfferwall.AUTO)
        offerwall.setOfferwallType(type)
        offerwall.setOrientationLandscape(orientation)
    }

    private fun offerwallView() {
        offerwallOption()
        val offerwall = PincruxOfferwall.getInstance()
        val view = offerwall.getPincruxOfferwallView(this, object : PincruxCloseImpl {
            override fun onClose() {
                finish()
            }

            override fun onPermissionDenied() {
                finish()
            }

            override fun onAction() {
                // 충전소 화면에서 사용자의 액션이 있을때 호출됩니다
                // 세션 타임아웃을 방지하는 용도로 사용해주세요
            }
        })

        container.addView(view)
    }

    private var isPaused: Boolean = false

    override fun onResume() {
        super.onResume()
        if (isPaused) {
            offerwall.refreshOfferwall()
        }
    }

    override fun onPause() {
        super.onPause()
        isPaused = true
    }

    override fun onDestroy() {
        super.onDestroy()
        offerwall.destroyView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.clear()
    }
}