package com.bigbang.service.bean.request;

import com.bigbang.classroom.bean.msg.PeerMsg;

public class CoVideoReq {

    @PeerMsg.CoVideoMsg.Type
    int type;

    public CoVideoReq(@PeerMsg.CoVideoMsg.Type int type) {
        this.type = type;
    }

}
