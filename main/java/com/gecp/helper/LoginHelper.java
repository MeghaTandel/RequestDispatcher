package com.gecp.helper;

import com.gecp.beans.LoginBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginHelper {

    private Connection getConnection() {

        Connection con = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/csedept", "root", "root");
            System.out.println("connection succssful");
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println(exception);
        }
        return con;
    }

    public boolean validateUser(LoginBean lb) {
        Connection con = getConnection();
        if (con != null) {
            try {
                PreparedStatement ps;
                String query = "SELECT * FROM persons WHERE userName = ? AND password = ?";
                ps = con.prepareStatement(query);

                ps.setString(1, lb.getUserName());
                ps.setString(2, lb.getPassword());
                System.out.println("Query : " + ps.toString());
                ResultSet rs = ps.executeQuery();
                return rs.next();

            } catch (SQLException ex) {
                Logger.getLogger(LoginHelper.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        return false;
    }

    public ArrayList<LoginBean> fetchAllUsers() {
        ArrayList<LoginBean> results = new ArrayList<>();
        Connection con = getConnection();
        if (con != null) {
            try {
                PreparedStatement ps;
                String query = "SELECT * FROM persons";
                ps = con.prepareStatement(query);
                System.out.println("Query : " + ps.toString());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    // Assuming YourObject has a constructor that takes column1 and column2
                    LoginBean lb = new LoginBean(rs.getInt("id"), rs.getString("userName"), rs.getString("password"), rs.getInt("age"));
                    results.add(lb);
                }

            } catch (SQLException ex) {
                Logger.getLogger(LoginHelper.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        return results;

    }

    public LoginBean fetchUser(int id) {
        LoginBean lb = null;
        Connection con = getConnection();
        if (con != null) {
            try {
                PreparedStatement ps;
                String query = "SELECT * FROM persons WHERE id = ?";
                ps = con.prepareStatement(query);
                ps.setInt(1, id);
                System.out.println("Query : " + ps.toString());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    // Assuming YourObject has a constructor that takes column1 and column2
                    lb = new LoginBean(rs.getInt("id"), rs.getString("userName"), rs.getString("password"), rs.getInt("age"));
                }

            } catch (SQLException ex) {
                Logger.getLogger(LoginHelper.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        return lb;
    }

    public void editUser(LoginBean loginBean) {
        Connection con = getConnection();
        if (con != null) {

            try {
                PreparedStatement ps = con.prepareStatement(
                        "UPDATE persons SET  userName = ?, password = ?, age = ? WHERE id = ?");

                // set the preparedstatement parameters
                ps.setString(1, loginBean.getUserName());
                ps.setString(2, loginBean.getPassword());
                ps.setInt(3, loginBean.getAge());
                ps.setInt(4, loginBean.getId());
                System.out.println("Query : " + ps.toString());
                // call executeUpdate to execute our sql update statement
                ps.executeUpdate();
                ps.close();

            } catch (SQLException ex) {
                Logger.getLogger(LoginHelper.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void deleteUser(int userId) {
        Connection con = getConnection();
        if (con != null) {

            try {
                PreparedStatement ps = con.prepareStatement(
                        "DELETE FROM persons WHERE id = ?");

                // set the preparedstatement parameters
                ps.setInt(1, userId);
                System.out.println("Query : " + ps.toString());
                // call executeUpdate to execute our sql update statement
                ps.executeUpdate();
                ps.close();

            } catch (SQLException ex) {
                Logger.getLogger(LoginHelper.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
