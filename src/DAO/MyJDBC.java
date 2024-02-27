package DAO;

import Entity.Student;

import javax.swing.*;
import java.sql.*;

public class MyJDBC {

    public static void deleteRowFromDatabase(int id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsdb", "root", "root");
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM students WHERE idstudents = " + id;
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static Object[][] searchField(String searchTerm) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsdb", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(conn);

        // Create a SQL query
        String query = "SELECT * FROM students WHERE nom LIKE '%" + searchTerm + "%' OR prenom LIKE '%" + searchTerm + "%' OR cne LIKE '%" + searchTerm + "%' OR telephone LIKE '%" + searchTerm + "%'";

        // Execute the SQL query and retrieve the result set
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int rowCount = 0;

        try {
            if (rs.last()) {
                rowCount = rs.getRow();
                rs.beforeFirst();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Object[][] data = new Object[rowCount][5];

        int i = 0;
        try {
            while (rs.next()) {
                data[i][0] = rs.getInt("idstudents");
                data[i][1] = rs.getString("nom");
                data[i][2] = rs.getString("prenom");
                data[i][3] = rs.getString("cne");
                data[i][4] = rs.getString("telephone");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int j = 0; j < rowCount; j++) {
            System.out.println(data[j][0] + " " + data[j][1] + " " + data[j][2]+ " " + data[j][3] + " " + data[j][4]);
        }

        return data;
    }


    public void AddStudentToDB(Student NewStudent) {
        String nom = NewStudent.nom;
        String prenom = NewStudent.prenom;
        String cne = NewStudent.cne;
        String telephone = NewStudent.telephone;

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsdb", "root", "root");
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO students (nom, prenom, cne, telephone)" +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, cne);
            preparedStatement.setString(4, telephone);

            int addedRows = preparedStatement.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object[][] FetchStudentsFromDB() {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsdb", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(conn);

        // Create a SQL query
        String query = "SELECT * FROM students";

        // Execute the SQL query and retrieve the result set
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int rowCount = 0;

        try {
            if (rs.last()) {
                rowCount = rs.getRow();
                rs.beforeFirst();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Object[][] data = new Object[rowCount][5];

        int i = 0;
        try {
            while (rs.next()) {
                data[i][0] = rs.getInt("idstudents");
                data[i][1] = rs.getString("nom");
                data[i][2] = rs.getString("prenom");
                data[i][3] = rs.getString("cne");
                data[i][4] = rs.getString("telephone");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int j = 0; j < rowCount; j++) {
            System.out.println(data[j][0] + " " + data[j][1] + " " + data[j][2]+ " " + data[j][3] + " " + data[j][4]);
        }

        return data;
    }

     public static void updateDB(int row, String columnName, Object data, JTable table){

         Connection conn = null;
         int columnIndex = table.getColumnModel().getColumnIndex(columnName);
         int rowIndex = table.getSelectedRow(); // get the index of the selected row
         int id = (int) table.getValueAt(rowIndex, 0); // get the value from the first column
         try {
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsdb", "root", "root");
         } catch (SQLException e) {
             e.printStackTrace();
         }
         System.out.println(conn);
         System.out.println(columnIndex);
         System.out.println(id);

         try {
             Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsdb", "root", "root");
             Statement stmt = connection.createStatement();

             String sql = "UPDATE students SET " + columnName + "=? WHERE idstudents=?";

             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             preparedStatement.setString(1, data.toString());
             preparedStatement.setInt(2,id);



             int addedRows = preparedStatement.executeUpdate();

         } catch (SQLException e) {
             e.printStackTrace();
         }
     }

}
