package com.nafisulbari.eib.Service;

import com.nafisulbari.eib.Dao.PoliceStationRepository;
import com.nafisulbari.eib.Model.PoliceStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class PoliceStationServiceImpl implements PoliceStationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PoliceStationRepository policeStationRepository;


    @Override
    public void savePoliceStation(PoliceStation policeStation) {

        policeStation.setPassword(passwordEncoder.encode(policeStation.getPassword()));
        policeStation.setRole("POLICE");
        policeStation.setPermissions("");
        policeStationRepository.save(policeStation);

    }


}

