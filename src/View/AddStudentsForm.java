package View;

import Controllers.MainController;
import Service.CreatingStudent;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

public class AddStudentsForm extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();
    public JPanel MyUpperSection = new JPanel();

    public JPanel MyMiddleSection = new JPanel();
    public JButton Quit = new JButton();

    public JButton Back = new JButton();

    public JLabel Wlcm = new JLabel();

    public JLabel F1 = new JLabel();
    public JLabel F2 = new JLabel();
    public JLabel F3 = new JLabel();
    public JLabel F4 = new JLabel();

    public JTextField nom_input = new JTextField();
    public JTextField prenom_input = new JTextField();
    public JTextField cne_input = new JTextField();
    public JTextField telephone_input = new JTextField();

    private JButton register = new JButton();
    private JButton cancel = new JButton();

    public boolean RegistrationError = false;

    public boolean RegistrationStatus = false;

    public boolean RegisterValue = false;

    public AddStudentsForm(){
        getContentPane().setLayout(null);

        //Background Image Upload
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("media/bg.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        setContentPane(new JLabel(imageIcon));

        //Window Parameters
        setUndecorated(true);
        setBounds((int) (width/4), (int) (height/8),800, 600);

        //Upper section components
        MyUpperSection.setLayout(null);
        MyMiddleSection.setOpaque(false);
        MyUpperSection.setBounds(10,10, 780, 50);

        //Wlcm Label
        Wlcm.setText("Student's registration App - Registering form");
        Wlcm.setBounds(10,10, 400,30);

        //Quit icon
        ImageIcon icon = new ImageIcon("media/exit.png");
        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
        Quit.setIcon(scaledIcon);
        Quit.setBounds(740, 10, 30,30);
        Quit.setBorder(new EmptyBorder(0, 0, 0, 0));

        //Back icon
        ImageIcon icon1 = new ImageIcon("media/back.png");
        ImageIcon scaledIcon1 = new ImageIcon(icon1.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
        Back.setIcon(scaledIcon1);
        Back.setBounds(700, 10, 30,30);
        Back.setBorder(new EmptyBorder(0, 0, 0, 0));

        //Adding components
        MyUpperSection.add(Wlcm);
        MyUpperSection.add(Back);
        MyUpperSection.add(Quit);

        Quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        } );
        Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainController().begin();
                dispose();
            }
        } );

        //Middle Section
        MyMiddleSection.setLayout(null);
        MyMiddleSection.setBounds(10,100, 780, 400);

        //Fields
        F1.setText("Last name");
        F1.setBounds(140,30, 60, 30);
        nom_input.setBounds(300, 30, 300, 30);

        F2.setText("First name");
        F2.setBounds(140,90, 60, 30);
        prenom_input.setBounds(300, 90, 300, 30);

        F3.setText("CNE");
        F3.setBounds(140,150, 60, 30);
        cne_input.setBounds(300, 150, 300, 30);

        F4.setText("Phone number");
        F4.setBounds(140,210, 90, 30);
        telephone_input.setBounds(300, 210, 300, 30);

        cancel.setText("Cancel");
        cancel.setBackground(Color.decode("#F0756F"));
        cancel.setBounds(250,300, 90, 30);
        cancel.setBorder(new EmptyBorder(0, 0, 0, 0));
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainController().begin();
                dispose();
            }
        } );

        register.setText("Register");
        register.setBackground(Color.decode("#DAF7A6"));
        register.setBounds(400,300, 90, 30);
        register.setBorder(new EmptyBorder(0, 0, 0, 0));
        Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainController().begin();
                dispose();
            }
        } );
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterValue = true;
                System.out.println("Register button clicked");
                registerStudent();
                if (RegistrationError){
                    dispose();
                    new AddStudentsForm();
                }
                if(!RegistrationError && RegistrationStatus) {
                    JOptionPane optionPane = new JOptionPane("Registered with success !",JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                    optionPane.addPropertyChangeListener(new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            if (evt.getSource() == optionPane && evt.getPropertyName().equals(JOptionPane.VALUE_PROPERTY)) {
                                if (evt.getNewValue() instanceof Integer && (Integer) evt.getNewValue() == JOptionPane.OK_OPTION) {
                                    // Customize the behavior of the OK button here
                                    MainController MyMain = new MainController();
                                    MyMain.begin();
                                }
                            }
                        }
                    });

                    JDialog dialog = optionPane.createDialog("Registration Status");
                    dialog.setVisible(true);
                    dispose();
                }
            }
        });


        MyMiddleSection.add(F1);
        MyMiddleSection.add(nom_input);
        MyMiddleSection.add(F2);
        MyMiddleSection.add(prenom_input);
        MyMiddleSection.add(F3);
        MyMiddleSection.add(cne_input);
        MyMiddleSection.add(F4);
        MyMiddleSection.add(telephone_input);
        MyMiddleSection.add(register);
        MyMiddleSection.add(cancel);

        getContentPane().add(MyMiddleSection);
        getContentPane().add(MyUpperSection);
        setVisible(true);
    }
    public void registerStudent(){
        String nom = nom_input.getText();
        String prenom = prenom_input.getText();
        String cne = cne_input.getText();
        String telephone = telephone_input.getText();
        if( nom.isEmpty() || prenom.isEmpty() || telephone.isEmpty() || cne.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields !",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            RegistrationError = true;
        }else{
            CreatingStudent NewStudent = new CreatingStudent();
            NewStudent.CreatingStudent(nom, prenom, cne, telephone);
            RegistrationStatus = true;
        }
    }

    /*public static void main(String[] args) {
        new AddStudentsForm();
    }*/
}
