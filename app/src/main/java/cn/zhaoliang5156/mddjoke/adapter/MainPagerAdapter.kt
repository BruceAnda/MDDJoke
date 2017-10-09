package cn.zhaoliang5156.mddjoke.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * 主界面适配器
 * Created by zhaoliang on 2017/10/9.
 */
class MainPagerAdapter(var titles: ArrayList<String>, var datas: ArrayList<Fragment>, fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return datas.size
    }

    override fun getItem(position: Int): Fragment {
        return datas[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}