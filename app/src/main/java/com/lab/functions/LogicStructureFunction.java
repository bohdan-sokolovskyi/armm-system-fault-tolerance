package com.lab.functions;

import com.lab.systemModel.SystemStateVector;

public interface LogicStructureFunction {
    boolean calculateF(SystemStateVector systemStateVector);
}
