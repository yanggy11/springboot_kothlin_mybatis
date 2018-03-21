package com.yanggy.kothlin.model

import com.yanggy.kothlin.common.Constants
import java.io.Serializable
import java.util.*

/**
 * Created by yangguiyun on 2018/3/14.
 */

open class Users :  Serializable{
    var id: Long? = null
    var name: String = Constants.EMPTY_STRING

    var age : Int = 0

    var createDate : Date? = null

    var updateDate : Date? = null
    /**
     * 0 未删除
     * 1 已删除
     */
    var deleteFlag : String = Constants.UNDELETED_FLAG
}