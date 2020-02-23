package com.nafisulbari.eib.Model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;

import java.util.Date;


@Entity
@Getter
@Setter
@ToString
public class CriminalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition="BIGINT(32)")
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String location;

    private String title;

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "citizen_id")
    private Citizen citizen;

    @ManyToOne
    @JoinColumn(name = "police_station_id")
    private PoliceStation policeStation;



}
