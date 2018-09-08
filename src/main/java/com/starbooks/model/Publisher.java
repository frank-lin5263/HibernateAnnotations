package com.starbooks.model;

import java.io.*;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "publisher", catalog = "StarBooks")
public class Publisher implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String publisherId;
	private String publisherName;
	private Set<Book> books = new HashSet<Book>(0);
	
	public Publisher() {
	}

	public Publisher(String publisherId, String publisherName) {
		super();
		this.publisherId = publisherId;
		this.publisherName = publisherName;
	}

	public Publisher(String publisherId, String publisherName, Set<Book> books) {
		super();
		this.publisherId = publisherId;
		this.publisherName = publisherName;
		this.books = books;
	}

	@Id
	@Column(name = "publisherId", unique = true, nullable = false, length = 2)
	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	@Column(name = "publisherName", nullable = false, length = 50)
	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publisher")
	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	

}
