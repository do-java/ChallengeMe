package com.dj.cm.model.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.StringJoiner;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Echo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String value;

	@CreatedDate
	private Date createdDate;

	@LastModifiedDate
	private Date modifiedDate;

	protected Echo() {
	}

	public Echo(String echo) {
		this.value = echo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Echo.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("value='" + value + "'")
				.add("createdDate=" + createdDate)
				.add("modifiedDate=" + modifiedDate)
				.toString();
	}
}
