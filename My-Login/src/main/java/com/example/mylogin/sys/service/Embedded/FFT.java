package com.example.mylogin.sys.service.Embedded;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FFT {
    static final int N = 1024;
    static Complex[] x = new Complex[N], W;
    static int size = 0;
    static double PI = Math.PI;

    public static void processFFT(MultipartFile file) {
        readInputFromFile(file);
        System.out.println("输出倒序后的序列");
        transform();
        fft();
        System.out.println("输出FFT后的结果");
        output();
        outputToFile();
    }
    static void readInputFromFile(MultipartFile file) {
        try {
            Scanner scanner = new Scanner(file.getInputStream());
            if (scanner.hasNextInt()) {
                size = scanner.nextInt();
                scanner.nextLine(); // Consume the rest of the line after reading integer
                System.out.println("Size: " + size);
            } else {
                throw new IllegalStateException("Expected an integer for size at the beginning of the file.");
            }
            x = new Complex[size]; // Initialize the array
            for (int i = 0; i < size; i++) {
                if (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    if (parts.length != 2) {
                        throw new IllegalStateException("Expected two values separated by comma for complex number.");
                    }
                    double real = Double.parseDouble(parts[0]);
                    double imag = Double.parseDouble(parts[1]);
                    System.out.println("Real part: " + real);
                    System.out.println("Imaginary part: " + imag);
                    x[i] = new Complex(real, imag);
                    System.out.println("Complex number: " + x[i]);
                } else {
                    throw new IllegalStateException("Expected more lines for complex numbers.");
                }
            }
            scanner.close();
        } catch (IllegalStateException | NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static String outputToFile() {
        try {
            String filename = "src/main/resources/OutPut/FFT/output.txt";
            FileWriter writer = new FileWriter(filename);
            for (int i = 0; i < size; i++) {
                writer.write(String.format("%.4f %.4f\n", x[i].real, x[i].imag));
            }
            writer.close();
            return "OutPut/FFT/output.txt";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static void output() {
        for (int i = 0; i < size; i++) {
            System.out.printf("%.4f", x[i].real);
            if (x[i].imag >= 0.0001) {
                System.out.printf("+%.4fj\n", x[i].imag);
            } else if (Math.abs(x[i].imag) < 0.0001) {
                System.out.println();
            } else {
                System.out.printf("%.4fj\n", x[i].imag);
            }
        }
    }

    static void change() {
        for (int i = 0; i < size; i++) {
            int j = 0, k = i;
            for (int l = (int)(Math.log(size) / Math.log(2)); l > 0; l--) {
                j = j << 1;
                j |= (k & 1);
                k = k >> 1;
            }
            if (j > i) {
                Complex temp = x[i];
                x[i] = x[j];
                x[j] = temp;
            }
        }
    }

    static void transform() {
        W = new Complex[size];
        for (int i = 0; i < size; i++) {
            W[i] = new Complex(Math.cos(2 * PI / size * i), -1 * Math.sin(2 * PI / size * i));
        }
    }

    static void fft() {
        change();
        output();
        for (int i = 0; i < Math.log(size) / Math.log(2); i++) {
            int m = 1 << i;
            for (int j = 0; j < size; j += 2 * m) {
                for (int k = 0; k < m; k++) {
                    Complex q = Complex.mul(x[k + j + m], W[size * k / 2 / m]);
                    Complex y = Complex.add(x[j + k], q);
                    Complex z = Complex.sub(x[j + k], q);
                    x[j + k] = y;
                    x[j + k + m] = z;
                }
            }
        }
    }

    static class Complex {
        double real, imag;

        public Complex(double real, double imag) {
            this.real = real;
            this.imag = imag;
        }
        @Override
        public String toString() {
            return String.format("%.4f+%.4fj", real, imag);
        }
        static Complex add(Complex a, Complex b) {
            return new Complex(a.real + b.real, a.imag + b.imag);
        }

        static Complex sub(Complex a, Complex b) {
            return new Complex(a.real - b.real, a.imag - b.imag);
        }

        static Complex mul(Complex a, Complex b) {
            double real = a.real * b.real - a.imag * b.imag;
            double imag = a.real * b.imag + a.imag * b.real;
            return new Complex(real, imag);
        }
    }
}
