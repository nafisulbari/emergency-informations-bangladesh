package com.nafisulbari.eib.service;


import com.nafisulbari.eib.model.Citizen;
import com.nafisulbari.eib.model.CriminalRecord;
import com.nafisulbari.eib.model.PoliceStation;

import java.util.List;

public interface PoliceStationService {


    PoliceStation findPoliceStationById(Long id);

    void savePoliceStation(PoliceStation policeStation);

    void saveCriminalRecord(CriminalRecord criminalRecord, PoliceStation policeStation, Citizen citizen);

    PoliceStation findPoliceStationByEmail(String email);

    List<CriminalRecord> findCriminalRecordsByCitizenId(Long id);

    CriminalRecord findCriminalRecordById(Long id);

    List<CriminalRecord> findCriminalRecordsOfPoliceStation();

    List<CriminalRecord> searchCriminalRecordsByCitizen(String key);

    List<CriminalRecord> findCriminalRecordsByActiveFalse();

    void saveActiveCriminalRecord(Long id);

    void deleteCriminalRecord(Long id);

    List<PoliceStation> searchPoliceStationByName(String key);
}
