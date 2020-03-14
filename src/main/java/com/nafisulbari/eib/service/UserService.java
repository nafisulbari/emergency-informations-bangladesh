package com.nafisulbari.eib.service;

import com.nafisulbari.eib.model.User;

public interface UserService {


    String getAuthUserRole();

    String getAuthUserEmail();

    User findUserById(Long userId);

    void save(User user);

    User findByEmail(String email);


}
