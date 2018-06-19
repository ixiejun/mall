package com.newhua.order.event

import com.newhua.order.data.protocol.ShipAddress

//选择收货人信息事件
class SelectAddressEvent(val address: ShipAddress)