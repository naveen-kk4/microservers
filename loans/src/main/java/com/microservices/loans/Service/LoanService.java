package com.microservices.loans.Service;

import com.microservices.loans.DTO.LoanDto;

public interface LoanService {

    public void createLoan(String mobNumber);

    LoanDto fetchLoanDetails(String mobileNumber);

    boolean updateLoan(LoanDto loanDto);

    boolean deleteLoan(String mobileNumber);
}
