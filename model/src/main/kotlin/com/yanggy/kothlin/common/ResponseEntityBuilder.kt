package com.yanggy.kothlin.common

/**
 * Created by derrick.yang on 3/15/18.
 */
open class ResponseEntityBuilder {
    /**
     * static method
     */
    companion object {
        fun<T> buildNormalResponse(data : T?) : ResponseEntity<T> {
            return ResponseEntity(data)
        }

        fun<T> buildErrorResponse(msg : String, status : String) : ResponseEntity<T> {
            return ResponseEntity(status, msg)
        }
    }
}