package View;

import Controllers.MainController;
import DAO.MyJDBC;
import Service.ExportingCSV;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

public class DisplayAllStudents extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();

    public JLabel search = new JLabel();

    public JTextField searchText = new JTextField();

    public JPanel MyUpperSection = new JPanel();
    public JPanel MySearchPanel = new JPanel();
    public JPanel MyMiddleSection = new JPanel();

    public JButton Quit = new JButton();

    public JButton Back = new JButton();

    public JLabel Wlcm = new JLabel();

    public JButton Delete = new JButton();

    public JButton SearchB = new JButton();

    public JButton ExportCSV = new JButton();



    public DisplayAllStudents(){

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
        MyUpperSection.setBounds(10,10, 780, 50);

        //Wlcm Label
        Wlcm.setText("Student's registration App - Display all students");
        Wlcm.setBounds(10,10, 400,30);

        //Quit icon
        ImageIcon icon = new ImageIcon("media/exit.png");
        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        Quit.setIcon(scaledIcon);
        Quit.setBounds(740, 10, 30,30);
        Quit.setBorder(new EmptyBorder(0, 0, 0, 0));

        //Back icon
        ImageIcon icon1 = new ImageIcon("media/back.png");
        ImageIcon scaledIcon1 = new ImageIcon(icon1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
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

        //MySearchPanel section
        MySearchPanel.setLayout(null);
        MySearchPanel.setOpaque(false);
        MySearchPanel.setBounds(10,70, 780, 50);
        search.setText("Search ");
        search.setForeground(Color.white);
        search.setBounds(10,10, 100, 25);
        //MySearchPanel.add(search);
        searchText.setBounds(10, 10, 300, 25);
        MySearchPanel.add(searchText);

        SearchB.setText("Search");
        SearchB.setBackground(Color.decode("#D7F4F7"));
        SearchB.setBounds(320 ,10, 90, 25);
        SearchB.setBorder(new EmptyBorder(0, 0, 0, 0));
        MySearchPanel.add(SearchB);

        ExportCSV.setText("Export CSV");
        ExportCSV.setBackground(Color.decode("#17F914"));
        ExportCSV.setBounds(670 ,10, 90, 25);
        ExportCSV.setBorder(new EmptyBorder(0, 0, 0, 0));
        MySearchPanel.add(ExportCSV);


        //Middle section
        MyMiddleSection.setLayout(null);
        MyMiddleSection.setOpaque(false);
        MyMiddleSection.setBounds(10,120, 780, 400);

        String columnNames[]  = {"idstudents ","nom", "prenom", "cne", "telephone"};

        Object[][] data = MyJDBC.FetchStudentsFromDB();
        System.out.println(data);

        //JTable DisplayTable = new JTable(data, columnNames);
        JTable DisplayTable = new JTable();
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        DisplayTable.setModel(model);
        DisplayTable.setBackground(Color.decode("#D7F4F7"));
        JScrollPane scrollPane = new JScrollPane(DisplayTable);
        scrollPane.setBounds(10,10,760,300);

        Delete.setText("Delete");
        Delete.setBackground(Color.decode("#F0756F"));
        Delete.setBounds(350,350, 90, 30);
        Delete.setBorder(new EmptyBorder(0, 0, 0, 0));

        MyMiddleSection.add(Delete);
        MyMiddleSection.add(scrollPane);



        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = DisplayTable.getSelectedRow();
                String nom = DisplayTable.getValueAt(selectedRow, 1).toString();
                String prenom = DisplayTable.getValueAt(selectedRow, 2).toString();
                DisplayTable.clearSelection();
                if (selectedRow != -1) { // check if a row is selected
                    DefaultTableModel model = (DefaultTableModel) DisplayTable.getModel();
                    int id = (int) model.getValueAt(selectedRow, 0); // get the ID from the first column
                    MyJDBC.deleteRowFromDatabase(id); // delete the row from the database
                    model.removeRow(selectedRow); // remove the row from the table model
                    JOptionPane.showMessageDialog(null, "Student " + nom +" "+ prenom + " Removed", "Success", JOptionPane.INFORMATION_MESSAGE);
                    //model.fireTableDataChanged();
                    //DisplayTable.revalidate(); // revalidate the JTable to update the view
                }
                /*int selectedRow = DisplayTable.getSelectedRow();
                if (selectedRow != -1) { // check if a row is selected
                    TableModel model = DisplayTable.getModel();
                    int id = (int) model.getValueAt(selectedRow, 0); // get the ID from the first column
                    System.out.println(id);
                    //MyJDBC.deleteRowFromDatabase(id); // delete the row from the database

                }*/
            }
        });

        ExportCSV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File myFile = new File("C://Users//astro//Desktop//data.csv");
                try {
                    int expt = new ExportingCSV().exportToCSV(DisplayTable, myFile);
                    if (expt == 1){
                        JOptionPane.showMessageDialog(null, "Data exported", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } );
        class MyTableModelListener implements TableModelListener {
            public void tableChanged(TableModelEvent e) {
                try {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    TableModel model = (TableModel)e.getSource();
                    String columnName = model.getColumnName(column);
                    Object data = model.getValueAt(row, column);
                    updateDatabase(row, columnName, data);
                } catch (Exception ex) {
                    System.out.println("Row deleted");
                }
            }

            private void updateDatabase(int row, String columnName, Object data) {
                MyJDBC.updateDB(row, columnName,data, DisplayTable);
            }
        }
        SearchB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchText.getText().toLowerCase();
                Object [][] newData = MyJDBC.searchField(searchTerm);
                TableModel modelU = new DefaultTableModel(newData, columnNames);
                DisplayTable.setModel(modelU);
                DisplayTable.getModel().addTableModelListener(new MyTableModelListener());
            }
        });

        DisplayTable.clearSelection();
        DisplayTable.getModel().addTableModelListener(new MyTableModelListener());

        getContentPane().add(MySearchPanel);
        getContentPane().add(MyMiddleSection);
        getContentPane().add(MyUpperSection);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DisplayAllStudents();
    }
}
