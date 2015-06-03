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

    /**
     * 构建带JSONObject参数的Notes构造方法，
     * 用于启动应用时从文件中读取JSON数据
     *
     * @param json
     * @throws JSONException
     */

    public Notes(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        mTitle = json.getString(JSON_TITLE);
        mSolved = json.getBoolean(JSON_SOLVED);
        mDate = new Date(json.getLong(JSON_DATE));
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

    /**
     * 用于将Notes数据打包成JSON数据格式。
     *
     * @return JSONObject
     * @throws JSONException
     */
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_TITLE, mTitle);
        json.put(JSON_SOLVED, mSolved);
        json.put(JSON_DATE, mDate.getTime());
        return json;
    }
}
