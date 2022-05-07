package pl.edu.pw.geometry;

public class Surface {

    private double A;
    private double B;
    private double C;
    private double D;

    public Surface() {
    }

    public Surface(double a, double b, double c, double d) {
        A = a;
        B = b;
        C = c;
        D = d;
    }

    public double getA() {
        return A;
    }

    public void setA(double a) {
        A = a;
    }

    public double getB() {
        return B;
    }

    public void setB(double b) {
        B = b;
    }

    public double getC() {
        return C;
    }

    public void setC(double c) {
        C = c;
    }

    public double getD() {
        return D;
    }

    public void setD(double d) {
        D = d;
    }

    public boolean isXeq0() {
        return A != 0 && B == 0 && C == 0 && D == 0;
    }

    public boolean isYeq0() {
        return A == 0 && B != 0 && C == 0 && D == 0;
    }
}
