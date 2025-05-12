package com.project.app.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequest {
    private Long customerId;
    private Double loanAmount;
    private Double interestRate;
    private Integer durationMonths;
    private String purpose;
}

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class LoanRequest {
//    @NotNull
//    private Double loanAmount;
//
//    @NotNull
//    private Double interestRate;
//
//    @NotNull
//    private Integer durationInMonths;
//
//    @NotBlank
//    private String purpose;
//
//	public double getDurationInMonths() {
//		// TODO Auto-generated method stub
//		return 0;
//	}

