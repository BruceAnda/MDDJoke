package cn.zhaoliang5156.mddjoke.adapter

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.View
import android.view.ViewGroup
import cn.zhaoliang5156.mddjoke.R
import cn.zhaoliang5156.mddjoke.api.TextResponseItem
import cn.zhaoliang5156.mddjoke.extensions.inflat
import kotlinx.android.synthetic.main.text_item.view.*

/**
 * 段子数据适配器
 * Created by zhaoliang on 2017/10/9.
 */
class TextAdapter(var data: ArrayList<TextResponseItem>) : RecyclerView.Adapter<TextAdapter.TextViewHolder>() {
    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TextViewHolder {
        return TextViewHolder(parent?.inflat(R.layout.text_item))
    }

    class TextViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: TextResponseItem) {
            itemView.tv_title.text = item.title
            itemView.tv_text.text = Html.fromHtml(item.text)
            itemView.tv_create_time.text = item.ct
        }
    }
}