package com.clearcapital.oss.json.serializers;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class EnumDeserializer<T extends Enum<T>> extends JsonDeserializer<T> {

    private final Map<String, T> enumChanges;
    private final Class<T> clazz;

    public EnumDeserializer(final Map<String, T> enumChanges, final Class<T> clazz) {
        this.enumChanges = enumChanges;
        this.clazz = clazz;
    }

    @Override
    public T deserialize(final JsonParser parser, final DeserializationContext context) throws IOException,
            JsonProcessingException {
        JsonToken token = parser.getCurrentToken();
        String value = null;
        if (JsonToken.VALUE_STRING.equals(token)) {
            value = parser.getValueAsString();
        } else if (JsonToken.FIELD_NAME.equals(token)) {
            value = parser.getCurrentName();
        }
        if (value == null) {
            return null;
        }
        T result = enumChanges.get(value);
        return result != null ? result : Enum.valueOf(clazz, value);
    }
}
