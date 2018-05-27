package com.ybg.hzt.device

import com.ybg.hzt.user.UserInfo
import com.ybg.hzt.utils.LocationUtil
import com.ybg.hzt.utils.UserUtil
import grails.converters.JSON

class BatteryLocationController {

    /**
     * 转换基站定位
     * @param token
     * @param lac
     * @param cid
     * @param type
     * @return
     */
    def getLocation(String token, Integer lac, Integer cid, Integer type) {
        def map = [:]
        if (UserUtil.checkToken(token)) {
            def userInfo = UserInfo.get(UserUtil.getUserId(token))
            if (userInfo) {
                def location = BatteryLocation.findByLacAndCidAndType(lac, cid, type)
                if (!location) {
                    location = new BatteryLocation()
                    location.lac = lac
                    location.cid = cid
                    location.type = type
                    def vo = LocationUtil.getLocation(lac, cid, type)
                    if (vo.address != "") {
                        location.lat = vo.lat
                        location.lon = vo.lon
                        location.radius = vo.radius
                        location.address = vo.address
                        location.save flush: true
                    }
                }

                map.isSuccess = true
                map.message = ""
                map.errorCode = "0"
                map.data = location.address
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

}
