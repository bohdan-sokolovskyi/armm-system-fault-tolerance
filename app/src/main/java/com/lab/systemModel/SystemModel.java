package com.lab.systemModel;

import com.lab.functions.LogicStructureFunction;

public class SystemModel {
    private final LogicStructureFunction logicStructureFunction;
    private final FailedElementsStatistics failedElementsStatistics;
    private final RearrangeTable rearrangeTable;

    private SystemStateVector systemStateVector;


    public SystemModel(LogicStructureFunction logicStructureFunction,
                       FailedElementsStatistics failedElementsStatistics,
                       RearrangeTable rearrangeTable) {
        this.logicStructureFunction = logicStructureFunction;
        this.failedElementsStatistics = failedElementsStatistics;
        this.rearrangeTable = rearrangeTable;
    }

    public TestSSVResult trySystemStateVector(SystemStateVector systemStateVector){
         this.systemStateVector = systemStateVector;
         boolean res = logicStructureFunction.calculateF(this.systemStateVector);
         failedElementsStatistics.addToStatistics(this.systemStateVector);
         if(!res){
             SystemStateVector v2 = useRearrangeTable();
             res = logicStructureFunction.calculateF(v2);
         }
         double prob = calculateSSVProbability();
         return new TestSSVResult(prob,res);
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

    static class TestSSVResult{
        public final double ssvProbabilty;
        public final boolean res;

        public TestSSVResult(double ssvProbabilty, boolean res) {
            this.ssvProbabilty = ssvProbabilty;
            this.res = res;
        }
    }
}
