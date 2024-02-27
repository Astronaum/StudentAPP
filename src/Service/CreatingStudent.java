package Service;

import DAO.MyJDBC;
import Entity.Student;

public class CreatingStudent {

    public Student CreatingStudent(String nom, String prenom, String cne, String telephone){
        Student NewStudent = new Student(nom, prenom, cne, telephone);
        MyJDBC DBInsert = new MyJDBC();
        DBInsert.AddStudentToDB(NewStudent);
        System.out.println(NewStudent.toString());
        return NewStudent;
    }
}
