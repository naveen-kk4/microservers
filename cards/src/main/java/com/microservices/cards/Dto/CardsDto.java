package com.microservices.cards.Dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
        name = "Cards",
        description = "Schema to hold cards information"
)
@Data
public class CardsDto {

    @NotEmpty(message = "Mobile number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})" , message = "Mobile number must be 10 digits")
    @Schema(
            description = "Mobile number of customer",
            example = "7896754677"
    )
    private String mobileNumber;

    @NotEmpty(message = "Card number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})" , message = "Mobile number must be 12 digits")
    @Schema(
            description = "Card number of customer",
            example = "789675467789"
    )
    private String cardNumber;

    @NotEmpty(message = "Card type cannot be null or empty")
    @Schema(
            description = "Type of card",
            example = "Debit card"
    )
    private String cardType;

    @Positive(message = "Limit of card has to be greater than zero")
    @Schema(
            description = "Total amount available against a card",
            example = "100000"
    )
    private int  totalLimit;

    @PositiveOrZero(message = "Total amount used has to be greater than or equal to zero")
    @Schema(
            description = "Total amount used against a card",
            example = "1000"
    )
    private int amountUsed;

    @PositiveOrZero(message = "Total amount available has to be greater than or equal to zero")
    @Schema(
            description = "Total balance available against a card",
            example = "1000"
    )
    private int  availableAmount ;

}
