package com.alfath.warnet.service;

import com.alfath.warnet.entity.Customer;
import com.alfath.warnet.model.response.CustomerResponse;
import org.springframework.data.domain.Page;

public interface CustomerService {
    Customer create(Customer customer);
    Page<CustomerResponse> getAllCustomer(Integer page, Integer size);
    void deleteCustomerById(String id);

    Customer getCustomerById(String id);
}
