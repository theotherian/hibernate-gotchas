package com.theotherian.hibernate.joinwithmaxresults.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.Property;

@Entity
@Table(name = "THING")
public class Thing {
  
  public Thing() {}
  
  public Thing(int id, String name, Person person) {
    this.id = id;
    this.name = name;
    this.person = person;
  }

  @Id
  @Property
  private int id;
  
  public int getId() { return id; }
  public void setId(int id) { this.id = id; }
  
  @Column
  @Property
  private String name;
  
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  
  @ManyToOne
  private Person person;
  
  public Person getPerson() { return person; }
  public void setPerson(Person person) { this.person = person; }
  
  @Override public boolean equals(Object obj) { return Pojomatic.equals(this, obj); }
  @Override public int hashCode() { return Pojomatic.hashCode(this); }
  @Override public String toString() { return Pojomatic.toString(this); }

}
