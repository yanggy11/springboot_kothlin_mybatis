package com.yanggy.kothlin.controller

import com.yanggy.kothlin.model.Users
import com.yanggy.kothlin.service.UsersService
import com.yanggy.kothlin.common.ResponseEntity
import com.yanggy.kothlin.param.UserParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

/**
 * Created by yangguiyun on 2018/3/14.
 */

@RestController
@RequestMapping("/user")
class UserController {
    @Resource
    lateinit var userService : UsersService

    @PostMapping(value = "/addUser")
    fun save(@RequestBody user : Users) : ResponseEntity<Any>? {
        return userService.addUser(user)
    }

    @PostMapping(value = "/updateUser")
    fun updateUser(@RequestBody user : Users) : ResponseEntity<Any>? {
        return userService.updateUser(user)
    }

    @PostMapping(value = "/deleteUser")
    fun deleteUser(@RequestBody user : Users)  : ResponseEntity<Any>? {
        return userService.deleteUser(user)
    }

    @PostMapping(value = "/getUsersById")
    fun getUsersById(@RequestBody user : Users) : ResponseEntity<Any>? {
        return userService.getUsersById(user)
    }

    @PostMapping(value = "/getUsersList")
    fun getUsersList(@RequestBody user : UserParam) : ResponseEntity<Any>? {
        return userService.getUsersList(user)
    }
}