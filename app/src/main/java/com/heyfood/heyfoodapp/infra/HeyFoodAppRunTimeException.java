package com.heyfood.heyfoodapp.infra;

public class HeyFoodAppRunTimeException extends RuntimeException{
    public HeyFoodAppRuntimeException(String msg) {
        super(msg);
    }
    public HeyFoodAppRuntimeException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
