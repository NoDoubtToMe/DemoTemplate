package katanskiy.DemoTemplate.Dto;

import katanskiy.DemoTemplate.Entities.BalanceType;
import katanskiy.DemoTemplate.Entities.User;

public class BalanceResponce {
    private Integer cash;
    private BalanceType balanceType;

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
}
