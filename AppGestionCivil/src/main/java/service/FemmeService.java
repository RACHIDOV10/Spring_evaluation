package service;

import dao.IDao;
import entites.Femme;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Repository
@Transactional
public class FemmeService implements IDao<Femme> {

    @Autowired
    private SessionFactory sessionFactory ;


    @Override
    public boolean create(Femme f) {
        Session session = sessionFactory.getCurrentSession();
        session.save(f);
        return true;
    }

    @Override
    public Femme getById(int id) {
        return sessionFactory.getCurrentSession().get(Femme.class, id);
    }

    @Override
    public List<Femme> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from EmployeTache", Femme.class)
                .list();
    }

    public int enfantFemme(Femme femme, Date d1, Date d2) {
        Session session = sessionFactory.getCurrentSession();
        Object result = session.getNamedQuery("enfantFemme")
                .setParameter("id", femme.getId())
                .setParameter("d1", d1)
                .setParameter("d2", d2)
                .uniqueResult();

        // Comme SUM peut retourner null si aucun enregistrement, on gère ce cas
        if (result == null) return 0;
        return ((Number) result).intValue();
    }

    // --- Méthode pour récupérer les femmes mariées au moins 'anne' fois ---
    public List<Femme> femmeMarriageCount(int anne) {
        Session session = sessionFactory.getCurrentSession();
        Query<Femme> query = session.getNamedQuery("femmeMarriageCount");
        query.setParameter("anne", (long) anne); // COUNT retourne un long
        return query.getResultList();
    }
}