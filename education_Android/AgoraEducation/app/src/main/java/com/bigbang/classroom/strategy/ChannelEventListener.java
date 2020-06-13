package com.bigbang.classroom.strategy;

import java.util.List;

import com.bigbang.classroom.bean.channel.Room;
import com.bigbang.classroom.bean.channel.User;
import com.bigbang.classroom.bean.msg.ChannelMsg;
import com.bigbang.classroom.bean.msg.PeerMsg;

public interface ChannelEventListener {

    void onChannelInfoInit();

    void onRoomChanged(Room room);

    void onLocalChanged(User local);

    void onCoVideoUsersChanged(List<User> users);

    void onChannelMsgReceived(ChannelMsg msg);

    void onPeerMsgReceived(PeerMsg msg);

    void onMemberCountUpdated(int count);

}
