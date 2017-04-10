package com.gonnaggstudio;

/**
 * Created by hschueh on 2017/4/9.
 */
public class OversizedPancakeFlipper extends Main.Template {

    OversizedPancakeFlipper(String fileName) {
        super(fileName);
    }

    @Override
    void solve(){
        char[] in = sc.next().toCharArray();
        int size = sc.nextInt();
        int step = 0;
        int i = 0;
        for(; i + size - 1< in.length; ++i) {
            if(in[i] == '+') {
                continue;
            }
            for(int j = 0; j < size; ++j){
                in[i+j] = (in[i+j] == '+')?'-':'+';
            }
            ++step;
        }
        for(; i < in.length; ++i) {
            if(in[i] == '-') {
                out.println("IMPOSSIBLE");
                return;
            }
        }

        out.println(step);
    }
}