package com.alfath.warnet.controller;

import com.alfath.warnet.entity.Computer;
import com.alfath.warnet.model.response.CommonResponse;
import com.alfath.warnet.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/computers")
public class ComputerController {
    private final ComputerService computerService
    @PostMapping
    public ResponseEntity<?> createComputer(@RequestBody Computer computer){
        computerService.createPc(computer);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully create computer")
                        .data(computer)
                        .build()
                );
    }

}
