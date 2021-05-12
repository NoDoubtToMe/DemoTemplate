package katanskiy.DemoTemplate.Dto;

import katanskiy.DemoTemplate.Entities.BalanceType;
import katanskiy.DemoTemplate.Entities.User;

public class BalanceRequest {
    private User user;
    private Integer cash;
    private BalanceType balanceType;
    private String date;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    public BalanceType getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(BalanceType balanceType) {
        this.balanceType = balanceType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
