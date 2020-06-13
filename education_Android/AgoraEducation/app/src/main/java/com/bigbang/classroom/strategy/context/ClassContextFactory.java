package com.bigbang.classroom.strategy.context;

import android.content.Context;

import com.bigbang.classroom.bean.channel.Room;
import com.bigbang.classroom.bean.channel.User;
import com.bigbang.classroom.strategy.ChannelStrategy;
import com.bigbang.classroom.strategy.HttpChannelStrategy;

public class ClassContextFactory {

    private Context context;

    public ClassContextFactory(Context context) {
        this.context = context;
    }

    public ClassContext getClassContext(@Room.Type int classType, String channelId, User local) {
        ChannelStrategy strategy = new HttpChannelStrategy(channelId, local);
        switch (classType) {
            case Room.Type.ONE2ONE:
                return new OneToOneClassContext(context, strategy);
            case Room.Type.SMALL:
                return new SmallClassContext(context, strategy);
            case Room.Type.LARGE:
            default:
                return new LargeClassContext(context, strategy);
        }
    }

}
