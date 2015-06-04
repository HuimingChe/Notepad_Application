package com.notepad.android.huiming.notepadapplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

/**
 * Created by huiming on 15-5-28.
 */
public class Notes {
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_SOLVED = "solved";
    private static final String JSON_DATE = "date";
    private static final String JSON_PHOTO = "photo";

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private Photo mPhoto;

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
        if (json.has(JSON_PHOTO)){
            mPhoto = new Photo(json.getJSONObject(JSON_PHOTO));
        }
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

    public Photo getPhoto(){
        return mPhoto;
    }

    public void setPhoto(Photo photo){
        mPhoto = photo;
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
        if (mPhoto != null){
            json.put(JSON_PHOTO,mPhoto.toJSON());
        }
        return json;
    }
}
