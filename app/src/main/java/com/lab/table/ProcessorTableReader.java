package com.lab.table;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lab.table.element.ProcessorTable;

import java.io.InputStreamReader;
import java.util.Objects;

public class ProcessorTableReader {
    private final Gson gson;
    private final ProcessorTableContainer container;

    public ProcessorTableReader() {
        gson = new Gson();
        container = ProcessorTableContainer.getContainer();
    }

    public ProcessorTable read(String fileName) {
        if(container.containTable(fileName)) {
            return container.getTable(fileName);
        }

        JsonReader reader = new JsonReader(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName))));
        ProcessorTable table = gson.fromJson(reader, ProcessorTable.class);

        container.addTable(fileName, table);

        return table;
    }
}
