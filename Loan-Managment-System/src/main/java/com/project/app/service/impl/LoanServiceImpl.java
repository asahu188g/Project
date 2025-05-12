package com.project.app.service.impl;

import org.springframework.stereotype.Service;

import com.project.app.entity.Loan;
import com.project.app.repository.LoanRepository;
import com.project.app.request.LoanRequest;
import com.project.app.response.LoanResponse;
import com.project.app.service.ILoanService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private final LoanRepository loanRepository;

    @Override
    public LoanResponse applyLoan(LoanRequest loanRequest) {
        log.info("Processing loan application for customerId: {}", loanRequest.getCustomerId());

        Loan loan = new Loan();
        loan.setCustomerId(loanRequest.getCustomerId());
        loan.setLoanAmount(loanRequest.getLoanAmount());
        loan.setInterestRate(loanRequest.getInterestRate());
        loan.setDurationMonths(loanRequest.getDurationMonths());
        loan.setPurpose(loanRequest.getPurpose());

        // Approval rule: Reject if amount > 1,00,000
        if (loan.getLoanAmount() > 100000) {
            loan.setStatus("REJECTED");
            loan.setInterestAmount(0.0);
            loanRepository.save(loan);
            return new LoanResponse(loan.getId(), "REJECTED", 0.0, 0.0, "Loan rejected: Amount exceeds limit");
        }

        // Simple Interest Calculation
        double interest = (loan.getLoanAmount() * loan.getInterestRate() * loan.getDurationMonths()) / (12 * 100);
        loan.setInterestAmount(interest);
        loan.setStatus("APPROVED");

        Loan savedLoan = loanRepository.save(loan);

        double totalPayable = loan.getLoanAmount() + interest;

        return new LoanResponse(
                savedLoan.getId(),
                savedLoan.getStatus(),
                interest,
                totalPayable,
                "Loan approved"
        );
    }
}
