package com.nafisulbari.eib.Dao;

import com.nafisulbari.eib.Model.CitizenRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitizenRequestRepository extends JpaRepository<CitizenRequest,Long> {

    CitizenRequest findCitizenRequestById(Long id);

    void deleteCitizenRequestById(Long id);

}
