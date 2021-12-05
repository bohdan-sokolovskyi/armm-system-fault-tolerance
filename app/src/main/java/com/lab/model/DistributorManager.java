package com.lab.model;

import com.lab.Pair;
import com.lab.table.ProcessorTableReader;
import com.lab.table.element.DistributionScheme;
import com.lab.table.element.LiveProcessor;
import com.lab.table.element.Processor;
import com.lab.table.element.ProcessorTable;

import java.util.*;

public class DistributorManager {
    // std files
    public static final String INITIAL_PROCESSOR_TABLE = "init-processor-table.json";
    public static final String MODIFIED_PROCESSOR_TABLE = "modified-processor-table.json";

    private final ProcessorTableReader processorTableReader;
    private final Map<String, Pair<Integer, LiveProcessor>> liveProcessors;
    private final Map<String, Pair<Integer, Processor>> failedProcessors;
    private final String processorTableFile;

    public static DistributorManager createForInitialSystem() {
        return new DistributorManager(INITIAL_PROCESSOR_TABLE);
    }

    public static DistributorManager createForModifiedSystem() {
        return new DistributorManager(MODIFIED_PROCESSOR_TABLE);
    }

    public DistributorManager(String processorTableFile) {
        processorTableReader = new ProcessorTableReader();
        liveProcessors = new HashMap<>();
        failedProcessors = new HashMap<>();
        this.processorTableFile = processorTableFile;
    }

    public SystemStateVector distributeProcessors(SystemStateVector ssvP) {
        SystemStateVector ssvV = new SystemStateVector(ssvP);
        ProcessorTable table = processorTableReader.read(processorTableFile);
        List<String> failedProcessorNames = failedProcessors.values().stream().map(Pair::getY).map(Processor::getName).toList();
        initLiveAndFailedProcessors(ssvP.pr, table);

        if(!failedProcessors.isEmpty()) {
            for(Map.Entry<String, Pair<Integer, Processor>> failPr : failedProcessors.entrySet()) {
                Processor processor = failPr.getValue().getY();
                DistributionScheme resultingScheme = null;

                for(DistributionScheme scheme: processor.getSchemes()) {
                    if(!schemeContainFailedProcessors(failedProcessorNames, scheme) && canApplyScheme(scheme)) {
                       resultingScheme = scheme;
                       break;
                    }
                }

                if(resultingScheme == null) {
                    System.out.printf("Can't find suitable scheme for %s%n", failPr.getKey());
                } else {
                    applyScheme(ssvV, failPr.getValue().getX(), resultingScheme);
                }
            }
        }

        return ssvV;
    }

    private void initLiveAndFailedProcessors(boolean[] pr, ProcessorTable table) {
        liveProcessors.clear();
        failedProcessors.clear();

        for(int i = 0; i < pr.length; i++) {
            String procName = convertProcessorName(i);

            if(pr[i]) {
                liveProcessors.put(procName, new Pair<>(i, new LiveProcessor(Objects.requireNonNull(table.getProcessor(procName)))));
            } else {
                failedProcessors.put(procName, new Pair<>(i, Objects.requireNonNull(table.getProcessor(procName))));
            }
        }
    }

    private void applyScheme(SystemStateVector ssv, int i, DistributionScheme scheme) {
        ssv.setToTrue(i);
        scheme.getScheme().forEach((key, value) -> liveProcessors.get(key).getY().addTime(value));
    }

    private boolean canApplyScheme(DistributionScheme scheme) {
        return scheme.getScheme().entrySet().stream().allMatch((e) -> liveProcessors.get(e.getKey()).getY().isCanAddTime(e.getValue()));
    }

    private boolean schemeContainFailedProcessors(List<String> failedProcessorNames, DistributionScheme scheme) {
        return failedProcessorNames.stream().anyMatch(scheme::containProcessor);
    }

    private String convertProcessorName(int i) {
        return String.format("pr%d", i);
    }
}
