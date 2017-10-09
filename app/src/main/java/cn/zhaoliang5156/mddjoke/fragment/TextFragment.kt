package cn.zhaoliang5156.mddjoke.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cn.zhaoliang5156.mddjoke.R
import cn.zhaoliang5156.mddjoke.adapter.TextAdapter
import cn.zhaoliang5156.mddjoke.api.JokeApi
import cn.zhaoliang5156.mddjoke.api.JokeService
import cn.zhaoliang5156.mddjoke.api.TextRespnose
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_text.*
import org.w3c.dom.Text

/**
 * A simple [Fragment] subclass.
 */
class TextFragment : Fragment() {

    private val TAG = TextFragment::class.java.simpleName
    private var page = 1
    private var maxResult = 50
    private val api: JokeApi
        get() {
            return JokeService().InstanceJokeApi()
        }
    private lateinit var textAdapter: TextAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_text, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()

        api.getText(page, maxResult)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { t: TextRespnose ->
                            Log.i(TAG, "${t.showapi_res_body.contentlist.size}")
                            textAdapter = TextAdapter(t.showapi_res_body.contentlist)
                            text_joke_list.adapter = textAdapter
                        },
                        { t: Throwable? ->
                        }
                )
    }

    private fun init() {
        text_joke_list.layoutManager = LinearLayoutManager(context)
        refresh_layout.setOnRefreshListener { refreshlayout ->
            if (page > 1) {
                page--

                api.getText(page, maxResult)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { t: TextRespnose ->
                                    Log.i(TAG, "${t.showapi_res_body.contentlist.size}")
                                    textAdapter.data.addAll(0, t.showapi_res_body.contentlist)
                                    textAdapter.notifyDataSetChanged()
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
        refresh_layout.setOnLoadmoreListener { refreshlayout ->
            page++
            api.getText(page, maxResult)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { t: TextRespnose ->
                                Log.i(TAG, "${t.showapi_res_body.contentlist.size}")
                                textAdapter.data.addAll(t.showapi_res_body.contentlist)
                                textAdapter.notifyDataSetChanged()
                                refreshlayout.finishLoadmore()
                            },
                            { t: Throwable? ->
                                refreshlayout.finishLoadmore()
                            }
                    )
        }
    }

}// Required empty public constructor
