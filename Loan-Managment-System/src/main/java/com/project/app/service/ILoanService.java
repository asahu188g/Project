package com.project.app.service;

import com.project.app.request.LoanRequest;
import com.project.app.response.LoanResponse;

public interface ILoanService {

	LoanResponse applyLoan(LoanRequest loanRequest);



}
