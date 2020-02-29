package com.nafisulbari.eib.dao;

import com.nafisulbari.eib.model.CriminalRecord;
import com.nafisulbari.eib.model.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriminalRecordRepository extends JpaRepository<CriminalRecord,Long> {

    List<CriminalRecord> findCriminalRecordsByCitizenIdAndActiveTrue(Long id);

    CriminalRecord findCriminalRecordById(Long id);

    List<CriminalRecord> findCriminalRecordsByPoliceStationAndActiveIsTrueOrderByDateDesc(PoliceStation policeStation);

    @Query(value = "SELECT u FROM CriminalRecord u WHERE u.policeStation IN :policeStation and u.active = true and u.citizen.name like %:name%")
    List<CriminalRecord> findCriminalRecordsByCitizenNameContainingOrderByDateDesc(PoliceStation policeStation, String name);

    List<CriminalRecord> findCriminalRecordsByActiveFalse();
}
