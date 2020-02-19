package com.nafisulbari.eib.Dao;

import com.nafisulbari.eib.Model.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliceStationRepository extends JpaRepository<PoliceStation,Long> {

    PoliceStation findPoliceStationByEmail(String email);
}
