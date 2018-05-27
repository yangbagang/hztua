package com.ybg.hzt.device

import com.ybg.hzt.user.UserInfo
import com.ybg.hzt.utils.UserUtil
import grails.converters.JSON

class DeviceHistoryValueController {

    def deviceValueHistoryService

    def list(String token, String uid, Integer pageSize, Integer pageNum) {
        def map = [:]
        if (UserUtil.checkToken(token)) {
            def userInfo = UserInfo.get(UserUtil.getUserId(token))
            if (userInfo && uid) {
                def c = DeviceHistoryValue.createCriteria()
                def data = c.list(max: pageSize, offset: (pageNum - 1) * pageSize) {
                    eq("uid", uid)
                    order("createTime", "desc")
                }

                map.isSuccess = true
                map.message = ""
                map.errorCode = "0"
                map.data = data
            } else {
                map.isSuccess = false
                map.message = "参数错误"
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

    def calculate(String token, String uid, String key, Integer period) {
        def map = [:]
        if (UserUtil.checkToken(token)) {
            def userInfo = UserInfo.get(UserUtil.getUserId(token))
            if (userInfo && uid) {
                def data = deviceValueHistoryService.calculate(uid, key, period)

                map.isSuccess = true
                map.message = ""
                map.errorCode = "0"
                map.data = data
            } else {
                map.isSuccess = false
                map.message = "参数错误"
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

}
