package com.example.mylogin.sys.service.Embedded;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FIR {

    public static String printFirResponse(double[] h) throws IOException {
        String filename = "src/main/resources/OutPut/FIR/output.txt";
        System.out.println("FIR digital filter");
        System.out.println(" * * * * impulse response * * * * \n");
        int n2 = h.length / 2;
        for (int i = 0; i <= n2; i++) {
            int j = h.length - i;
            System.out.printf("h(%2d) = %12.8f = h(%2d)\n", i, h[i], j);
        }

        System.out.println("\n * * * * impulse response by Java * * * * \n");

        System.out.print("static final double[] s_arrFirH = {");
        try (FileWriter writer = new FileWriter(filename)) {
            double[] c = new double[10240];
            double[] x = new double[300];
            double[] y = new double[300];
            Gain(h, c, h.length - 1, h.length - 1, x, y, 300, 2, writer);
        }
        System.out.println("};");
        return "OutPut/FIR/output.txt";
    }



    public static double[] Firls(MultipartFile file) throws IOException {
        double[] h = new double[10240];

        Scanner scanner = new Scanner(file.getInputStream());

        int n = scanner.nextInt();
        double fc = scanner.nextDouble();
        double fs = scanner.nextDouble();
        double f = scanner.nextDouble();
        int tp = scanner.nextInt();

        fc /= f;
        fs /= f;

        double fq = fs - fc;
        double fr = fs + fc;
        ls(h, n, fr);
        wgt(h, n, tp, fq);

        scanner.close();
        return h;
    }

    static void ls(double[] h, int n, double fc) {
        double pi = Math.PI;
        int m = n / 2;
        double am = n / 2.0;
        int n2 = (n - 1) / 2;
        if (m == am) {
            h[m] = fc;
        }
        for (int i = 0; i <= n2; i++) {
            double q = pi * (i - am);
            h[i] = Math.sin(fc * q) / q;
        }
    }

    static void wgt(double[] h, int n, int tp, double fq) {
        double pi = Math.PI;
        double q = pi * fq;
        double am = n / 2.0;
        if (fq == 0.0) {
            return;
        }
        for (int i = 0; i < am; i++) {
            double ql = q * (i - am) / tp;
            double wt;
            if (tp != 0) {
                wt = Math.pow(Math.sin(ql) / ql, tp);
            } else {
                wt = Math.cos(q * (i - am));
                if (Math.abs(wt) > 1.0e-6) {
                    wt /= (1 - Math.pow((2 * fq * (i - am)), 2));
                } else {
                    wt = pi / 4.0;
                }
            }
            h[i] = wt * h[i];
        }
        for (int i = 0; i < am; i++) {
            h[n - i] = h[i];
        }
    }

    static void Gain(double[] b, double[] a, int m, int n, double[] x, double[] y, int len, int sign, FileWriter writer)
            throws IOException {
        double ar, ai, br, bi, zr, zi, im, re, den, numr, numi, freq, temp;
        for (int k = 0; k < len; k++) {
            freq = k * 0.5 / (len - 1);
            zr = Math.cos(-8.0 * Math.atan(1.0) * freq);
            zi = Math.sin(-8.0 * Math.atan(1.0) * freq);
            br = 0.0;
            bi = 0.0;
            for (int i = m; i > 0; i--) {
                re = br;
                im = bi;
                br = (re + b[i]) * zr - im * zi;
                bi = (re + b[i]) * zi + im * zr;
            }
            ar = 0.0;
            ai = 0.0;
            for (int i = n; i > 0; i--) {
                re = ar;
                im = ai;
                ar = (re + a[i]) * zr - im * zi;
                ai = (re + a[i]) * zi + im * zr;
            }
            br = br + b[0];
            ar = ar + 1.0;
            numr = ar * br + ai * bi;
            numi = ar * bi - ai * br;
            den = ar * ar + ai * ai;
            x[k] = numr / den;
            y[k] = numi / den;
            switch (sign) {
                case 1: {
                    temp = Math.sqrt(x[k] * x[k] + y[k] * y[k]);
                    y[k] = Math.atan2(y[k], x[k]);
                    x[k] = temp;
                    break;
                }
                case 2: {
                    temp = x[k] * x[k] + y[k] * y[k];
                    y[k] = Math.atan2(y[k], x[k]);
                    x[k] = 10.0 * Math.log10(temp);
                }
            }
            writer.write(freq + "," + x[k] + "\n");
        }
    }
}
