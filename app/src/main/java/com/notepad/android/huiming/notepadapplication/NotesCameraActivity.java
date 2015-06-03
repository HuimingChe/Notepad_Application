package com.notepad.android.huiming.notepadapplication;

import android.app.Fragment;

/**
 * Created by huiming on 15-6-3.
 */
public class NotesCameraActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {

        return new NoteCameraFragment();
    }
}
