package com.exp.ysy.Tools;

public class NativeFir {

    public NativeFir() {

    }

    public  native void FirInit(double[] fir);

    public  native void DoFir(short[] src, short[] dst);

}