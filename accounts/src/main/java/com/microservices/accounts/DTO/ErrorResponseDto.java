package com.microservices.accounts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "Error response",
        description = "Schema to hold error response info"

)
public class ErrorResponseDto {


    @Schema(

            description = "API path invoked by client"

    )
    private String apiPath;

    @Schema(

            description = "Error code representing the error",

            example = "500"

    )
    private HttpStatus errorCode;

    @Schema(

            description = "Error msg representing the error",

            example = "Internal Server Error"

    )
    private String errorMsg;

    @Schema(

            description = "Local time at which error was thrown"

    )
    private LocalDateTime errorTime;
}
