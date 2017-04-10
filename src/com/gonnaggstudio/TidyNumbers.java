package com.gonnaggstudio;

import java.math.BigInteger;

/**
 * Created by hschueh on 2017/4/8.
 */
public class TidyNumbers extends Main.Template {

    TidyNumbers(String fileName) {
        super(fileName);
    }

    @Override
    void solve() {
        String in = sc.next();
        char[] ans = in.toCharArray();
        int untidyPlace = isTidy(ans);

        while (untidyPlace != -1) {
            tidinize(ans, untidyPlace);
            untidyPlace = isTidy(ans);
        }

        out.println(new BigInteger(new String(ans)));
    }

    static private void tidinize(char[] in, int startFrom) {
        --in[startFrom - 1];
        for (int i = startFrom; i < in.length; ++i) {
            in[i] = '9';
        }
    }

    static private int isTidy(char[] in) {
        char lastChar = in[0];
        int notTidyPlace = 1;
        boolean isTidy = true;
        for (; notTidyPlace < in.length; ++notTidyPlace) {
            int diff = (int) in[notTidyPlace] - (int) lastChar;
            if (diff < 0) {
                isTidy = false;
                break;
            }
            lastChar = in[notTidyPlace];
        }

        if (isTidy)
            return -1;
        else
            return notTidyPlace;
    }
}
