package com.notepad.android.huiming.notepadapplication;

import android.app.Fragment;

import java.util.UUID;


public class NotesActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        UUID noteId = (UUID) getIntent().getSerializableExtra(NoteFragment.EXTRA_NOTES_ID);
        return NoteFragment.newInstance(noteId);
    }


}
