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

        String exampleURL = "nikita.vorobev99";
        //String exampleURL = "durov";
        StringBuilder sb = new StringBuilder(exampleURL);
        VKPageParser parser = new VKPageParser(token, versionAPI);

        Document docUser = parser.connectDataUser(sb);
        System.out.println(docUser.text());
        String json = docUser.text();

        JSONObject jsonObject = new JSONObject(json);
        JSONArray respArr = jsonObject.getJSONArray("response");
        System.out.println("respArr = " + respArr);
        JSONObject mainInfo = respArr.getJSONObject(0);
        System.out.println("mainInfo = " + mainInfo + '\n');

        Document docFriends = parser.connectDataFriends(sb);
        System.out.println(docFriends.text());
        String jsonFriends = docFriends.text();

        JSONObject jsonObjectFriends = new JSONObject(jsonFriends);
        JSONObject jsonObjFriends2 = jsonObjectFriends.getJSONObject("response");
        System.out.println("jsonObjFriends2 = " + jsonObjFriends2);
        JSONArray respArrFriends = jsonObjFriends2.getJSONArray("items");
        System.out.println("respArrFriends = " + respArrFriends);
        System.out.println();

        Document docPhotos = parser.connectDataPhotos(sb);
        System.out.println(docPhotos.text());
        String jsonPhotos = docPhotos.text();

        JSONObject jsonObjectPhotos = new JSONObject(jsonPhotos);
        JSONObject jsonObjPhotos2 = jsonObjectPhotos.getJSONObject("response");
        System.out.println("jsonObjPhotos2 = " + jsonObjPhotos2);
        JSONArray respArrPhotos = jsonObjPhotos2.getJSONArray("items");
        System.out.println("respArrPhotos = " + respArrPhotos);
        //JSONObject temp = respArrPhotos.getJSONObject(0);
        //System.out.println("temp = " + temp);
        //JSONArray respArrPhotos2 = temp.getJSONArray("sizes");
        //System.out.println("respArrPhotos2 = " + respArrPhotos2);
        System.out.println();

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

            //String instagramText = mainInfo.getString("instagram");
            //System.out.println("Insta = " + instagramText);

            //String universityNameText = mainInfo.getString("university_name");
            //System.out.println("University = " + universityNameText);

            String photoURLText = mainInfo.getString("photo_max_orig");
            System.out.println("Photo = " + photoURLText);

            for (int i = 0; i < jsonObjFriends2.getInt("count"); i++) {
                JSONObject mainInfoFriends = respArrFriends.getJSONObject(i);
                String friendsFirstNames = mainInfoFriends.getString("first_name");
                String friendsLastNames = mainInfoFriends.getString("last_name");
                System.out.println(i + ") First_name = " + friendsFirstNames + ", Lat_name = " + friendsLastNames + ';');
            }
            for (int i = 0; i < jsonObjPhotos2.getInt("count"); i++) {
                JSONObject temp = respArrPhotos.getJSONObject(i);
                //System.out.println("temp = " + temp);
                JSONArray respArrPhotos2 = temp.getJSONArray("sizes");
                //System.out.println("respArrPhotos2 = " + respArrPhotos2);
                for (int j = 0; j < jsonObjPhotos2.getInt("count"); j++) {
                    JSONObject mainInfoPhotos = respArrPhotos2.getJSONObject(j);
                    //System.out.println(mainInfoPhotos);
                    String linkPhotos = mainInfoPhotos.getString("url");
                    System.out.println(i + ") URL link = " + linkPhotos + ";");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //String friendsTitleText = mainInfo.getString("friends");
        //System.out.println("Friends = " + friendsTitleText);
    }
}
