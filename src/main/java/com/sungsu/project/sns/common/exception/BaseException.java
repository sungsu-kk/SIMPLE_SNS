package com.sungsu.project.sns.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;

    public BaseException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

}
