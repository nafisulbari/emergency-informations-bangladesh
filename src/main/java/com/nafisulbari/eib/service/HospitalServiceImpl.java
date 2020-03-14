package com.nafisulbari.eib.service;


import com.nafisulbari.eib.dao.HospitalRepository;
import com.nafisulbari.eib.dao.MedicalRecordRepository;
import com.nafisulbari.eib.model.Citizen;
import com.nafisulbari.eib.model.Hospital;
import com.nafisulbari.eib.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Calendar;
import java.util.List;

@Component
public class HospitalServiceImpl implements HospitalService {


    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;


    @Override
    public void saveHospital(Hospital hospital) {

        hospital.setPassword(passwordEncoder.encode(hospital.getPassword()));
        hospital.setRole("HOSPITAL");
        hospital.setPermissions("");

        hospitalRepository.save(hospital);
    }


    @Override
    public Hospital findHospitalById(Long id) {

        return hospitalRepository.findHospitalById(id);
    }


    @Override
    public Hospital findHospitalByEmail(String email) {

        return hospitalRepository.findHospitalByEmail(email);
    }


    @Override
    public void saveMedicalRecord(MedicalRecord medicalRecord, Hospital hospital, Citizen citizen) {

        Calendar c = Calendar.getInstance();
        c.setTime(medicalRecord.getDate());
        c.add(Calendar.DATE, 1);

        medicalRecord.setDate(c.getTime());
        medicalRecord.setHospital(hospital);
        medicalRecord.setCitizen(citizen);

        medicalRecordRepository.save(medicalRecord);
    }


    @Override
    public MedicalRecord findMedicalRecordById(Long id) {

        return medicalRecordRepository.findMedicalRecordById(id);
    }


    @Override
    public List<MedicalRecord> findMedicalRecordsByCitizenId(Long id) {

        return medicalRecordRepository.findMedicalRecordsByCitizenIdOrderByIdDesc(id);
    }


    @Override
    public List<MedicalRecord> findMedicalRecordsByCitizenIdOrderByIdASC(Long id) {

        return medicalRecordRepository.findMedicalRecordsByCitizenIdOrderByIdAsc(id);
    }


    @Override
    public List<MedicalRecord> findAdmittedCitizensOfHospital() {

        Hospital hospital = hospitalRepository.findHospitalByEmail(userService.getAuthUserEmail());

        return medicalRecordRepository.findMedicalRecordsByHospitalOrderByDateDesc(hospital);
    }


    @Override
    public List<MedicalRecord> searchMedicalRecordsByCitizen(String key) {

        Hospital hospital = hospitalRepository.findHospitalByEmail(userService.getAuthUserEmail());

        return medicalRecordRepository.findMedicalRecordsByCitizenNameContainingOrderByDateDesc(hospital, key);
    }


    @Override
    public List<Hospital> searchByHospitalName(String key) {

        return hospitalRepository.findByNameContaining(key);
    }
}
