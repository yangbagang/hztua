package com.ybg.hzt.device

import com.ybg.hzt.user.UserInfo
import com.ybg.hzt.utils.UserUtil
import grails.converters.JSON

class BatteryController {

    def batteryService

    def saveData() {
        def map = [:]
        def uid = params.uid
        if (uid) {
            batteryService.addData(params)

            map.isSuccess = true
            map.message = ""
            map.errorCode = "0"
            map.data = "true"
        } else {
            println "参数uid没有找到"
            map.isSuccess = false
            map.message = "数据错误"
            map.errorCode = "1"
            map.data = "false"
        }

        render map as JSON
    }

    def listByUid(String token, String uid, Integer pageSize, Integer pageNum) {
        def map = [:]
        if (UserUtil.checkToken(token)) {
            def userInfo = UserInfo.get(UserUtil.getUserId(token))
            if (userInfo) {
                def c = Battery.createCriteria()
                def data = c.list(max: pageSize, offset: (pageNum - 1) * pageSize) {
                    eq("uid", uid)
                    order("num", "asc")
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

}
