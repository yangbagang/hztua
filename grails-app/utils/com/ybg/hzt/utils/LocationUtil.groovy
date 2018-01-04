package com.ybg.hzt.utils

import groovy.json.JsonSlurper

class LocationUtil {

    private static serverUrl = "http://api.cellocation.com:81/cell/?mcc=460&mnc=%d&lac=%d&ci=%d&output=json"

    static getLocation(Integer lac, Integer cid, Integer type) {
        String responseText = ""
        try {
            def url = String.format(serverUrl, type, lac, cid)
            responseText = url.toURL().text
        } catch (Exception e) {
            println(e.message)
        }
        def jsonSlurper = new JsonSlurper()
        def vo = new LocationVo()
        try {
            def location = jsonSlurper.parseText(responseText)
            vo.errcode = location.errcode
            vo.lat = location.lat
            vo.lon = location.lon
            vo.radius = location.radius
            vo.address = location.address
        } catch (Exception e1) {
            println(e1.message)
        }
        vo
    }

    static getLocation(Integer lac, Integer cid) {
        getLocation(lac, cid, 0)
    }

    static void main(String[] args) {
        def location = getLocation(4301, 20986, 1)
        println(location.address)
    }

}
