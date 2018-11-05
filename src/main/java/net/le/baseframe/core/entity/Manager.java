package net.le.baseframe.core.entity;

import lombok.*;

import java.io.Serializable;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Manager implements Serializable {
    private static final long serialVersionUID = 588326771651283345L;

    private Long id;
    private String managerNumber;
    private String managerPwd;
    private String managerName;
    private Long loginTime;
    private Integer managerStatus;
    private String managerRemark;
    private Long createTime;
    private Long editTime;
    private String createUser;
    private String editUser;
}
