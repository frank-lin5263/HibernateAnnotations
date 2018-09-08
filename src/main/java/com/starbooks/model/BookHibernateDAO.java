package com.starbooks.model;

import org.hibernate.*;
import com.starbooks.helper.*;

public class BookHibernateDAO {
	public Book findByPrimaryKey(int bookId) {
		Book book = new Book();

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			book = (Book) session.load(Book.class, bookId);
			Hibernate.initialize(book);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			System.out.println(e.getMessage());
		}

		return book;
	}
}
