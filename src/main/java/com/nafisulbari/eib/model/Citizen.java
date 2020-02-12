package com.nafisulbari.eib.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Citizen extends User {

    private String bloodGroup;

    private String sex;

    private String address;


    @Temporal(TemporalType.DATE)
    private Date date;

    private int age;

    private Long nid;

    private int mobile;

    private int emergencyMobile;

    private String emergencyRelation;

    @OneToMany
    private List<MedicalRecord> medicalRecords;

    @OneToMany
    private List<CriminalRecord> criminalRecords;




}
