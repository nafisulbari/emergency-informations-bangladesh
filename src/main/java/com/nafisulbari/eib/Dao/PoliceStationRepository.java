package com.nafisulbari.eib.dao;

import com.nafisulbari.eib.model.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliceStationRepository extends JpaRepository<PoliceStation,Long> {
}
