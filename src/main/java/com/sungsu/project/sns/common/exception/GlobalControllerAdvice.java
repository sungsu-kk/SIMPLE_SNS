package com.sungsu.project.sns.common.exception;

import com.sungsu.project.sns.common.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({BaseException.class})
    public CommonResponse<?> baseErrorHandler(BaseException e){
        log.warn("[BaseException] errorMsg = {}", NestedExceptionUtils.getMostSpecificCause(e).getMessage());
        return CommonResponse.fail(e.getMessage(), e.getErrorCode().name());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public CommonResponse<?> methodArgumentNotValidErrorHandler(MethodArgumentNotValidException e){
        log.warn("[MethodArgumentNotValidException] errorMsg = {}", NestedExceptionUtils.getMostSpecificCause(e).getMessage());
        BindingResult bindingResult = e.getBindingResult();
        FieldError fe = bindingResult.getFieldError();
        if (fe != null) {
            String message = "Request Error" + " " + fe.getField() + "=" + fe.getRejectedValue() + " (" + fe.getDefaultMessage() + ")";
            return CommonResponse.fail(message, ErrorCode.ERROR_INVALID_REQUEST.name());
        } else {
            return CommonResponse.fail(ErrorCode.ERROR_INVALID_REQUEST.getMessage(), ErrorCode.ERROR_INVALID_REQUEST.name());
        }
    }

}
