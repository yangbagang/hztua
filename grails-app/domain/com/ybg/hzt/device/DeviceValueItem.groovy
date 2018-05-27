package com.ybg.hzt.device

class DeviceValueItem {

    static belongsTo = [deviceCatalog: DeviceCatalog]

    static constraints = {
    }

    String name = ""//英文名称，与数值完全一致。
    String label = ""//中文名称
    String unit = ""//单位
    Float minValue = 0f//最小值
    Float maxValue = 0f//最大值
    Short enableCheck = 0 as Short//是否启用检查。如启用则会触发告警信息
}
