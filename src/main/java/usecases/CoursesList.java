package usecases;

import entities.Course;
import lombok.Getter;
import lombok.Setter;
import persistence.CoursesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
@RequestScoped
public class CoursesList {

    @Inject
    private CoursesDAO coursesDAO;

    @Getter
    @Setter
    private Course course = new Course();


    @Getter
    private List<Course> coursesList;


    @PostConstruct
    public void init(){
        loadAllCourses();
    }

    private void loadAllCourses() {
        this.coursesList = coursesDAO.loadAll();
    }

    @Transactional
    public String createCourse() {
        this.coursesDAO.persist(course);

        return "index?faces-redirect=true";
    }
}
