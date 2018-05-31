package com.ybg.hzt.device

class DeviceValue {

    static constraints = {
    }

    String uid = ""//为序列号即为“站号”
    Integer lac = 0//基站定位，app定位地图
    Integer cid = 0//基站定位，app定位地图
    Integer num = 0//一个站内，多个被采集对象的序号
    Float bv = 0f//电池电压
    Float bt = 0f//电池温度
    Float br = 0f//电池内阻
    Float bi = 0f//电池电流
    Float mepur = 0f//R相市电电压
    Float mepus = 0f//S相市电电压
    Float meput = 0f//T相市电电压
    Float mepf = 0f//市电频率
    Float bepur = 0f//R相旁路电压
    Float bepus = 0f//S相旁路电压
    Float beput = 0f//T相旁路电压
    Float bepf = 0f//旁路频率
    Float iepur = 0f//R相逆变电压
    Float iepus = 0f//S相逆变电压
    Float ieput = 0f//T相逆变电压
    Float iepf = 0f//逆变频率
    Float oepur = 0f//R相输出电压
    Float oepus = 0f//S相输出电压
    Float oeput = 0f//T相输出电压
    Float oepf = 0f//输出频率
    Float ocr = 0f//R相输出电流
    Float ocs = 0f//S相输出电流
    Float oct = 0f//T相输出电流
    Float opr = 0f//R相输出功率
    Float ops = 0f//S相输出功率
    Float opt = 0f//T相输出功率
    Float otp = 0f//输出总功率
    Float opfrs = 0f//RS输出功率因素
    Float opfst = 0f//ST输出功率因素
    Float opftr = 0f//TR输出功率因素
    Float btv = 0f//电池组电压
    Float bbv = 0f//母线电压
    Float bcc = 0f//电池充电电流
    Float bdc = 0f//电池放电电流
    Float mp = 0f//正母线电压
    Float mn = 0f//负母线电压
    Float hmv = 0f//合母电压
    Float kmv = 0f//控母电压
    Float oi = 0f//输出电流
    Integer alm = 0//报警
    Date createTime = new Date()
    Float dp = 0f//电池压差
    Float chv = 0f//单体最高电压
    Float clv = 0f//单体最低电压
    Float cht = 0f//单体最高温度
    Float chr = 0f//单体最高内阻
}
