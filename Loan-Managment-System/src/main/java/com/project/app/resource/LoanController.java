package com.project.app.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.request.LoanRequest;
import com.project.app.response.ErrorResponse;
import com.project.app.response.GenericResponse;
import com.project.app.response.LoanResponse;
import com.project.app.service.ILoanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final ILoanService loanService;

        @Operation(
            summary = "Apply for a loan",
            description = "Allows customers to apply for loans by providing loan amount, interest rate, duration, and purpose."
        )
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan processed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid loan request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        })
        @PostMapping
        public ResponseEntity<GenericResponse<LoanResponse>> applyLoan(@RequestBody @Valid LoanRequest loanRequest) {
            log.info("Received loan application...");
            LoanResponse loanResponse = loanService.applyLoan(loanRequest);
            return ResponseEntity.ok(new GenericResponse<>(loanResponse, "Loan application processed successfully"));
        }
    
//    @PostMapping
//    public ResponseEntity<GenericResponse<LoanResponse>> applyLoan(@RequestBody @Valid LoanRequest request) {
//        log.info("Received loan application...");
//        LoanResponse response = loanService.applyLoan(request);
//        return ResponseEntity.ok(new GenericResponse<>(response, "Loan processed successfully"));
//    }
}










