package com.olaolu.database.serviceAndDao;

import com.olaolu.database.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.olaolu.database.model.UserModel;


/**
 * @author akano.olanrewaju  @on 04/11/2019
 */
@Component
public class LoginAuthService implements UserDetailsService{
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        GrantedAuthority grantedAuthority ;
        PasswordEncoder passwordEncoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
        List<UserModel> users=userDao.findUserByUserName(s);
        if (users.size()==0){
            return null;
        }else {
            List<UserRole> userRoles=new ArrayList<>();
            for(UserModel user:users){
                userRoles.add(new UserRole(user.getNAME()));
            }
            UserModel user=users.get(0);
            grantedAuthority=new SimpleGrantedAuthority(userRoles.get(0).getNAME());
            user.setRoles(userRoles);
            String password=passwordEncoder.encode("clientSecrete");
            return new User(user.getUsername(),user.getPassword(),Arrays.asList(grantedAuthority) );
//            CustomUserDetailsServie customUserDetailsServie=new CustomUserDetailsServie(user);
//            return customUserDetailsServie;
        }

        }
}

