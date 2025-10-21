package test;

import dao.IDao;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import util.HibernateUtil;

public class TestHibernate {
    public static void main(String[] args) {

        ApplicationContext context=  new AnnotationConfigApplicationContext(HibernateUtil.class);

        SessionFactory sessionFactory= context.getBean(SessionFactory.class);

        if (sessionFactory != null)
            System.out.println("SessionFactory is configured correctly.");
        else
            System.out.println("SessionFactory not found.");

        HibernateTransactionManager txManager = context.getBean(HibernateTransactionManager.class);
        if (txManager != null)
            System.out.println("Transaction Manager is configured correctly.");
        else
            System.out.println("Transaction Manager not found.");



    }
}
