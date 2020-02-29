package com.nafisulbari.eib.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class CitizenRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition="BIGINT(32)")
    private Long id;

    private String address;

    private String mobile;

    private String emergencyRelation;

    private String emergencyMobile;

    private String email;

    private String password;

    private String imageUrl;

    @OneToOne
    private Citizen citizen;

}
