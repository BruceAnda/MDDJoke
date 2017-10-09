package cn.zhaoliang5156.mddjoke

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import cn.zhaoliang5156.mddjoke.adapter.MainPagerAdapter
import cn.zhaoliang5156.mddjoke.fragment.GifFragment
import cn.zhaoliang5156.mddjoke.fragment.ImageFragment
import cn.zhaoliang5156.mddjoke.fragment.TextFragment

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

/**
 * 主界面
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // 标题
        val titles = ArrayList<String>()
        titles.add(getString(R.string.text))
        titles.add(getString(R.string.image))
        titles.add(getString(R.string.gif))
        // 页面
        val datas = ArrayList<Fragment>()
        datas.add(TextFragment())
        datas.add(ImageFragment())
        datas.add(GifFragment())
        pagers.adapter = MainPagerAdapter(titles, datas, supportFragmentManager)
        pagers.offscreenPageLimit = 3
        // TabLayout和ViewPager关联起来
        tabs.setupWithViewPager(pagers)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_about -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
