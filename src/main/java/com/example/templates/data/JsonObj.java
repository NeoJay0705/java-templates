package com.example.templates.data;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Values which have a getter will be export.
 */
public class JsonObj {

    public static void main(String[] args) throws JsonProcessingException {
        
        ObjectMapper mapper = new ObjectMapper().registerModule(new SimpleModule("sea").addSerializer(JsonObj.class, new JsonObjserialization())).registerModule(new SimpleModule("dea").addDeserializer(JsonObj.class, new JsonObjDeserialization()));
        System.out.println(mapper.writeValueAsString(new JsonObj()));
        System.out.println(mapper.readValue("{\"a\": 666, \"b\":\"hhh\"}", JsonObj.class));
        System.out.println(new ObjectMapper().writeValueAsString(new JsonObj()));
        System.out.println(Arrays.toString(new ObjectMapper().readValue("[{\"a\":10, \"e\": [\"q\", \"s\"]}, {\"B\":\"qwer\"}]", JsonObj[].class)));
    }

    private int a;
    private String b;
    private List<String> d;
    private String[] e;

    public void setA(int a) {
        this.a = 99;
    }

    /**
     * Lower A
     * @return
     */
    public int getA() {
        return this.a;
    }

    /**
     * Higher B
     * @return
     */
    @JsonProperty("B")
    public String getB() {
        return this.b;
    }

    @JsonIgnore
    public String getC() {
        return "asdf";
    }

    public List<String> getd() {
        return this.d;
    }

    public String[] gete() {
        return this.e;
    }

    @Override
    public String toString() {
        return this.b + this.a + this.d + Arrays.toString(this.e);
    }
}
