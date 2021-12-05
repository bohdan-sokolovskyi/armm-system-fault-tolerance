package com.lab.table.element;

public class LiveProcessor extends Processor {
    private int curTime;

    public LiveProcessor(Processor processor) {
        super(processor);
        this.curTime = this.timeNom;
    }

    public void addTime(int deltaTime) {
        if(isCanAddTime(deltaTime)) {
            return;
        }

        curTime += deltaTime;
    }

    public boolean isCanAddTime(int deltaTime) {
        return !((curTime + deltaTime) > timeMax);
    }
}
