package com.nafisulbari.eib.service;

import com.nafisulbari.eib.dao.PoliceStationRepository;
import com.nafisulbari.eib.model.PoliceStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PoliceStationServiceImpl implements PoliceStationService{


    @Autowired
    private PoliceStationRepository policeStationRepository;


    @Override
    public void savePoliceStation(PoliceStation policeStation) {
        policeStationRepository.save(policeStation);
    }
}
