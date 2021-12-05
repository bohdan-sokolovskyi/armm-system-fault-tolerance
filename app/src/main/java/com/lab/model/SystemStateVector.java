package com.lab.model;

import java.util.Arrays;

public final class SystemStateVector {
    public final boolean[] pr; //processors
    public final boolean[] c; //controllers
    public final boolean[] a; //adapters
    public final boolean[] m; //magistrates
    public final boolean[] d; //detectors
    public final boolean[] b; //busses

    private final int[] boundaries;

    public static SystemStateVector createForInitialSystem(){
        return new SystemStateVector(new boolean[5],new boolean[5],new boolean[3],new boolean[2],new boolean[5],new boolean[3]);
    }

    public static SystemStateVector createForModifiedSystem(){
        throw new RuntimeException("not implemented");
    }

    public SystemStateVector(SystemStateVector ssv) {
        this(ssv.pr, ssv.c, ssv.a, ssv.m, ssv.d, ssv.b);
    }

    private SystemStateVector(boolean[] pr, boolean[] c, boolean[] a, boolean[] m, boolean[] d, boolean[] b) {
        this.pr = pr;
        this.c = c;
        this.a = a;
        this.m = m;
        this.d = d;
        this.b = b;

        boundaries = new int[6];
        boundaries[0] = pr.length;
        boundaries[1] = boundaries[0] + c.length;
        boundaries[2] = boundaries[1] + a.length;
        boundaries[3] = boundaries[2] + m.length;
        boundaries[4] = boundaries[3] + d.length;
        boundaries[5] = boundaries[4] + b.length;
    }

    public void setAllTrue(){
        Arrays.fill(pr,true);
        Arrays.fill(c,true);
        Arrays.fill(a,true);
        Arrays.fill(m,true);
        Arrays.fill(d,true);
        Arrays.fill(b,true);
    }

    public void setToTrue(int pos) {
        setTo(pos, true);
    }

    public void setToFalse(int pos){
        setTo(pos, false);
    }

    public void setTo(int pos, boolean val) {
        if(pos < 0){
            throw new RuntimeException("pos can't be less 0!");
        }else if(pos < boundaries[0]){
            pr[pos] = false;
        }else if(pos < boundaries[1]){
            c[pos - boundaries[0]] = val;
        }else if(pos < boundaries[2]){
            a[pos - boundaries[1]] = val;
        }else if(pos < boundaries[3]){
            m[pos - boundaries[2]] = val;
        }else if(pos < boundaries[4]){
            d[pos - boundaries[3]] = val;
        }else if(pos < boundaries[5]){
            b[pos - boundaries[4]] = val;
        }else {
            throw new RuntimeException("pos is greater then " + boundaries[5]);
        }
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
