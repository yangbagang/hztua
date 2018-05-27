package com.ybg.hzt.device

import com.ybg.hzt.user.UserInfo
import com.ybg.hzt.utils.UserUtil
import grails.converters.JSON

class DeviceInfoController {

    def deviceInfoService

    /**
     * 列出某个用户下绑定的所有设备
     * @param token
     * @param name
     */
    def list(String token, String name) {
        def map = [:]
        if (UserUtil.checkToken(token)) {
            def userInfo = UserInfo.get(UserUtil.getUserId(token))
            if (userInfo) {
                def data = deviceInfoService.list(userInfo.id, name)

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

    /**
     * 更新名称
     * @param uid
     * @param name
     * @return
     */
    def updateName(String uid, String name) {
        def map = [:]
        if (uid && name) {
            def deviceInfo = DeviceInfo.findByUid(uid)
            if (deviceInfo) {
                deviceInfo.name = name
                deviceInfo.save flush: true
            }

            map.isSuccess = true
            map.message = ""
            map.errorCode = "0"
            map.data = "true"
        } else {
            map.isSuccess = false
            map.message = "数据错误"
            map.errorCode = "1"
            map.data = "false"
        }

        render map as JSON
    }
}
