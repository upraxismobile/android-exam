package com.vj.myapplication;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.vj.myapplication.fragments.SearchListFragment;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {
    public static String TAG = MainActivity.class.getSimpleName();

    private AnimatedVectorDrawable mMenuDrawable;
    private AnimatedVectorDrawable mBackDrawable;
    private boolean mMenuFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_vector);

        mMenuDrawable = (AnimatedVectorDrawable) getDrawable(R.drawable.ic_menu_animatable);
        mBackDrawable = (AnimatedVectorDrawable) getDrawable(R.drawable.ic_back_animatable);

        getSupportFragmentManager().addOnBackStackChangedListener(this);

        addFragment(new SearchListFragment(), SearchListFragment.TAG);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackStackChanged() {
        if (!mMenuFlag) {
            getSupportActionBar().setHomeAsUpIndicator(mBackDrawable);
            mBackDrawable.start();
        } else {
            getSupportActionBar().setHomeAsUpIndicator(mMenuDrawable);
            mMenuDrawable.start();
        }
        mMenuFlag = !mMenuFlag;
    }

    public void addFragment(Fragment fragment, String tag) {
        Log.e(TAG, "=== addFragment | tag:" + tag + " ===");
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Fragment frg = fragmentManager.findFragmentByTag(tag);

            if(frg == null) {
//                fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left, R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                fragmentTransaction.add(R.id.fragment_container, fragment, tag);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                Log.e(TAG, tag + "is visible: " + frg.isVisible());

                if(!frg.isVisible()) {
                    fragmentManager.popBackStack();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeFragment(Fragment fragment, String tag) {
        Log.e(TAG, "=== changeFragment ===");
        Log.e(TAG, "Position: " + fragment);
        try {
            String TAG = "tag";
            if(fragment != null) {
                try {
                    Fragment frg = getSupportFragmentManager().findFragmentByTag(tag);
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    Log.e(TAG, "Fragment is null: " + (frg == null));
                    if(frg == null) {
//                        fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left, R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        fragmentTransaction.replace(R.id.fragment_container, fragment, tag);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    } else {
                        Log.e(TAG, "Is visible: " + frg.isVisible());
                        if(!frg.isVisible()) {
                            getSupportFragmentManager().popBackStack();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        Log.e(TAG, "=== onBackPressed ===");
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if(count > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }
}
