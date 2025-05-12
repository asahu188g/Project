package com.project.app.service;

import java.util.List;

import com.project.app.request.CustomerRequest;
import com.project.app.response.CustomerResponse;

public interface ICustomerService 
{
    List<CustomerResponse> getAllCustomers();
    CustomerResponse getCustomerById(Long id);
    CustomerResponse createCustomer(CustomerRequest request);
    CustomerResponse updateCustomer(Long id, CustomerRequest request);
    void deleteCustomer(Long id);
}
