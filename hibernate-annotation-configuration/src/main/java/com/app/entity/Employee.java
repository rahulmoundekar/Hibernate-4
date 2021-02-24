package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Columns;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries(value = { @NamedQuery(name = "findAll", query = "from Employee"),
		@NamedQuery(name = "findByName", query = "from Employee where name=:ename") })
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column
	private String name;

	private Float salary;
	
	@Version
	private Integer vCount;

}
