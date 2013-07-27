package com.theotherian.hibernate;

import org.hibernate.Session;

public interface SessionRunnable<T> {
  
  public T run(Session session);

}
