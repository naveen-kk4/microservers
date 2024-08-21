package com.microservices.accounts.Controller;


import com.microservices.accounts.Constants.AccountsConstants;
import com.microservices.accounts.DTO.CustomerDto;
import com.microservices.accounts.DTO.ErrorResponseDto;
import com.microservices.accounts.DTO.ResponseDto;
import com.microservices.accounts.Service.AccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
//the produces parameter is very valuable as it tells the user what type of media the end point will return
//Spring will automatically set the Content-type header of the HTTP response to application/json
//by default it is set to json but still specifying it increases clarity
// refer ig - sunchitdudeja
@RequestMapping(path="/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
//now this will be the header for controller layer in OPEN API documentation
@Tag(
  name = "CRUD REST APIs for Accounts in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE,READ,UPDATE and DELETE account details"
        )
public class AccountsController {
    // here also we need not autowire as there is only a single all args constructor
    // and hence it follows constructor based DI
    private AccountsService accountsService;



    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create a new customer & account inside EazyBank"
    )
    // this ApiResponse annotation overrides the default response code generated for all apis
    // inside openAPI which is 200 and created

    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status Created"
            ), @ApiResponse(
            responseCode = "500",
            description = "Http Status Internal Server Error",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
           // anytime Customer already exists exception is thrown from the service layer
        /// then it automatically navigates to the global exception handler and does not come here
        // there a jackson libraries within Spring boot application which converts the raw json we are providing
        // into the POJO class and vice versa
            accountsService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

   @Operation(
        summary = "Fetch Account Details REST API",
        description = "REST API to fetch customer and account details based on account number"
   )

   @ApiResponses({
           @ApiResponse(
                   responseCode = "200",
                   description = "Http Status OK"
           ), @ApiResponse(
           responseCode = "500",
           description = "Http Status Internal Server Error",
           content = @Content(
                   schema =  @Schema(implementation = ErrorResponseDto.class)
           )
   )
   }

   )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                               String mobileNumber){
     CustomerDto customerDto = accountsService.fetchAccount(mobileNumber);
     return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update customer and account details based on account number"
    )
    // here we are specifying API response doc for positive and negative case
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation failed"
            ),
            @ApiResponse(
            responseCode = "500",
            description = "Http Status Internal Server Error",
            // this content keyword is important because:
            // by default the documentation shows only those schemas which are present within the controller layer
            // as this ErrorResponseDto is present within GlobalExceptionHandler it is important to explicitly mention it here
            content = @Content(
                    schema =  @Schema(implementation = ErrorResponseDto.class)
            )
    )
    }

    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdated = accountsService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));

        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).
                body(new ResponseDto(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_UPDATE));
    }

    @Operation(
            summary = "Delete Account Details REST API",
            description = "REST API to delete customer and account details based on account number"
    )
    // here we are specifying API response doc for positive and negative case
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK"
            ), @ApiResponse(
            responseCode = "500",
            description = "Http Status Internal Server Error",
            content = @Content(
                    schema =  @Schema(implementation = ErrorResponseDto.class)
            )
    ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation failed"
            )
    }

    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                         String mobileNumber){
          boolean isDeleted = accountsService.deleteAccount(mobileNumber);
          if(isDeleted){
              return ResponseEntity.status(HttpStatus.OK)
                      .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
          }
          return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                  .body(new ResponseDto(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_UPDATE));
    }

}
