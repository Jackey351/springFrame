package com.yanfei.springFrame.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@CreatedDate
	@Column(name = "createTime", updatable = false)
	@JsonIgnore
	private Date createTime;
	
	@LastModifiedDate
	@Column(name = "updateTime")
	@JsonIgnore
	private Date updateTime;
}
