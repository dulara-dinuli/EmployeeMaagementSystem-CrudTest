package com.example.employeemanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")

public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId", nullable = false)
    private long employeeId;

    @Column(name = "password")
    private String password;

    @Column(name = "title")
    private String title;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "contactNo")
    private String contactNo;

    @Column(name = "email")
    private String email;

    @Column(name = "dob")
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dob;

    @Column(name = "address")
    private String address;

}
