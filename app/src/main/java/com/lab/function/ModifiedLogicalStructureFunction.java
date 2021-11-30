package com.lab.function;

import com.lab.model.SystemStateVector;

public final class ModifiedLogicalStructureFunction extends AbstractLogicalStructureFunction {

    private SystemStateVector ssv;

    @Override
    public boolean f(SystemStateVector systemStateVector) {
        this.ssv = systemStateVector;
        return f1() && f2() && f3() && f4();
    }

    @Override
    boolean f1() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public boolean f2() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public boolean f3() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public boolean f4() {
        throw new UnsupportedOperationException("not implemented");
    }
}
