package service;

import dao.IDao;
import entities.Categorie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public class CategorieService implements IDao<Categorie> {

    @Autowired
    private  SessionFactory sessionFactory;

    @Override
    public boolean create(Categorie c) {
        Session session = sessionFactory.getCurrentSession();
        session.save(c);
        return true;

    }

    @Override
    public boolean update(Categorie c) {
        Session session = sessionFactory.getCurrentSession();
        session.update(c);
        return true;
    }

    @Override
    public boolean delete(Categorie c) {
        sessionFactory.getCurrentSession().delete(c);
        return true;
    }

    @Override
    public Categorie findbyId(int id) {
        return sessionFactory.getCurrentSession().get(Categorie.class, id);
    }

    @Override
    public List<Categorie> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Product", Categorie.class)
                .list();
    }
}
