package com.booksandsubjectsmvcrestful;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by rahul.rg on 9/6/2018.
 */
@Entity
@Table(name="BOOK")
public class Book 
{
	@Id
	@Column(name="BOOK_ID")
    private long bookId;
	
    private String title;
    
    private double price;
    
    private Integer volume;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinTable(name="BOOK_SUBJECT_LINK",joinColumns=@JoinColumn(name="BOOK_ID_LINK"),inverseJoinColumns=@JoinColumn(name="SUBJECT_ID_LINK"))
    private Subject subject;
    
    public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Column(name="PUBLISH_DATE")
    @Convert(converter=DateConvertor.class)
    private LocalDate publishDate;

    public LocalDate getPublishDate() {
		return publishDate;
	}

	public long getBookId()
    {
        return bookId;
    }

    public void setBookId(long bookId)
    {
        this.bookId = bookId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public Integer getVolume()
    {
        return volume;
    }

    public void setVolume(Integer volume)
    {
        this.volume = volume;
    }
    
    
    public void Book()
    {
    	
    }

	public void setPublishDate(LocalDate date) 
	{
		this.publishDate=date;
		
	}
	
	public String toString()
	{
		return "Book ID->"+this.bookId+",volume->"+this.volume+",publish date->"+this.publishDate+",price->"+this.price;
	}
}