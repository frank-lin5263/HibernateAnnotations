package com.starbooks.helper;

import java.text.*;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class HibernateUtil {
	private static SessionFactory factory = null;

	static {
		try {
/*	        Configuration cfg = new Configuration();
	        cfg.configure();
	        factory = cfg.buildSessionFactory(); */			
			factory = new AnnotationConfiguration()
					.configure()
					.buildSessionFactory();
			System.out.println("HibernateUtil: " + factory.toString());
		} 
		catch (Throwable ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static SessionFactory getSessionFactory() {
		return factory;
	}
}
