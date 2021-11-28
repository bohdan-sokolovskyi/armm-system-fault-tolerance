package com.lab.systemModel;

import java.util.Arrays;

public class SystemStateVector {
    public final boolean[] pr; //processors
    public final boolean[] c; //controllers
    public final boolean[] a; //adapters
    public final boolean[] m; //magistrates
    public final boolean[] d; //detectors
    public final boolean[] b; //busses

    public static SystemStateVector createForInitialSystem(){
        return new SystemStateVector(new boolean[5],new boolean[5],new boolean[3],new boolean[2],new boolean[5],new boolean[3]);
    }

    public static SystemStateVector createSSVforModifiedSystem(){
        //TODO change for modifiedSystem
        throw new RuntimeException("Not implemented!");
    }

    private SystemStateVector(boolean[] pr, boolean[] c, boolean[] a, boolean[] m, boolean[] d, boolean[] b) {
        this.pr = pr;
        this.c = c;
        this.a = a;
        this.m = m;
        this.d = d;
        this.b = b;
    }

    @Override
    public String toString() {
        return "SystemStateVector{" +
                "pr=" + Arrays.toString(pr) +
                ", c=" + Arrays.toString(c) +
                ", a=" + Arrays.toString(a) +
                ", m=" + Arrays.toString(m) +
                ", d=" + Arrays.toString(d) +
                ", b=" + Arrays.toString(b) +
                '}';
    }
}
