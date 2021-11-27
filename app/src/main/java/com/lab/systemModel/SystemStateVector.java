package com.lab.systemModel;

import java.util.Arrays;

public class SystemStateVector {
    public boolean[] pr; //processors
    public boolean[] c; //controllers
    public boolean[] a; //adapters
    public boolean[] m; //magistrates
    public boolean[] d; //detectors
    public boolean[] b; //busses

    public SystemStateVector(boolean[] pr, boolean[] c, boolean[] a, boolean[] m, boolean[] d, boolean[] b) {
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
