package com.nafisulbari.eib.Dao;

import com.nafisulbari.eib.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    User findUserById(Long id);

    User findByEmail(String email);


}
