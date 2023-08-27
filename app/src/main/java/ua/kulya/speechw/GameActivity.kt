package ua.kulya.speechw

import android.animation.Animator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnLayout
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.kulya.speechw.databinding.ActivityGameBinding
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private var initialX = 0f
    private var offsetX = 0f

    private val handler = Handler(Looper.getMainLooper())
    private val collisionCheckInterval = 100 // Milliseconds
    private var animationDuration = 3000L

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val displayWidthInFloat = resources.displayMetrics.widthPixels.toFloat()


        val scope = MainScope()
        scope.launch {
            setAnimation()
        }

        binding.platform.setOnTouchListener { _, event ->
            when (event.action) {

                MotionEvent.ACTION_DOWN -> {
                    initialX = binding.platform.x
                    offsetX = event.rawX - initialX
                }

                MotionEvent.ACTION_MOVE -> {
                    val newX = event.rawX - offsetX
                    binding.platform.x = newX.coerceIn(0f, displayWidthInFloat - binding.platform.width)
                }
            }
            true
        }

        handler.postDelayed(object : Runnable {
            override fun run() {
                checkCollision(binding.elem1)
                checkCollision(binding.elem2)
                checkCollision(binding.elem3)
                checkCollision(binding.elem4)
                checkCollision(binding.elem5)
                checkCollision(binding.elem6)
                checkCollision(binding.elem7)
                checkCollision(binding.elem8)
                checkCollision(binding.elem9)
                checkCollision(binding.elem10)
                checkCollision(binding.elem11)
                checkCollision(binding.elem12)
                handler.postDelayed(this, collisionCheckInterval.toLong())
            }
        }, collisionCheckInterval.toLong())

    }

    suspend fun setAnimation(){
        animateFalling(binding.elem1)
        animateFalling(binding.elem2)
        animateFalling(binding.elem3)
        animateFalling(binding.elem4)
        delay(1000)
        animateFalling(binding.elem5)
        animateFalling(binding.elem6)
        animateFalling(binding.elem7)
        animateFalling(binding.elem8)
        delay(1000)
        animateFalling(binding.elem9)
        animateFalling(binding.elem10)
        animateFalling(binding.elem11)
        animateFalling(binding.elem12)
    }

    private fun checkCollision(imageView: ImageView) {
        val elemRect = Rect()
        imageView.getGlobalVisibleRect(elemRect) // Update the Rect position based on the view's bounds

        val platformRect = Rect()
        binding.platform.getGlobalVisibleRect(platformRect) // Update the Rect position based on the view's bounds

        if (Rect.intersects(elemRect, platformRect)) {
            // Collision detected
            // Stop the falling animation
            
            Log.d("123123", "Collision has been occurred")
            imageView.visibility = View.GONE
            //imageView.clearAnimation()
        }
    }
    private fun animateFalling(view: ImageView) {
        val screenHeight = resources.displayMetrics.heightPixels.toFloat()

        val animator = view.animate()
            .translationY(screenHeight+256f)
            .setDuration(animationDuration)
            .setListener(object : Animator.AnimatorListener {

                override fun onAnimationStart(animation: Animator) {

                }

                override fun onAnimationEnd(animation: Animator) {
                    view.translationY = 0f
                    view.setImageResource(getRandomImage())
                    view.visibility = View.VISIBLE
                    animateFalling(view) // Restart the animation
                }

                override fun onAnimationCancel(animation: Animator) {

                }

                override fun onAnimationRepeat(animation: Animator) {

                }
            })

        animator.start()
    }

    private fun getRandomImage(): Int{
        val randomIntForListOfImages = Random.nextInt(0, listOfImages.size)
        return listOfImages[randomIntForListOfImages]
    }

    companion object{
        val listOfImages = listOf(
            R.drawable.stone1,
            R.drawable.stone2,
            R.drawable.stone3,
            R.drawable.tree1,
            R.drawable.tree2,
            R.drawable.tree3,
            R.drawable.monster4,
            R.drawable.monster5,
            R.drawable.monster6,
            R.drawable.monster7,
            R.drawable.monster8,
            R.drawable.monster9,
            R.drawable.monster10
        )
    }


}