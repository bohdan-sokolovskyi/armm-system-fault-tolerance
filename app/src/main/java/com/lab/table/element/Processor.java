package com.lab.table.element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Processor {
    protected String name;
    protected Integer timeNom;
    protected Integer timeMax;
    protected List<Map<String, Integer>> schemes;

    public Processor() {
        name = null;
        timeNom = -1;
        timeMax = -1;
        schemes = new ArrayList<>();
    }

    public Processor(Processor processor) {
        this.name = processor.name;
        this.timeNom = processor.timeNom;
        this.timeMax = processor.timeMax;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimeNom(Integer timeNom) {
        this.timeNom = timeNom;
    }

    public void setTimeMax(Integer timeMax) {
        this.timeMax = timeMax;
    }

    public void setSchemes(List<Map<String, Integer>> schemes) {
        this.schemes = schemes;
    }

    public String getName() {
        return name;
    }

    public Integer getTimeNom() {
        return timeNom;
    }

    public Integer getTimeMax() {
        return timeMax;
    }

    public List<Map<String, Integer>> getSchemes() {
        return schemes;
    }

    @Override
    public String toString() {
        return "Processor{" +
                "name='" + name + '\'' +
                ", timeNom=" + timeNom +
                ", timeMax=" + timeMax +
                ", schemes=" + schemes +
                '}';
    }
}
