package cn.zhaoliang5156.mddjoke.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cn.zhaoliang5156.mddjoke.R
import cn.zhaoliang5156.mddjoke.adapter.GifAdapter
import cn.zhaoliang5156.mddjoke.api.GifResponse
import cn.zhaoliang5156.mddjoke.api.JokeApi
import cn.zhaoliang5156.mddjoke.api.JokeService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_gif.*

/**
 * A simple [Fragment] subclass.
 */
class GifFragment : Fragment() {

    private val TAG = GifFragment::class.java.simpleName
    private var page = 1
    private var maxResult = 50
    private val api: JokeApi
        get() {
            return JokeService().InstanceJokeApi()
        }
    private lateinit var gifAdapter: GifAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_gif, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()

        api.getGif(page, maxResult)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { t: GifResponse ->
                            Log.i(TAG, "${t?.showapi_res_body?.contentlist?.size}")
                            gifAdapter = GifAdapter(t.showapi_res_body.contentlist)
                            gif_joke_list.adapter = gifAdapter
                        },
                        { t: Throwable? -> }
                )
    }

    private fun init() {
        gif_joke_list.layoutManager = LinearLayoutManager(context)
        git_refresh_layout.setOnRefreshListener { refreshlayout ->
            if (page > 1) {
                page--
                api.getGif(page, maxResult)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { t: GifResponse ->
                                    Log.i(TAG, "${t?.showapi_res_body?.contentlist?.size}")
                                    gifAdapter.data.addAll(0, t.showapi_res_body.contentlist)
                                    gifAdapter.notifyDataSetChanged()
                                    refreshlayout.finishRefresh()
                                },
                                { t: Throwable? ->
                                    refreshlayout.finishRefresh()
                                }
                        )
            } else {
                refreshlayout.finishRefresh(2000)
            }

        }
        git_refresh_layout.setOnLoadmoreListener { refreshlayout ->
            page++
            api.getGif(page, maxResult)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { t: GifResponse ->
                                Log.i(TAG, "${t?.showapi_res_body?.contentlist?.size}")
                                gifAdapter.data.addAll(t.showapi_res_body.contentlist)
                                gifAdapter.notifyDataSetChanged()
                                refreshlayout.finishLoadmore()
                            },
                            { t: Throwable? ->
                                refreshlayout.finishLoadmore()
                            }
                    )
        }
    }

}// Required empty public constructor
