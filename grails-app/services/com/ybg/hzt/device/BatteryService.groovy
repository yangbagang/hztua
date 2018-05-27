package com.ybg.hzt.device

import grails.gorm.transactions.Transactional

@Transactional
class BatteryService {

    def addData(Map map) {
        def uid = map.uid
        if (uid != null && uid instanceof String && uid != "") {
            saveDeviceValue(map)
        }
    }

    private saveDeviceValue(Map map) {
        def uid = map.uid as String
        def deviceValue = DeviceValue.findByUid(uid)
        if (!deviceValue) {
            deviceValue = new DeviceValue()
            deviceValue.uid = uid
            deviceValue.save flush: true
        }

        def num = map.num
        if (num == null || num == 0) {
            deviceValue.properties = map
            deviceValue.createTime = new Date()
            deviceValue.save flush: true

            def deviceHistoryValue = new DeviceHistoryValue()
            deviceHistoryValue.properties = map
            deviceHistoryValue.save flush: true
        } else {
            def battery = Battery.findByUidAndNum(uid, num)
            if (!battery) {
                battery = new Battery()
                battery.uid = uid
                battery.num = Integer.valueOf(num)
            }
            battery.properties = map
            battery.createTime = new Date()
            battery.save flush: true

            def batteryHistory = new BatteryHistory()
            batteryHistory.battery = battery
            batteryHistory.properties = map
            batteryHistory.save flush: true
        }
    }

}
