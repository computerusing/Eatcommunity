package com.project.eatapp.LoginandMain

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import com.project.eatapp.*
import com.project.eatapp.Board.EditorListActivity
import com.project.eatapp.Board.InActivity
import com.project.eatapp.Board.MyReviewActivity
import com.project.eatapp.Chat.ChattingActivity
import com.viewpagerindicator.CirclePageIndicator
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class HomeActivity : AppCompatActivity() {
    /*
    lateinit var viewflipper : ViewFlipper

    val image = intArrayOf(R.drawable.firstimage, R.drawable.secondimage)
    */


    private var imageModelArrayList: ArrayList<ImageModel>? = null
    //사진 추가하는 배열
    private val myImageList = intArrayOf(
        R.drawable.firstimage,
        R.drawable.secondimage,
        R.drawable.thirdimage
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        imageModelArrayList = ArrayList()
        imageModelArrayList = populateList()

        init()
        /*
        viewflipper = findViewById(R.id.v_flipper)

        for(i in 0 until image.size) {
            flip_image(image[i])
        }*/

        //에디터리스트버튼리스너
        ec_btn.setOnClickListener{
            val intent1 = Intent(this, EditorListActivity::class.java)
            startActivity(intent1)
        }
        //리뷰버튼리스너
        my_btn.setOnClickListener {
            val intent2 = Intent(this, MyReviewActivity::class.java)
            startActivity(intent2)
        }
        //채팅방버튼리스너
        ot_btn.setOnClickListener {
            val intent3 = Intent(this, ChattingActivity::class.java)
            startActivity(intent3)
        }
        //지식인버튼리스너
        in_btn.setOnClickListener {
            val intent4 = Intent(this, InActivity::class.java)
            startActivity(intent4)
        }
    }

    private fun populateList(): ArrayList<ImageModel> {

        val list = ArrayList<ImageModel>()
        //출력할 사진 수 설정
        for (i in 0..2) {
            val imageModel = ImageModel()
            imageModel.setImage_drawables(myImageList[i])
            list.add(imageModel)
        }

        return list
    }
    /*
    fun flip_image(i : Int) {
        val view = ImageView(this)
        view.setBackgroundResource(i)
        viewflipper.addView(view)
        viewflipper.setFlipInterval(3000)
        viewflipper.setAutoStart(true)
        viewflipper.setInAnimation(this , android.R.anim.slide_in_left)
        viewflipper.setOutAnimation(this , android.R.anim.slide_out_right)
    }
    */
    private fun init() {

        mPager = findViewById(R.id.pager) as ViewPager
        mPager!!.adapter =
            SlidingImage_Adapter(this, this.imageModelArrayList!!)

        val indicator = findViewById(R.id.indicator) as CirclePageIndicator

        indicator.setViewPager(mPager)

        val density = resources.displayMetrics.density

        //Set circle indicator radius
        indicator.setRadius(5 * density)

        NUM_PAGES = imageModelArrayList!!.size

        // 뷰페이저 자동으로 시작
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            mPager!!.setCurrentItem(currentPage++, true)
        }
        //넘어가는 시간 설정
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 3000, 3000)

        //넘어가는 o 설정
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                currentPage = position

            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(pos: Int) {

            }
        })

    }

    companion object {

        private var mPager: ViewPager? = null
        private var currentPage = 0
        private var NUM_PAGES = 0
    }
}
