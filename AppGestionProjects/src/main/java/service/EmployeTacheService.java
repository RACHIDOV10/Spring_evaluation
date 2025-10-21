package service;

import dao.IDao;
import entities.Employe;
import entities.EmployeTache;
import entities.Projet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public class EmployeTacheService implements IDao<EmployeTache> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean create(EmployeTache e) {
        Session session = sessionFactory.getCurrentSession();
        session.save(e);
        return true;
    }

    @Override
    public EmployeTache findById(int id) {
        return sessionFactory.getCurrentSession().get(EmployeTache.class, id);
    }


    public EmployeTache getById(int id) {
        return sessionFactory.getCurrentSession().get(EmployeTache.class, id);
    }

    public List<EmployeTache> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from EmployeTache", EmployeTache.class)
                .list();
    }

    public List<EmployeTache> employeTacheByTache(int tacheId) {
        return sessionFactory.getCurrentSession()
                .createNamedQuery("employeTachebyTache", EmployeTache.class)
                .setParameter("tacheId", tacheId)
                .list();
    }
}

