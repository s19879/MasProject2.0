package priv.stud.database.utlis;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DatabaseSession {

    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private DatabaseSession(){};

    public static Session openSession(){
        return sessionFactory.openSession();
    }

}
