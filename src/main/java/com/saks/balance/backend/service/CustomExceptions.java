package com.saks.balance.backend.service;

public class CustomExceptions{

    /**
     * DB에 데이터를 조회하였는데 존재하지 않는 경우 Exception -> 404 Not Found
     */
    public static class NoEntityException extends RuntimeException{
        public NoEntityException(String message){
            super(message);
        }
    }
}