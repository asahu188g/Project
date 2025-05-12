package com.project.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.app.entity.Customer;
import com.project.app.repository.CustomerRepository;
import com.project.app.request.CustomerRequest;
import com.project.app.response.CustomerResponse;
import com.project.app.service.ICustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerResponse> getAllCustomers() {
        log.info("Fetching all customers");
        return customerRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        log.info("Fetching customer with ID: {}", id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return mapToResponse(customer);
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        log.info("Creating customer: {}", request);
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setContactNumber(request.getContactNumber());
        customer.setAddress(request.getAddress());
        return mapToResponse(customerRepository.save(customer));
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        log.info("Updating customer with ID: {}", id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setContactNumber(request.getContactNumber());
        customer.setAddress(request.getAddress());
        return mapToResponse(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(Long id) {
        log.info("Deleting customer with ID: {}", id);
        customerRepository.deleteById(id);
    }

    private CustomerResponse mapToResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getContactNumber(),
                customer.getAddress()
        );
    }
}
