package ru.netology.kriger;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class LoanOperation extends Operation implements ConsolePrintable {
    private int loanId;

    public LoanOperation(int id, int sum, String currency, String merchant, int loanId) {
        super(id, sum, currency, merchant);
        this.loanId = loanId;
    }

    @Override
    public void printToConsole() {
        System.out.println("LoanOperation Information: ");
        System.out.println("Id: " + getId());
        System.out.println("loanId: " + loanId);
        System.out.println("sum: " + getSum());
        System.out.println("currency: " + getCurrency());
        System.out.println("merchant: " + getMerchant());
    }
}
