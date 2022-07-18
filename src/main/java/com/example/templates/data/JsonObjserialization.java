package com.example.templates.data;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class JsonObjserialization extends StdSerializer<JsonObj> {

    protected JsonObjserialization() {
        this(JsonObj.class);
    }

    protected JsonObjserialization(Class<JsonObj> t) {
        super(t);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void serialize(JsonObj value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("a", 10);
        gen.writeObjectField("b", new HashMap<String, String>());
        gen.writeEndObject();
    }
    
}
