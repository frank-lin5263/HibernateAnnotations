package com.starbooks.model;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "book", catalog = "starbooks")
public class Book implements Serializable {
	static final long serialVersionUID = 2010L;

	private int bookId;
	private Publisher publisher;
	private String isbn;
	private String title;
	private Date releaseDate;
	private double listPrice;
	private byte[] coverImage;
	private Set<Author> authors = new HashSet<Author>(0);

	public Book() {
	}

	public Book(int bookId, Publisher publisher, String isbn) {
		super();
		this.bookId = bookId;
		this.publisher = publisher;
		this.isbn = isbn;
	}

	public Book(int bookId, Publisher publisher, String isbn, String title, Date releaseDate,
			double listPrice, byte[] coverImage, Set<Author> authors) {
		super();
		this.bookId = bookId;
		this.publisher = publisher;
		this.isbn = isbn;
		this.title = title;
		this.releaseDate = releaseDate;
		this.listPrice = listPrice;
		this.coverImage = coverImage;
		this.authors = authors;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookId", unique = true, nullable = false)
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pubId", nullable = false)
	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@Column(name = "isbn", nullable = false, length = 10)
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Column(name = "title", nullable = false, length = 100)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "releaseDate", length = 19)
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Column(name = "listPrice", nullable = false, length = 6)
	public double getListPrice() {
		return listPrice;
	}

	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}

	@Column(name = "coverImage")
	public byte[] getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(byte[] coverImage) {
		this.coverImage = coverImage;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "bookauthor", catalog = "starbooks",
		joinColumns = {
				@JoinColumn(name = "bkId", nullable = false, updatable = false)},
		inverseJoinColumns = {
				@JoinColumn(name = "arId", nullable = false, updatable = false)}
	)
	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	
}
