package com.microservices.loans.Service.Impl;

import com.microservices.loans.Constants.LoansConstants;
import com.microservices.loans.DTO.LoanDto;
import com.microservices.loans.Entity.Loans;
import com.microservices.loans.Exception.LoanAlreadyExistException;
import com.microservices.loans.Exception.ResourceNotFoundException;
import com.microservices.loans.Mapper.LoansMapper;
import com.microservices.loans.Repository.LoansRepository;
import com.microservices.loans.Service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {
   private  LoansRepository loansRepository;
    @Override
    public void createLoan(String mobNumber) {
        Optional<Loans> loanOptional = loansRepository.findByMobileNumber(mobNumber);
        if(loanOptional.isPresent())throw new LoanAlreadyExistException(String.format("A loan has already been assigned to the user %s  mobile number",mobNumber));
        loansRepository.save(createNewLoan(mobNumber));

    }

    @Override
    public LoanDto fetchLoanDetails(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan" , "mobileNumber" , mobileNumber)
        );
        return LoansMapper.mapToLoansDto(loans,new LoanDto());
    }

    @Override
    public boolean updateLoan(LoanDto loanDto) {
        Loans loans = loansRepository.findByLoanNumber(loanDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan" , "loanNumber" , loanDto.getLoanNumber())
        );
        LoansMapper.mapToLoans(loanDto,loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan","mobileNumber",mobileNumber)
        );
        loansRepository.delete(loans);
        return  true;
    }

    private Loans createNewLoan(String mobileNumber){
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }
}
