package com.lab;

public final class Startup {

    public static void main(String... args) {
        RunnableEnvironment env = RunnableEnvironment.init();

        env.runTestInitialSystem();
       // env.runTestModifiedSystem();
    }
}
