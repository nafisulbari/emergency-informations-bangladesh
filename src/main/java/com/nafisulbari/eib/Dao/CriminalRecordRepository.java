package com.nafisulbari.eib.Dao;

import com.nafisulbari.eib.Model.CriminalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriminalRecordRepository extends JpaRepository<CriminalRecord,Long> {
}
