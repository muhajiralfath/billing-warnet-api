package com.alfath.warnet.controller;

import com.alfath.warnet.entity.Customer;
import com.alfath.warnet.entity.Operator;
import com.alfath.warnet.model.response.CommonResponse;
import com.alfath.warnet.service.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/operator")
public class OperatorController {
    private final OperatorService operatorService;
    @PostMapping
    public ResponseEntity<?> createOperator(@RequestBody Operator operator){
        operatorService.create(operator);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully create operator")
                        .data(operator)
                        .build()
                );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteOperatorById(@PathVariable(name = "id")String id){
        operatorService.deleteOperatorById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully delete operator")
                        .build()
                );
    }
}
