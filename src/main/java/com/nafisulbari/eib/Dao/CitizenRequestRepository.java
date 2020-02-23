package com.nafisulbari.eib.Dao;

import com.nafisulbari.eib.Model.CitizenRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRequestRepository extends JpaRepository<CitizenRequest,Long> {
}
