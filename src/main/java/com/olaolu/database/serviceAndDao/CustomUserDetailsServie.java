package com.olaolu.database.serviceAndDao;

import com.olaolu.database.model.UserModel;
import com.olaolu.database.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author akano.olanrewaju  @on 19/05/2020
 */
public class CustomUserDetailsServie implements UserDetails {
    UserModel user=null;
    public CustomUserDetailsServie(UserModel user){
        this.user= user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<UserRole> userRoles= new ArrayList<>();
        userRoles.add(user.getRoles().get(0));
        return userRoles;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
