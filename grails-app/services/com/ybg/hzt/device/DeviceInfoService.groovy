package com.ybg.hzt.device

import grails.gorm.transactions.Transactional
import groovy.sql.Sql

@Transactional
class DeviceInfoService {

    def dataSource

    def list(Long userId, String name) {
        def sql = new Sql(dataSource)
        def query = "select a.name, b.*, g.name as catalog from user_battery u left join device_info a on u.uid=a.uid " +
                "left join device_value b on u.uid=b.uid left join device_catalog g on a.device_info_id = g.id" +
                "where a.user_info_id=? and a.name like ?"
        sql.rows(query, userId, "%" + name + "%")
    }

}
