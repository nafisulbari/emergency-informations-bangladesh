package com.nafisulbari.eib.Service;

import com.nafisulbari.eib.Dao.CriminalRecordRepository;
import com.nafisulbari.eib.Dao.PoliceStationRepository;
import com.nafisulbari.eib.Model.Citizen;
import com.nafisulbari.eib.Model.CriminalRecord;
import com.nafisulbari.eib.Model.PoliceStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;


@Component
public class PoliceStationServiceImpl implements PoliceStationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PoliceStationRepository policeStationRepository;

    @Autowired
    private CriminalRecordRepository criminalRecordRepository;

    @Autowired
    private UserService userService;


    @Override
    public PoliceStation findPoliceStationById(Long id) {
        return policeStationRepository.findPoliceStationById(id);
    }

    @Override
    public void savePoliceStation(PoliceStation policeStation) {

        policeStation.setPassword(passwordEncoder.encode(policeStation.getPassword()));
        policeStation.setRole("POLICE");
        policeStation.setPermissions("");
        policeStationRepository.save(policeStation);

    }

    @Override
    public void saveCriminalRecord(CriminalRecord criminalRecord, PoliceStation policeStation, Citizen citizen) {
        Calendar c = Calendar.getInstance();
        c.setTime(criminalRecord.getDate());
        c.add(Calendar.DATE, 1);
        criminalRecord.setDate(c.getTime());

        criminalRecord.setActive(false);

        criminalRecord.setPoliceStation(policeStation);
        criminalRecord.setCitizen(citizen);
        criminalRecordRepository.save(criminalRecord);
    }

    @Override
    public PoliceStation findPoliceStationByEmail(String email) {
        return policeStationRepository.findPoliceStationByEmail(email);
    }

    @Override
    public List<CriminalRecord> findCriminalRecordsByCitizenId(Long id) {
        return criminalRecordRepository.findCriminalRecordsByCitizenId(id);
    }

    @Override
    public CriminalRecord findCriminalRecordById(Long id) {
        return criminalRecordRepository.findCriminalRecordById(id);

    }

    @Override
    public List<CriminalRecord> findCriminalRecordsOfPoliceStation() {
        PoliceStation policeStation=policeStationRepository.findPoliceStationByEmail(userService.getAuthUserEmail());
        return criminalRecordRepository.findCriminalRecordsByPoliceStationOrderByDateDesc(policeStation);
    }

    @Override
    public List<CriminalRecord> searchCriminalRecordsByCitizen(String key) {
        PoliceStation policeStation=policeStationRepository.findPoliceStationByEmail(userService.getAuthUserEmail());
        return criminalRecordRepository.findCriminalRecordsByCitizenNameContainingOrderByDateDesc(policeStation,key);
    }


}

