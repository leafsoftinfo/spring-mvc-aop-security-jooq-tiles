/*
 * Copyright 2014 cwirth.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.wirth.web.service;

import fr.wirth.web.dao.UtilisateurDao;
import fr.wirth.web.dao.UtilisateurDao.Role;
import fr.wirth.web.dao.UtilisateurDao.Utilisateur;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cwirth
 */
@Service("customUserDetailsService")
@Transactional
public class SecurityService implements ISecurityService {
    
    @Autowired
    private UtilisateurDao dao;
    
    @Autowired
    private PasswordEncoder encoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Utilisateur utilisateur = dao.loadByUsername(username);

        if (utilisateur == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        } else if (utilisateur.getRoles().isEmpty()) {
            throw new UsernameNotFoundException("User " + username + " has no authorities");
        }

        return new User(
                utilisateur.getUsername(),
                utilisateur.getPassword(),
                utilisateur.isIsEnabled(),
                utilisateur.isAccountNonExpired(),
                utilisateur.isCredentialsNonExpired(),
                utilisateur.isAccountNonLocked(),
                toGrantedAutorithy(utilisateur.getRoles()));

    }

    private static List<GrantedAuthority> toGrantedAutorithy(List<Role> roles) {
        List<GrantedAuthority> res = new ArrayList<GrantedAuthority>();
        if (roles != null && !roles.isEmpty()) {
            for (Role r : roles) {
                res.add(new SimpleGrantedAuthority(r.getName()));
            }
        }

        return res;
    }

    @Override
    public void register(Utilisateur user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        dao.save(user);
    }
}
