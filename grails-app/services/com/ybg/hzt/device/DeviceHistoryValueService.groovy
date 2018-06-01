package com.ybg.hzt.device

import grails.gorm.transactions.Transactional
import groovy.sql.Sql

import java.text.SimpleDateFormat

@Transactional(readOnly = true)
class DeviceHistoryValueService {

    def sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    def dataSource

    def calculate(String uid, String key, Integer period) {
        if (period == 1) {
            return calculateBySecond(uid, key)
        } else if (period == 2) {
            return calculateByMinute(uid, key)
        } else if (period == 3) {
            return calculateByHour(uid, key)
        } else if (period == 4) {
            return calculateByDay(uid, key)
        } else if (period == 5) {
            return calculateByMonth(uid, key)
        } else if (period == 6) {
            return calculateByYear(uid, key)
        } else {
            return []
        }
    }

    def calculateBySecond(String uid, String key) {
        def sql = new Sql(dataSource)
        def calender = Calendar.getInstance(Locale.CHINA)
        def endTime = calender.time
        def currentSecond = calender.get(Calendar.SECOND)
        calender.set(Calendar.SECOND, currentSecond - 59)
        def startTime = calender.time
        def startStr = sdf.format(startTime)
        def endStr = sdf.format(endTime)
        def query = "select create_second as xValue, round(avg(${key}),3) as yValue from device_history_value where " +
                "uid=? and create_time >= ? and create_time <=? group by create_second"
        sql.rows(query, [uid, startStr, endStr])
    }

    def calculateByMinute(String uid, String key) {
        def sql = new Sql(dataSource)
        def calender = Calendar.getInstance(Locale.CHINA)
        def endTime = calender.time
        def currentMinute = calender.get(Calendar.MINUTE)
        calender.set(Calendar.MINUTE, currentMinute - 59)
        def startTime = calender.time
        def startStr = sdf.format(startTime)
        def endStr = sdf.format(endTime)
        def query = "select create_minute as xValue, round(avg(${key}),3) as yValue from device_history_value where " +
                "uid=? and create_time >= ? and create_time <=? group by create_minute"
        sql.rows(query, [uid, startStr, endStr])
    }

    def calculateByHour(String uid, String key) {
        def sql = new Sql(dataSource)
        def calender = Calendar.getInstance(Locale.CHINA)
        def endTime = calender.time
        def currentHour = calender.get(Calendar.HOUR_OF_DAY)
        calender.set(Calendar.HOUR_OF_DAY, currentHour - 23)
        def startTime = calender.time
        def startStr = sdf.format(startTime)
        def endStr = sdf.format(endTime)
        def query = "select create_hour as xValue, round(avg(${key}),3) as yValue from device_history_value where " +
                "uid=? and create_time >= ? and create_time <=? group by create_hour"
        sql.rows(query, [uid, startStr, endStr])
    }

    def calculateByDay(String uid, String key) {
        def sql = new Sql(dataSource)
        def calender = Calendar.getInstance(Locale.CHINA)
        def endTime = calender.time
        def currentDay = calender.get(Calendar.DAY_OF_MONTH)
        calender.set(Calendar.DAY_OF_MONTH, currentDay - 29)
        def startTime = calender.time
        def startStr = sdf.format(startTime)
        def endStr = sdf.format(endTime)
        def query = "select create_day as xValue, round(avg(${key}),3) as yValue from device_history_value where " +
                "uid=? and create_time >= ? and create_time <=? group by create_day"
        sql.rows(query, [uid, startStr, endStr])
    }

    def calculateByMonth(String uid, String key) {
        def sql = new Sql(dataSource)
        def calender = Calendar.getInstance(Locale.CHINA)
        def endTime = calender.time
        def currentMonth = calender.get(Calendar.MONTH)
        calender.set(Calendar.MONTH, currentMonth - 11)
        def startTime = calender.time
        def startStr = sdf.format(startTime)
        def endStr = sdf.format(endTime)
        def query = "select create_month as xValue, round(avg(${key}),3) as yValue from device_history_value where " +
                "uid=? and create_time >= ? and create_time <=? group by create_month"
        sql.rows(query, [uid, startStr, endStr])
    }

    def calculateByYear(String uid, String key) {
        def sql = new Sql(dataSource)
        def calender = Calendar.getInstance(Locale.CHINA)
        def endTime = calender.time
        def currentYear = calender.get(Calendar.YEAR)
        calender.set(Calendar.YEAR, currentYear - 9)
        def startTime = calender.time
        def startStr = sdf.format(startTime)
        def endStr = sdf.format(endTime)
        def query = "select create_year as xValue, round(avg(${key}),3) as yValue from device_history_value where " +
                "uid=? and create_time >= ? and create_time <=? group by create_year"
        sql.rows(query, [uid, startStr, endStr])
    }

    def listByKey(String uid, String key, Integer pageSize, Integer pageName) {
        def sql = new Sql(dataSource)
        def query = "select ? as yValue, create_time as xValue from device_history_value where uid=?"
        sql.rows(query, [key, uid], (pageName - 1) * pageSize, pageSize)
    }

}
