package com.alfath.warnet.service.impl;

import com.alfath.warnet.entity.Customer;
import com.alfath.warnet.model.response.CustomerResponse;
import com.alfath.warnet.repository.CustomerRepository;
import com.alfath.warnet.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public Customer create(Customer customer) {
        try {
          return customerRepository.save(customer);
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "computer already exist");
        }
    }

    @Override
    public Page<CustomerResponse> getAllCustomer(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customers = customerRepository.findAllCustomersWithPagination(pageable);

        List<Customer> customerList = new ArrayList<>(customers.getContent());
        List<CustomerResponse> customerResponses = customerList.stream().map(customer -> CustomerResponse.builder()
                .name(customer.getName())
                .build()).collect(Collectors.toList());
        return new PageImpl<>(customerResponses, pageable, customers.getTotalElements());
    }

    @Override
    public void deleteCustomerById(String id) {
        getCustomerById(id);
        customerRepository.deleteCustomerById(id);
    }

    @Override
    public Customer getCustomerById(String id) {
        return customerRepository.findCustomerById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Computer not found"));
    }
}
