package com.nafisulbari.eib.Dao;

import com.nafisulbari.eib.Model.Citizen;
import com.nafisulbari.eib.Model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {


    MedicalRecord findMedicalRecordById(Long id);

    void deleteByCitizen(Citizen citizen);

    List<MedicalRecord> findMedicalRecordsByCitizenIdOrderByIdDesc(Long id);

}
