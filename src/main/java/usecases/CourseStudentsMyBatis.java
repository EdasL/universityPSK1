package usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import lombok.Getter;
import lombok.Setter;
import mybatis.dao.CourseStudentsMapper;
import mybatis.dao.StudentMapper;
import mybatis.model.CourseStudents;
import mybatis.model.Student;
import persistence.CoursesDAO;
import persistence.StudentsDAO;
import entities.Course;

import java.util.List;
import java.util.Map;


@Model
@RequestScoped
public class CourseStudentsMyBatis {

    @Inject
    private StudentMapper studentMapper;
    @Inject
    private CourseStudentsMapper courseStudentsMapper;

    @Getter
    @Setter
    private CourseStudents courseStudents = new CourseStudents();

    @Getter
    @Setter
    private Course course = new Course();

    @Inject
    private CoursesDAO coursesDAO;

    @Getter
    @Setter
    private List<Student> allStudents;

    @Getter
    @Setter
    private List<Student> enrolledStudents;

    @Inject
    StudentsDAO studentsDAO;

    @Getter
    @Setter
    private Student student = new Student();


    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer courseId = Integer.parseInt(requestParameters.get("courseId"));

        this.course = coursesDAO.findOne(courseId);
        this.loadAllStudents();
        this.getEnrolledStudents(courseId);
    }

    private void loadAllStudents() { this.allStudents = studentMapper.selectAll(); }
    private void getEnrolledStudents(Integer courseId) {
        this.enrolledStudents = studentMapper.getEnrolledStudents(courseId);
    }

    @Transactional
    public String createStudent() {
        studentMapper.insert(student);

        return "students?faces-redirect=true&courseId=" + this.course.getId();
    }

    @Transactional
    public String addStudent() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer studentId = Integer.parseInt(requestParameters.get("studentId"));

        courseStudents.setCoursesId(this.course.getId());
        courseStudents.setStudentsStudentId(studentId);
        courseStudentsMapper.insert(courseStudents);

        return "students?faces-redirect=true&courseId=" + this.course.getId();
    }

}
