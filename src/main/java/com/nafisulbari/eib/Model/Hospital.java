package com.nafisulbari.eib.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
public class Hospital extends User{

    private String address;



}
