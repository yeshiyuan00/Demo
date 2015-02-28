package com.resources;

import android.app.Activity;
import android.os.Bundle;

import com.exp.ysy.Tools.NativeFir;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ysy on 2015/2/28.
 */
public class TestForFir extends Activity {

    private static final String FIR3_NAME = "FIR3.txt";
    private static final String FIR4_NAME = "FIR4.txt";

    private double[] double_fir3;
    private double[] double_fir4;

    private final int order = 128;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ReadFir();

        short[] data = new short[2048];
        double[] result = new double[2048];
        short[] result_native = new short[2048];
        for (int i = 0; i < data.length; i++) {
            data[i] = (short) i;
        }

        NativeFir nativeFir = new NativeFir();
        nativeFir.FirInit(double_fir3);


        nativeFir.DoFir(data, result_native);

        FIR fir = new FIR(double_fir3);

        for (int i = 0; i < data.length; i++) {

            result[i] = fir.getOutputSample(data[i]);
        }


        System.out.println("double_fir3[0]=" + double_fir3[1027]);

        System.out.println("result[0]=" + result[0]);
        System.out.println("result_native[0]=" + result_native[0]);
        System.out.println("result[1027]=" + result[1027]);
        System.out.println("result_native[1027]=" + result_native[1027]);


    }


    private void ReadFir() {


        byte[] byte_fir = new byte[order * 8];

        double_fir3 = new double[order];
        double_fir4 = new double[order];

        InputStream fis = null;
        try {
            fis = this.getAssets().open(FIR3_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fis != null) {
            try {
                fis.read(byte_fir, 0, order * 8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (fis != null) {

            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        byte[] temp = new byte[8];
        for (int i = 0; i < order; i++) {

            for (int j = 0; j < 8; j++) {
                temp[j] = byte_fir[i * 8 + j];
            }

            double_fir3[i] = bytesToDouble(temp);

        }

        InputStream fis2 = null;
        try {
            fis = this.getAssets().open(FIR4_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fis2 != null) {
            try {
                fis2.read(byte_fir, 0, order * 8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (fis2 != null) {

            try {
                fis2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        byte[] temp2 = new byte[8];
        for (int i = 0; i < order; i++) {

            for (int j = 0; j < 8; j++) {
                temp2[j] = byte_fir[i * 8 + j];
            }

            double_fir4[i] = bytesToDouble(temp2);

        }
    }



    static {

        System.loadLibrary("NativeFir");
    }

    //字节到浮点转换
    public static double bytesToDouble(byte[] readBuffer) {
        return Double.longBitsToDouble((((long) readBuffer[7] << 56) +
                        ((long) (readBuffer[6] & 255) << 48) +
                        ((long) (readBuffer[5] & 255) << 40) +
                        ((long) (readBuffer[4] & 255) << 32) +
                        ((long) (readBuffer[3] & 255) << 24) +
                        ((readBuffer[2] & 255) << 16) +
                        ((readBuffer[1] & 255) << 8) +
                        ((readBuffer[0] & 255) << 0))

        );
    }
}
