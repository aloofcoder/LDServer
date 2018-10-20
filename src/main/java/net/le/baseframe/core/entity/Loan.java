package net.le.baseframe.core.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
@Setter
@Data
public class Loan implements Serializable {

    private static final long serialVersionUID = 4071758522911713080L;

    private Long id;
    private Long userId;
    // 贷款总金额
    private Integer loanMoney;
    // 贷款总利息
    private Integer loanInterest;
    // 贷款总期数
    private Integer loanPeriods;
    // 先息后款利息期数(等额本息为0)
    private Integer loanInterestPeriod;
    // 贷款还款类型 (0.等额本息，1.先息后本)
    private Integer loanType;
    // 贷款质押
    private String loanPledge;
    // 状态(0:还款中，1:还款完成，2：无效记录)
    private Integer loanStatus;
    private String loanRemarks;

    private String createUser;
    private String editUser;
    private Long createTime;
    private Long editTime;

    public Loan() {
    }
}
