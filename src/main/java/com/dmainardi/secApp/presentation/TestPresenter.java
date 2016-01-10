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

import com.dmainardi.secApp.business.boundary.TestService;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
@Named
@RequestScoped
public class TestPresenter {
    @Inject
    TestService testService;
    
    public String toGuestPage() {
        return testService.guestPage();
    }
    
    public String toUserPage() {
        return testService.userPage();
    }
    
    public String toAdminPage() {
        return testService.adminPage();
    }
    
    public String cambiamiHashed() {
        String password = "cambiami";
        String CHARSET = "UTF-8";
        String ENCRYPTION_ALGORITHM = "SHA-256";
        MessageDigest md;
        try {
            byte[] bytesOfMessage = password.getBytes(CHARSET);
            
            md = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
            md.update(bytesOfMessage);
            
            return Base64.getEncoder().encodeToString(md.digest());
            
            /*BigInteger bigInteger = new BigInteger(1, md.digest());
            return String.format("%x", bigInteger);*/

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            return "Error";
        }
    }
}
