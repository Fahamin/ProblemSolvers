package com.tbl.problemsolvers.LeaderBoard;

public class LeaderBordModel {
    String psrName;
    String earnCoinNum;

    public LeaderBordModel(String psrName, String earnCoinNum) {
        this.psrName = psrName;
        this.earnCoinNum = earnCoinNum;
    }

    public String getPsrName() {
        return psrName;
    }

    public void setPsrName(String psrName) {
        this.psrName = psrName;
    }

    public String getEarnCoinNum() {
        return earnCoinNum;
    }

    public void setEarnCoinNum(String earnCoinNum) {
        this.earnCoinNum = earnCoinNum;
    }
}
