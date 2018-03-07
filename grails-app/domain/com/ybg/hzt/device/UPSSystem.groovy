package com.ybg.hzt.device

class UPSSystem {

    static constraints = {
    }

    String uid = ""//为序列号即为“站号”
    String name = ""//用户自定义名称
    Integer lac = 0//基站定位，app定位地图
    Integer cid = 0//基站定位，app定位地图
    Float bi = 0f//电池电流*
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
    Float btv = 0f//电池组电压*
    Float bbv = 0f//母线电压
    Float bcc = 0f//电池充电电流
    Float bdc = 0f//电池放电电流
    Integer alm = 0//报警
    Float ex1 = 0f//扩展
    Float ex2 = 0f//扩展
    Float ex3 = 0f//扩展
    Float ex4 = 0f//扩展
    Float ex5 = 0f//扩展
    Date createTime = new Date()

}