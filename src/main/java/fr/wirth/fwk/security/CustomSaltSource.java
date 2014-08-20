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

package fr.wirth.fwk.security;

import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author cwirth
 */
public class CustomSaltSource extends ReflectionSaltSource implements SaltSource{
    
    private final String chaine;

    public CustomSaltSource(String chaine) {
        super();
        this.chaine = chaine;
    }
    
    @Override
    public Object getSalt(UserDetails ud) {
        Object o = super.getSalt(ud);
        if(o == null){
            throw new IllegalStateException("ReflectionSaltSource.getSalt must not be null");
        }
        StringBuilder sb = new StringBuilder(o.toString());
        if(chaine != null){
            sb.append(chaine);
        }
        return sb.toString();
    }
    
    
    
    
}
