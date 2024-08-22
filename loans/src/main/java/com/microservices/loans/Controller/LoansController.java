package com.microservices.loans.Controller;

import com.microservices.loans.Constants.LoansConstants;
import com.microservices.loans.DTO.ErrorResponseDto;
import com.microservices.loans.DTO.LoanDto;
import com.microservices.loans.DTO.ResponseDto;
import com.microservices.loans.Mapper.LoansMapper;
import com.microservices.loans.Service.LoanService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@Validated
@Tag(
       name = "CRUD REST APIs for Loans in EazyBank",
        description = "CRUD Rest APIs in EazyBank to CREATE , READ , UPDATE , FETCH and DELETE loan details"
)
@RequestMapping(path ="/api" , produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class LoansController {
    private LoanService loanService;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam
                                                  @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                      String mobNumber){
  loanService.createLoan(mobNumber);
  return ResponseEntity.status(HttpStatus.CREATED).body(
          new ResponseDto(LoansConstants.STATUS_201,LoansConstants.MESSAGE_201)
  );

    }

    @GetMapping(value = "/fetch")
    public ResponseEntity<LoanDto> fetchLoanDetails(@RequestParam
                                                            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                            String mobileNumber){
        LoanDto loansDto = loanService.fetchLoanDetails(mobileNumber);
        return new ResponseEntity<>(loansDto,HttpStatus.OK);
    }

    @PutMapping(value="/update")
    public ResponseEntity<ResponseDto> updateLoanDetails(@Valid @RequestBody LoanDto loanDto){
        boolean isUpdated = loanService.updateLoan(loanDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200)
            );
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                new ResponseDto(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_UPDATE)
        );
    }

    @DeleteMapping(value="/delete")
    public ResponseEntity<ResponseDto> deleteLoan(@RequestParam
                                                      @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                  String mobileNumber){

        boolean isDeleted = loanService.deleteLoan(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto(
                            LoansConstants.STATUS_200 , LoansConstants.MESSAGE_200
                    )
            );
        }
         return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED
         ).body(
                new ResponseDto(
                        LoansConstants.STATUS_417 , LoansConstants.MESSAGE_417_DELETE
                )
        );
    }



}
