package br.com.customapp.views.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AlphaAnimation
import br.com.customapp.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val intent: Intent
            if (true) { // User is logged
                intent = Intent(this, MainActivity::class.java)
            } else { // User request login
                intent = Intent(this, LoginActivity::class.java)
            }

            startActivity(intent)
            finish()
        }, 1500)
    }

//    private fun changeToFinalImage() {
//
//        var fadeOut = AlphaAnimation(1f, 0f)
//        fadeOut.interpolator = android.view.animation.LinearInterpolator()
//        fadeOut.duration = 800
//
//        var fadeIn = AlphaAnimation(0f, 1f)
//        fadeIn.interpolator = android.view.animation.LinearInterpolator()
//        fadeIn.duration = 800
//
//        logo_2.startAnimation(fadeOut)
//
//        logo_3.startAnimation(fadeIn)
//        logo_2.visibility = View.GONE
//        logo_3.visibility = View.VISIBLE
//
//        Handler().postDelayed({
//            val intent: Intent
//            if (SDK4all.userIsLogged()) {
//                intent = Intent(this, HomeActivity::class.java)
//            } else {
//                val appPersistence = AppPersistence()
//                var appHistory: AppHistory = appPersistence.read()
//
//                intent = Intent(this, OnboardingActivity::class.java)
//                intent.putExtra(EXTRA_SHOW_ONBOARDING, !appHistory.alreadyPassedByOnboarding)
//            }
//
//            startActivity(intent)
//            finish()
//        }, 1500)
//    }
}
