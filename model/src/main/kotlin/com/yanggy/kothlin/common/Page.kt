package com.yanggy.kothlin.common

import java.io.Serializable

/**
 * Created by yangguiyun on 2018/3/20.
 */

class Page<T>(var pageNo : Int = 1, var pageSize : Int = 20) : Serializable {
    var data : T? = null
    var offset : Int = 0
    var totalRecord : Int? = 0
    var totalPage : Int? = 0

    constructor(data : T?) : this(1, 20) {
        this.data = data
    }

    constructor(pageNo : Int = 1, pageSize : Int = 20, data : T?) : this(1, 20) {
        this.data = data
        this.pageNo = pageNo
        this.pageSize = pageSize
    }
}
