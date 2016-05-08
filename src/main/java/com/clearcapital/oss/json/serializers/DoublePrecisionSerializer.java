package com.clearcapital.oss.json.serializers;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * A JsonSerializer for Jackson that limits Double values to 2 decimal places. Use by specifying, e.g., :
 * 
 * <pre>
 * {@code
 * class MyBean {
 *     @JsonSerialize(using = DoublePrecisionSerializer.class)
 *     Double myValue;
 *     ...
 * }
 * }
 * </pre>
 */
public class DoublePrecisionSerializer extends JsonSerializer<Double> {

    final String pattern = "#.##";
    // final String pattern = "###,###,##0.00";
    final DecimalFormat myFormatter = new DecimalFormat(pattern);

    @Override
    public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
            JsonProcessingException {
        if (jgen == null) {
            throw new NullPointerException("Jackson gave me a null JsonGenerator object");
        }
        myFormatter.setRoundingMode(RoundingMode.HALF_UP);
        final String output = myFormatter.format(value);
        jgen.writeNumber(output);
    }
}