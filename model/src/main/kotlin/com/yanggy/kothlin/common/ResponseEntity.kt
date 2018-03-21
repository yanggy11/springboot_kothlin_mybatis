package com.yanggy.kothlin.common

import java.io.Serializable

/**
 * Created by yangguiyun on 2018/3/14.
 */

open class ResponseEntity<T> : Serializable {

    var status : String
    var msg : String
    var data : T?

    /**
     * initialize member properties
     */
    init {
        this.status = Constants.SUCCESS_STATUS
        msg = Constants.SUCCESS_MSG
        this.data = null
    }

    constructor(data : T?) {
        this.data = data
    }

    constructor(status : String, msg : String) {
        this.msg = msg
        this.status = status
    }

    constructor(status : String, msg : String, data : T?) {
        this.msg = msg
        this.status = status
        this.data = data
    }
}