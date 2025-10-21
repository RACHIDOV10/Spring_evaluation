package service;

import dao.IDao;
import entites.Homme;
import entites.Mariage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public class MariageService implements IDao<Mariage> {

    @Autowired
    private SessionFactory sessionFactory ;

    @Override
    public boolean create(Mariage m) {
        Session session = sessionFactory.getCurrentSession();
        session.save(m);
        return true;
    }

    @Override
    public Mariage getById(int id) {
        return sessionFactory.getCurrentSession().get(Mariage.class, id);
    }

    @Override
    public List<Mariage> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from EmployeTache", Mariage.class)
                .list();
    }
    public List<Mariage> mariagesEnCours(Homme homme) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(
                        "from Mariage m where m.homme = :h and m.dateFin is null", Mariage.class)
                .setParameter("h", homme)
                .getResultList();
    }

    // Mariages échoués : dateFin != null
    public List<Mariage> mariagesEchoues(Homme homme) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(
                        "from Mariage m where m.homme = :h and m.dateFin is not null", Mariage.class)
                .setParameter("h", homme)
                .getResultList();
    }

}
