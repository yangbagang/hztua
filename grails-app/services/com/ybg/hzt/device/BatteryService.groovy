package com.ybg.hzt.device

import grails.gorm.transactions.Transactional

@Transactional
class BatteryService {

    def addData(Battery battery, Map map) {
        def history = new BatteryHistoryData()
        history.battery = battery
        battery.properties = map
        history.properties = map

        battery.save flush: true
        history.save flush: true
    }

}
