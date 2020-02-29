package com.nafisulbari.eib.dao;

import com.nafisulbari.eib.model.CitizenRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRequestRepository extends JpaRepository<CitizenRequest,Long> {

    CitizenRequest findCitizenRequestById(Long id);

    void deleteCitizenRequestById(Long id);

}
