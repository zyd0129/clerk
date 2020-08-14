package com.wind.clerk.oauth.exception.handler;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Map;

public class ClerkOAuthExceptionJacksonSerializer extends StdSerializer<ClerkOAuthException> {

    protected ClerkOAuthExceptionJacksonSerializer() {
        super(ClerkOAuthException.class);
    }

    @Override
    public void serialize(ClerkOAuthException value, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeObjectField("code", value.getHttpErrorCode());
        jgen.writeStringField("message", value.getMessage());
        jgen.writeBooleanField("success", false);
        if (value.getAdditionalInformation() != null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                jgen.writeStringField(key, add);
            }
        }
        jgen.writeEndObject();
    }
}
