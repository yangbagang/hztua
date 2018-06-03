package com.ybg.hzt.user

import com.ybg.hzt.utils.UserUtil
import grails.converters.JSON
import org.apache.commons.codec.digest.DigestUtils

class UserInfoController {

    /**
     * 用户登录
     * @param mobile 手机号
     * @param password 密码
     */
    def login(String mobile, String password) {
        def map = [:]
        if (mobile && password) {
            def user = UserInfo.findByMobile(mobile)
            if (!user) {
                map.isSuccess = false
                map.message = "用户名或密码错误"
                map.errorCode = "2"
                map.data = "false"
            } else {
                //生成实例
                def pwd = DigestUtils.sha256Hex(password)
                if (pwd == user.password) {
                    //构造返回数据
                    def token = UserUtil.createToken("user", user.id)
                    user.token = token
                    map.isSuccess = true
                    map.message = "登录完成"
                    map.errorCode = "0"
                    map.data = user
                } else {
                    map.isSuccess = false
                    map.message = "用户名或密码错误"
                    map.errorCode = "3"
                    map.data = "false"
                }
            }
        } else {
            map.isSuccess = false
            map.message = "参数不能为空"
            map.errorCode = "1"
            map.data = "false"
        }
        render map as JSON
    }

    /**
     * 更新用户个性信息。
     * @param token 用户token
     * @param name 昵称
     * @param email
     * @param company
     * @return
     */
    def updatePassword(String token, String oldPwd, String newPwd) {
        def map = [:]
        if (UserUtil.checkToken(token)) {
            def userInfo = UserInfo.get(UserUtil.getUserId(token))
            if (userInfo) {
                if (userInfo.password == DigestUtils.sha256Hex(oldPwd)) {
                    userInfo.password = DigestUtils.sha256Hex(newPwd)
                    userInfo.save flush: true

                    map.isSuccess = true
                    map.message = ""
                    map.errorCode = "0"
                    map.data = "true"
                } else {
                    map.isSuccess = false
                    map.message = "旧密码不正确"
                    map.errorCode = "3"
                    map.data = "true"
                }
            } else {
                map.isSuccess = false
                map.message = "用户不存在"
                map.errorCode = "2"
                map.data = "false"
            }
        } else {
            map.isSuccess = false
            map.message = "登录凭证失效，请重新登录"
            map.errorCode = "1"
            map.data = "false"
        }

        render map as JSON
    }

    def logout(String token) {
        def map = [:]
        UserUtil.removeToken(token)

        map.isSuccess = true
        map.message = ""
        map.errorCode = "0"
        map.data = "true"

        render map as JSON
    }

    /**
     * 更新App Token
     * @param userToken
     * @param appToken
     * @return
     */
    def updateAppToken(String userToken, String appToken) {
        def map = [:]
        if (UserUtil.checkToken(userToken)) {
            def userInfo = UserInfo.get(UserUtil.getUserId(userToken))
            if (userInfo) {
                userInfo.appToken = appToken
                userInfo.save flush: true

                map.isSuccess = true
                map.message = ""
                map.errorCode = "0"
                map.data = "true"
            } else {
                map.isSuccess = false
                map.message = "用户不存在"
                map.errorCode = "2"
                map.data = "false"
            }
        } else {
            map.isSuccess = false
            map.message = "登录凭证失效，请重新登录"
            map.errorCode = "1"
            map.data = "false"
        }

        render map as JSON
    }

    /**
     * 获取用户信息
     * @param token 用户token
     * @return
     */
    def getUserInfo(String token) {
        def map = [:]
        if (UserUtil.checkToken(token)) {
            def userId = UserUtil.getUserId(token)
            def userInfo = UserInfo.get(userId)

            map.isSuccess = true
            map.message = ""
            map.errorCode = "0"
            map.data = userInfo
        } else {
            map.isSuccess = false
            map.message = "登录凭证失效，请重新登录"
            map.errorCode = "1"
            map.data = "false"
        }

        render map as JSON
    }

}
