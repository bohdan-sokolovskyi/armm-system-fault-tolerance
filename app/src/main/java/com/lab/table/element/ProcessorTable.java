package com.lab.table.element;

import java.util.ArrayList;
import java.util.List;

public class ProcessorTable {
    List<Processor> processors;

    public ProcessorTable() {
        processors = new ArrayList<>();
    }

    public void setProcessors(List<Processor> processors) {
        this.processors = processors;
    }

    public List<Processor> getProcessors() {
        return processors;
    }

    public Processor getProcessor(String processorName) {
        for(Processor pr : processors) {
            if(pr.getName().equals(processorName)) {
                return pr;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "ProcessorTable{" +
                "processors=" + processors +
                '}';
    }
}
