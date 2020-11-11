package com.project.server.controller1;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class MainController {
    // здесь будет писать обработчики GET и POST запросов

    // для примера тут некоторые обработчики запросов

    // localhost:8080/
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getIndexHandler() {
        return "Hello world";
    }

    // localhost:8080/foo
    @RequestMapping(value = "foo", method = RequestMethod.GET)
    public String getFooHandler() {
        return "foo bar";
    }

    // пример обработчика POST запроса с параметрами.
    @RequestMapping(value = "concat", method = RequestMethod.POST)
    public @ResponseBody String getItem(@RequestParam("str1") String string1, @RequestParam("str2") String string2) {
        return String.format("<h2>concat('%s', '%s') = '%s'</h2>", string1, string2, string1 + string2);
    }
}
