package com.ybg.hzt.user

class UserInfo {

    static constraints = {
    }

    String name
    String code = ""//用户编号
    Integer sideNum = 0//站点数量
    Integer installedCapacity = 0//装机容量
    String mobile
    String password
    String appToken = ""
    String email = ""
    String company = ""

    transient String token
}
