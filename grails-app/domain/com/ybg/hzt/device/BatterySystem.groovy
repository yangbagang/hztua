package com.ybg.hzt.device

class BatterySystem {

    static constraints = {
    }

    //电池系统
    String uid = ""//为序列号即为“站号”
    String name = ""//用户自定义名称
    Integer lac = 0//基站定位，app定位地图
    Integer cid = 0//基站定位，app定位地图
    Float bi = 0f//电池电流*
    Float btv = 0f//电池组电压*
    Integer alm = 0//报警
    Float ex1 = 0f//扩展
    Float ex2 = 0f//扩展
    Float ex3 = 0f//扩展
    Float ex4 = 0f//扩展
    Float ex5 = 0f//扩展
    Date createTime = new Date()

}
