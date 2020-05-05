package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "select s from Student as s")
})
@Table(name = "STUDENT")
@Getter @Setter
public class Student {

    public Student(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private Integer id;

    private String name;
    private String lastname;

    @OneToMany(mappedBy = "student")
    private List<Grade> grades = new ArrayList<>();

    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student  student = (Student) o;

        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
