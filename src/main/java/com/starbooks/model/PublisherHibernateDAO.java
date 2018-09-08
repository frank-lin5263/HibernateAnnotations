package com.starbooks.model;

import java.util.*;
import org.hibernate.*;
import com.starbooks.helper.*;

public class PublisherHibernateDAO {
	public Collection findAll() {
		Set<Book> publisherList = null;

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from Publisher");
			publisherList = (Set<Book>) query.list();
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}

		return publisherList;
	}

	public Collection findBooksByPublisherId(String publisherId) {
		Set<Book> bookList = null;

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			
			//Query query = session.createQuery("from Book b where b.pubId = :pubId");
			//query.setParameter("pubId", publisherId);
			//bookList = (Set<Book>) query.list();
			
			Publisher publisher = (Publisher) session.load(Publisher.class, publisherId);
			bookList = publisher.getBooks();
			
			tx.commit();
		} 
		catch (Exception e) {
			if (tx != null)
				tx.rollback();
			System.out.println(e.getMessage());
		}

		return bookList;
	}
}
