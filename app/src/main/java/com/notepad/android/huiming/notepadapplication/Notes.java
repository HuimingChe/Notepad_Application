package com.notepad.android.huiming.notepadapplication;

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
}
