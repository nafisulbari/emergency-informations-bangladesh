package com.nafisulbari.eib.Service;



import com.nafisulbari.eib.Model.Citizen;
import com.nafisulbari.eib.Model.CriminalRecord;
import com.nafisulbari.eib.Model.PoliceStation;

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
