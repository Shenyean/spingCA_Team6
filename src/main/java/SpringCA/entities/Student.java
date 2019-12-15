package SpringCA.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Student extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
    private String status;
    private String level;
    @ManyToOne
    @JoinColumn(name = "semesterId")
    private Semester semesterEnrolled;

    @Column(columnDefinition = "integer default 0")
    private int creditNo;
    private float GPA;

    @ManyToOne
    @JoinColumn(name = "degreeId")
    private Degree degree;

    @OneToMany(mappedBy = "studentByCourse")
    private List<StudentCourse> studentListByCourse;

    @OneToOne(mappedBy = "studentUser")
    private StudentUser studentUser;

    public Student() {
    }

    public Student(String firstName, String middleName, String lastName, String gender, Date birthDate,
                   String address, String email, String mobile, String status, String level, Semester semesterEnrolled,
                   float GPA, int creditNo, Degree degree) {
        super(firstName, middleName, lastName, gender, birthDate, address, email, mobile);
        this.status = status;
        this.level = level;
        this.semesterEnrolled = semesterEnrolled;
        this.GPA = GPA;
        this.creditNo = creditNo;
        this.degree = degree;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Semester getSemesterEnrolled() {
        return semesterEnrolled;
    }

    public void setSemesterEnrolled(Semester semesterEnrolled) {
        this.semesterEnrolled = semesterEnrolled;
    }

    public float getGPA() {
        return GPA;
    }

    public void setGPA(float GPA) {
        this.GPA = GPA;
    }

    public int getCreditNo() {
        return creditNo;
    }

    public void setCreditNo(int creditNo) {
        this.creditNo = creditNo;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }
}
