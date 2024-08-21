package com.microservices.accounts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold successfull response info "
)
public class ResponseDto {
    @Schema(

            description = "Response status code"


    )
    private String statusCode;

    @Schema(

            description = "Status message in the response"

    )
    private String statusMsg;


}
