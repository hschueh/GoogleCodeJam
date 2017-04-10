package com.gonnaggstudio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws Exception {
        new BathroomStalls("C-large-practice").run();

    }

    static class Template {
        Scanner sc;
        PrintStream out;

        Template(String fileName){
            String FILENAME = fileName;
            String IN = FILENAME + ".in";
            String OUT = FILENAME + ".out";
            sc = new Scanner(getClass().getResourceAsStream(IN));
            try {
                File f = new File(OUT);
                if(!f.exists())
                    f.createNewFile();
                out = new PrintStream(new FileOutputStream(OUT));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        void solve() {
            int ans = 0;

            out.println(ans);
        }

        void run() throws Exception {
            int t = sc.nextInt();
            for (int i = 1; i <= t; i++) {
                System.out.print("Case #" + i + ": ");
                out.print("Case #" + i + ": ");
                solve();
            }
            sc.close();
            out.close();
        }
    }
}
