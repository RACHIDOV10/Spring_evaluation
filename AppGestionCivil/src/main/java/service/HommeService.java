package service;

import dao.IDao;
import entites.Femme;
import entites.Homme;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;
@Repository
@Transactional
public class HommeService implements IDao<Homme> {

    @Autowired
    private SessionFactory sessionFactory ;

    @Override
    public boolean create(Homme h) {
        Session session = sessionFactory.getCurrentSession();
        session.save(h);
        return true;
    }

    @Override
    public Homme getById(int id) {
        return sessionFactory.getCurrentSession().get(Homme.class, id);
    }

    @Override
    public List<Homme> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from EmployeTache", Homme.class)
                .list();
    }

    public List<Femme> femmeByHomme(Homme homme, Date d1, Date d2) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createNamedQuery("femmeByHomme", Femme.class);
        query.setParameter("homme", homme);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        return query.getResultList();
    }






}
