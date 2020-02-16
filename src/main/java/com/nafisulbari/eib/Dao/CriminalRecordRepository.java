package com.nafisulbari.eib.dao;

import com.nafisulbari.eib.model.CriminalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriminalRecordRepository extends JpaRepository<CriminalRecord,Long> {
}
