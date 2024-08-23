package com.microservices.cards.Controller;


import com.microservices.cards.Constants.CardsConstants;
import com.microservices.cards.Dto.CardsDto;
import com.microservices.cards.Dto.ErrorResponseDto;
import com.microservices.cards.Dto.ResponseDto;
import com.microservices.cards.Service.CardsService;
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

import java.awt.*;

@RestController
@Tag(
        name = "CRUD REST APIs for card in EazyBank",
        description = "REST API collection in EazyBank to do  Create , Read , Update , Delete card details"
)
@AllArgsConstructor
@Validated
@RequestMapping(path="/api" , produces = {MediaType.APPLICATION_JSON_VALUE})
public class CardsController {

    private CardsService cardsService;

    @PostMapping("/create")
    @Operation(
            summary = "Create card REST API",
            description = "REST API to create new  card under a mobile number inside EazyBank"

    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "201",
                            description = "HTTP Status created"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Http Status Internal Server Error",
                            content=@Content(schema = @Schema(implementation = ErrorResponseDto.class))
                    )
            }
    )
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber){
        cardsService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto(CardsConstants.STATUS_201,CardsConstants.MESSAGE_201)
        );
    }


    @Operation(
            summary = "Fetch card details REST API",
            description = "REST API to fetch card details based on mobile number"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Http Status OK"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Http Status Internal Server  Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                    )
                    )
            }
    )
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@Valid @RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber){
        CardsDto cardsDto = cardsService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @Operation(
            summary = "Update Card details REST API",
            description = "REST API to update  card details based on card number"
    )
    @ApiResponses(
            {
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
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardsDto cardsDto){
        boolean isUpdated = cardsService.updateCard(cardsDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200)
            );
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                new ResponseDto(CardsConstants.STATUS_417,CardsConstants.MESSAGE_417_UPDATE)
        );

    }

    @Operation(
            summary = "Delete Card Details REST API",
            description = "REST API to delete card details"

    )
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
                    description = "Http status Internal Server Error",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    })

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCard(@Valid @RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber){

         boolean isDeleted = cardsService.deleteCard(mobileNumber);
         if(isDeleted){
             return ResponseEntity.status(HttpStatus.OK).body(
                     new ResponseDto(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200)
             );
         }
         return  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                 new ResponseDto(CardsConstants.STATUS_417,CardsConstants.MESSAGE_417_DELETE)
         );

    }


}
