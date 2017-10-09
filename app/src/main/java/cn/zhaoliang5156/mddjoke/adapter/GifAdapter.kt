package cn.zhaoliang5156.mddjoke.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import cn.zhaoliang5156.mddjoke.R
import cn.zhaoliang5156.mddjoke.api.GifRespnoseItem
import cn.zhaoliang5156.mddjoke.extensions.inflat
import cn.zhaoliang5156.mddjoke.extensions.setControllerListener
import kotlinx.android.synthetic.main.gif_item.view.*

/**
 * Gif适配器
 * Created by zhaoliang on 2017/10/9.
 */
class GifAdapter(var data: ArrayList<GifRespnoseItem>) : RecyclerView.Adapter<GifAdapter.GifViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        return GifViewHolder(parent.inflat(R.layout.gif_item))
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class GifViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: GifRespnoseItem) {
            itemView.tv_title.text = item.title
            itemView.iv_gif.setControllerListener(item.img)
            itemView.tv_create_time.text = item.ct
        }
    }
}