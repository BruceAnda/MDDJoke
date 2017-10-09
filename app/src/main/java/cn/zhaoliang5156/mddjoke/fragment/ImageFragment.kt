package cn.zhaoliang5156.mddjoke.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cn.zhaoliang5156.mddjoke.R
import cn.zhaoliang5156.mddjoke.adapter.ImageAdapter
import cn.zhaoliang5156.mddjoke.api.ImageRespnose
import cn.zhaoliang5156.mddjoke.api.JokeApi
import cn.zhaoliang5156.mddjoke.api.JokeService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_image.*


/**
 * A simple [Fragment] subclass.
 */
class ImageFragment : Fragment() {

    private val TAG = ImageFragment::class.java.simpleName
    private var page = 1
    private var maxResult = 50
    private val api: JokeApi
        get() {
            return JokeService().InstanceJokeApi()
        }
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_image, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()

        api.getImage(page, maxResult)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { t: ImageRespnose ->
                            Log.i(TAG, "${t?.showapi_res_body?.contentlist?.size}")
                            imageAdapter = ImageAdapter(t.showapi_res_body.contentlist)
                            image_joke_list.adapter = imageAdapter
                        },
                        { t: Throwable? ->
                        }
                )
    }

    private fun init() {
        image_joke_list.layoutManager = LinearLayoutManager(context)
        image_refresh_layout.setOnRefreshListener { refreshlayout ->
            if (page > 1) {
                page--
                api.getImage(page, maxResult)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { t: ImageRespnose ->
                                    Log.i(TAG, "${t?.showapi_res_body?.contentlist?.size}")
                                    imageAdapter.data.addAll(0, t.showapi_res_body.contentlist)
                                    imageAdapter.notifyDataSetChanged()
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
        image_refresh_layout.setOnLoadmoreListener { refreshlayout ->
            page++
            api.getImage(page, maxResult)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { t: ImageRespnose ->
                                Log.i(TAG, "${t?.showapi_res_body?.contentlist?.size}")
                                imageAdapter.data.addAll(t.showapi_res_body.contentlist)
                                imageAdapter.notifyDataSetChanged()
                                refreshlayout.finishLoadmore()
                            },
                            { t: Throwable? ->
                                refreshlayout.finishLoadmore()
                            }
                    )
        }
    }

}// Required empty public constructor
