package com.auth.tokenserver.payloadManager;

import com.auth.tokenserver.model.CustomUser;
import com.auth.tokenserver.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserPayload extends PayloadHandler<UserDTO, CustomUser> {

    @Override
    public ResponseEntity<?> buildPayload(HttpStatus status, boolean isList) {
        return isList ? new ResponseEntity<>(getBodyList(), status) :
                new ResponseEntity<>(getSingleBody(), status);
    }

    @Override
    public void mapToDTO(List<CustomUser> bodyList) {
        List<UserDTO> dtoList = bodyList
                .stream().map(item -> new UserDTO(item.getId(), item.getUsername()))
                .collect(Collectors.toList());
        this.setBodyList(dtoList);
    }


}
