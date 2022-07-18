package com.example.templates.data;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class JsonObjDeserialization extends StdDeserializer<JsonObj> {

    public JsonObjDeserialization() {
        this(JsonObj.class);
    }
    
    protected JsonObjDeserialization(Class<?> vc) {
        super(vc);
    }

    @Override
    public JsonObj deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);
        
        JsonObj obj = new JsonObj();
        obj.setA(777);
        return obj;
    }
    
}
