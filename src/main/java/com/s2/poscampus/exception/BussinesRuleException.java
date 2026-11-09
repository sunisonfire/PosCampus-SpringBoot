package com.s2.poscampus.exception;

public class BussinesRuleException extends RuntimeException{
    public BussinesRuleException(String mensaje){
        super(mensaje);
    }
}