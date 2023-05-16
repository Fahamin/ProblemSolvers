
package com.tbl.problemsolvers.profile;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProfileData implements Serializable, Parcelable {

    @SerializedName("PsrId")
    @Expose
    private String psrId;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("PresentAddress")
    @Expose
    private String presentAddress;
    @SerializedName("PermanentAddress")
    @Expose
    private String permanentAddress;
    @SerializedName("FatherName")
    @Expose
    private String fatherName;
    @SerializedName("MotherName")
    @Expose
    private String motherName;
    @SerializedName("Personal Mobile")
    @Expose
    private String personalMobile;
    @SerializedName("OfficialMobile")
    @Expose
    private String officialMobile;
    @SerializedName("DOB")
    @Expose
    private String dob;
    @SerializedName("Religion")
    @Expose
    private String religion;
    @SerializedName("MaritalStatus")
    @Expose
    private String maritalStatus;
    @SerializedName("EmergencyContactNumber")
    @Expose
    private String emergencyContactNumber;
    @SerializedName("Image")
    @Expose
    private String image;
    public final static Creator<ProfileData> CREATOR = new Creator<ProfileData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProfileData createFromParcel(android.os.Parcel in) {
            return new ProfileData(in);
        }

        public ProfileData[] newArray(int size) {
            return (new ProfileData[size]);
        }

    };
    private final static long serialVersionUID = 4496374760017384178L;

    protected ProfileData(android.os.Parcel in) {
        this.psrId = ((String) in.readValue((String.class.getClassLoader())));
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.presentAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.permanentAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.fatherName = ((String) in.readValue((String.class.getClassLoader())));
        this.motherName = ((String) in.readValue((String.class.getClassLoader())));
        this.personalMobile = ((String) in.readValue((String.class.getClassLoader())));
        this.officialMobile = ((String) in.readValue((String.class.getClassLoader())));
        this.dob = ((String) in.readValue((String.class.getClassLoader())));
        this.religion = ((String) in.readValue((String.class.getClassLoader())));
        this.maritalStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.emergencyContactNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ProfileData() {
    }

    public String getPsrId() {
        return psrId;
    }

    public void setPsrId(String psrId) {
        this.psrId = psrId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getPersonalMobile() {
        return personalMobile;
    }

    public void setPersonalMobile(String personalMobile) {
        this.personalMobile = personalMobile;
    }

    public String getOfficialMobile() {
        return officialMobile;
    }

    public void setOfficialMobile(String officialMobile) {
        this.officialMobile = officialMobile;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(psrId);
        dest.writeValue(code);
        dest.writeValue(name);
        dest.writeValue(presentAddress);
        dest.writeValue(permanentAddress);
        dest.writeValue(fatherName);
        dest.writeValue(motherName);
        dest.writeValue(personalMobile);
        dest.writeValue(officialMobile);
        dest.writeValue(dob);
        dest.writeValue(religion);
        dest.writeValue(maritalStatus);
        dest.writeValue(emergencyContactNumber);
        dest.writeValue(image);
    }

    public int describeContents() {
        return 0;
    }

}
