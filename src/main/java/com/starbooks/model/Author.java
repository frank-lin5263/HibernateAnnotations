package com.starbooks.model;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "book", catalog = "StarBooks")
public class Author {
	private Integer authorId;
	private String authorName;
	private Set<Book> books = new HashSet<Book>(0);

	public Author() {
	}

	public Author(Integer authorId, String authorName) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
	}

	public Author(Integer authorId, String authorName, Set<Book> books) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.books = books;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "authorId", unique = true, nullable = false)	
	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	@Column(name = "authorName", nullable = false, length = 50)
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "bookauthor", catalog = "starbooks",
			joinColumns = {@JoinColumn(name = "arId", nullable = false, updatable = false)},
			inverseJoinColumns = {@JoinColumn(name = "bkId", nullable = false, updatable = false)})
	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

}
