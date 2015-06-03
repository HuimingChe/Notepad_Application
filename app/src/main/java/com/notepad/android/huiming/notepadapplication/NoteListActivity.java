package com.notepad.android.huiming.notepadapplication;

import android.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by huiming on 15-5-29.
 */
public class NoteListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new NoteListFragment();
    }

}
