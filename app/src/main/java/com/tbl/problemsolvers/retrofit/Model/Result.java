package com.tbl.problemsolvers.retrofit.Model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Result implements Serializable, Parcelable {

    @SerializedName("PSRid")
    @Expose
    private Integer pSRid;
    @SerializedName("Emp_code")
    @Expose
    private String empCode;
    @SerializedName("PSRName")
    @Expose
    private String pSRName;
    @SerializedName("DBId")
    @Expose
    private Integer dBId;
    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("DBName")
    @Expose
    private String dBName;
    @SerializedName("AppVersion")
    @Expose
    private Integer appVersion;
    @SerializedName("survay")
    @Expose
    private Integer survay;
    @SerializedName("ActivePromo")
    @Expose
    private String activePromo;
    @SerializedName("ActiveQr")
    @Expose
    private Integer activeQr;
    @SerializedName("PsswordExpire")
    @Expose
    private Integer psswordExpire;
    @SerializedName("Token")
    @Expose
    private String token;
    @SerializedName("LoginTime")
    @Expose
    private String loginTime;
    @SerializedName("LoginDistance")
    @Expose
    private String loginDistance;
    @SerializedName("TodaysTarget")
    @Expose
    private Integer todaysTarget;
    @SerializedName("TodaysAch")
    @Expose
    private Integer todaysAch;
    @SerializedName("Image")
    @Expose
    private String image;
    @SerializedName("TodayDate")
    @Expose
    private String todayDate;
    public final static Creator<Result> CREATOR = new Creator<Result>() {


        public Result createFromParcel(android.os.Parcel in) {
            return new Result(in);
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    };
    private final static long serialVersionUID = 3363920789824930097L;

    @SuppressWarnings({
            "unchecked"
    })
    protected Result(android.os.Parcel in) {
        this.pSRid = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.empCode = ((String) in.readValue((String.class.getClassLoader())));
        this.pSRName = ((String) in.readValue((String.class.getClassLoader())));
        this.dBId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.mobileNo = ((String) in.readValue((String.class.getClassLoader())));
        this.dBName = ((String) in.readValue((String.class.getClassLoader())));
        this.appVersion = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.survay = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.activePromo = ((String) in.readValue((String.class.getClassLoader())));
        this.activeQr = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.psswordExpire = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.token = ((String) in.readValue((String.class.getClassLoader())));
        this.loginTime = ((String) in.readValue((String.class.getClassLoader())));
        this.loginDistance = ((String) in.readValue((String.class.getClassLoader())));
        this.todaysTarget = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.todaysAch = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.todayDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Result() {
    }

    public Integer getPSRid() {
        return pSRid;
    }

    public void setPSRid(Integer pSRid) {
        this.pSRid = pSRid;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getPSRName() {
        return pSRName;
    }

    public void setPSRName(String pSRName) {
        this.pSRName = pSRName;
    }

    public Integer getDBId() {
        return dBId;
    }

    public void setDBId(Integer dBId) {
        this.dBId = dBId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getDBName() {
        return dBName;
    }

    public void setDBName(String dBName) {
        this.dBName = dBName;
    }

    public Integer getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(Integer appVersion) {
        this.appVersion = appVersion;
    }

    public Integer getSurvay() {
        return survay;
    }

    public void setSurvay(Integer survay) {
        this.survay = survay;
    }

    public String getActivePromo() {
        return activePromo;
    }

    public void setActivePromo(String activePromo) {
        this.activePromo = activePromo;
    }

    public Integer getActiveQr() {
        return activeQr;
    }

    public void setActiveQr(Integer activeQr) {
        this.activeQr = activeQr;
    }

    public Integer getPsswordExpire() {
        return psswordExpire;
    }

    public void setPsswordExpire(Integer psswordExpire) {
        this.psswordExpire = psswordExpire;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginDistance() {
        return loginDistance;
    }

    public void setLoginDistance(String loginDistance) {
        this.loginDistance = loginDistance;
    }

    public Integer getTodaysTarget() {
        return todaysTarget;
    }

    public void setTodaysTarget(Integer todaysTarget) {
        this.todaysTarget = todaysTarget;
    }

    public Integer getTodaysAch() {
        return todaysAch;
    }

    public void setTodaysAch(Integer todaysAch) {
        this.todaysAch = todaysAch;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(pSRid);
        dest.writeValue(empCode);
        dest.writeValue(pSRName);
        dest.writeValue(dBId);
        dest.writeValue(mobileNo);
        dest.writeValue(dBName);
        dest.writeValue(appVersion);
        dest.writeValue(survay);
        dest.writeValue(activePromo);
        dest.writeValue(activeQr);
        dest.writeValue(psswordExpire);
        dest.writeValue(token);
        dest.writeValue(loginTime);
        dest.writeValue(loginDistance);
        dest.writeValue(todaysTarget);
        dest.writeValue(todaysAch);
        dest.writeValue(image);
        dest.writeValue(todayDate);
    }

    public int describeContents() {
        return 0;
    }

}
