package View;

import Controllers.AddStudentController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LandingPage extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();

    public JPanel MyUpperSection = new JPanel();

    public JPanel MyMiddleSection = new JPanel();
    public JButton Quit = new JButton();

    public JLabel Wlcm = new JLabel();

    public JButton Register = new JButton();

    public JButton Display = new JButton();

    public JLabel Register_label = new JLabel();

    public JLabel Display_label = new JLabel();

    public boolean RegisterValue = false;

    public boolean DisplayValue = false;


    public LandingPage(){

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
        MyUpperSection.setBackground(Color.decode("#F2ECF5"));
            /*Border border = new LineBorder(Color.decode("#CADEF2"), 3, true);
            MyUpperSection.setBorder(border);*/
        MyUpperSection.setBounds(10,10, 780, 50);

        //Wlcm Label
        Wlcm.setText("Student's registration App");
        Wlcm.setBounds(10,10, 400,30);

        //Quit icon
        ImageIcon icon = new ImageIcon("media/exit.png");
        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
        Quit.setIcon(scaledIcon);
        Quit.setBounds(740, 10, 30,30);
        Quit.setBorder(new EmptyBorder(0, 0, 0, 0));

        //Adding components
        MyUpperSection.add(Wlcm);
        MyUpperSection.add(Quit);

        Quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        } );

        //Middle Section
        MyMiddleSection.setLayout(null);
        MyMiddleSection.setOpaque(false);
            /*Border border = new LineBorder(Color.decode("#CADEF2"), 3, true);
            MyUpperSection.setBorder(border);*/
        MyMiddleSection.setBounds(10,250, 780, 100);

        //Buttons
        //Register Icon
        ImageIcon icon1 = new ImageIcon("media/register.png");
        ImageIcon scaledIcon1 = new ImageIcon(icon1.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
        Register.setIcon(scaledIcon1);
        Register.setBounds(200, 20, 30,30);
        Register.setBorder(new EmptyBorder(0, 0, 0, 0));
        Register_label.setText("Register a new student");
        Register_label.setBounds(150,60, 150,30);
        MyMiddleSection.add(Register_label);
        MyMiddleSection.add(Register);

        //Display Icon
        ImageIcon icon2 = new ImageIcon("media/computer.png");
        ImageIcon scaledIcon2 = new ImageIcon(icon2.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
        Display.setIcon(scaledIcon2);
        Display.setBounds(550, 20, 30,30);
        Display.setBorder(new EmptyBorder(0, 0, 0, 0));
        Display_label.setText("Display all students");
        Display_label.setBounds(500,60, 150,30);
        MyMiddleSection.add(Display_label);
        MyMiddleSection.add(Display);

        getContentPane().add(MyMiddleSection);
        getContentPane().add(MyUpperSection);
        setVisible(true);

    }


    /*public static void main(String[] args) {
        new LandingPage();
    }*/
}
