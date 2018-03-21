package com.yanggy.kothlin.param

import com.yanggy.kothlin.common.Page
import com.yanggy.kothlin.model.Users
import java.io.Serializable

/**
 * Created by yangguiyun on 2018/3/21.
 */
class UserParam : Serializable {
    var id : String = ""
    var name : String = ""
    var page : Page<Any> = Page<Any>()
}