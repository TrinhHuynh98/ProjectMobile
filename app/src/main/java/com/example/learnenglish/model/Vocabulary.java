package com.example.learnenglish.model;

public class Vocabulary {
    private int mID;
    private String mName;
    private String mDefine;
    private String mDescription;
    private String mExample;
    private String mSyn;

    public Vocabulary() {

    }

    public Vocabulary(int mID, String mName, String mDefine, String mDescription, String mExample, String mSyn) {
        this.mID = mID;
        this.mName = mName;
        this.mDefine = mDefine;
        this.mDescription = mDescription;
        this.mExample = mExample;
        this.mSyn = mSyn;
    }

    public Vocabulary(String mName, String mDefine, String mDescription, String mExample, String mSyn) {
        this.mName = mName;
        this.mDefine = mDefine;
        this.mDescription = mDescription;
        this.mExample = mExample;
        this.mSyn = mSyn;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDefine() {
        return mDefine;
    }

    public void setmDefine(String mDefine) {
        this.mDefine = mDefine;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmExample() {
        return mExample;
    }

    public void setmExample(String mExample) {
        this.mExample = mExample;
    }

    public String getmSyn() {
        return mSyn;
    }

    public void setmSyn(String mSyn) {
        this.mSyn = mSyn;
    }
}

