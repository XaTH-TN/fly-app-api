package com.edu.fly.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class UsersEntity {
    @Id
    private Long id;
    @Column
    private Long createdById;
    @Column(columnDefinition = "TIMESTAMP")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column
    private Long updatedById;
    @Column(columnDefinition = "TIMESTAMP")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column
    private String username;
    @Column
    private String password;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP")
    private Date lockedDate;
    @Column
    private Integer timesLoginFailed;
    @Column
    private String address;
    @Column
    private String displayName;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String avatar;
    @Column
    private Date dateOfBirth;
    @Column
    private Date identifyDate;
    @Column
    private Long identifyNumber;
    @Column
    private String identifyPlace;
    @Column
    private Long roleId;
    @Column
    private Long parentId;
}
