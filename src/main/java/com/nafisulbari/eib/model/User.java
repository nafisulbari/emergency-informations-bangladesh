package com.nafisulbari.eib.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition="BIGINT(32)")
    private Long id;


    private String name;


    private String email;


    private String password;


    private String role;


}
