package com.project.app.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanResponse {
    private Long loanId;
    private String status;
    private Double interestAmount;
    private Double totalPayable;
    private String message;
}
