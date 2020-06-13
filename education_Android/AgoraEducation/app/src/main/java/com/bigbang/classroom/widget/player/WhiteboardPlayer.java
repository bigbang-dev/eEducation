package com.bigbang.classroom.widget.player;

import com.bigbang.timeline.Timeline;
import com.bigbang.timeline.TimelineState;
import com.bigbang.whiteboard.netless.manager.ReplayManager;

public class WhiteboardPlayer implements Timeline {
    private ReplayManager mPlayer;

    WhiteboardPlayer(ReplayManager player) {
        mPlayer = player;
    }

    @Override
    public void start() {
        mPlayer.play();
    }

    @Override
    public void pause() {
        mPlayer.pause();
    }

    @Override
    public void seekTo(long positionMs) {
        mPlayer.seekToScheduleTime(positionMs);
    }

    @Override
    public void stop() {
        mPlayer.stop();
    }

    @TimelineState
    @Override
    public int getState() {
        switch (mPlayer.getPlayerPhase()) {
            case waitingFirstFrame:
            case buffering:
                return TimelineState.STATE_BUFFERING;
            case playing:
                return TimelineState.STATE_START;
            case pause:
                return TimelineState.STATE_PAUSE;
            case stopped:
            case ended:
                return TimelineState.STATE_STOP;
        }
        return TimelineState.STATE_IDLE;
    }
}
