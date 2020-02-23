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
public class Citizen extends User {

    private String bloodGroup;

    private String sex;

    private String address;

    private int citizenPoint;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    private Long nid;

    private String mobile;

    private String emergencyMobile;

    private String emergencyRelation;

    private String imageUrl;


}
