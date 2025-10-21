package service;

import dao.IDao;
import entities.Categorie;
import entities.CommandeProduit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CommandeProduitService implements IDao<CommandeProduit> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean create( CommandeProduit cp) {
        Session session = sessionFactory.getCurrentSession();
        session.save(cp);
        return true;

    }

    @Override
    public boolean update(CommandeProduit cp) {
        Session session = sessionFactory.getCurrentSession();
        session.update(cp);
        return true;
    }

    @Override
    public boolean delete(CommandeProduit cp) {
        sessionFactory.getCurrentSession().delete(cp);
        return true;
    }

    @Override
    public CommandeProduit findbyId(int id) {
        return sessionFactory.getCurrentSession().get(CommandeProduit.class, id);
    }

    @Override
    public List<CommandeProduit> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Product", CommandeProduit.class)
                .list();
    }
}
