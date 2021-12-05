package com.lab.table;

import com.lab.table.element.ProcessorTable;

import java.util.HashMap;
import java.util.Map;

public class ProcessorTableContainer {
    public static ProcessorTableContainer PROCESSOR_TABLE_CONTAINER;

    private final Map<String, ProcessorTable> container;

    public static ProcessorTableContainer getContainer() {
        if(PROCESSOR_TABLE_CONTAINER == null) {
            PROCESSOR_TABLE_CONTAINER = new ProcessorTableContainer();
        }

        return PROCESSOR_TABLE_CONTAINER;
    }

    private ProcessorTableContainer() {
        this.container = new HashMap<>();
    }

    public void addTable(String fileName, ProcessorTable table) {
        container.put(fileName, table);
    }

    public ProcessorTable getTable(String fileName) {
        return container.get(fileName);
    }

    public boolean containTable(String fileName) {
        return container.containsKey(fileName);
    }
}
