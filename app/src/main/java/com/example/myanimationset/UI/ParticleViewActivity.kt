package com.example.myanimationset.UI

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myanimationset.R
import kotlinx.android.synthetic.main.activity_particle_view.*

class ParticleViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_particle_view)

        initMusicAvatar()
    }

    private fun initMusicAvatar() {
        Glide.with(this)
                .load(R.drawable.floatingball_icon)
                .apply(RequestOptions.circleCropTransform())
                .into(iv_musicAvatar)
    }

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, ParticleViewActivity::class.java)
            context.startActivity(intent)
        }
    }
}
