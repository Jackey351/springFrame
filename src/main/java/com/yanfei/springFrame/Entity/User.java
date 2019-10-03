package com.yanfei.springFrame.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {
    @Column(name = "nickName")
    private String nickName;
    @Min(0)
    @Max(3)
    @Column(name = "role")
    private Integer role = 0;
}
