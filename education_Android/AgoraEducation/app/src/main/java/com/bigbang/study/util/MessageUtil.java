package com.bigbang.study.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by a.nigam on 14/04/18.
 */

public class MessageUtil {
    public static void showToastMessage(CharSequence text, Context context) {
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
