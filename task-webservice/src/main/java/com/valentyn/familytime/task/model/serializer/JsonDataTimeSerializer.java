/**
 * Created by Andrii Gaidychuk on 01.10.2016.
 */

package com.valentyn.familytime.task.model.serializer;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonDataTimeSerializer extends JsonSerializer<LocalDateTime> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public void serialize(
            LocalDateTime date,
            JsonGenerator generator,
            SerializerProvider provider) throws IOException {
        //- Serialize date -//
        String dateString = date.format(formatter);
        generator.writeString(dateString);
    }
}
