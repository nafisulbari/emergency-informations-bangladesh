package com.nafisulbari.eib.dao;

import com.nafisulbari.eib.model.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoliceStationRepository extends JpaRepository<PoliceStation,Long> {

    PoliceStation findPoliceStationById(Long id);

    PoliceStation findPoliceStationByEmail(String email);

    List<PoliceStation> findByNameContaining(String key);
}
