package com.yanggy.kothlin.repository

import com.yanggy.kothlin.model.Users
import com.yanggy.kothlin.param.UserParam
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * Created by yangguiyun on 2018/3/21.
 */

@Mapper
interface UserMapper {
    fun addUser(user : Users) : Int

    fun updateUser(user : Users) : Int

    fun deleteUser(@Param("id")id : Long?)  : Int

    fun getUsersById(@Param("id")id : Long?) : Users?

    fun getUsersList(user : UserParam) : List<Users>?
    fun getUsersCount(user : UserParam) : Int?
}