package cn.zhaoliang5156.mddjoke.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import cn.zhaoliang5156.mddjoke.R
import cn.zhaoliang5156.mddjoke.api.ImageRespnoseItem
import cn.zhaoliang5156.mddjoke.extensions.inflat
import cn.zhaoliang5156.mddjoke.extensions.setControllerListener
import kotlinx.android.synthetic.main.image_item.view.*

/**
 * 图片适配器
 * Created by zhaoliang on 2017/10/9.
 */
class ImageAdapter(var data: ArrayList<ImageRespnoseItem>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ImageViewHolder {
        return ImageViewHolder(parent?.inflat(R.layout.image_item))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ImageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ImageRespnoseItem) {
            itemView.tv_title.text = item.title
            itemView.iv_image.setControllerListener(item.img)
            itemView.tv_create_time.text = item.ct
        }
    }
}