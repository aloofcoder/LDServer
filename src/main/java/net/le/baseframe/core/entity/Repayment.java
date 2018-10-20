package net.le.baseframe.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class Repayment implements Serializable {

    private static final long serialVersionUID = 2027743048617581300L;

    private Long id;
    // 用户id
    private Long userId;
    // 贷款id
    private Long loanId;
    // 还款金额
    private Integer repaymentMoney;
    // 还款渠道
    private String repaymentSource;
    // 还款时间
    private Long repaymentTime;
    // 还款账单产生时间
    private long lowerTime;
//    还款状态(0:待还款，1:还款完成，2:冲正，3:删除)
    private Integer repaymentStatus;
    private String repaymentRemark;
    private String createUser;
    private String editUser;
    private Long createTime;
    private Long editTime;

    public Repayment() {
    }
}
