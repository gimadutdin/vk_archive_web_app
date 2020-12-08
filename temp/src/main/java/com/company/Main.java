package com.company;

import java.io.*;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Map;

//import com.google.gson.Gson;
//import org.jsoup.Jsoup;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Document;

//import javax.swing.text.Document;
//import static com.company.VKPageParser.connectDataUser;

public class Main {

    public static void main(String[] args) throws IOException {
        String token = "d1adb2dad1adb2dad1adb2da59d1d9d642dd1add1adb2da8e2b9de42ba576790649c910";
        String versionAPI = "5.103"; //current version

        //String exampleURL = "nikita.vorobev99";
        //String exampleURL = "durov";
        String exampleURL = "id414423500";
        StringBuilder sb = new StringBuilder(exampleURL);

        VKPageParser parser = new VKPageParser(token, versionAPI);
        Document doc = parser.connectDataUser(sb);
        System.out.println(doc.text());
        String json = doc.text();

        JSONObject jsonObject = new JSONObject(json);
        JSONArray respArr = jsonObject.getJSONArray("response");
        JSONObject mainInfo = respArr.getJSONObject(0);
        try {
            String firstNameText = mainInfo.getString("first_name");
            System.out.println("First Name = " + firstNameText);

            String lastNameText = mainInfo.getString("last_name");
            System.out.println("Last name = " + lastNameText);

            String cityTitleText = mainInfo.getJSONObject("city").getString("title");
            System.out.println("City = " + cityTitleText);

            String countyTitleText = mainInfo.getJSONObject("country").getString("title");
            System.out.println("Country = " + countyTitleText);

            String birthDateText = mainInfo.getString("bdate");
            System.out.println("Birth date = " + birthDateText);

            String instagramText = mainInfo.getString("instagram");
            System.out.println("Insta = " + instagramText);

            String universityNameText = mainInfo.getString("university_name");
            System.out.println("University = " + universityNameText);

            String photoURLText = mainInfo.getString("photo_max_orig");
            System.out.println("Photo = " + photoURLText);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //String friendsTitleText = mainInfo.getString("friends");
        //System.out.println("Friends = " + friendsTitleText);
        System.out.println(jsonObject);
    }
}
