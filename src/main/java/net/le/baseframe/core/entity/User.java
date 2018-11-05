package net.le.baseframe.core.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -3059456620602082015L;

    private Long id;
    private String userNumber;
    private String userName;
    private Integer userGender;
    private String userMobile;
    private String userAddress;
    private String userIdCard;
    private String userIdCardUrl;
    private String userImgUrl;
    private String userProfession;
    private String userCompany;
    private String userCompanyAdr;
    private String userCar;
    private String userHouse;
    private String linkmanName1;
    private String linkmanMobile1;
    private String linkmanName2;
    private String linkmanMobile2;
    private String linkmanName3;
    private String linkmanMobile3;
    private Integer userStatus;
    private String userRemark;
    private String createUser;
    private String editUser;
    private Long createTime;
    private Long editTime;

    public User() {
    }
}
