package com.lab.systemModel;

import java.util.Arrays;

public class FailedElementsStatistics {
    public final int[] pr; //processors
    public final int[] c; //controllers
    public final int[] a; //adapters
    public final int[] m; //magistrates
    public final int[] d; //detectors
    public final int[] b; //busses

    public static FailedElementsStatistics createForInitialSystem(){
        return new FailedElementsStatistics(new int[5],new int[5],new int[3],new int[2],new int[5],new int[3]);
    }

    public static FailedElementsStatistics createForModifiedSystem(){
        //TODO create then
        throw new RuntimeException("Not implemented!");
    }

    private FailedElementsStatistics(int[] pr, int[] c, int[] a, int[] m, int[] d, int[] b) {
        this.pr = pr;
        this.c = c;
        this.a = a;
        this.m = m;
        this.d = d;
        this.b = b;
    }

    public void addToStatistics(SystemStateVector systemStateVector){
        for(int i = 0; i < systemStateVector.pr.length; i++){
            if(!systemStateVector.pr[i]){
                pr[i]++;
            }
        }
        //TODO and so on;
    }

    @Override
    public String toString() {
        return "FailedElementsStatistics{" +
                "pr=" + Arrays.toString(pr) +
                ", c=" + Arrays.toString(c) +
                ", a=" + Arrays.toString(a) +
                ", m=" + Arrays.toString(m) +
                ", d=" + Arrays.toString(d) +
                ", b=" + Arrays.toString(b) +
                '}';
    }
}
