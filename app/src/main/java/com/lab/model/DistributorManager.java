package com.lab.model;

import com.lab.Pair;
import com.lab.table.ProcessorTableReader;
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
        initLiveAndFailedProcessors(ssvP.pr, table);

        if(!failedProcessors.isEmpty()) {
            for(Map.Entry<String, Pair<Integer, Processor>> failPr : failedProcessors.entrySet()) {
                Processor processor = failPr.getValue().getY();
                Map<String, Integer> resultingScheme = new HashMap<>();
                var schemes = processor.getSchemes().entrySet();
                int res = -1;

                for(var scheme: schemes) {
                    Pair<Integer, LiveProcessor> pair = liveProcessors.get(scheme.getKey());

                    if(pair != null && pair.getY().isCanAddTime(scheme.getValue())) {
                        res = scheme.getValue();
                        resultingScheme.put(scheme.getKey(), scheme.getValue());

                        for(var scheme2: schemes) {
                            Pair<Integer, LiveProcessor> pair2 = liveProcessors.get(scheme2.getKey());

                            if(pair2 != null && !scheme.getKey().equals(scheme2.getKey()) && pair2.getY().isCanAddTime(scheme2.getValue()) ) {
                                res += scheme2.getValue();

                                if(res == processor.getTimeNom()) {
                                    resultingScheme.put(scheme2.getKey(), scheme.getValue());
                                    break;
                                } else if(res > processor.getTimeNom()) {
                                    resultingScheme.clear();
                                    res = -1;
                                    break;
                                } else {
                                    resultingScheme.put(scheme2.getKey(), scheme.getValue());
                                }
                            }
                        }
                    }

                    if(res != -1) {
                        break;
                    }
                }

                if(resultingScheme.isEmpty()) {
                   // for debug
                   //System.out.printf("Can't find suitable scheme for %s%n", failPr.getKey());
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

    private void applyScheme(SystemStateVector ssv, int i, Map<String, Integer> scheme) {
        ssv.setToTrue(i);
        scheme.forEach((key, value) -> liveProcessors.get(key).getY().addTime(value));
    }

    private boolean canApplyScheme(Map<String, Integer> scheme) {
        return scheme.entrySet().stream().allMatch((e) -> Objects.requireNonNull(liveProcessors.get(e.getKey())).getY().isCanAddTime(e.getValue()));
    }

    private boolean schemeContainFailedProcessors(List<String> failedProcessorNames, Map<String, Integer> scheme) {
        return failedProcessorNames.stream().anyMatch(scheme::containsKey);
    }

    private String convertProcessorName(int i) {
        return String.format("pr%d", i);
    }
}
