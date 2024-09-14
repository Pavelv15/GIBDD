package GIBDD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        var gibdd = new GIBDDOffice(3);
        System.out.println(gibdd.getCarNumberList());
        gibdd.showFIOByNumber();


    }
}
