package com.ybg.hzt.device

import com.ybg.hzt.user.UserInfo
import com.ybg.hzt.utils.UserUtil
import grails.converters.JSON

class BatteryHistoryController {

    def batteryHistoryService

    def list(String token, Long batteryId, Integer pageSize, Integer pageNum) {
        def map = [:]
        if (UserUtil.checkToken(token)) {
            def userInfo = UserInfo.get(UserUtil.getUserId(token))
            def battery = Battery.get(batteryId)
            if (userInfo && battery) {
                def c = BatteryHistory.createCriteria()
                def data = c.list(max: pageSize, offset: (pageNum - 1) * pageSize) {
                    eq("battery", battery)
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

    def calculate(String token, Long batteryId, String key, Integer period) {
        def map = [:]
        if (UserUtil.checkToken(token)) {
            def userInfo = UserInfo.get(UserUtil.getUserId(token))
            def battery = Battery.get(batteryId)
            if (userInfo && battery) {
                def data = batteryHistoryService.calculate(batteryId, key, period)
                println(data)

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
