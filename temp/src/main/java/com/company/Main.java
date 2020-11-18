package com.company;
import java.io.*;
import org.jsoup.Jsoup;

import javax.swing.text.Document;

import static com.company.VKPageParser.connectDataUser;

public class Main {

    public static void main(String[] args) throws IOException {
        String token = "d1adb2dad1adb2dad1adb2da59d1d9d642dd1add1adb2da8e2b9de42ba576790649c910";
        String versionAPI = "5.103"; //current version
        String domain = "<group id>";
        String exampleURL = "https://vk.com/nikita.vorobev99";
        StringBuilder sb = new StringBuilder(exampleURL);
        //String connectionUrl = "jdbc:mysql://localhost:3306/test?useSSL=false";
        VKPageParser parser = new VKPageParser(token, versionAPI);
        Document doc = (Document) parser.connectDataUser(sb);
        System.out.println(doc);
        //(new VKPageParser(token, versionAPI, domain, new DataBase("root", "root", connectionUrl))).getUsers();
    }
}
