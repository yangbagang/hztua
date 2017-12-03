package com.ybg.hzt.device

import com.ybg.hzt.user.UserInfo

class UserBattery {

    static belongsTo = [userInfo: UserInfo]

    static constraints = {
    }

    String uid = ""
}
