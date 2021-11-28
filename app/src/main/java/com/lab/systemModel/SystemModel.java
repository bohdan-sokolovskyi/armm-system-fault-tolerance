package com.lab.systemModel;

import com.lab.functions.InitialLogicStructureFunction;
import com.lab.functions.LogicStructureFunction;
import com.lab.functions.ModifiedLogicStructureFunction;

public class SystemModel {
    private final LogicStructureFunction logicStructureFunction;
    private final FailedElementsStatistics failedElementsStatistics;
    private final RearrangeTable rearrangeTable;
    private final int elementsCount;

    private SystemStateVector systemStateVector;

    public static SystemModel createInitialSystem(){
        return new SystemModel(new InitialLogicStructureFunction(),
                FailedElementsStatistics.createForInitialSystem(),
                RearrangeTable.createForInitialSystem(),
                23);
    }

    public static SystemModel createModifiedSystem(){
        //TODO fix elements count soon
        return new SystemModel(new ModifiedLogicStructureFunction(),
                FailedElementsStatistics.createForModifiedSystem(),
                RearrangeTable.createForModifiedSystem(),
                -1);
    }

    private SystemModel(LogicStructureFunction logicStructureFunction,
                       FailedElementsStatistics failedElementsStatistics,
                       RearrangeTable rearrangeTable,
                        int elementsCount) {
        this.logicStructureFunction = logicStructureFunction;
        this.failedElementsStatistics = failedElementsStatistics;
        this.rearrangeTable = rearrangeTable;
        this.elementsCount = elementsCount;
    }

    public TestSSVResult testSystemStateVector(SystemStateVector systemStateVector){
         this.systemStateVector = systemStateVector;
         boolean isSystemLive = logicStructureFunction.calculateF(this.systemStateVector);
         if(!isSystemLive){
             SystemStateVector v2 = useRearrangeTable();
             isSystemLive = logicStructureFunction.calculateF(v2);
             if(!isSystemLive){
                 failedElementsStatistics.addToStatistics(this.systemStateVector);
             }
         }
         double prob = calculateSSVProbability();
         return new TestSSVResult(prob,isSystemLive);
    }


    private SystemStateVector useRearrangeTable(){
        //TODO
        return systemStateVector;
    }

    private double calculateSSVProbability(){
        double prob = 1.0;
        for(var pr: systemStateVector.pr){
            final double PR_I_PROB = 3.2E-4;
            if(pr){
                prob *= (1.0- PR_I_PROB);
            }else{
                prob *= PR_I_PROB;
            }
        }

        for(var b: systemStateVector.b){
            final double B_I_PROB = 2.4E-5;
            if(b){
                prob *= (1.0- B_I_PROB);
            }else{
                prob *= B_I_PROB;
            }
        }

        for(var a: systemStateVector.a){
            final double A_I_PROB = 2.2E-4;
            if(a){
                prob *= (1.0- A_I_PROB);
            }else{
                prob *= A_I_PROB;
            }
        }


        for(var c: systemStateVector.c){
            double C_I_PROB = 4.1E-4;
            if(c){
                prob *= (1.0- C_I_PROB);
            }else{
                prob *= C_I_PROB;
            }
        }

        for(var d: systemStateVector.d){
            final double D_I_PROB = 3.2E-5;
            if(d){
                prob *= (1.0- D_I_PROB);
            }else{
                prob *= D_I_PROB;
            }
        }

        for(var m: systemStateVector.m){
            final double M_I_PROB = 2.3E-4;
            if(m){
                prob *= (1.0- M_I_PROB);
            }else{
                prob *= M_I_PROB;
            }
        }
        return  prob;
    }

    public FailedElementsStatistics getFailedElementsStatistics() {
        return failedElementsStatistics;
    }

    public int getElementsCount() {
        return elementsCount;
    }

    static public class TestSSVResult{
        public final double ssvProbabilty;
        public final boolean isSystemLive;

        public TestSSVResult(double ssvProbabilty, boolean isSystemLive) {
            this.ssvProbabilty = ssvProbabilty;
            this.isSystemLive = isSystemLive;
        }

        @Override
        public String toString() {
            return "TestSSVResult{" +
                    "ssvProbabilty=" + ssvProbabilty +
                    ", isSystemLive=" + isSystemLive +
                    '}';
        }
    }
}
