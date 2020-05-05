package usecases;

import entities.Grade;
import entities.Student;
import lombok.Getter;
import lombok.Setter;
import persistence.GradesDAO;
import persistence.StudentsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;
import java.util.List;

@Model
@RequestScoped
public class StudentGrades {

    @Inject
    private StudentsDAO studentsDAO;

    @Inject
    private GradesDAO gradesDAO;

    @Getter
    @Setter
    private Student student = new Student();

    @Getter
    @Setter
    private Grade gradeToCreate = new Grade();

    @Getter
    private List<Grade> gradeList;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer studentId = Integer.parseInt(requestParameters.get("studentId"));

        this.student = studentsDAO.findOne(studentId);
        loadStudentGrades(studentId);
    }

    private void loadStudentGrades(Integer studentId) {this.gradeList = gradesDAO.loadAll(studentId);}

    @Transactional
    public String createGrade() {
        gradeToCreate.setStudent(this.student);
        gradesDAO.persist(gradeToCreate);

        return "studentGrades?faces-redirect=true&studentId=" + this.student.getId();
    }
}
