package com.ybg.hzt.device

class DCHistory {

    static belongsTo = [dcSystem: DCSystem]

    static constraints = {
    }

    String uid = ""//为序列号即为“站号”
    Integer lac = 0//基站定位，app定位地图
    Integer cid = 0//基站定位，app定位地图
    Integer num = 0//一个站内，多个被采集对象的序号
    Float bi = 0f//电池电流*
    Float mepur = 0f//R相市电电压
    Float mepus = 0f//S相市电电压
    Float meput = 0f//T相市电电压
    Float mepf = 0f//市电频率
    Float btv = 0f//电池组电压*
    Float bcc = 0f//电池充电电流
    Float bdc = 0f//电池放电电流
    Float mp = 0f//正母线电压
    Float mn = 0f//负母线电压
    Float hmv = 0f//合母电压
    Float kmv = 0f//控母电压
    Float oi = 0f//输出电流
    Integer alm = 0//报警
    Float ex1 = 0f//扩展
    Float ex2 = 0f//扩展
    Float ex3 = 0f//扩展
    Float ex4 = 0f//扩展
    Float ex5 = 0f//扩展
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
