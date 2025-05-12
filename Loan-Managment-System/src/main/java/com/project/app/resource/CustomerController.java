package com.project.app.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.request.CustomerRequest;
import com.project.app.response.CustomerResponse;
import com.project.app.response.ErrorResponse;
import com.project.app.response.GenericResponse;
import com.project.app.service.ICustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Create new customer", description = "Adds a new customer to the system.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Customer created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<GenericResponse<CustomerResponse>> addCustomer(@RequestBody @Valid CustomerRequest request) {
        log.info("Creating a new customer...");
        CustomerResponse savedCustomer = customerService.createCustomer(request);
        return ResponseEntity.ok(new GenericResponse<>(savedCustomer, "Customer created successfully"));
    }
    
    @Operation(summary = "Get all customers", description = "Returns a list of all customers.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved all customers"),
        @ApiResponse(responseCode = "404", description = "Customers not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<GenericResponse<List<CustomerResponse>>> getAllCustomers() {
        log.info("Fetching all customers...");
        List<CustomerResponse> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(new GenericResponse<>(customers, "Customer list fetched successfully"));
    }
    
    
    @Operation(summary = "Update customer by ID", description = "Updates details of a specific customer.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
        @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    
    
    
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<CustomerResponse>> updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerRequest request) {
        log.info("Updating customer with ID: {}", id);
        CustomerResponse updatedCustomer = customerService.updateCustomer(id, request);
        return ResponseEntity.ok(new GenericResponse<>(updatedCustomer, "Customer updated successfully"));
    }

}