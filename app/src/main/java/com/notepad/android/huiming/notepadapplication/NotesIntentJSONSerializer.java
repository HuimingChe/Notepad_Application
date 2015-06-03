package com.notepad.android.huiming.notepadapplication;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by huiming on 15-6-2.
 */
public class NotesIntentJSONSerializer {
    private Context mContext;
    private String mFilename;

    public NotesIntentJSONSerializer(Context c, String f) {
        mContext = c;
        mFilename = f;
    }

    public void saveNotes(ArrayList<Notes> notes)
            throws JSONException, IOException {
        JSONArray array = new JSONArray();
        for (Notes c : notes) {
            array.put(c.toString());
        }

        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
