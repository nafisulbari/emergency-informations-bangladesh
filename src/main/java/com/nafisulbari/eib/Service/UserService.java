package com.nafisulbari.eib.Service;

import com.nafisulbari.eib.Model.User;

public interface UserService {


     String getAuthUserRole();

     String getAuthUserEmail();

     User findUserById(Long userId);

     void save(User user);

     User findByEmail(String email);


}
