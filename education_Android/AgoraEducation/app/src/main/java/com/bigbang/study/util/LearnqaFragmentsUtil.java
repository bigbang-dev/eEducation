package com.bigbang.study.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by a.nigam on 30/06/18.
 */

public class LearnqaFragmentsUtil {

    /**
     * Refer: https://developer.android.com/training/implementing-navigation/temporal
     * Replace whatever is in the fragment_container view with this fragment,
     * and add the transaction to the back stack so the user can navigate back
     */
    public static void beginTransactionWithBackNavEnabled(int id, Fragment fragment,
                                                          FragmentManager supportFragmentManager) {
        FragmentTransaction ft = supportFragmentManager.beginTransaction();
        ft.replace(id, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
