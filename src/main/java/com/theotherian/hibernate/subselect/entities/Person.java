package com.theotherian.hibernate.subselect.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

@Entity
@Table(name = "PERSON")
@AutoProperty
public class Person {
  
  public Person() {}
  
  public Person(int id, String name) {
    this.id = id;
    this.name = name;
  }
  
  @Id
  private int id;
  
  public int getId() { return id; }
  public void setId(int id) { this.id = id; }
  
  @Column
  private String name;
  
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  
  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "PERSON_ID")
  @Fetch(FetchMode.SUBSELECT)
  private List<Thing> things;
  
  public List<Thing> getThings() { return things; }
  public void setThings(List<Thing> things) { this.things = things; }
  
  @Override public boolean equals(Object obj) { return Pojomatic.equals(this, obj); }
  @Override public int hashCode() { return Pojomatic.hashCode(this); }
  @Override public String toString() { return Pojomatic.toString(this); }
}
