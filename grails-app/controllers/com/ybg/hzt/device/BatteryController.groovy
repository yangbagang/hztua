package com.ybg.hzt.device

import com.ybg.hzt.user.UserInfo
import com.ybg.hzt.utils.LocationUtil
import com.ybg.hzt.utils.UserUtil
import grails.converters.JSON

class BatteryController {

    def batteryService

    /**
     * 列出某个用户下绑定的所有电池系统
     * @param token
     */
    def listBS(String token) {
        def map = [:]
        if (UserUtil.checkToken(token)) {
            def userInfo = UserInfo.get(UserUtil.getUserId(token))
            if (userInfo) {
                def data = BatterySystem.findAllByUidInList(UserBattery.findAllByUserInfo(userInfo)*.uid)

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
     * 列出某个用户下绑定的所有UPS系统
     * @param token
     */
    def listUPS(String token) {
        def map = [:]
        if (UserUtil.checkToken(token)) {
            def userInfo = UserInfo.get(UserUtil.getUserId(token))
            if (userInfo) {
                def data = UPSSystem.findAllByUidInList(UserBattery.findAllByUserInfo(userInfo)*.uid)

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
     * 列出某个用户下绑定的所有直流系统
     * @param token
     */
    def listDC(String token) {
        def map = [:]
        if (UserUtil.checkToken(token)) {
            def userInfo = UserInfo.get(UserUtil.getUserId(token))
            if (userInfo) {
                def data = DCSystem.findAllByUidInList(UserBattery.findAllByUserInfo(userInfo)*.uid)

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

    /**
     * 更新名称
     * @param uid
     * @param name
     * @return
     */
    def updateName(String uid, String name) {
        def map = [:]
        if (uid && name) {
            batteryService.updateName(uid, name)

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

    def listDataByUid(String token, String uid, Integer pageSize, Integer pageNum) {
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
