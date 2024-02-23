package com.actuator.metrics.exception;


import com.actuator.metrics.constant.Constants;
import com.actuator.metrics.exception.type.ProductNotFoundException;
import com.actuator.metrics.exception.type.QuantityExceedException;
import com.actuator.metrics.exception.type.UnableToSaveProductException;
import com.actuator.metrics.model.json.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppResponse> handlerException(Exception ex) {
        AppResponse response = new AppResponse();
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof ProductNotFoundException) {
            response.setAppResponseCode(Constants.PRODUCT_NOT_FOUND_ERROR_CODE);
            response.setAppMessageCode(Constants.PRODUCT_NOT_FOUND_MESSAGE_CODE);
            response.setDescription(ex.getMessage());
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (ex instanceof UnableToSaveProductException) {
            response.setAppResponseCode(Constants.UNABLE_TO_SAVE_PRODUCT_ERROR_CODE);
            response.setAppMessageCode(Constants.UNABLE_TO_SAVE_PRODUCT_MESSAGE_CODE);
            response.setDescription(ex.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }else if (ex instanceof QuantityExceedException){
            response.setAppResponseCode(Constants.QUANTITY_EXCEED_ERROR_CODE);
            response.setAppMessageCode(Constants.QUANTITY_EXCEED_MESSAGE_CODE);
            response.setDescription(ex.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(response, httpStatus);
    }
}
