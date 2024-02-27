package Controllers;

import View.DisplayAllStudents;
import View.LandingPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    public void begin(){
        LandingPage MyLandingPage = new LandingPage();
        MyLandingPage.Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStudentController().begin();
                MyLandingPage.dispose();
            }
        });
        MyLandingPage.Display.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DisplayAllStudentsController().begin();
                MyLandingPage.dispose();
            }
        });


    }
}
