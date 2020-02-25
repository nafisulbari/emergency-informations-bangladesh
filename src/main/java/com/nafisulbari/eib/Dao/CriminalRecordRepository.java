package com.nafisulbari.eib.Dao;

import com.nafisulbari.eib.Model.CriminalRecord;
import com.nafisulbari.eib.Model.Hospital;
import com.nafisulbari.eib.Model.MedicalRecord;
import com.nafisulbari.eib.Model.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriminalRecordRepository extends JpaRepository<CriminalRecord,Long> {

    List<CriminalRecord> findCriminalRecordsByCitizenId(Long id);

    CriminalRecord findCriminalRecordById(Long id);

    List<CriminalRecord> findCriminalRecordsByPoliceStationOrderByDateDesc(PoliceStation policeStation);

    @Query(value = "SELECT u FROM CriminalRecord u WHERE u.policeStation IN :policeStation and u.citizen.name like %:name%")
    List<CriminalRecord> findCriminalRecordsByCitizenNameContainingOrderByDateDesc(PoliceStation policeStation, String name);

    List<CriminalRecord> findCriminalRecordsByActiveFalse();
}
