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

import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;

    public CustomExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public void handle() throws FacesException {
        Iterator i = getUnhandledExceptionQueuedEvents().iterator();
        while (i.hasNext()) {
            ExceptionQueuedEvent event = (ExceptionQueuedEvent) i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

            Throwable t = context.getException();
            FacesContext fc = FacesContext.getCurrentInstance();
            try {/* Here you can use the Throwable object in order to verify the exceptions you want to handle in the application */
                NavigationHandler navigationHandler = fc.getApplication().getNavigationHandler();
                String errorPage;
                if ("javax.ejb.EJBAccessException".equals(getRootCause(t).getMessage())) {
                    errorPage = "/unauthorized";
                    fc.renderResponse();
                } else {
                    errorPage = "/error";
                }
                navigationHandler.handleNavigation(fc, null, errorPage + "?faces-redirect=true");
                fc.renderResponse();
            } finally {
                i.remove();
            }
        }
        // Call the parent exception handler’s handle() method
        getWrapped().handle();
    }
}
