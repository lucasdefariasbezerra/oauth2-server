package com.auth.tokenserver.payloadManager;

import com.auth.tokenserver.model.CustomUser;
import com.auth.tokenserver.model.CustomUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserPayload extends GenericPayloadGenerator<CustomUserDTO, CustomUser> {

    @Override
    public ResponseEntity<?> buildSuccessPayload(HttpStatus status, boolean isList) {
        return isList ? new ResponseEntity<>(getResponseBodyList(), status) :
                new ResponseEntity<>(getResponseBodyObject(), status);
    }

    @Override
    public ResponseEntity<?> buildErrorPayload(HttpStatus status, String description) {
        return new ResponseEntity<>(getErrorMessage(description), status);
    }

    @Override
    public void mapToDTO(List<CustomUser> resultList) {
        List<CustomUserDTO> dtoList = resultList
                .stream().map(item -> new CustomUserDTO(item.getId(), item.getUsername()))
                .collect(Collectors.toList());
        this.setResponseBodyList(dtoList);
    }

    @Override
    public void mapToDTO(CustomUser resultObject) {
        CustomUserDTO dtoObject = new CustomUserDTO(resultObject.getId(), resultObject.getUsername());
        this.setResponseBodyObject(dtoObject);
    }
}
