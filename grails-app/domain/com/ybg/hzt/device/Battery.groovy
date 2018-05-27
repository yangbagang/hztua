package com.ybg.hzt.device

class Battery {

    static constraints = {
    }

    //具体电池特性
    String uid = ""//为序列号即为“站号”
    Integer lac = 0//基站定位，app定位地图
    Integer cid = 0//基站定位，app定位地图
    Integer num = 0//一个站内，多个被采集对象的序号
    Float bv = 0f//电池电压
    Float bt = 0f//电池温度
    Float br = 0f//电池内阻
    Date createTime = new Date()
}
