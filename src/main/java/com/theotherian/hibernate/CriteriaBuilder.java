package com.theotherian.hibernate;

import org.hibernate.Criteria;

public interface CriteriaBuilder {
  
  public Criteria build(Criteria criteria);

}
