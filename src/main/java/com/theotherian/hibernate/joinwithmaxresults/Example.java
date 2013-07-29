package com.theotherian.hibernate.joinwithmaxresults;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;

import com.theotherian.hibernate.HibernateUtil;
import com.theotherian.hibernate.joinwithmaxresults.entities.Person;
import com.theotherian.hibernate.joinwithmaxresults.entities.Thing;

public class Example {
  
  public static void main(String[] args) throws Exception {

    create();

    
// HQL example which is also busted since it computes the results in memory and loads all records
//    Session hqlSession = HibernateUtil.getSessionFactory().getCurrentSession();
//    hqlSession.beginTransaction();
//    org.hibernate.Query query = hqlSession.createQuery(
//        "select distinct p from Person p left outer join fetch p.things order by p.id");
//    query.setFirstResult(0);
//    query.setMaxResults(2);
//    @SuppressWarnings("unchecked")
//    List<Person> peeps = query.list();
//    System.out.println(peeps);
//    hqlSession.getTransaction().commit();
    
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    Criteria criteria = session.createCriteria(Person.class);
    criteria.addOrder(Order.asc("id"));
//        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    criteria.setFirstResult(0).setMaxResults(2);
    
    @SuppressWarnings("unchecked")
    List<Person> persons = criteria.list();
    System.out.println(persons.size());
    System.out.println(persons);
    session.getTransaction().commit();
    
    HibernateUtil.shutdown();
  }

  private static void create() {
    Person person1 = new Person(1, "Ian");
    Person person2 = new Person(2, "Daniel");
    Person person3 = new Person(3, "Neil");
    Person person4 = new Person(4, "Tom");
    HibernateUtil.saveMultiple(
      person1, new Thing(1, "Laptop", person1), new Thing(2, "Car", person1),
      person2, new Thing(3, "TV", person2), new Thing(4, "Motorcycle", person2),
      person3, new Thing(5, "House", person3), new Thing(6, "Cat", person3),
      person4, new Thing(7, "Dog", person4), new Thing(8, "PC", person4)
    );
  }
  

}
