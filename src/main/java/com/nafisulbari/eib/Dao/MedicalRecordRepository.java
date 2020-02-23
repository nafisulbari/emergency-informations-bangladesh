package com.nafisulbari.eib.Dao;

import com.nafisulbari.eib.Model.Citizen;
import com.nafisulbari.eib.Model.Hospital;
import com.nafisulbari.eib.Model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {


    MedicalRecord findMedicalRecordById(Long id);

    void deleteByCitizen(Citizen citizen);

    List<MedicalRecord> findMedicalRecordsByCitizenIdOrderByIdDesc(Long id);

    List<MedicalRecord> findMedicalRecordsByHospitalOrderByDateDesc(Hospital hospital);

    @Query(value = "SELECT u FROM MedicalRecord u WHERE u.hospital IN :hospital and u.citizen.name like %:name%")
    List<MedicalRecord> findMedicalRecordsByCitizenNameContainingOrderByDateDesc(Hospital hospital,String name);

}
