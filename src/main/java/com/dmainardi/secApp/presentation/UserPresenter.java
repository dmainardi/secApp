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
import com.dmainardi.secApp.business.entity.GroupApp;
import com.dmainardi.secApp.business.entity.UserApp;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    
    @Inject
    Authenticator authenticator;
    
    private UserApp user;
    
    public List<UserApp> listUserApps() {
        return userService.listUserApps();
    }
        
    public void deleteUserApp(UserApp user) {
        if (user.getUserName().equals(authenticator.getLoggedUser().getUserName()))
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You cannot delete yourself."));
        else
            userService.deleteUserApp(user);
    }
    
    public String saveUserApp() {
        userService.saveUserApp(user);
        
        return "/secured/manageUser/users?faces-redirect=true";
    }
    
    public String detailUserApp(String userName) {
        if (userName == null)
            user = new UserApp();
        else {
            if (userName.equals(authenticator.getLoggedUser().getUserName())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You cannot change yourself."));
                return null;
            }
            else
                user = userService.readUserApp(userName);
        }
        
        return "/secured/manageUser/user?faces-redirect=true";
    }

    public UserApp getUserApp() {
        return user;
    }

    public void setUserApp(UserApp userApp) {
        this.user = userApp;
    }
    
    public void removeGroup(GroupApp group) {
        this.user.getGroups().remove(group);
    }
}
