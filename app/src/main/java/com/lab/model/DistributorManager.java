package com.lab.model;

public class DistributorManager {

    //TODO implement this class

    public static DistributorManager createForInitialSystem(){
        //TODO change for normal
        return new DistributorManager();
    }

    public static DistributorManager createForModifiedSystem(){
        //TODO should be implemented after modified system creation
        throw new RuntimeException("not implemented");
    }

    private DistributorManager(){}
}
