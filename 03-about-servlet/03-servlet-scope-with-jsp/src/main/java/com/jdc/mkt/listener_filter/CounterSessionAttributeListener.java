package com.jdc.mkt.listener_filter;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class CounterSessionAttributeListener implements HttpSessionAttributeListener, HttpSessionListener {

    public CounterSessionAttributeListener() {
    }

    public void attributeReplaced(HttpSessionBindingEvent event)  { 
    }

    public void sessionCreated(HttpSessionEvent se)  { 
    	System.out.println("Session Created");
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	System.out.println("Session Destroyed");
    }

	public void attributeRemoved(HttpSessionBindingEvent event)  { 
		
    }

	public void attributeAdded(HttpSessionBindingEvent event)  { 
		System.out.println("Session Attribute added");
    }
	
}
