package com.lab;

import com.lab.model.SystemStateVector;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;

public final strictfp class ProbabilityCalculator {
    public static final int COUNT_OF_SUB_PROBABILITIES = 5;

    // probabilities of modules
    //TODO: maybe need set scale
    public static final BigDecimal PR_I_PROB = new BigDecimal("3.2E-4");
    public static final BigDecimal B_I_PROB = new BigDecimal("2.4E-5");
    public static final BigDecimal A_I_PROB = new BigDecimal("2.2E-4");
    public static final BigDecimal C_I_PROB = new BigDecimal("4.1E-4");
    public static final BigDecimal D_I_PROB = new BigDecimal("3.2E-5");
    public static final BigDecimal M_I_PROB = new BigDecimal("2.3E-4");

    // P[0] = probability of system live when 0 errors; P[1] = when 1 errors and so on;
    private final BigDecimal[] p;

    public ProbabilityCalculator() {
        p = new BigDecimal[COUNT_OF_SUB_PROBABILITIES];
        reset();
    }

    public void set(int i, BigDecimal val) {
        p[i] = val;
    }

    public void addTo(int i, BigDecimal val) {
        p[i] = p[i].add(val);
    }

    public void multiplyTo(int i, BigDecimal val) { p[i] = p[i].multiply(val);}

    public void reset() {
        Arrays.fill(p, BigDecimal.ZERO);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.00000");

        for(int i = 0; i < p.length; i++) {
            result.append(String.format("P[%d] = %s\n", i, df.format(p[i])));
        }

        return result.toString();
    }

    public static BigDecimal calcSSVProb(SystemStateVector vec) {
        //TODO: maybe need set scale
        BigDecimal prob = new BigDecimal("1.0");

        for(var pr: vec.pr){
            prob = prob.multiply(pr ? PR_I_PROB : BigDecimal.ONE.subtract(PR_I_PROB));
        }

        for(var b: vec.b) {
            prob = prob.multiply(b ? B_I_PROB : BigDecimal.ONE.subtract(B_I_PROB));
        }

        for(var a: vec.a) {
            prob = prob.multiply(a ? A_I_PROB : BigDecimal.ONE.subtract(A_I_PROB));
        }

        for(var c: vec.c) {
            prob = prob.multiply(c ? C_I_PROB : BigDecimal.ONE.subtract(C_I_PROB));
        }

        for(var d: vec.d) {
            prob = prob.multiply(d ? D_I_PROB : BigDecimal.ONE.subtract(D_I_PROB));
        }

        for(var m: vec.m) {
            prob = prob.multiply(m ? M_I_PROB : BigDecimal.ONE.subtract(M_I_PROB));
        }

        return prob;
    }
}
