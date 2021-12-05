package com.lab;

import com.lab.model.SystemModel;
import com.lab.model.SystemStateVector;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class RunnableEnvironment {
    private static RunnableEnvironment env = null;

    private SystemModel model;
    private SystemStateVector ssv;
    private final ProbabilityCalculator probabilityCalculator;

    public static RunnableEnvironment init() {
        if(env == null) {
            env = new RunnableEnvironment();
        }

        return env;
    }

    private RunnableEnvironment() {
        probabilityCalculator = new ProbabilityCalculator();
    }

    public void runTestInitialSystem() {
        System.out.println("=== Test of Initial System ===");
        model = SystemModel.createInitialSystem();
        ssv = SystemStateVector.createForInitialSystem();

        runTests();
    }

    public void runTestModifiedSystem() {
        System.out.println("=== Test of Modified System ===");
        model = SystemModel.createModifiedSystem();
        ssv = SystemStateVector.createForModifiedSystem();

        runTests();
    }

    public void runTests() {
        probabilityCalculator.reset();

        test0Errors();
        test1Errors();
        test2Errors();
        test3Errors();
        test4Errors();

        //System.out.println(systemModel.getFailedElementsStatistics());

        System.out.println("= Probability =\n");
        System.out.println(probabilityCalculator);
    }

    public void test0Errors() {
        ssv.setAllTrue();
        probabilityCalculator.set(0, model.testSystemStateVector(ssv).getSsvProbability());
    }

    public void test1Errors() {
        for(int i = 0; i < model.getElementsCount(); i++) {
            ssv.setAllTrue();
            ssv.setToFalse(i);
            SystemModel.TestSSVResult res = model.testSystemStateVector(ssv);

            if(res.isSystemLive()) {
                probabilityCalculator.addTo(1, res.getSsvProbability());
            }
        }
    }

    public void test2Errors() {
        for(int i = 0; i < model.getElementsCount() - 1; i++) {
            for(int j = i + 1; j < model.getElementsCount(); j++) {
                ssv.setAllTrue();
                ssv.setToFalse(i);
                ssv.setToFalse(j);
                SystemModel.TestSSVResult res = model.testSystemStateVector(ssv);

                if(res.isSystemLive()) {
                    probabilityCalculator.addTo(2, res.getSsvProbability());
                }
            }
        }
    }

    // 50%
    public void test3Errors() {
        List<Integer> hashes = new ArrayList<>();
        int count = (int) Math.round(model.getElementsCount() * 0.5);
        int i = 0;

        while(i != count) {
            int[] seq = MagicRandomUtils.generateRandomCombination(0, model.getElementsCount(), 3);
            int hash = Arrays.hashCode(seq);

            if(hashes.contains(hash)) {
                continue;
            }

            hashes.add(hash);

            ssv.setAllTrue();
            ssv.setToFalse(seq[0]);
            ssv.setToFalse(seq[1]);
            ssv.setToFalse(seq[2]);

            SystemModel.TestSSVResult res = model.testSystemStateVector(ssv);

            if(res.isSystemLive()) {
                probabilityCalculator.addTo(3, res.getSsvProbability());
            }

            i++;
        }
        probabilityCalculator.multiplyTo(3,new BigDecimal("2.0"));
    }

    // 10 %
    public void test4Errors() {
        List<Integer> hashes = new ArrayList<>();
        int count = (int) Math.round(model.getElementsCount() * 0.1);
        int i = 0;

        while(i != count) {
            int[] seq = MagicRandomUtils.generateRandomCombination(0, model.getElementsCount(), 4);
            int hash = Arrays.hashCode(seq);

            if(hashes.contains(hash)) {
                continue;
            }

            hashes.add(hash);

            ssv.setAllTrue();
            ssv.setToFalse(seq[0]);
            ssv.setToFalse(seq[1]);
            ssv.setToFalse(seq[2]);
            ssv.setToFalse(seq[3]);

            SystemModel.TestSSVResult res = model.testSystemStateVector(ssv);

            if(res.isSystemLive()) {
                probabilityCalculator.addTo(4, res.getSsvProbability());
            }

            i++;
        }
        probabilityCalculator.multiplyTo(3,new BigDecimal("10.0"));
    }
}
