package com.nafisulbari.eib.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
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

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "citizen_id")
    private Citizen citizen;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;


}
