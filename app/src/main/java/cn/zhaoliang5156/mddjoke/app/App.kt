package cn.zhaoliang5156.mddjoke.app

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by zhaoliang on 2017/10/9.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}