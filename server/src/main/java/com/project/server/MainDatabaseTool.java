package com.project.server;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MainDatabaseTool {

    public MainDatabaseTool() {
       //Connection conn = connect();
    }

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:database/main.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public String selectAll(){
        String sql = "SELECT user_id, fio, city, birthday, study_place, friends_number FROM users";
        String result = "";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set

            while (rs.next()) {
                result += (rs.getString("user_id") +  "\t" +
                        rs.getString("fio") + "\t" +
                        rs.getString("city") + "\t" +
                        rs.getString("birthday") + "\t" +
                        rs.getString("study_place") + "\t" +
                        rs.getInt("friends_number") + "<br>");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
