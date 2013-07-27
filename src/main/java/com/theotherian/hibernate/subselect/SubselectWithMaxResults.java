package com.theotherian.hibernate.subselect;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.hsqldb.Server;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.theotherian.hibernate.subselect.entities.Person;
import com.theotherian.hibernate.subselect.entities.Thing;

public final class SubselectWithMaxResults {
  
  public static void main(String[] args) throws Exception {
    Server.main(new String[]{"server.trace", "true", "server.silent", "false"});
    Injector injector = Guice.createInjector(new JpaPersistModule("hibernate-gotchas"));
    injector.getInstance(PersistService.class).start();
    EntityManager entityManager = injector.getInstance(EntityManager.class);
    try {
      entityManager.getTransaction().begin();
      Person person1 = new Person(1, "Ian");
      Thing thing1 = new Thing(1, "Car", person1);
      Thing thing2 = new Thing(2, "Laptop", person1);
      entityManager.persist(person1);
      entityManager.persist(thing1);
      entityManager.persist(thing2);
//      Person person2 = new Person(2, "Daniel");
//      Thing thing3 = new Thing(1, "TV", person1);
//      Thing thing4 = new Thing(2, "Motorcycle", person1);
//      entityManager.persist(person2);
//      entityManager.persist(thing3);
//      entityManager.persist(thing4);
    }
    finally {
      entityManager.getTransaction().commit();
    }
    
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Person> query = criteriaBuilder.createQuery(Person.class);
    Root<Person> root = query.from(Person.class);
    
//    query.where(criteriaBuilder.isNotNull(root.get(model.getSingularAttribute("name"))));
    query.where(criteriaBuilder.isNotNull(root.get("name")));
    List<Person> persons = entityManager.createQuery(query).getResultList();
    System.out.println(persons);
//    Person person1 = entityManager.find(Person.class, 1);
//    System.out.println(person1);
//    entityManager.detach(person1);
//    
//    Person personOne = entityManager.find(Person.class, 1);
//    System.out.println(personOne);
    
  }
  
}
