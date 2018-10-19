package com.booksandsubjectssecurity;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name="AUTHORITIES")
public class Authorities implements Serializable
{

   @EmbeddedId
  protected EmbeddedIdObject embeddedIdObject;

public EmbeddedIdObject getEmbeddedIdObject()
{
	return embeddedIdObject;
}

public void setEmbeddedIdObject(EmbeddedIdObject embeddedId) {
	this.embeddedIdObject = embeddedId;
}

}