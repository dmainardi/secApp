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
package com.dmainardi.secApp.presentation;

import com.dmainardi.secApp.business.boundary.UserService;
import com.dmainardi.secApp.business.entity.UserApp;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
@Named
@SessionScoped
public class UserPresenter implements Serializable {
    @Inject
    UserService userService;
    
    private UserApp user;
    
    public boolean login() {
        return userService.login(user);
    }
    
    public List<UserApp> listUserApps() {
        return userService.listUserApps();
    }
        
    public void deleteUserApp(UserApp user) {
        userService.deleteUserApp(user);
    }
    
    public String saveUserApp() {
        userService.saveUserApp(user);
        
        return "userApps";
    }
    
    public String detailUserApp(String userName) {
        if (userName == null)
            user = new UserApp();
        else
            user = userService.readUserApp(userName);
        
        return "userApp";
    }

    public UserApp getUserApp() {
        return user;
    }

    public void setUserApp(UserApp userApp) {
        this.user = userApp;
    }
}
