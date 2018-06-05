package com.ybg.hzt.objectMarshaller

import com.ybg.hzt.user.UserInfo
import grails.converters.JSON
import org.grails.web.converters.exceptions.ConverterException
import org.grails.web.converters.marshaller.ObjectMarshaller
import org.grails.web.json.JSONWriter

class UserInfoMarshaller implements ObjectMarshaller<JSON> {

    @Override
    boolean supports(Object object) {
        return object instanceof UserInfo
    }

    @Override
    void marshalObject(Object object, JSON converter) throws ConverterException {
        JSONWriter writer = converter.getWriter()
        writer.object()
        writer.key('id').value(object.id)
                .key('name').value(object.name)
                .key('mobile').value(object.mobile)
                .key("email").value(object.email)
                .key("company").value(object.company)
                .key("appToken").value(object.appToken)
                .key('token').value(object.token)
                .key("code").value(object.code)
                .key("sideNum").value(object.sideNum)
                .key('installedCapacity').value(object.installedCapacity)
        writer.endObject()
    }

}
