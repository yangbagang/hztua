package com.ybg.hzt.device

import com.ybg.hzt.user.UserInfo
import com.ybg.hzt.utils.UserUtil
import grails.converters.JSON

class BatteryController {

    def batteryService

    /**
     * 列出某个用户下绑定的所有电池
     * @param token
     */
    def list(String token) {
        def map = [:]
        if (UserUtil.checkToken(token)) {
            def userInfo = UserInfo.get(UserUtil.getUserId(token))
            if (userInfo) {
                def data = Battery.findAllByUidInList(UserBattery.findAllByUserInfo(userInfo)*.uid)

                map.isSuccess = true
                map.message = ""
                map.errorCode = "0"
                map.data = data
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

    def saveData() {
        def map = [:]
        def uid = params.uid
        if (uid != null && uid != "") {
            def instance = Battery.findByUid(uid)
            if (instance) {
                batteryService.addData(instance, params)

                map.isSuccess = true
                map.message = ""
                map.errorCode = "0"
                map.data = "true"
            } else {
                map.isSuccess = false
                map.message = "设备不存在"
                map.errorCode = "2"
                map.data = "false"
            }
        } else {
            map.isSuccess = false
            map.message = "数据错误"
            map.errorCode = "1"
            map.data = "false"
        }

        render map as JSON
    }
}
