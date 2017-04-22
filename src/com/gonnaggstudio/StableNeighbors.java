package com.gonnaggstudio;

import java.util.ArrayList;

/**
 * Created by hschueh on 2017/4/23.
 */

public class StableNeighbors extends Main.Template {

    StableNeighbors(String fileName) {
        super(fileName);
    }
    private Integer Number;
    private ArrayList<Integer> unicorns;
    private int lastColor;//R = 0, O = 1, Y = 2, G = 3, B = 4, V = 5
    private int firstColor;//R = 0, O = 1, Y = 2, G = 3, B = 4, V = 5
    private char[] unicornColor = {'R','O','Y','G','B','V'};
    private char[] draft;
    @Override
    void solve() {
        boolean success = true;
        Number = sc.nextInt();
        unicorns = new ArrayList<>();
        unicorns.add(sc.nextInt());
        unicorns.add(sc.nextInt());
        unicorns.add(sc.nextInt());
        unicorns.add(sc.nextInt());
        unicorns.add(sc.nextInt());
        unicorns.add(sc.nextInt());
        draft = new char[Number];
        int max = 0;
        for(int i = 0; i < 6; ++i){
            if(unicorns.get(i) > 0 && windowSameColorSum(i) > max){
                max = windowSameColorSum(i);
                lastColor = i;
            }
        }
        putDraft(0, lastColor);
        firstColor = lastColor;
        int currentSelection = -1;
        for(int i = 1; i < Number; ++i){
            currentSelection = selectColor();
            if(currentSelection == -1 ) {
                success = false;
                break;
            }
            else {
                putDraft(i, currentSelection);
            }
        }
        success = success?checkAvailability(firstColor):success;

        if(success){
            System.out.println(draft);
            out.println(draft);
        } else {
            System.out.println("IMPOSSIBLE");
            out.println("IMPOSSIBLE");
        }
    }

    private int selectColor() {
        int max = checkAvailability(firstColor)?windowSameColorSum(firstColor):0;
        int selection = checkAvailability(firstColor)?firstColor:-1;
        for(int i = 0; i < 6; ++i){
            int currentColor = modSix(firstColor - 1 + i);
            if(windowSameColorSum(currentColor) > max && unicorns.get(currentColor) > 0 && checkAvailability(currentColor)){
                max = windowSameColorSum(currentColor);
                selection = currentColor;
            }
        }
        return selection;
    }

    private int windowSameColorSum(int color) {
        return unicorns.get(modSix(color-1))+unicorns.get(modSix(color))+unicorns.get(modSix(color+1));
    }

    private boolean checkAvailability(int color) {
        return lastColor != color && lastColor != modSix(color - 1)  && lastColor != modSix(color + 1);
    }

    private int modSix(int number) {
        if(number > 5)
            return number - 6;
        else if(number < 0)
            return number + 6;
        else
            return number;
    }

    private void putDraft(int position, int selection){
        lastColor = selection;
        draft[position] = unicornColor[lastColor];
        unicorns.set(lastColor, unicorns.get(lastColor) - 1);
    }
}
