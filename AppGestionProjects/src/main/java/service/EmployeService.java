package service;

import dao.IDao;
import entities.Employe;
import entities.Projet;
import entities.Tache;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public class EmployeService implements IDao<Employe> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean create(Employe e) {
        Session session = sessionFactory.getCurrentSession();
        session.save(e);
        return true;
    }

    @Override
    public Employe findById(int id) {
        return sessionFactory.getCurrentSession().get(Employe.class, id);
    }


    public Employe getById(int id) {
        return sessionFactory.getCurrentSession().get(Employe.class, id);
    }

    public List<Employe> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from EmployeTache", Employe.class)
                .list();
    }

    public List<Tache> tachByEmploye(Employe employe) {
        Session session = sessionFactory.getCurrentSession();
        return session.createNamedQuery("tacheByEmploye", Tache.class)
                .setParameter("employe", employe)
                .list();
    }

    public List<Projet> projetByEmploye(Employe employe) {
        Session session = sessionFactory.getCurrentSession();
        return session.createNamedQuery("projetByEmploye", Projet.class)
                .setParameter("employe", employe)
                .list();
    }
}
