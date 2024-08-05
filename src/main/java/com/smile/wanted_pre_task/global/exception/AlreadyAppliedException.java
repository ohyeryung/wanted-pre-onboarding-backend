package com.smile.wanted_pre_task.global.exception;

public class AlreadyAppliedException extends RuntimeException {
    public AlreadyAppliedException() {
        super(ResponseMessage.ALREADY_APPLIED);
    }
}