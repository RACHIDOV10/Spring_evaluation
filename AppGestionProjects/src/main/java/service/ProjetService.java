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
public class ProjetService implements IDao<Projet> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean create(Projet p) {
        Session session = sessionFactory.getCurrentSession();
        session.save(p);
        return true;
    }

    @Override
    public Projet findById(int id) {
        return sessionFactory.getCurrentSession().get(Projet.class, id);
    }


    public Projet getById(int id) {
            return sessionFactory.getCurrentSession().get(Projet.class, id);
        }

    public List<Tache> tacheByProjet(Projet projet){
        Session session = sessionFactory.getCurrentSession();
        return session.createNamedQuery("tacheByProjet", Tache.class)
                .setParameter("projet", projet)
                .list();

    }
}
