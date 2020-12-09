package com.exam.cash.serializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.PageImpl;

@JsonComponent
public class PageImplJacksonSerializer extends JsonSerializer<PageImpl<?>> {

    @Override
    public void serialize(PageImpl<?> page, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        Map<String, Object> pagingData = new HashMap<>();
        pagingData.put("page", page.getNumber());
        pagingData.put("size", page.getSize());
        pagingData.put("total", page.getTotalElements());

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("items", page.getContent());
        jsonGenerator.writeObjectField("paging", pagingData);
    }
}
