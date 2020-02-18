package com.nafisulbari.eib.Dao;

import com.nafisulbari.eib.Model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long> {

    Hospital findHospitalById(Long id);

    Hospital findHospitalByEmail(String email);
}
