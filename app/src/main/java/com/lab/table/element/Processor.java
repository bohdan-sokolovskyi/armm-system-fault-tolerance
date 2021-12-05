package com.lab.table.element;

import java.util.ArrayList;
import java.util.List;

public class Processor {
    protected String name;
    protected Integer timeNom;
    protected Integer timeMax;
    protected List<DistributionScheme> schemes;

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

    public void setSchemes(List<DistributionScheme> schemes) {
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

    public List<DistributionScheme> getSchemes() {
        return schemes;
    }
}
