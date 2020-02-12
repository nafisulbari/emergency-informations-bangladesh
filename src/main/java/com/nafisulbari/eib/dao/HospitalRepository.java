package com.nafisulbari.eib.dao;

import com.nafisulbari.eib.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long> {

    Hospital findHospitalById(Long id);
}
