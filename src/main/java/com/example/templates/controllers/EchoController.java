package com.example.templates.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.templates.data.EchoData;

// return is always in payload
@RestController
// ip:port/echo
@RequestMapping("echo")
public class EchoController {
    @Autowired
    private String title;
    @Autowired
    private String alias1;
    @Autowired
    private String alias2;
    @Autowired
    private Properties appProperties;
    private int num = 0;

    /**
     * Singleton controller
     */
    @RequestMapping("increament")
    public int add() {
        return ++num;
    }

    @RequestMapping("/title")
    public String title() {
        return title;
    }

    @RequestMapping("/alias1")
    public String alias1() {
        return alias1;
    }

    @RequestMapping("/alias2")
    public String alias2() {
        return alias2;
    }

    @RequestMapping("/property/a")
    public String propertyA() {
        return appProperties.getProperty("a");
    }

    @RequestMapping(value = "/filename/{filename}", method = RequestMethod.GET, produces = "text/plain")
    public String filename(@PathVariable("filename") String file) {
        return file;
    }

    @RequestMapping(value = "/filename/{filename1}/{filename2}", method = RequestMethod.GET, produces = "text/plain")
    public String filename2(@PathVariable() Map<String, String> file) {
        return file.get("filename1") + file.get("filename2");
    }

    @RequestMapping(value = "/filenameRegex/{filename:[a-z]+}-{filename2:[0-9]+}", method = RequestMethod.GET, produces = "text/plain")
    public String filename3(@PathVariable() Map<String, String> file) {
        return file.get("filename") + file.get("filename2");
    }

    @RequestMapping(value = "headerabonly", headers = {"a=10", "b=20"})
    public String hasHeaderAB(@RequestHeader("b") int bHeader) {
        return "Yes " + bHeader;
    }

    @RequestMapping(value = "paramabonly", params = {"a=10", "b=10"})
    public String hasParamAB(@RequestParam("a") int aAlias) {
        return "Yes " + aAlias;
    }

    @RequestMapping(value = "x-www-form-urlencoded")
    public String formurl(@RequestBody MultiValueMap<String, Object> aAlias) {
        return "Yes " + aAlias.getFirst("a");
    }

    @RequestMapping(value = "multipart-form", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String form1(@RequestPart("a") String col1, @RequestPart("b") String col2) {
        return "Yes " + col1 + col2;
    }

    @RequestMapping(value = "multipart-file")
    public String form2(@RequestPart("disposition.name") MultipartFile file) {
        return "multipart-file " + file;
    }

    @RequestMapping(value = "body")
    public String body(HttpEntity<String> aAlias) {
        return "body " + aAlias.getBody();
    }

    @RequestMapping(value = "bodyls")
    public List<String> bodyls(HttpEntity<String> aAlias) {
        List<String> ls = new ArrayList<>();
        ls.add("sasdf");
        return ls;
    }

    @RequestMapping(value = "bodymap")
    public Map<String, Object> bodymap(HttpEntity<String> aAlias) {
        Map<String, Object> a = new HashMap<>();
        a.put("a", 1);
        a.put("b", "asdf");
        a.put("c", new ArrayList<>());
        Map<String, Object> o = new HashMap<>();
        o.put("d", LocalDateTime.now());
        o.put("echo", new EchoData());
        a.put("o", o);
        return a;
    }

    @RequestMapping(value = "/jsononly", consumes = "application/json")
    public String isJson() {
        return "Yes";
    }

    @RequestMapping(value = "jsonmap")
    public EchoData bodyls(@RequestBody EchoData data) {
        return data;
    }

    /**
     * This overrides controlleradvice
     */
    @ExceptionHandler(NullPointerException.class)
    public String error(NullPointerException e) {
        return e.getMessage();
    }

    /**
     * Captured exception by @ExceptionHandler
     */
    @RequestMapping(value = "nullexp")
    public String bodyls() {
        int a = 1;
        if (a == 1) throw new NullPointerException("123");
        return "nullexpreturn";
    }

    /**
     * Other supported annotations for arguments of methods
     * 
     * Locale
     * InputStream/Reader for request payload
     * OutputStream/Writer for response payload
     * java.security.Principal
     * @PathVariable
     * @RequestParam
     * @RequestHeader
     * @RequestBody with HttpMessageConverter | @RequestPart for multipart/form-data
     * HttpEntity<?> with HttpMessageConverter
     * @CookieValue("key")
     * Map | Model | ModelMap for request context
     * BindingResult for assessment of Converter<?, ?>
     * SessionStatus
     */

    /**
     * For return types of method 
     * 
     * ModelAndView
     * Model | Map | View | String of View name
     * void for directly accessing response socket
     * @ResponseBody with HttpMessageConverter to write result to payload, @RestController has default @ResponseBody for every method
     * HttpEntity<?> | ResponseEntity<?>, for more accesses of headers
     */

    /**
     * At @SessionAttribute
     * @ModelAttribute("") is in the model
     * URI template "/{attri}" with type converter
     * Default constructor of the type, Converter<String, Integer>
     * 
     * Check binding by BindingResult
     */
    public void consumeMode(@ModelAttribute("a") Integer a, BindingResult result) {

    }

    /**
     * Store model attribute to session by @SessionAttribute("attributeName") at controller level
     */

}
