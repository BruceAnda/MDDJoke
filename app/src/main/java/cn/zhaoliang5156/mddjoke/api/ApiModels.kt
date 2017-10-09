package cn.zhaoliang5156.mddjoke.api

/**
 * Created by zhaoliang on 2017/10/9.
 */
class TextRespnose(
        var showapi_res_code: Int,
        var showapi_res_error: String,
        var showapi_res_body: TextResponseBody
)

class ImageRespnose(
        var showapi_res_code: Int,
        var showapi_res_error: String,
        var showapi_res_body: ImageResponseBody
)

class GifResponse(
        var showapi_res_code: Int,
        var showapi_res_error: String,
        var showapi_res_body: GifResponseBody
)

class TextResponseBody(
        var allPages: Int,
        var ret_code: Int,
        var contentlist: ArrayList<TextResponseItem>,
        var currentPage: Int,
        var allNum: Int,
        var maxResult: Int
)

class ImageResponseBody(
        var allPages: Int,
        var ret_code: Int,
        var contentlist: ArrayList<ImageRespnoseItem>,
        var currentPage: Int,
        var allNum: Int,
        var maxResult: Int
)

class GifResponseBody(
        var allPages: Int,
        var ret_code: Int,
        var contentlist: ArrayList<GifRespnoseItem>,
        var currentPage: Int,
        var allNum: Int,
        var maxResult: Int
)

class TextResponseItem(
        var text: String,
        var title: String,
        var type: Int,
        var ct: String
)

class ImageRespnoseItem(
        var img: String,
        var title: String,
        var type: Int,
        var ct: String
)

class GifRespnoseItem(
        var img: String,
        var title: String,
        var type: Int,
        var ct: String
)