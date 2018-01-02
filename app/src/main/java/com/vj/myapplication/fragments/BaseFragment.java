package com.vj.myapplication.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.vj.myapplication.MainActivity;

/**
 *
 * Created by VJ on 12/30/2017.
 */

public class BaseFragment extends Fragment {

    MainActivity mainActivity;

    @Override
    public void onAttach(Context context) {
        mainActivity = (MainActivity) context;
        super.onAttach(context);
    }

    public void changeFragment(Fragment fragment, String tag) {
        mainActivity.changeFragment(fragment, tag);
    }
}
