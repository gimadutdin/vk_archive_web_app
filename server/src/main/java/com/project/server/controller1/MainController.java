package com.project.server.controller1;

import com.project.server.MainDatabaseTool;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.*;

import com.project.server.controller1.VKPageParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

@RestController
public class MainController {
    // GET and POST events handlers are implemented here

    // localhost:8080/
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getIndexHandler() {
        MainDatabaseTool mdt = new MainDatabaseTool();
        return "vk archive server\n" + mdt.selectAll();
    }

    // localhost:8080/

    @RequestMapping(value = "user-info/{id}", method = RequestMethod.GET) // todo change to POST
    public ObjectNode getItem(@PathVariable("id") String paramUserId) throws IOException {

        // check if user already in our database
        MainDatabaseTool mdt = new MainDatabaseTool();
        boolean userPresent = mdt.isUserPresent(paramUserId);
        // if user already exists in db then read its info
        if (userPresent) {
            ObjectNode resNode = mdt.readUserInfo(paramUserId);
            resNode.put("was_in_db", true);
            return resNode;
        }

        String token = "d1adb2dad1adb2dad1adb2da59d1d9d642dd1add1adb2da8e2b9de42ba576790649c910";
        String versionAPI = "5.103"; //current version

        VKPageParser parser = new VKPageParser(token, versionAPI);
        Document respDoc = parser.connectDataUser(new StringBuilder(paramUserId));
        System.out.println(respDoc.text());
        String respJson = respDoc.text();

        JSONObject respJsonObject = new JSONObject(respJson);
        if (!respJsonObject.has("response")) {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("error", "user_not_found");
            return objectNode;
        }
        JSONArray respArr = respJsonObject.getJSONArray("response");
        JSONObject mainInfo = respArr.getJSONObject(0);

        String firstName = mainInfo.has("first_name") ? mainInfo.getString("first_name") : null;
        String lastName = mainInfo.has("last_name") ? mainInfo.getString("last_name") : null;
        String cityTitle = mainInfo.has("city") ? mainInfo.getJSONObject("city").getString("title") : null;
        //String countyTitle = mainInfo.getJSONObject("country").getString("title");
        String birthDate = mainInfo.has("bdate") ? mainInfo.getString("bdate") : null;
        String studyPlace = mainInfo.has("university_name") ? mainInfo.getString("university_name") : null;

        int friendsNumber = 0; // TODO

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("user_id", paramUserId);
        objectNode.put("fio", firstName + " " + lastName);
        //objectNode.put("last_name", lastName);
        objectNode.put("city", cityTitle);
        //objectNode.put("country", countyTitle);
        objectNode.put("birthday", birthDate);
        objectNode.put("study_place", studyPlace);
        objectNode.put("friends_number", friendsNumber);
        objectNode.put("was_in_db", false);

        mdt.writeUserInfo(objectNode);

        return objectNode;
    }
}
