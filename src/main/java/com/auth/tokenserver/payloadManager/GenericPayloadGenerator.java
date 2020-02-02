package com.auth.tokenserver.payloadManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class GenericPayloadGenerator<T, E> {

    protected List<T> responseBodyList;
    protected T responseBodyObject;
    private ObjectMapper objectMapper;

    public GenericPayloadGenerator() {
        objectMapper = new ObjectMapper();
    }

    public abstract ResponseEntity<?> buildSuccessPayload(HttpStatus status, boolean isList);

    public abstract ResponseEntity<?> buildErrorPayload(HttpStatus status, String errorDescription);

    public abstract void mapToDTO(List<E> resultList);

    public abstract void mapToDTO(E resultObject);

    public List<T> getResponseBodyList() {
        return responseBodyList;
    }

    public void setResponseBodyList(List<T> responseBodyList) {
        this.responseBodyList = responseBodyList;
    }

    public T getResponseBodyObject() {
        return responseBodyObject;
    }

    public void setResponseBodyObject(T responseBodyObject) {
        this.responseBodyObject = responseBodyObject;
    }

    public ObjectNode getErrorMessage(String description) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("description", description);
        return objectNode;
    }
}
