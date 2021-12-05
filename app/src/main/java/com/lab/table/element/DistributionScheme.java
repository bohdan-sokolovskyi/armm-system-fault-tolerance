package com.lab.table.element;

import java.util.HashMap;
import java.util.Map;

public class DistributionScheme {
    private Map<String, Integer> scheme;

    public DistributionScheme() {
        scheme = new HashMap<>();
    }

    public void setScheme(Map<String, Integer> scheme) {
        this.scheme = scheme;
    }

    public Map<String, Integer> getScheme() {
        return scheme;
    }

    public boolean containProcessor(String processorName) {
        return scheme.containsKey(processorName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("distribution scheme: \n\t");

        for(Map.Entry<String, Integer> subScheme: scheme.entrySet()) {
            sb.append(String.format("to %s set %d\n\t", subScheme.getKey(), subScheme.getValue()));
        }

        return sb.toString();
    }
}
