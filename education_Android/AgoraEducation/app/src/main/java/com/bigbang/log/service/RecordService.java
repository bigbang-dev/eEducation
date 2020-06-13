package com.bigbang.log.service;

import com.bigbang.log.service.bean.ResponseBody;
import com.bigbang.log.service.bean.response.RecordRes;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RecordService {

    @GET("/edu/v1/apps/{appId}/room/{roomId}/record/{recordId}")
    Call<ResponseBody<RecordRes>> record(
            @Path("appId") String appId,
            @Path("roomId") String roomId,
            @Path("recordId") String recordId
    );

}
