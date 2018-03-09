package com.ybg.hzt.device

class BatteryHistory {

    static belongsTo = [battery: Battery]

    static constraints = {
    }

    String uid = ""//为序列号即为“站号”
    Integer lac = 0//基站定位，app定位地图
    Integer cid = 0//基站定位，app定位地图
    Integer num = 0//一个站内，多个被采集对象的序号
    Float bv = 0f//电池电压
    Float bt = 0f//电池温度
    Float br = 0f//电池内阻
    Date createTime = new Date()

    Integer createYear = 0//年
    Integer createMonth = 0//月
    Integer createDay = 0//日
    Integer createHour = 0//时
    Integer createMinute = 0//分
    Integer createSecond = 0//秒
    Integer dayInWeek = 0//星期几
    Integer weekInYear = 0//第几周

    def beforeInsert() {
        def calendar = Calendar.getInstance(Locale.CHINA)
        createTime = calendar.time
        createYear = calendar.get(Calendar.YEAR)
        createMonth = calendar.get(Calendar.MONTH)
        createDay = calendar.get(Calendar.DAY_OF_MONTH)
        createHour = calendar.get(Calendar.HOUR_OF_DAY)
        createMinute = calendar.get(Calendar.MINUTE)
        createSecond = calendar.get(Calendar.SECOND)
        dayInWeek = calendar.get(Calendar.DAY_OF_WEEK)
        weekInYear = calendar.get(Calendar.WEEK_OF_YEAR)
    }

}
