package com.microservices.loans.Mapper;

import com.microservices.loans.DTO.LoanDto;
import com.microservices.loans.Entity.Loans;

public final class LoansMapper {

    private LoansMapper(){};

    public static LoanDto mapToLoansDto(Loans loans , LoanDto loanDto){
        loanDto.setLoanNumber(loans.getLoanNumber());
        loanDto.setMobileNumber(loans.getMobileNumber());
        loanDto.setTotalLoan(loans.getTotalLoan());
        loanDto.setAmountPaid(loans.getAmountPaid());
        loanDto.setLoanType(loans.getLoanType());
        loanDto.setOutstandingAmount(loans.getOutstandingAmount());
        return loanDto;
    }
    public static Loans mapToLoans(LoanDto loanDto , Loans loan){
        loan.setLoanNumber(loanDto.getLoanNumber());
        loan.setLoanType(loanDto.getLoanType());
        loan.setMobileNumber(loanDto.getMobileNumber());
        loan.setTotalLoan(loanDto.getTotalLoan());
        loan.setAmountPaid(loanDto.getAmountPaid());
        loan.setOutstandingAmount(loanDto.getOutstandingAmount());
        return loan;
    }


}
