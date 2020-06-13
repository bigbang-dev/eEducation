package com.bigbang.log.service;

import com.bigbang.log.service.bean.ResponseBody;
import com.bigbang.log.service.bean.request.ChatReq;
import com.bigbang.log.service.bean.request.CoVideoReq;
import com.bigbang.log.service.bean.request.RoomEntryReq;
import com.bigbang.log.service.bean.request.UserReq;
import com.bigbang.log.service.bean.response.RoomBoardRes;
import com.bigbang.log.service.bean.response.RoomEntryRes;
import com.bigbang.log.service.bean.response.RoomRes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RoomService {

    @POST("/edu/v1/apps/{appId}/room/entry")
    Call<ResponseBody<RoomEntryRes>> roomEntry(
            @Path("appId") String appId,
            @Body RoomEntryReq body
    );

    @POST("/edu/v1/apps/{appId}/room/{roomId}/user/{userId}")
    Call<ResponseBody<Boolean>> user(
            @Path("appId") String appId,
            @Path("roomId") String roomId,
            @Path("userId") String userId,
            @Body UserReq body
    );

    @GET("/edu/v1/apps/{appId}/room/{roomId}")
    Call<ResponseBody<RoomRes>> room(
            @Path("appId") String appId,
            @Path("roomId") String roomId
    );

    @POST("/edu/v1/apps/{appId}/room/{roomId}/exit")
    Call<ResponseBody<Boolean>> roomExit(
            @Path("appId") String appId,
            @Path("roomId") String roomId
    );

    @GET("/edu/v1/apps/{appId}/room/{roomId}/board")
    Call<ResponseBody<RoomBoardRes>> roomBoard(
            @Path("appId") String appId,
            @Path("roomId") String roomId
    );

    @POST("/edu/v1/apps/{appId}/room/{roomId}/covideo")
    Call<ResponseBody<Boolean>> roomCoVideo(
            @Path("appId") String appId,
            @Path("roomId") String roomId,
            @Body CoVideoReq body
    );

    @POST("/edu/v1/apps/{appId}/room/{roomId}/chat")
    Call<ResponseBody<Boolean>> roomChat(
            @Path("appId") String appId,
            @Path("roomId") String roomId,
            @Body ChatReq body
    );

}
