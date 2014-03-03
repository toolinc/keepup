// Copyright 2014 University of Detroit Mercy.

package com.udm.common.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Specifies the basic behavior for a JSON serializer.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public abstract class AbstractJsonSerializer {

    private Gson gson;

    protected AbstractJsonSerializer(boolean isCompact) {
        this(false, isCompact);
    }

    protected AbstractJsonSerializer(boolean isPretty, boolean isCompact) {
        super();
        if (isPretty && isCompact) {
            this.buildForPrettyCompact();
        } else if (isCompact) {
            this.buildForCompact();
        } else {
            this.build();
        }
    }

    protected Gson gson() {
        return this.gson;
    }

    private void build() {
        this.gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateSerializer())
                .registerTypeAdapter(Date.class, new DateDeserializer()).serializeNulls().create();
    }

    private void buildForCompact() {
        this.gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateSerializer())
                .registerTypeAdapter(Date.class, new DateDeserializer()).create();
    }

    private void buildForPrettyCompact() {
        this.gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateSerializer())
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .setPrettyPrinting()
                .create();
    }

    private class DateSerializer implements JsonSerializer<Date> {

        @Override
        public JsonElement serialize(Date source, Type typeOfSource,
                                     JsonSerializationContext context) {
            return new JsonPrimitive(Long.toString(source.getTime()));
        }
    }

    private class DateDeserializer implements JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonElement json, Type typeOfTarget,
                                JsonDeserializationContext context) throws JsonParseException {
            long time = Long.parseLong(json.getAsJsonPrimitive().getAsString());
            return new Date(time);
        }
    }
}
