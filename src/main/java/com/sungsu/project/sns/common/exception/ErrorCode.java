package com.sungsu.project.sns.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    ERROR_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid Token"),
    ERROR_USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User Not Found"),
    ERROR_INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid Password"),
    ERROR_DUPLICATED_USER(HttpStatus.CONFLICT, "Duplicated User"),
    ERROR_INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "Invalid Permission"),
    ERROR_DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Database Error"),
    ERROR_NOTIFICATION_CONNECT(HttpStatus.INTERNAL_SERVER_ERROR, "Notification Connect Error"),
    ERROR_INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Invalid Request"),;
    private final HttpStatus status;
    private final String message;

}
