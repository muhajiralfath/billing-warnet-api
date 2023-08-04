package com.alfath.warnet.service;

import com.alfath.warnet.entity.Customer;
import com.alfath.warnet.model.CustomerResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    Page<CustomerResponse> getAllCustomer(Integer page, Integer size);
    void deleteCustomerById(String id);

    Customer getCustomerById(String id);
}
