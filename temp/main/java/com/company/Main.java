package com.company;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {

    public static void main(String[] args) {
        String token = "d1adb2dad1adb2dad1adb2da59d1d9d642dd1add1adb2da8e2b9de42ba576790649c910";
        String versionAPI = "<5.103>"; //current version
        String domain = "<group id>";
        String connectionUrl = "jdbc:mysql://localhost:3306/test?useSSL=false";

        (new VKPageParser(token, versionAPI, domain, new DataBase("root", "root", connectionUrl))).getUsers();
    }
}
