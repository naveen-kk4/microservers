package com.microservices.loans.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(
        title = "Response",
        description = "Schema to hold successful  response info"
)

@AllArgsConstructor
public class ResponseDto {

    @Schema(
            description = "Success response code"
    )
    private String statusCode;

    @Schema(
            description = "Status message in the response code"
    )
    private String statusMsg;

}
