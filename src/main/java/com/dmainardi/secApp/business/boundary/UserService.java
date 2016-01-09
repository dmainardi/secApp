/*
 * Copyright (C) 2016 Davide Mainardi <ingmainardi at live.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.dmainardi.secApp.business.boundary;

import com.dmainardi.secApp.business.entity.UserApp;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
@Stateless
public class UserService {
    @PersistenceContext
    EntityManager em;
    
    public UserApp saveUserApp(UserApp userApp) {
        if (userApp.getUserName() == null)
            em.persist(userApp);
        else
            return em.merge(userApp);
        
        return null;
    }
    
    public UserApp readUserApp(String userName) {
        return em.find(UserApp.class, userName);
    }
    
    public void deleteUserApp(UserApp userApp) {
        em.remove(em.merge(userApp));
    }

    public List<UserApp> listUserApps() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserApp> query = cb.createQuery(UserApp.class);
        Root<UserApp> root = query.from(UserApp.class);
        query.select(root);
        return em.createQuery(query).getResultList();
    }
    
    public boolean login(UserApp user) {
        if (em.find(UserApp.class, user.getUserName()) != null) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(user.getPassword().getBytes("UTF-8")); // Change this to "UTF-16" if needed
                byte[] digest = md.digest();
                BigInteger bigInt = new BigInteger(1, digest);
                String output = bigInt.toString(16);
                
                System.out.println(output);
            }
            catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                return false;
            }
        }
        return false;
    }
}
