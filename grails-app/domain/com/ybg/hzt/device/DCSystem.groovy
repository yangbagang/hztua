package com.ybg.hzt.device

class DCSystem {

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

}
