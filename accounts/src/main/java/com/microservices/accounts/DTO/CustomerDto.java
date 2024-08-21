package com.microservices.accounts.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name="Customer",
        description = "Schema to hold Customer and account info"
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer",
            example="John Doe"
    )
    @NotEmpty(message="Name cannot be null or empty")
    @Size(min=5,max=30 ,message="The length of the customer name should be between 5 and 30")
    private String name;

    @Schema(
            description = "Email of the customer",
            example="john.doe@gmail.com"
    )
    @NotEmpty(message="Email cannot be null or empty")
    @Email(message="Email address should be in valid format")
    private String email;

    @Schema(
            description = "Ten digit mobile number of the customer",
            example="6238633456"
    )
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "AccountDetails of the customer"

    )
    AccountsDto accountsDto;
}
