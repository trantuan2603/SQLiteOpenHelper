package com.landsoft.sqliteopenhelper.Model;

/**
 * Created by TRANTUAN on 18-Nov-17.
 */

public class Students {
    private int mId;
    private String mName;
    private String mPhone;
    private String mAddress;
    private String mEmail;

    public Students() {
    }

    public Students(String mName, String mPhone, String mAddress, String mEmail) {
        this.mName = mName;
        this.mPhone = mPhone;
        this.mAddress = mAddress;
        this.mEmail = mEmail;
    }

    public Students(int mId, String mName, String mPhone, String mAddress, String mEmail) {
        this.mId = mId;
        this.mName = mName;
        this.mPhone = mPhone;
        this.mAddress = mAddress;
        this.mEmail = mEmail;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}
