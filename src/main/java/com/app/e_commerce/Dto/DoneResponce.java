package com.app.e_commerce.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoneResponce {


    @Schema(name = "message", description = "pass the message", defaultValue = "success")
    private String message;
    @Schema(name = "statusBoolean", description = "pass a boolean for chack is the response is success", defaultValue = "true")
    private boolean statusBoolean;
    @Schema(name = "isException", description = "pass a boolean for chack is the response is failed", defaultValue = "false")
    private Boolean isException = false;

    public DoneResponce(String message, Boolean status){
        this.message = message;
        this.statusBoolean = status;
    }

}
