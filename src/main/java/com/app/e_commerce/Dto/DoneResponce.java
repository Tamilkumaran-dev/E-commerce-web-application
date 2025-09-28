package com.app.e_commerce.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoneResponce {


    private String message;
    private boolean statusBoolean;
    private Boolean isException = false;

    public DoneResponce(String message, Boolean status){
        this.message = message;
        this.statusBoolean = status;
    }

}
