package com.project.server.controller1;

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
        return "vk archive server";
    }

    // localhost:8080/

    @RequestMapping(value = "user-info/{id}", method = RequestMethod.GET) // todo change to POST
    public ObjectNode getItem(@PathVariable("id") String paramUserId) throws IOException {

        String token = "d1adb2dad1adb2dad1adb2da59d1d9d642dd1add1adb2da8e2b9de42ba576790649c910";
        String versionAPI = "5.103"; //current version

        VKPageParser parser = new VKPageParser(token, versionAPI);
        Document respDoc = parser.connectDataUser(new StringBuilder(paramUserId));
        //System.out.println(doc.text());
        String respJson = respDoc.text();

        JSONObject respJsonObject = new JSONObject(respJson);
        JSONArray respArr = respJsonObject.getJSONArray("response");
        JSONObject mainInfo = respArr.getJSONObject(0);

        String firstName = mainInfo.getString("first_name");
        String lastName = mainInfo.getString("last_name");
        String cityTitle = mainInfo.getJSONObject("city").getString("title");
        String countyTitle = mainInfo.getJSONObject("country").getString("title");
        String birthDate = mainInfo.getString("bdate");

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("first_name", firstName);
        objectNode.put("last_name", lastName);
        objectNode.put("city", cityTitle);
        objectNode.put("country", countyTitle);
        objectNode.put("birth_date", birthDate);

        return objectNode;
    }
}
