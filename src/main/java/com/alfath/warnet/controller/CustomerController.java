package com.alfath.warnet.controller;

import com.alfath.warnet.entity.Customer;
import com.alfath.warnet.model.response.CommonResponse;
import com.alfath.warnet.model.response.ComputerResponse;
import com.alfath.warnet.model.response.CustomerResponse;
import com.alfath.warnet.model.response.PagingResponse;
import com.alfath.warnet.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer){
        customerService.create(customer);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully create customer")
                        .data(customer)
                        .build()
                );
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers (@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        Page<CustomerResponse> customer = customerService.getAllCustomer(page - 1, size);

        PagingResponse pagingResponse = PagingResponse.builder()
                .currentPage(page)
                .totalPage(customer.getTotalPages())
                .size(size)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all customers")
                        .data(customer.getContent())
                        .paging(pagingResponse)
                        .build());

    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteComputerById(@PathVariable(name = "id")String id){
        customerService.deleteCustomerById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully delete customers")
                        .build()
                );
    }
}
