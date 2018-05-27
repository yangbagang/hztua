package com.ybg.hzt.device

class WarningMsg {

    static constraints = {
        readTime nullable: true
    }

    Long sourceId//告警来源
    String uid = ""//设备UID
    Integer num = 0//一个站内，多个被采集对象的序号
    Integer type//类型，1为设备，0为电池
    String itemName//发生告警的项
    Float itemValue//发生告警的值
    Short flag//标志，1为过高，0为过低
    Date createTime = new Date()//发生时间
    Short hasRead = 0 as Short//是否己读，1己读，0未读
    String userName = ""//阅读者姓名
    String readTime = new Date()//阅读时间
}
