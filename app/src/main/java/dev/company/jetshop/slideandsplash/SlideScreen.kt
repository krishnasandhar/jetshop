package dev.company.jetshop.slideandsplash

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import dev.company.jetshop.loginregister.LoginActivity
import dev.company.jetshop.R

class SlideScreen: AppCompatActivity() {

    private lateinit var myAdapter: SlideScreenAdapter
    private lateinit var dotsTv:Array<TextView?>
    private lateinit var layouts:IntArray

    var btn_next:Button?=null
    var btn_skip:Button?=null
    var viewPager:ViewPager?=null
    var dotsLayout:LinearLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!isFirstTimeAppStart()) {
            setAppStartStatus(false)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        setContentView(R.layout.activity_slide_screen)

        viewPager  = findViewById (R.id.viewPager)
        dotsLayout = findViewById (R.id.dotsLayout)
        btn_skip = findViewById (R.id.btn_skip)
        btn_next = findViewById (R.id.btn_next)

        statusbartransparent()

        btn_next?.setOnClickListener {

            val currentpage: Int = viewPager!!.currentItem + 1

            if (currentpage < layouts.size) {
                viewPager?.currentItem = currentpage
            } else {
                setAppStartStatus(false)
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        btn_skip?.setOnClickListener {
            setAppStartStatus(false)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        layouts = intArrayOf(R.layout.slide1, R.layout.slide2, R.layout.slide3, R.layout.slide4)
        myAdapter = SlideScreenAdapter(layouts, applicationContext)
        viewPager?.adapter = myAdapter
        viewPager?.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{

            override fun onPageScrollStateChanged(state: Int) {
            }
            override fun onPageScrolled(position: Int,positionOffset: Float,positionOffsetPixels: Int) {
            }
            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                if (position == layouts.size - 1) {
                    btn_next?.text="START"
                    btn_skip?.visibility = View.GONE
                } else {
                    btn_next?.text="NEXT"
                    btn_skip?.visibility = View.VISIBLE
                }
                setDots(position)
            }
        })
        setDots(0)
    }
    private fun isFirstTimeAppStart() : Boolean {
        val pref = applicationContext.getSharedPreferences("JetShop", Context.MODE_PRIVATE)
        return pref.getBoolean("APP_START", true)
    }
    private fun setAppStartStatus(status: Boolean) {
        val pref = applicationContext.getSharedPreferences("JetShop", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putBoolean("APP_START", status)
        editor.apply()
    }
    private fun statusbartransparent() {
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }
    private fun setDots(page: Int) {
        dotsLayout?.removeAllViews()
        dotsTv = arrayOfNulls(layouts.size)

        for (i in dotsTv.indices) {
            dotsTv[i] = TextView(this)
            dotsTv[i]!!.text = Html.fromHtml("&#8226;")
            dotsTv[i]!!.textSize = 30f
            dotsTv[i]!!.setTextColor(Color.parseColor("#a9b4bb"))
            dotsLayout?.addView(dotsTv[i])
        }
        if (dotsTv.isNotEmpty()) {
            dotsTv[page]!!.setTextColor(Color.parseColor("#ffffff"))
        }
    }
}