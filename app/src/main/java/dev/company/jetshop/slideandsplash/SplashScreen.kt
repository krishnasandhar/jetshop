package dev.company.jetshop.slideandsplash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import dev.company.jetshop.R

class SplashScreen: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val gymmotto = findViewById <ImageView> (R.id.gymmoto)
        gymmotto?.alpha = 0f
        gymmotto.animate().setDuration(5000).alpha(1f).withEndAction {
            val intent = Intent(this, SlideScreen::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}