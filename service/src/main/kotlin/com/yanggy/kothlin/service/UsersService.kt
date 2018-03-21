package com.yanggy.kothlin.service

import com.yanggy.kothlin.model.Users
import com.yanggy.kothlin.common.ResponseEntity
import com.yanggy.kothlin.param.UserParam

/**
 * Created by yangguiyun on 2018/3/15.
 */
interface UsersService {
    fun addUser(user : Users) : ResponseEntity<Any>?

    fun updateUser(user : Users) : ResponseEntity<Any>?

    fun deleteUser(user : Users)  : ResponseEntity<Any>?

    fun getUsersById(user : Users) : ResponseEntity<Any>?

    fun getUsersList(user : UserParam) : ResponseEntity<Any>?
}