package com.chelex.phonebook.util.exception;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Error {

    private ErrorDetails errorDetails;

    @Data
    public static class ErrorDetails {
        private String id = UUID.randomUUID().toString();
        private int status;
        private String exceptionReason;
        private Date exceptionTime = new Date();

        public ErrorDetails(int exStatus, String exReason) {
            this.status = exStatus;
            this.exceptionReason = exReason;
        }
    }
}
