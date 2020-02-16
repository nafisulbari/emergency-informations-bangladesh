package com.nafisulbari.eib.Service;

import com.nafisulbari.eib.Dao.PoliceStationRepository;
import com.nafisulbari.eib.Model.PoliceStation;
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
