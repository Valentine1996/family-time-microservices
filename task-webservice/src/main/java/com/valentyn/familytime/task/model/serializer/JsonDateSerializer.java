/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 * @date 3/20/16 7:20 PM
 */

package com.valentyn.familytime.task.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Serializer of LocalDate.
 *
 * @version 1.0
 */
public class JsonDateSerializer extends JsonSerializer<LocalDate> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void serialize(
            LocalDate date,
            JsonGenerator generator,
            SerializerProvider provider) throws IOException {
        //- Serialize date -//
        String dateString = date.format(formatter);
        generator.writeString(dateString);
    }
}
