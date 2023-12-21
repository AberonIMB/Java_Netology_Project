package ru.netology.kriger.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.netology.kriger.ConsolePrintable;


@Data
@EqualsAndHashCode(callSuper = true)
public class LoanOperation extends Operation implements ConsolePrintable {
    private int loanId;

    public LoanOperation(int id, int sum, String currency, String merchant, int loanId, int customerId) {
        super(id, sum, currency, merchant, customerId);
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
        System.out.println("customerId: " + getCustomerId());
    }
}
