package com.notepad.android.huiming.notepadapplication;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by huiming on 15-5-29.
 * 创建一个单例类，设置数组列表存放Note
 */
public class NoteLab {

    private static final String TAG = "NotesLab";
    private static final String FILENAME = "notes.json";
    private static NoteLab sNotelab;
    private  NotesIntentJSONSerializer mSerializer;
    private Context mAppContext;
    private ArrayList<Notes> mNoteList;

    private NoteLab(Context appContext) {
        mAppContext = appContext;
        mNoteList = new ArrayList<Notes>();

//        测试代码
  /*      for (int i = 0; i < 10; i++) {
            Notes c = new Notes();
            c.setTitle("Note#" + i);
            c.setSolved(i % 2 == 0);
            mNoteList.add(c);
        }*/
    }

    //      获取单例对象
    public static NoteLab get(Context c) {
        if (sNotelab == null) {
            sNotelab = new NoteLab(c.getApplicationContext());
        }
        return sNotelab;
    }

    public ArrayList<Notes> getNotelist() {
        return mNoteList;
    }

    public Notes getNote(UUID id) {
        for (Notes c : mNoteList) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }

    public void addNotes(Notes c){
        mNoteList.add(c);
    }

    public boolean saveNotesLab(){
        try {
            mSerializer.saveNotes(mNoteList);
            Log.d(TAG,"notes saved to file");
            return true;
        }catch (Exception e){
            Log.e(TAG,"Error saving notes: ", e);
            return false;
        }
    }
}
