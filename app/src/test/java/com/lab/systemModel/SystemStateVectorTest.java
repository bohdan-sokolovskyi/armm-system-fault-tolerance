package com.lab.systemModel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SystemStateVectorTest {

    @Test
    public void setSomeBitsToFalseTest(){
        SystemStateVector ssv = SystemStateVector.createForInitialSystem();
        ssv.setAllTrue();
        ssv.setToFalse(3);
        ssv.setToFalse(22);
        Assertions.assertFalse(ssv.pr[3]);
        Assertions.assertFalse(ssv.b[2]);
    }
}