package com.auth.tokenserver.payloadManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class PayloadHandler<T, E> {

    protected String message;
    protected List<T> bodyList;
    T singleBody;

    public abstract ResponseEntity<?> buildPayload(HttpStatus status, boolean isList);
    public abstract void mapToDTO(List<E> bodyList);

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getBodyList() {
        return bodyList;
    }

    public void setBodyList(List<T> bodyList) {
        this.bodyList = bodyList;
    }

    public T getSingleBody() {
        return singleBody;
    }

    public void setSingleBody(T singleBody) {
        this.singleBody = singleBody;
    }
}
