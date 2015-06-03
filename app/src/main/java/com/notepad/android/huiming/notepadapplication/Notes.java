package com.notepad.android.huiming.notepadapplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

/**
 * Created by huiming on 15-5-28.
 */
public class Notes {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_SOLVED = "solved";
    private static final String JSON_DATE = "date";

    public Notes() {

        mId = UUID.randomUUID();
        mDate = new Date();

    }

    public UUID getId() {
        return mId;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getTitle() {

        return mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject josn = new JSONObject();
        josn.put(JSON_ID, mId.toString());
        josn.put(JSON_TITLE, mTitle);
        josn.put(JSON_SOLVED, mSolved);
        josn.put(JSON_DATE, mDate.toString());
        return josn;
    }
}
