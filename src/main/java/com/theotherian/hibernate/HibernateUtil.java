package com.theotherian.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hsqldb.server.Server;

public class HibernateUtil {
  
  private static final Server server = startDb();

  private static final SessionFactory sessionFactory = buildSessionFactory();
  
  private static Server startDb() {
    Server server = new Server();

    server.setDatabaseName(0, "test");
    server.setDatabasePath(0, "target/mem:test");
    server.setSilent(false);
    server.setTrace(true);
    server.start();
    
    return server;
  }

  private static SessionFactory buildSessionFactory() {
    try {
      
      // Create the SessionFactory from hibernate.cfg.xml
      Configuration config = new Configuration();
      return config.configure().buildSessionFactory();
    }
    catch (Throwable ex) {
        // Make sure you log the exception, as it might be swallowed
        System.err.println("Initial SessionFactory creation failed." + ex);
        throw new ExceptionInInitializerError(ex);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
  
  public static void saveMultiple(final Object...objects) {
    runInSession(new SessionRunnable<Void>() {
      
      @Override
      public Void run(Session session) {
        for (Object object : objects) {
          session.save(object);
        }
        return null;
      }
    });
  }
  
  public static <T> T runInSession(SessionRunnable<T> runnable) {
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    T t = null;
    try {
      t = runnable.run(session);
      session.getTransaction().commit();
      return t;
    }
    catch (Exception e) {
      session.getTransaction().rollback();
      throw new RuntimeException(e);
    }
  }
  
  public static void shutdown() {
    sessionFactory.close();
    server.shutdown();
  }
  
}