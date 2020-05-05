package persistence;

import entities.Course;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CoursesDAO {

    @Inject
    private EntityManager em;

    public void persist(Course course){
        this.em.persist(course);
    }

    public Course findOne(Integer id){
        return em.find(Course.class, id);
    }

    public Course update(Course course){
        return em.merge(course);
    }

    public List<Course> loadAll() {
        return em.createNamedQuery("Course.findAll", Course.class).getResultList();
    }
}
