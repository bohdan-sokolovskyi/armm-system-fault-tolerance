package com.lab.model;

import java.util.Arrays;

public final class FailedElementsStatistics {
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
        return new FailedElementsStatistics(new int[5],new int[6],new int[4],new int[2],new int[5],new int[4]);
    }

    private FailedElementsStatistics(int[] pr, int[] c, int[] a, int[] m, int[] d, int[] b) {
        this.pr = pr;
        this.c = c;
        this.a = a;
        this.m = m;
        this.d = d;
        this.b = b;
    }

    public void addToStatistics(SystemStateVector systemStateVector, int coef){
        for(int i = 0; i < systemStateVector.pr.length; i++){
            if(!systemStateVector.pr[i]){
                pr[i] += coef;
            }
        }

        for(int i = 0; i < systemStateVector.c.length; i++){
            if(!systemStateVector.c[i]){
                c[i] += coef;
            }
        }

        for(int i = 0; i < systemStateVector.a.length; i++){
            if(!systemStateVector.a[i]){
                a[i] += coef;
            }
        }

        for(int i = 0; i < systemStateVector.m.length; i++){
            if(!systemStateVector.m[i]){
                m[i] += coef;
            }
        }

        for(int i = 0; i < systemStateVector.d.length; i++){
            if(!systemStateVector.d[i]){
                d[i] += coef;
            }
        }

        for(int i = 0; i < systemStateVector.b.length; i++){
            if(!systemStateVector.b[i]){
                b[i] += coef;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("pr: %s\n", Arrays.toString(pr)) +
                String.format("c: %s\n", Arrays.toString(c)) +
                String.format("a: %s\n", Arrays.toString(a)) +
                String.format("m: %s\n", Arrays.toString(m)) +
                String.format("d: %s\n", Arrays.toString(d)) +
                String.format("b: %s\n", Arrays.toString(b));
    }
}
