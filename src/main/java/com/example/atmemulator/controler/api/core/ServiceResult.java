package com.example.atmemulator.controler.api.core;

import com.example.atmemulator.controler.api.error.api.ApiError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResult<T> {
    private Integer code;
    private T data;
    private List<T> dataList;
    private String message;
    private ApiError apiError;

    public static <T> ServiceResult<T> Success(T data){
        return new ServiceResult<>(
                ErrorStatus.SUCCESS.getCode(),
                data,
                null,
                "SUCCESS",
                null
        );
    }

    public static <T> ServiceResult<T> Success(List<T> dataList){
        return new ServiceResult<>(
                ErrorStatus.SUCCESS.getCode(),
                null,
                dataList,
                "SUCCESS",
                null
        );
    }

    public static <T> ServiceResult<T> Success(){
        return new ServiceResult<>(
                ErrorStatus.SUCCESS.getCode(),
                null,
                null,
                "SUCCESS",
                null
        );
    }

    public static <T> ServiceResult<T> notFound(T data){
        return new ServiceResult<>(
                ErrorStatus.NOT_FOUND.getCode(),
                data,
                null,
                "NOT_FOUND",
                null
        );
    }

    public static <T> ServiceResult<T> fail(HttpStatus httpStatus,ErrorStatus errorStatus){
        return new ServiceResult<>(
                errorStatus.getCode(),
                null,
                null,
                errorStatus.getMessage(),
                new ApiError(httpStatus,errorStatus.getMessage())
        );
    }

    public static <T> ServiceResult<Void> fail(ApiError apiError){
        return new ServiceResult<>(
                apiError.getHttpStatus().value(),
                null,
                null,
                apiError.getMessage(),
                apiError
        );
    }

}
