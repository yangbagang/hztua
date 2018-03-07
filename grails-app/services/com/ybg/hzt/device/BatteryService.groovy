package com.ybg.hzt.device

import grails.gorm.transactions.Transactional

@Transactional
class BatteryService {

    def addData(Map map) {
        def uid = map.uid
        if (uid != null && uid instanceof String && uid != "") {
            if (uid.startsWith("WLCB")) {
                addBSData(map)
            } else if (uid.startsWith("WLCU")) {
                addUPSData(map)
            } else if (uid.startsWith("WLCD")) {
                addDCData(map)
            } else {
                println "uid=${uid}"
                println("参数错误")
            }
        }
    }

    private addBSData(Map map) {
        def uid = map.uid as String
        def bs = BatterySystem.findByUid(uid)
        if (!bs) {
            bs = new BatterySystem()
            bs.uid = uid
            bs.save flush: true
        }

        def num = map.num
        if (num == null || num == 0) {
            bs.properties = map
            bs.save flush: true

            def bsHistory = new BSHistory()
            bsHistory.batterySystem = bs
            bsHistory.properties = map
            bsHistory.save flush: true
        } else {
            def battery = Battery.findByUidAndNum(uid, num)
            if (!battery) {
                battery = new Battery()
                battery.uid = uid
                battery.num = Integer.valueOf(num)
                battery.system_id = bs.id
                battery.catalog = 1
            }
            battery.properties = map
            battery.save flush: true

            def batteryHistory = new BatteryHistory()
            batteryHistory.battery =battery
            batteryHistory.properties = map
            batteryHistory.save flush: true
        }
    }

    private addUPSData(Map map) {
        def uid = map.uid as String
        def ups = UPSSystem.findByUid(uid)
        if (!ups) {
            ups = new UPSSystem()
            ups.uid = uid
            ups.save flush: true
        }

        def num = map.num
        if (num == null || num == 0) {
            ups.properties = map
            ups.save flush: true

            def upsHistory = new UPSHistory()
            upsHistory.upsSystem = ups
            upsHistory.properties = map
            upsHistory.save flush: true
        } else {
            def battery = Battery.findByUidAndNum(uid, num)
            if (!battery) {
                battery = new Battery()
                battery.uid = uid
                battery.num = Integer.valueOf(num)
                battery.system_id = ups.id
                battery.catalog = 2
            }
            battery.properties = map
            battery.save flush: true

            def batteryHistory = new BatteryHistory()
            batteryHistory.battery =battery
            batteryHistory.properties = map
            batteryHistory.save flush: true
        }
    }

    private addDCData(Map map) {
        def uid = map.uid as String
        def dc = DCSystem.findByUid(uid)
        if (!dc) {
            dc = new DCSystem()
            dc.uid = uid
            dc.save flush: true
        }

        def num = map.num
        if (num == null || num == 0) {
            dc.properties = map
            dc.save flush: true

            def dcHistory = new DCHistory()
            dcHistory.dcSystem = dc
            dcHistory.properties = map
            dcHistory.save flush: true
        } else {
            def battery = Battery.findByUidAndNum(uid, num)
            if (!battery) {
                battery = new Battery()
                battery.uid = uid
                battery.num = Integer.valueOf(num)
                battery.system_id = dc.id
                battery.catalog = 3
            }
            battery.properties = map
            battery.save flush: true

            def batteryHistory = new BatteryHistory()
            batteryHistory.battery =battery
            batteryHistory.properties = map
            batteryHistory.save flush: true
        }
    }

    def updateName(String uid, String name) {
        if (uid.startsWith("WLCB")) {
            def bs = BatterySystem.findByUid(uid)
            if (bs) {
                bs.name = name
                bs.save flush: true
            }
        } else if (uid.startsWith("WLCU")) {
            def ups = UPSSystem.findByUid(uid)
            if (ups) {
                ups.name = name
                ups.save flush: true
            }
        } else if (uid.startsWith("WLCD")) {
            def ds = DCSystem.findByUid(uid)
            if (ds) {
                ds.name = name
                ds.save flush: true
            }
        }
    }

}
