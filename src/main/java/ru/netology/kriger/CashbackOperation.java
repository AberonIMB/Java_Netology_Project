package ru.netology.kriger;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CashbackOperation extends Operation implements ConsolePrintable {
    private int cashbackAmount;

    public CashbackOperation(int id, int sum, String currency, String merchant, int cashbackAmount) {
        super(id, sum, currency, merchant);
        this.cashbackAmount = cashbackAmount;
    }

    @Override
    public void printToConsole() {
        System.out.println("CashbackOperation Information: ");
        System.out.println("Id: " + getId());
        System.out.println("sum: " + getSum());
        System.out.println("currency: " + getCurrency());
        System.out.println("merchant: " + getMerchant());
        System.out.println("cashbackAmount: " + cashbackAmount);
    }
}
