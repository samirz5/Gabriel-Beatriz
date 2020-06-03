package Logic.Models;

import java.util.List;

public class SchoolClass {
    private List<Player> studentList;
    private Admin teacher;

    public List<Player> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Player> studentList) {
        this.studentList = studentList;
    }

    public Admin getTeacher() {
        return teacher;
    }

    public void setTeacher(Admin teacher) {
        this.teacher = teacher;
    }
}
