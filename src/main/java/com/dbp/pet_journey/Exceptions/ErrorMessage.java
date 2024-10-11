package com.dbp.pet_journey.Exceptions;

import lombok.Data;

@Data
public class ErrorMessage {
    private String message;
    private String status;

    public ErrorMessage(String message, String status) {
        this.message = message;
        this.status = status;
    }
}
