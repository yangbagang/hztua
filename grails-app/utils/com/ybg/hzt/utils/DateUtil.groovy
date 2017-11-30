package com.ybg.hzt.utils

import java.text.SimpleDateFormat

/**
 * Created by yangbagang on 2016/10/25.
 */
class DateUtil {

    static sdf_short = new SimpleDateFormat("yyyy-MM-dd")
    static sdf_full = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    static getDefaultBirthday() {
        sdf_short.parse("1990-01-01")
    }

    static getTimeString(Date date) {
        sdf_full.format(date)
    }

    static getXieHouEndTime() {
        def now = Calendar.getInstance(Locale.CHINA)
        def hours = now.get(Calendar.HOUR_OF_DAY)
        if (hours >=2 && hours < 21) {
            return now.time
        }
        if (hours >= 21) {
            now.add(Calendar.DAY_OF_YEAR, 1)
        }
        now.set(Calendar.HOUR_OF_DAY, 2)
        now.set(Calendar.MINUTE, 0)
        now.set(Calendar.SECOND, 0)
        now.set(Calendar.MILLISECOND, 0)
        return now.time
    }

    static getNextDaysDate(Integer days) {
        def now = Calendar.getInstance(Locale.CHINA)
        now.add(Calendar.DAY_OF_YEAR, days)
        now.time
    }

}
