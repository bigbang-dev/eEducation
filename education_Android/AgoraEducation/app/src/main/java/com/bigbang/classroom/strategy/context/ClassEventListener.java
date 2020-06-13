package com.bigbang.classroom.strategy.context;

import androidx.annotation.Nullable;

import com.bigbang.classroom.bean.channel.User;
import com.bigbang.classroom.bean.msg.ChannelMsg;
import com.bigbang.sdk.annotation.NetworkQuality;

public interface ClassEventListener {

    void onTeacherInit(@Nullable User teacher);

    void onNetworkQualityChanged(@NetworkQuality int quality);

    void onClassStateChanged(boolean isBegin, long time);

    void onWhiteboardChanged(String uuid, String roomToken);

    void onLockWhiteboard(boolean locked);

    void onMuteLocalChat(boolean muted);

    void onMuteAllChat(boolean muted);

    void onChatMsgReceived(ChannelMsg.ChatMsg msg);

    void onScreenShareJoined(int uid);

    void onScreenShareOffline(int uid);

}
