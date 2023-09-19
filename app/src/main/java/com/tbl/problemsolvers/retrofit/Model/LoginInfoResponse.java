package com.tbl.problemsolvers.retrofit.Model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginInfoResponse implements Serializable, Parcelable
{

    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Result")
    @Expose
    private Result result;
    public final static Creator<LoginInfoResponse> CREATOR = new Creator<LoginInfoResponse>() {


        public LoginInfoResponse createFromParcel(android.os.Parcel in) {
            return new LoginInfoResponse(in);
        }

        public LoginInfoResponse[] newArray(int size) {
            return (new LoginInfoResponse[size]);
        }

    }
    ;
    private final static long serialVersionUID = 7010508970141829805L;

    @SuppressWarnings({
        "unchecked"
    })
    protected LoginInfoResponse(android.os.Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.result = ((Result) in.readValue((Result.class.getClassLoader())));
    }

    public LoginInfoResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(result);
    }

    public int describeContents() {
        return  0;
    }

}
