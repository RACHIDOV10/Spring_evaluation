package service;

import dao.IDao;
import entities.Employe;
import entities.EmployeTache;
import entities.Projet;
import entities.Tache;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Repository
@Transactional
public class TacheService implements IDao<Tache> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean create(Tache t) {
        Session session = sessionFactory.getCurrentSession();
        session.save(t);
        return true;
    }

    @Override
    public Tache findById(int id) {
        return sessionFactory.getCurrentSession().get(Tache.class, id);
    }


    public List<EmployeTache> employeTachebyTache(Tache tache) {
        Session session = sessionFactory.getCurrentSession();
        return session.createNamedQuery("employeTachebyTache", EmployeTache.class)
                .setParameter("tache", tache)
                .list();
    }

    public List<Tache> tacheByPrix(double prix) {
        Session session = sessionFactory.getCurrentSession();
        return session.createNamedQuery("tachesByPrix", Tache.class)
                .setParameter("prix", prix)
                .list();
    }

    public List<Tache> tacheByDate(Date d1, Date d2) {
        Session session = sessionFactory.getCurrentSession();
        return session.createNamedQuery("tacheByDate", Tache.class)
                .setParameter("d1", d1)
                .setParameter("d2", d2)
                .list();
    }
}


