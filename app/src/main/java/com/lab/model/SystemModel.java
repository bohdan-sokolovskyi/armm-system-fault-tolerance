package com.lab.model;

import com.lab.ProbabilityCalculator;
import com.lab.function.InitialLogicalStructureFunction;
import com.lab.function.LogicalStructureFunction;
import com.lab.function.ModifiedLogicalStructureFunction;

import java.math.BigDecimal;

public class SystemModel {
    private final LogicalStructureFunction logicStructureFunction;
    private final FailedElementsStatistics failedElementsStatistics;
    private final DistributorManager distributorManager;
    private final int elementsCount;

    private SystemStateVector systemStateVector;

    public static SystemModel createInitialSystem() {
        return new SystemModel(
                new InitialLogicalStructureFunction(),
                FailedElementsStatistics.createForInitialSystem(),
                DistributorManager.createForInitialSystem(),
                23
        );
    }

    public static SystemModel createModifiedSystem(){
        return new SystemModel(
                new ModifiedLogicalStructureFunction(),
                FailedElementsStatistics.createForModifiedSystem(),
                DistributorManager.createForModifiedSystem(),
                26
        );
    }

    private SystemModel(LogicalStructureFunction logicStructureFunction,
                        FailedElementsStatistics failedElementsStatistics,
                        DistributorManager rearrangeTable,
                        int elementsCount) {

        this.logicStructureFunction = logicStructureFunction;
        this.failedElementsStatistics = failedElementsStatistics;
        this.distributorManager = rearrangeTable;
        this.elementsCount = elementsCount;
    }

    public TestSSVResult testSystemStateVector(SystemStateVector systemStateVector, int addCoef){
         this.systemStateVector = systemStateVector;
         boolean isSystemLive = logicStructureFunction.f(this.systemStateVector);

         if(!isSystemLive){
             SystemStateVector v2 = distributorManager.distributeProcessors(this.systemStateVector);
             isSystemLive = logicStructureFunction.f(v2);

             if(!isSystemLive){
                 failedElementsStatistics.addToStatistics(this.systemStateVector, addCoef);
             }
         }

         BigDecimal prob = ProbabilityCalculator.calcSSVProb(this.systemStateVector);
         return new TestSSVResult(prob, isSystemLive);
    }

    public FailedElementsStatistics getFailedElementsStatistics() {
        return failedElementsStatistics;
    }

    public int getElementsCount() {
        return elementsCount;
    }

    public record TestSSVResult(BigDecimal ssvProbability, boolean isSystemLive) {

        public BigDecimal getSsvProbability() {
            return ssvProbability;
        }

        @Override
        public String toString() {
            return String.format("SSV probability: %s\nIs system live: %b\n", ssvProbability, isSystemLive);
        }
    }
}
