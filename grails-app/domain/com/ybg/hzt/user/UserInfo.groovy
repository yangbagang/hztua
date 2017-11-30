package com.ybg.hzt.user

import org.apache.commons.codec.digest.DigestUtils

class UserInfo {

    static constraints = {
    }

    String name
    String mobile
    String password
    String appToken = ""
    String email = ""
    String company = ""

    transient String token
}
