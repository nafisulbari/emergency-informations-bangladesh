package com.nafisulbari.eib.security;

import com.nafisulbari.eib.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * CustomUserDetails is responsible for Implementing UserDetails
 * which is being used for managing login
 *
 * @author  Ahmed Nafisul Bari
 */


public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        //Extract list of permissions from logged in user
        this.user.getPermissionsList().forEach(p -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(p);
            authorities.add(authority);
        });

        //Extract list of roles from logged in user
        this.user.getRoleList().forEach(r -> {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
            authorities.add(authority);
        });
        return authorities;
    }



    //some user UserDetails methods are set from db, some are kept true to spring security to work
    @Override
    public String getPassword() {

        if (this.user==null){
            System.err.println("Wrong credentials");
            return "";
        }
        System.out.println(this.user.getPassword());
        return this.user.getPassword();
    }



    public Long getId() {
        return this.user.getId();
    }

    public String getName() {
        return this.user.getName();
    }



    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}