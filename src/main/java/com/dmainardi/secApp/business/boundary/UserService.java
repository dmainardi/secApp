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
import java.util.List;
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
    
    public UserApp readUserApp(Long id) {
        return em.find(UserApp.class, id);
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
}
