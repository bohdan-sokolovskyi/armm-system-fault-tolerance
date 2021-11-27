package com.lab.functions;

import com.lab.systemModel.SystemStateVector;

public class ModifiedLogicStructureFunction implements LogicStructureFunction {

    private SystemStateVector ssv;

    @Override
    public boolean calculateF(SystemStateVector systemStateVector) {
        this.ssv = systemStateVector;
        return f1() && f2() && f3() && f4();
    }

    //TODO change soon
    boolean f1(){
        return false;
    }

    boolean f2(){
        return false;
    }

    boolean f3(){
        return false;
    }

    boolean f4(){
        return false;
    }
}
