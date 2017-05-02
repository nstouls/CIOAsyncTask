package fr.insa_cvl.cioasynctask;

/**
 * Created by jf on 02/05/17.
 */
public class Fibonacci {

    static protected int fibo(int n)
    {
        if (n <= 1)
            return n;
        else
            return fibo(n-1) + fibo(n-2);
    }
}
