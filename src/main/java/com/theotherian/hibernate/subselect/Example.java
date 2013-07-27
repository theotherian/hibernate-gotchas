package com.theotherian.hibernate.subselect;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;

import com.theotherian.hibernate.HibernateUtil;
import com.theotherian.hibernate.subselect.entities.Person;
import com.theotherian.hibernate.subselect.entities.Thing;

public class Example {
  
  public static void main(String[] args) throws Exception {

    create();
    
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    Criteria criteria = session.createCriteria(Person.class);
    
    //    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

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
    HibernateUtil.saveMultiple(
      person1, new Thing(1, "Laptop", person1), new Thing(2, "Car", person1),
      person2, new Thing(3, "TV", person2), new Thing(4, "Motorcycle", person2)
    );
  }
  

}
