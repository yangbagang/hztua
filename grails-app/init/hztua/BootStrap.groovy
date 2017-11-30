package hztua

import com.ybg.hzt.objectMarshaller.UserInfoMarshaller
import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->
        JSON.registerObjectMarshaller(Date) {
            return it?.format("yyyy-MM-dd HH:mm:ss")
        }
        JSON.registerObjectMarshaller(new UserInfoMarshaller(), 9999)
    }
    def destroy = {
    }
}
