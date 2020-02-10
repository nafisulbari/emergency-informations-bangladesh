package com.nafisulbari.eib.dao;

import com.nafisulbari.eib.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    User findUserById(int id);


}
