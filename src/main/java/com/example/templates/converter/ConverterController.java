package com.example.templates.converter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.templates.beans.Response;

@RestController
@RequestMapping("converter")
public class ConverterController {
    @RequestMapping("sToH")
    public User sToHeader(@RequestHeader("user") User user, @RequestHeader("demand") String demand) {
        return user;
    }

    @RequestMapping("demand")
    public String demand(@RequestHeader("demand") String demand) {
        return demand;
    }

    @RequestMapping(value = "body", method = RequestMethod.POST)
    public User jsonBody(@RequestBody User user) {
        return user;
    }

    @RequestMapping(value = "form1", method = RequestMethod.POST)
    public User form1(@RequestBody User user) {
        return user;
    }

    @RequestMapping(value = "form2", method = RequestMethod.POST, headers = {"user"}, consumes = {"application/json"})
    public void lacks(@RequestBody String user, HttpServletRequest request) {
        ((Response) request.getAttribute("res")).setData(user);
        return ;
    }
}
