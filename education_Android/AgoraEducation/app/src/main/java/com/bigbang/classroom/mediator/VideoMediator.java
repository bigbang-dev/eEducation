package com.bigbang.classroom.mediator;

import com.bigbang.classroom.widget.RtcVideoView;
import io.agora.rtc.video.VideoCanvas;
import com.bigbang.sdk.manager.RtcManager;

public class VideoMediator {

    public static void setupLocalVideo(RtcVideoView item) {
        RtcManager manager = RtcManager.instance();
        if (item.getSurfaceView() == null) {
            item.setSurfaceView(manager.createRendererView(item.getContext()));
        }
        manager.setupLocalVideo(item.getSurfaceView(), VideoCanvas.RENDER_MODE_HIDDEN);
        manager.startPreview();
    }

    public static void setupRemoteVideo(RtcVideoView item, int uid) {
        RtcManager manager = RtcManager.instance();
        if (item.getSurfaceView() == null) {
            item.setSurfaceView(manager.createRendererView(item.getContext()));
        }
        manager.setupRemoteVideo(item.getSurfaceView(), VideoCanvas.RENDER_MODE_HIDDEN, uid);
    }

}
