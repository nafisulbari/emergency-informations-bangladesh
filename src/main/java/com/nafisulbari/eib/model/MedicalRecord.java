package com.nafisulbari.eib.model;


import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@ToString
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition="BIGINT(32)")
    private Long id;


    @Temporal(TemporalType.DATE)
    private Date date;

    private String doctor;

    private String title;

    private String testCBC;

    private String testBP;

    private String testBS;

    private String testECG;

    private String testLP;

    private String testURINE;

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "citizen_id")
    private Citizen citizen;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTestCBC() {
        return testCBC;
    }

    public void setTestCBC(String testCBC) {
        this.testCBC = testCBC;
    }

    public String getTestBP() {
        return testBP;
    }

    public void setTestBP(String testBP) {
        this.testBP = testBP;
    }

    public String getTestBS() {
        return testBS;
    }

    public void setTestBS(String testBS) {
        this.testBS = testBS;
    }

    public String getTestECG() {
        return testECG;
    }

    public void setTestECG(String testECG) {
        this.testECG = testECG;
    }

    public String getTestLP() {
        return testLP;
    }

    public void setTestLP(String testLP) {
        this.testLP = testLP;
    }

    public String getTestURINE() {
        return testURINE;
    }

    public void setTestURINE(String testURINE) {
        this.testURINE = testURINE;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
