package com.clearcapital.oss.json.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;

public class EmptyStringAsNullIntegerKeyDeserializer extends KeyDeserializer implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public final Object deserializeKey(String key, DeserializationContext ctxt) throws IOException,
            JsonProcessingException {
        if (key == null) { // is this even legal call?
            return null;
        }
        try {
            return _parse(key, ctxt);
        } catch (Exception re) {
            throw ctxt.weirdKeyException(Integer.class, key, "not a valid representation: " + re.getMessage());
        }
    }

    public Integer _parse(String key, DeserializationContext ctxt) throws JsonMappingException {
        if (key.isEmpty()) {
            return null;
        }
        return Integer.parseInt(key);
    }
}
