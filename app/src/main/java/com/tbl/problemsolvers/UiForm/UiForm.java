
package com.tbl.problemsolvers.UiForm;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class UiForm implements Serializable, Parcelable
{

    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Result")
    @Expose
    private Result result;
    public final static Creator<UiForm> CREATOR = new Creator<UiForm>() {


        @SuppressWarnings({
            "unchecked"
        })
        public UiForm createFromParcel(android.os.Parcel in) {
            return new UiForm(in);
        }

        public UiForm[] newArray(int size) {
            return (new UiForm[size]);
        }

    }
    ;
    private final static long serialVersionUID = -3171226852672757468L;

    protected UiForm(android.os.Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.result = ((Result) in.readValue((Result.class.getClassLoader())));
    }

    public UiForm() {
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
