package com.nafisulbari.eib.dao;

import com.nafisulbari.eib.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long>{

    Citizen findCitizenById(Long id);

    Citizen findCitizenByEmail(String email);

    void deleteCitizenById(Long id);

    List<Citizen> findByNameContaining(String key);


}
