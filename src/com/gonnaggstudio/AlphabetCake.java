package com.gonnaggstudio;

/**
 * Created by hschueh on 2017/4/15.
 */

public class AlphabetCake extends Main.Template {

    AlphabetCake(String fileName) {
        super(fileName);
    }

    char[][] cake;
    String alreadyDone;
    int width;
    int height;

    @Override
    void solve() {
        height = sc.nextInt();
        width = sc.nextInt();
        alreadyDone = "?";
        
        //Lazy to write clean code.
        sc.nextLine();
        out.println();

        cake = new char[height][];
        for(int i = 0; i < height; ++i) {
            String line = sc.nextLine();
            cake[i] = line.toCharArray();
        }

        for(int i = 0; i < height; ++i){
            for(int j = 0; j < width; ++j){
                if(alreadyDone.indexOf(cake[i][j]) == -1) {
                    expand(i, j);
                    alreadyDone += cake[i][j];
                }
            }
        }
        for(int i = 0; i < height; ++i){
            for(int j = 0; j < width; ++j){
                out.print(cake[i][j]);
            }
            out.println("");
        }
    }

    void expand(int i, int j){
        char c = cake[i][j];
        int U = i;
        int B = i;
        int L = j;
        int R = j;
        boolean isAlive = true;
        boolean LAlive = false;
        boolean RAlive = false;
        while (isAlive) {

            if(L > 0 && cake[i][L-1] == '?') {
                --L;
                LAlive = true;
            }
            else {
                LAlive = false;
            }

            if(R+1 < width && cake[i][R+1] == '?') {
                ++R;
                RAlive = true;
            }
            else {
                RAlive = false;
            }
            isAlive = LAlive || RAlive;
        }

        isAlive = true;
        boolean UAlive = true;
        boolean BAlive = true;
        while (isAlive) {


            if(!(U > 0))
                UAlive = false;
            else {
                for (int jj = L; jj <= R; ++jj) {
                    if (cake[U - 1][jj] != '?') {
                        UAlive = false;
                        break;
                    }
                }
            }

            if (UAlive)
                --U;

            if(B+1 >= height)
                BAlive = false;
            else {
                for (int jj = L; jj <= R; ++jj) {
                    if (cake[B + 1][jj] != '?') {
                        BAlive = false;
                        break;
                    }
                }
            }

            if (BAlive)
                ++B;
            isAlive = UAlive || BAlive;
        }

        for(int ii = U; ii < B+1; ++ii){
            for(int jj = L; jj < R+1; ++jj){
                cake[ii][jj] = c;
            }
        }
    }

}