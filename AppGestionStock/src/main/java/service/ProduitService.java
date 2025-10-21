package service;

import dao.IDao;
import entities.Categorie;
import entities.Produit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProduitService implements IDao<Produit> {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public boolean create(Produit p) {
        Session session = sessionFactory.getCurrentSession();
        session.save(p);
        return true;
    }

    @Override
    public boolean update(Produit p) {
        Session session = sessionFactory.getCurrentSession();
        session.update(p);
        return true;
    }

    @Override
    public boolean delete(Produit p) {
        sessionFactory.getCurrentSession().delete(p);
        return true;
    }

    @Override
    public Produit findbyId(int id) {
        return sessionFactory.getCurrentSession().get(Produit.class, id);
    }

    @Override
    public List<Produit> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Product", Produit.class)
                .list();
    }
}

