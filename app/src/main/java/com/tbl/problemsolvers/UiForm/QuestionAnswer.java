
package com.tbl.problemsolvers.UiForm;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class QuestionAnswer implements Serializable, Parcelable
{

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Question")
    @Expose
    private String question;
    @SerializedName("Answer")
    @Expose
    private String answer;
    @SerializedName("AnswerType")
    @Expose
    private String answerType;
    @SerializedName("AnswerTypeInfo")
    @Expose
    private List<String> answerTypeInfo = null;
    public final static Creator<QuestionAnswer> CREATOR = new Creator<QuestionAnswer>() {


        @SuppressWarnings({
            "unchecked"
        })
        public QuestionAnswer createFromParcel(android.os.Parcel in) {
            return new QuestionAnswer(in);
        }

        public QuestionAnswer[] newArray(int size) {
            return (new QuestionAnswer[size]);
        }

    }
    ;
    private final static long serialVersionUID = -6420828421456564797L;

    protected QuestionAnswer(android.os.Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.question = ((String) in.readValue((String.class.getClassLoader())));
        this.answer = ((String) in.readValue((String.class.getClassLoader())));
        this.answerType = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.answerTypeInfo, (String.class.getClassLoader()));
    }

    public QuestionAnswer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public List<String> getAnswerTypeInfo() {
        return answerTypeInfo;
    }

    public void setAnswerTypeInfo(List<String> answerTypeInfo) {
        this.answerTypeInfo = answerTypeInfo;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(question);
        dest.writeValue(answer);
        dest.writeValue(answerType);
        dest.writeList(answerTypeInfo);
    }

    public int describeContents() {
        return  0;
    }

}
