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
package fr.wirth.web.dao;

import static fr.wirth.db.Tables.UTILISATEUR;
import fr.wirth.db.tables.records.UtilisateurRecord;
import java.util.Arrays;
import java.util.List;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cwirth
 */
@Repository
public class UtilisateurDao {

    @Autowired
    private DSLContext dsl;
    
    public void save(Utilisateur u){
        dsl.insertInto(UTILISATEUR)
                .set(UTILISATEUR.LOGIN, u.getUsername())
                .set(UTILISATEUR.PASSWORD, u.getPassword())
                .set(UTILISATEUR.REGISTER_DATE, DSL.currentDate())
                .execute();
    }
    
    public Utilisateur loadByUsername(String username){
        UtilisateurRecord res = dsl.selectFrom(UTILISATEUR).where(UTILISATEUR.LOGIN.equal(username)).fetchOne();
        if(res == null){
            return null;
        }
        
        Utilisateur u = new Utilisateur();
        u.setUsername(username);
        u.setPassword(res.getPassword());
        u.setRoles(Arrays.asList(new Role("ROLE_USER")));
        
        return u;
    }
    
    public static class Utilisateur {

        private boolean accountNonExpired = true;
        private boolean credentialsNonExpired = true;
        private boolean accountNonLocked = true;
        private boolean isEnabled = true;
        private String username;
        private String password;
        private List<Role> roles;

        public boolean isAccountNonExpired() {
            return accountNonExpired;
        }

        public void setAccountNonExpired(boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
        }

        public boolean isCredentialsNonExpired() {
            return credentialsNonExpired;
        }

        public void setCredentialsNonExpired(boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
        }

        public boolean isAccountNonLocked() {
            return accountNonLocked;
        }

        public void setAccountNonLocked(boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
        }

        public boolean isIsEnabled() {
            return isEnabled;
        }

        public void setIsEnabled(boolean isEnabled) {
            this.isEnabled = isEnabled;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public List<Role> getRoles() {
            return roles;
        }

        public void setRoles(List<Role> roles) {
            this.roles = roles;
        }

    }

    public static class Role {

        private final String name;

        public Role(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }
}
