package com.gonnaggstudio;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by hschueh on 2017/4/15.
 */

public class Ratatouille extends Main.Template {

    Ratatouille(String fileName) {
        super(fileName);
    }

    int packageType;
    int packageCount;
    int[] needIngredient;
    int[] packagePivots;
    ArrayList<ArrayList<Integer>> packageMass;
    @Override
    void solve(){
        packageType = sc.nextInt();
        packageCount = sc.nextInt();
        needIngredient = new int[packageType];
        packagePivots = new int[packageType];
        packageMass = new ArrayList<>();

        for(int i = 0; i < packageType; ++i) {
            needIngredient[i] = sc.nextInt();
        }
        for(int i = 0; i < packageType; ++i) {
            ArrayList<Integer> list = new ArrayList<>();
            for(int j = 0; j < packageCount; ++j) {
                list.add(sc.nextInt());
            }
            Collections.sort(list);
            packageMass.add(list);
        }

        int currentServing = 1;
        int setCount = 0;

        while (stillGotPackage()) {
            boolean found = true;
            boolean needAddServing = false;
            int[] packageSelection = new int[packageType];
            for (int i = 0; i < packageType; ++i) {
                int target = needIngredient[i] * currentServing;
                boolean targetTooSmall = false;
                boolean foundOne = false;
                for (int j = packagePivots[i]; j < packageMass.get(i).size(); ++j) {
                    if (packageMass.get(i).get(j) >= target * 0.9 && packageMass.get(i).get(j) <= target * 1.1) {
                        packageSelection[i] = j + 1;
                        foundOne = true;
                        targetTooSmall = false;
                        break;
                    }
                    else if (packageMass.get(i).get(j) > target * 1.1) {
                        targetTooSmall = true;
                    }
                }
                needAddServing = needAddServing || targetTooSmall;
                found = foundOne && found;
            }

            if(found) {
//                    System.out.println("Found! Current Serving is:"+ currentServing);
                packagePivots = packageSelection;
                ++setCount;
            }
            else if (needAddServing)
                ++currentServing;
            else
                break;
        }

        out.println(setCount);
    }

    boolean stillGotPackage() {
        for(int i = 0; i < packageType; ++i){
            if(packagePivots[i] > packageCount) {
                System.out.println("oops, no package left.");
                return false;
            }
        }
        return true;
    }
}
