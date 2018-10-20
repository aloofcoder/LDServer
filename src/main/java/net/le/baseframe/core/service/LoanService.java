package net.le.baseframe.core.service;

import net.le.baseframe.core.entity.Loan;
import net.le.baseframe.web.PageBean;
import net.le.baseframe.web.PageQuest;

public interface LoanService {
    PageBean getLoans(PageQuest pageQuest);
    Loan getLoan(Long userId);
    int addLoan(Loan loan);
    int renovateLoan(Loan loan);
    int removeLoan(Long id);
}
