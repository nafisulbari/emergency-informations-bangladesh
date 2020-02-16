package com.nafisulbari.eib.dao;

import com.nafisulbari.eib.model.Citizen;
import com.nafisulbari.eib.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    MedicalRecord findMedicalRecordById(Long id);
    void deleteByCitizen(Citizen citizen);

}
