package com.dut.CinemaProject.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(){
        super();
    }
    public ItemNotFoundException(String message){
        super(message);
    }
}
