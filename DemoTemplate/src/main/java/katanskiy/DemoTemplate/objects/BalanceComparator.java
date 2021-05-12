package katanskiy.DemoTemplate.objects;

import katanskiy.DemoTemplate.Dto.BalanceRequest;

import java.util.Comparator;

public class BalanceComparator implements Comparator<BalanceRequest> {
    public int compare(BalanceRequest a, BalanceRequest b){
        return b.getDate().compareTo(a.getDate());
    }
}
