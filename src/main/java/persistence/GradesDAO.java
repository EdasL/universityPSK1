package persistence;

import entities.Grade;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class GradesDAO {

    @Inject
    private EntityManager em;

    public void persist(Grade grade){
        this.em.persist(grade);
    }

    public Grade findOne(Integer id){
        return em.find(Grade.class, id);
    }

    public Grade update(Grade grade){
        return em.merge(grade);
    }

    public List<Grade> loadAll(Integer studentId) {
        return em.createNamedQuery("Grade.findAll", Grade.class).setParameter("studentId", studentId).getResultList();
    }
}
