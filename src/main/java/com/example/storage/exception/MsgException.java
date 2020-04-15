package com.example.storage.exception;

/**
 * 本类是消息异常类
 * @author allen
 */
public class MsgException extends Exception{
    public MsgException(){
        super();
    }

    public MsgException(String msg){
        super(msg);
    }
}
