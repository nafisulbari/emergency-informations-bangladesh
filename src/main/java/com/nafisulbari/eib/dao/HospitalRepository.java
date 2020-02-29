package com.nafisulbari.eib.dao;

import com.nafisulbari.eib.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long> {

    Hospital findHospitalById(Long id);

    Hospital findHospitalByEmail(String email);

    List<Hospital> findByNameContaining(String key);
}
