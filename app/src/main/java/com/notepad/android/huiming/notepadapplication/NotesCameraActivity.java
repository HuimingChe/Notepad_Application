package com.notepad.android.huiming.notepadapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.WindowManager;

/**
 * Created by huiming on 15-6-3.
 * NotesCameraActivity
 */
public class NotesCameraActivity extends SingleFragmentActivity {

    /**
     * Called to do initial creation of a fragment.  This is called after
     * {@link #onAttach(Activity)} and before
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * <p/>
     * <p>Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, see {@link #onActivityCreated(Bundle)}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //ActionBar actionBar = getSupportActionBar();
       // actionBar.hide();
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {

        return new NoteCameraFragment();
    }
}
