package com.microservices.loans.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
        name = "Loan",
        description = "Schema to hold loan information"
)
public class LoanDto {

    @NotEmpty(message = "mobile number cannot  be null or empty")
    @Pattern( regexp = "(^$|[0-9]{10})" , message = "Mobile number must be 10 digits")
    @Schema(
    description = "Mobile number of customer" , example = "6789098876"
    )
    private String mobileNumber;

    @NotEmpty(message = "loan number cannot  be null or empty")
    @Pattern( regexp = "(^$|[0-9]{12})" , message = "Mobile number must be 12 digits")
    @Schema(
            description = "Mobile number of customer" , example = "678909768756"
    )

    private String loanNumber;

    @NotEmpty(message = "LoanType can not be a null or empty")
    @Schema(
            description = "Type of the loan", example = "Home Loan"
    )
    private String loanType;


    @Positive(
            message = "Total loan amount should be greater than zero"
    )
    @Schema(
            description = "Total loan amount" , example = "10000"
    )
    private int totalLoan;

    @PositiveOrZero(
            message = "Total loan amount paid should be greater than or equal to zero"
    )
   @Schema(
           description = "Total loan amount paid"  , example = "1000"
   )
    private int amountPaid;

    @PositiveOrZero(
            message = "Total outstanding amount should be greater than or equal to zero"
    )
    @Schema(
            description = "Total outstanding amount"  , example = "9000"
    )

    private int outstandingAmount;

}
