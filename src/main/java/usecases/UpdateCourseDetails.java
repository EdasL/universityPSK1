package usecases;

import entities.Course;
import interceptors.LoggedInvocation;
import lombok.Getter;
import lombok.Setter;
import persistence.CoursesDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdateCourseDetails implements Serializable {

    private Course course;

    @Inject
    private CoursesDAO coursesDAO;

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer courseId = Integer.parseInt(requestParameters.get("courseId"));

        this.course = coursesDAO.findOne(courseId);
    }

    @Transactional
    @LoggedInvocation
    public String updateCourse(){
        try {
            coursesDAO.update(this.course);
        } catch (OptimisticLockException e) {
            return "/courseDetails.xhtml?faces-redirect=true&courseId=" + this.course.getId() + "&error=optimistic-lock-exception";
        }

        return "index.xhtml?&faces-redirect=true";
    }
}
