package com.tbl.problemsolvers.coinFly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.coinanimation.Coin
import com.example.coinanimation.ktx.randomArcAnimation
import com.example.coinanimation.ktx.springAnimate
import com.example.coinanimation.ktx.toPx
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tbl.problemsolvers.R

class CoinFlyingActivity : AppCompatActivity(), Runnable {

    @BindView(R.id.parentView)
    lateinit var  parentView : FrameLayout

    @BindView(R.id.addCoinButton)
  lateinit   var  addCoinButton : FloatingActionButton

    @BindView(R.id.counterTxt)
    lateinit var  counterTxt : TextView

 /*   private val edgeHeight by lazy { (parentView.height - 2 * toolbar.height - 24.toPx) / 2f }
    private val edgeWidth by lazy { (parentView.width + 24.toPx) / 2f }
    private val handler = Handler(Looper.getMainLooper())
*/

    private val edgeHeight by lazy { (parentView.height - 20) / 2f }
    private val edgeWidth by lazy { (parentView.width + 24.toPx) / 2f }
    private val handler = Handler(Looper.getMainLooper())

    private var coinMaxCount = 0
    private var coinCount = 0
    var count = 1;
    var cReceive = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_flying)
        ButterKnife.bind(this)


        addCoinButton.setOnClickListener {
            it?.springAnimate(stiffness = 500f)
            coinMaxCount = cReceive
            coinCount = 0
            handler.post(this)

        }
    }


   /* private fun incrementCounter() {
        recurtiontext(cReceive)

        *//*  for (i in 1 until cReceive + 1) {
              count += 1

              Handler(Looper.getMainLooper()).postDelayed(Runnable {
                  counterTxt.text = "Counter :" + count
              }, 1000)

          }*//*

    }

    private fun recurtiontext(num: Int) {
        if (num == 0) {
            return
        } else {

            counterTxt.text = "Counter :" + count
            count += 1
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                recurtiontext(num - 1)
            }, 100)
        }
    }*/

    override fun run() {
        if (coinCount < coinMaxCount) {
            val coin = Coin.addTo(parentView)
            coin.randomArcAnimation(0f, -edgeWidth, 0f, -edgeHeight) {
                parentView.removeView(coin)
            }
            handler.postDelayed(this, 50)
            coinCount++
        }
    }

}


