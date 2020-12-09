package com.project.server;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


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

    public boolean isUserPresent(String user_id) {
        String sql = String.format("SELECT user_id FROM users WHERE user_id = '%s'", user_id);
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public ObjectNode readUserInfo(String user_id) {
        String sql = String.format("SELECT * FROM users WHERE user_id = '%s'", user_id);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                //objectNode.put("user_id", rs.getString("user_id"));
                objectNode.put("fio", rs.getString("fio"));
                objectNode.put("city", rs.getString("city"));
                objectNode.put("birthday", rs.getString("birthday"));
                objectNode.put("study_place", rs.getString("study_place"));
                objectNode.put("friends_number", rs.getInt("friends_number"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return objectNode;
    }

    public void writeUserInfo(ObjectNode user_info) {
        String sql = "INSERT INTO USERS (USER_ID, FIO, CITY, BIRTHDAY, STUDY_PLACE, FRIENDS_NUMBER) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user_info.get("user_id").asText());
            pstmt.setString(2, user_info.get("fio").asText());
            pstmt.setString(3, user_info.get("city").asText());
            pstmt.setString(4, user_info.get("birthday").asText());
            pstmt.setString(5, user_info.get("study_place").asText());
            pstmt.setInt(6, user_info.get("friends_number").asInt());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String selectAll(){
        String sql = "SELECT user_id, fio, city, birthday, study_place, friends_number FROM users";
        String result = "";
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
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
