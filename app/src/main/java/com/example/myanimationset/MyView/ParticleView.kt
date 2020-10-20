package com.example.myanimationset.MyView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * description ：类的作用
 * author : LongSh1z
 * email : 2674461089@qq.com
 * date : 2020/10/20 10:34
 */
class ParticleView(context: Context?, attributeSet: AttributeSet?) : View(context, attributeSet) {

    //定义一个粒子的集合
    private var particleList = mutableListOf<Particle>()

    //定义画笔
    var paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.WHITE
        paint.isAntiAlias = true
        var particle = Particle(0f, 0f, 2f, 2f, 100)
        canvas?.drawCircle(particle.x, particle.y, particle.radius, paint)
    }

    class Particle(
            var x: Float,// X坐标
            var y: Float,// Y坐标
            var radius: Float,//半径
            var speed: Float,//速度
            var alpha: Int//透明度
    )
}