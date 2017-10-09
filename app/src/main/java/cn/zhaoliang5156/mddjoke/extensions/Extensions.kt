package cn.zhaoliang5156.mddjoke.extensions

import android.content.Context
import android.graphics.drawable.Animatable
import android.net.Uri
import android.util.DisplayMetrics
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.image.ImageInfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.facebook.drawee.backends.pipeline.Fresco
import android.R.id.primary
import cn.zhaoliang5156.mddjoke.R
import com.facebook.drawee.drawable.ProgressBarDrawable



/**
 * Created by zhaoliang on 2017/10/9.
 */
fun ViewGroup.inflat(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun Context.getScreenWidth(): Int {
    val service = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val outMetrics = DisplayMetrics()
    service.getDefaultDisplay().getMetrics(outMetrics)
    return outMetrics.widthPixels

}

fun SimpleDraweeView.setControllerListener(imagePath: String) {
    var controllerListener = object : BaseControllerListener<ImageInfo>() {
        override fun onFinalImageSet(id: String?, imageInfo: ImageInfo?, animatable: Animatable?) {
            super.onFinalImageSet(id, imageInfo, animatable)
            if (imageInfo == null) {
                return
            }
            val height = imageInfo.height
            val width = imageInfo.width
            val screenWidth = context.getScreenWidth()
            layoutParams.width = screenWidth
            layoutParams.height = screenWidth * height / width
            setLayoutParams(layoutParams)
        }

        override fun onIntermediateImageSet(id: String?, imageInfo: ImageInfo?) {
            super.onIntermediateImageSet(id, imageInfo)
        }

        override fun onIntermediateImageFailed(id: String?, throwable: Throwable?) {
            super.onIntermediateImageFailed(id, throwable)
        }
    }
    val draweeController = Fresco
            .newDraweeControllerBuilder()
            .setControllerListener(controllerListener)
            .setUri(Uri.parse(imagePath))
            .setAutoPlayAnimations(true)
            .build()
    val progressBarDrawable = ProgressBarDrawable()
    progressBarDrawable.color = resources.getColor(R.color.accent)
    progressBarDrawable.backgroundColor = resources.getColor(R.color.primary)
    progressBarDrawable.radius = resources.getDimensionPixelSize(R.dimen.drawee_hierarchy_progress_radius)
    hierarchy.setProgressBarImage(progressBarDrawable)
    controller = draweeController
}