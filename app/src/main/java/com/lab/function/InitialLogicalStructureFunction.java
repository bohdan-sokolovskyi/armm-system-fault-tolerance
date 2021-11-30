package com.lab.function;

import com.lab.model.SystemStateVector;

public final class InitialLogicalStructureFunction extends AbstractLogicalStructureFunction {

    private SystemStateVector ssv;

    @Override
    public boolean f(SystemStateVector systemStateVector) {
        this.ssv = systemStateVector;
        return f1() && f2() && f3() && f4();
    }

    @Override
    boolean f1() {
        return ssv.d[0] && ssv.d[1] && ssv.c[0] && ssv.b[0] && ssv.pr[0] && ssv.pr[1];
    }

    @Override
    boolean f2() {
        return ssv.d[2] && (ssv.c[1] || ssv.c[2]) && ssv.m[0] && ssv.a[0] && ssv.pr[2];
    }

    @Override
    boolean f3() {
        return ssv.d[3] && ssv.c[3] && ssv.b[1] && ssv.pr[4];
    }

    @Override
    boolean f4() {
        return ssv.d[4] && (ssv.c[3] || ssv.c[4]) && (ssv.b[1] || ssv.b[2]) && (ssv.a[1] || ssv.a[2])
                && (ssv.m[0] || ssv.m[1]) && ssv.a[0] && ssv.pr[3];
    }
}
