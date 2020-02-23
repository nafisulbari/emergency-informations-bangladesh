package com.nafisulbari.eib.Model;


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

    private String mobile;

    private String address;

    private String emergencyRelation;

    private String emergencyMobile;

    private String imageUrl;

    @OneToOne
    private Citizen citizen;

}
