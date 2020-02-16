package com.nafisulbari.eib.dao;

import com.nafisulbari.eib.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long>{

    Citizen findCitizenById(Long id);
    void deleteCitizenById(Long id);

}
