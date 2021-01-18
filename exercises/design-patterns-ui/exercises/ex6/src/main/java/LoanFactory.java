import java.util.Date;

public class LoanFactory {
    public Loan getTermLoan(double commitment, int riskTaking, Date maturity){
        return new Loan( commitment, riskTaking,maturity);
    }
    public Loan getRevolverLoan(double commitment, int riskTaking, Date maturity, Date expiry){
        return new Loan(commitment,riskTaking,maturity,expiry);
    }

    public Loan getRCTLLoan(CapitalStrategy capitalStrategy, double commitment, int riskTaking, Date maturity, Date expiry){
        return new Loan(capitalStrategy,commitment,riskTaking,maturity,expiry);
    }

}
