package com.theotherian.hibernate.joinwithmaxresults.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
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
  
  /**
   * This relationship demonstrates the different ways the join can be mapped
   * and is separated by comments for 'groups' of annotations
   */
// uncomment the next two lines for eager/join 
//  @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
//  @Fetch(FetchMode.JOIN)
// uncomment the next three lines for eager/join with join column
//  @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
//  @Fetch(FetchMode.JOIN)
//  @JoinColumn(name = "PERSON_ID")
// uncomment the next two lines for eager/subselect
//  @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
//  @Fetch(FetchMode.SUBSELECT)
// uncomment the next three lines for eager/subselect with join column
//  @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
//  @Fetch(FetchMode.SUBSELECT)
//  @JoinColumn(name = "PERSON_ID")
// uncomment the next three lines for eager/select with batch
//  @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
//  @Fetch(FetchMode.SELECT)
//  @BatchSize(size = 2)
// uncomment the next four lines for eager/select with join column and batch
  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "PERSON_ID")
  @Fetch(FetchMode.SELECT)
  @BatchSize(size = 2)
  private List<Thing> things;
  
  public List<Thing> getThings() { return things; }
  public void setThings(List<Thing> things) { this.things = things; }
  
  @Override public boolean equals(Object obj) { return Pojomatic.equals(this, obj); }
  @Override public int hashCode() { return Pojomatic.hashCode(this); }
  @Override public String toString() { return Pojomatic.toString(this); }
}
