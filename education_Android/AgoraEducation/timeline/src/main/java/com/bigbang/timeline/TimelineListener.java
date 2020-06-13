package com.bigbang.timeline;

public interface TimelineListener {
    void onStart();

    void onPause();

    void onTimelineChanged(long currentTime, long totalTime);

    void onStop();
}
