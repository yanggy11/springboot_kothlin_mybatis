package com.yanggy.kothlin.service.impl

import com.yanggy.kothlin.model.Users
import com.yanggy.kothlin.service.UsersService
import com.yanggy.kothlin.common.ResponseEntity
import com.yanggy.kothlin.common.ResponseEntityBuilder
import com.yanggy.kothlin.common.Constants
import com.yanggy.kothlin.common.Page
import com.yanggy.kothlin.param.UserParam
import com.yanggy.kothlin.repository.UserMapper

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * Created by yangguiyun on 2018/3/15.
 */

@Service("userService")
@Transactional
open class UserServiceImpl : UsersService {

    @Autowired
    lateinit private var userMapper : UserMapper

    override fun addUser(user : Users) : ResponseEntity<Any>? {
        return ResponseEntityBuilder.buildNormalResponse(userMapper.addUser(user))
    }

    override fun updateUser(user : Users) : ResponseEntity<Any>? {
        return ResponseEntityBuilder.buildNormalResponse(userMapper.updateUser(user))
    }

    override fun deleteUser(user : Users)  : ResponseEntity<Any>? {
        userMapper.deleteUser(user.id)

        return ResponseEntityBuilder.buildNormalResponse(null)
    }

    override fun getUsersById(user : Users) : ResponseEntity<Any>? {

        return ResponseEntityBuilder.buildNormalResponse(userMapper.getUsersById(user.id))
    }

    override fun getUsersList(user : UserParam) : ResponseEntity<Any>? {
        user.page.offset = (user.page.pageNo - 1) * user.page.pageSize

        return ResponseEntityBuilder.buildNormalResponse(this.buildPage(userMapper.getUsersList(user), userMapper.getUsersCount(user), user.page))
    }

    private fun buildPage(data : Any?, count : Int?, page : Page<Any>) : Page<Any> {
        if(null != count) {
            page.data = data
            page.totalRecord = count
            var totalPage = count as Int / (page.pageSize)
            page.totalPage = when(count % page.pageSize) {
                0 -> totalPage
                else -> totalPage + 1
            }
        }

        return page
    }
}
