package service;

import dao.IDao;
import entities.Commande;
import entities.Produit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CommandeService implements IDao<Commande> {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public boolean create(Commande co) {
        Session session = sessionFactory.getCurrentSession();
        session.save(co);
        return true;
    }

    @Override
    public boolean update(Commande co) {
        Session session = sessionFactory.getCurrentSession();
        session.update(co);
        return true;
    }

    @Override
    public boolean delete(Commande co) {
        sessionFactory.getCurrentSession().delete(co);
        return true;
    }

    @Override
    public Commande findbyId(int id) {
        return sessionFactory.getCurrentSession().get(Commande.class, id);
    }

    @Override
    public List<Commande> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Product", Commande.class)
                .list();
    }
}

