package com.lab.systemModel;

import java.util.Arrays;

public class FailedElementsStatistics {
    public int[] pr; //processors
    public int[] c; //controllers
    public int[] a; //adapters
    public int[] m; //magistrates
    public int[] d; //detectors
    public int[] b; //busses

    public FailedElementsStatistics(int[] pr, int[] c, int[] a, int[] m, int[] d, int[] b) {
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
