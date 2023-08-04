package com.alfath.warnet.controller;

import com.alfath.warnet.entity.Computer;
import com.alfath.warnet.model.request.ComputerRequest;
import com.alfath.warnet.model.response.CommonResponse;
import com.alfath.warnet.model.response.ComputerResponse;
import com.alfath.warnet.model.response.PagingResponse;
import com.alfath.warnet.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/computers")
public class ComputerController {
    private final ComputerService computerService;

    @PostMapping
    public ResponseEntity<?> createComputer(@RequestBody ComputerRequest computer) {
        computerService.createPc(computer);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully create computer")
                        .data(computer)
                        .build()
                );
    }

    @GetMapping
    public ResponseEntity<?> getAllComputer(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        Page<ComputerResponse> computers = computerService.getAllComp(page - 1, size);

        PagingResponse pagingResponse = PagingResponse.builder()
                .currentPage(page)
                .totalPage(computers.getTotalPages())
                .size(size)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all computers")
                        .data(computers.getContent())
                        .paging(pagingResponse)
                        .build());

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteComputerById(@PathVariable(name = "id")String id){
        computerService.deleteCompById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully delete computer")
                        .build()
                );
    }
    @PutMapping
    public ResponseEntity<?> updateComputer(@RequestBody Computer computer){
        ComputerResponse computerResponse = computerService.updateComputer(computer);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully update computer")
                        .data(computerResponse)
                        .build()
                );
    }

}
