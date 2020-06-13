package com.bigbang.classroom.strategy.context;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import java.util.List;

import com.bigbang.base.Callback;
import com.bigbang.base.ToastManager;
import com.bigbang.base.network.RetrofitManager;


import com.bigbang.BuildConfig;
import com.bigbang.EduApplication;
import com.bigbang.R;
import com.bigbang.base.BaseCallback;
import com.bigbang.classroom.bean.channel.Room;
import com.bigbang.classroom.bean.channel.User;
import com.bigbang.classroom.bean.msg.PeerMsg;
import com.bigbang.classroom.strategy.ChannelStrategy;
import com.bigbang.log.service.RoomService;
import com.bigbang.log.service.bean.request.CoVideoReq;
import io.agora.rtc.Constants;
import com.bigbang.sdk.manager.RtcManager;

import static com.bigbang.classroom.bean.msg.PeerMsg.CoVideoMsg.Type.ACCEPT;
import static com.bigbang.classroom.bean.msg.PeerMsg.CoVideoMsg.Type.APPLY;
import static com.bigbang.classroom.bean.msg.PeerMsg.CoVideoMsg.Type.CANCEL;
import static com.bigbang.classroom.bean.msg.PeerMsg.CoVideoMsg.Type.EXIT;
import static com.bigbang.classroom.bean.msg.PeerMsg.CoVideoMsg.Type.REJECT;

public class LargeClassContext extends ClassContext {

    LargeClassContext(Context context, ChannelStrategy strategy) {
        super(context, strategy);
    }

    @Override
    public void checkChannelEnterable(@NonNull Callback<Boolean> callback) {
        callback.onSuccess(true);
    }

    @Override
    void preConfig() {
        RtcManager.instance().setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING);
        RtcManager.instance().setClientRole(Constants.CLIENT_ROLE_AUDIENCE);
        RtcManager.instance().enableDualStreamMode(false);
    }

    @Override
    public void onRoomChanged(Room room) {
        super.onRoomChanged(room);
        if (classEventListener instanceof LargeClassEventListener) {
            runListener(() -> ((LargeClassEventListener) classEventListener).onUserCountChanged(room.onlineUsers));
        }
    }

    @Override
    public void onCoVideoUsersChanged(List<User> users) {
        super.onCoVideoUsersChanged(users);
        if (classEventListener instanceof LargeClassEventListener) {
            LargeClassEventListener listener = (LargeClassEventListener) classEventListener;
            runListener(() -> {
                User linkUser = null;
                for (User user : users) {
                    if (user.isTeacher()) {
                        listener.onTeacherMediaChanged(user);
                    } else {
                        linkUser = user;
                        break;
                    }
                }
                listener.onLinkMediaChanged(linkUser);
            });
        }
    }

    @SuppressLint("SwitchIntDef")
    @Override
    public void onPeerMsgReceived(PeerMsg msg) {
        super.onPeerMsgReceived(msg);
        if (msg.cmd == PeerMsg.Cmd.CO_VIDEO) {
            PeerMsg.CoVideoMsg coVideoMsg = msg.getMsg(PeerMsg.CoVideoMsg.class);
            switch (coVideoMsg.type) {
                case REJECT:
                    ToastManager.showShort(R.string.reject_interactive);
                    break;
                case ACCEPT:
                    ToastManager.showShort(R.string.accept_interactive);
                    break;
            }
        }
    }

    public void apply() {
        RetrofitManager.instance().getService(BuildConfig.API_BASE_URL, RoomService.class)
                .roomCoVideo(EduApplication.getAppId(), channelStrategy.getChannelId(), new CoVideoReq(APPLY))
                .enqueue(new BaseCallback<>(null));
    }

    public void cancel() {
        RetrofitManager.instance().getService(BuildConfig.API_BASE_URL, RoomService.class)
                .roomCoVideo(EduApplication.getAppId(), channelStrategy.getChannelId(), new CoVideoReq(channelStrategy.getLocal().isCoVideoEnable() ? EXIT : CANCEL))
                .enqueue(new BaseCallback<>(null));
    }

    public interface LargeClassEventListener extends ClassEventListener {
        void onUserCountChanged(int count);

        void onTeacherMediaChanged(User user);

        void onLinkMediaChanged(User user);

        void onHandUpCanceled();
    }

}
