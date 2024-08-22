package com.microservices.loans.DTO;

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
            description = "Error code implying the error"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error msg describing the error"
    )
    private String errorMsg;

   @Schema(
        description = "Time representing the occurrence of the error" +
                ""
   )
    private LocalDateTime errorTime;
}
