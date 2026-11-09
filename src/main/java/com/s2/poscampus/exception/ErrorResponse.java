package com.s2.poscampus.exception;

import java.time.LocalDateTime;

public record ErrorResponse
        (LocalDateTime timestamp, int status, String message, String errorCode){
}