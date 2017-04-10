package com.gonnaggstudio;

import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by hschueh on 2017/4/8.
 */
public class BathroomStalls extends Main.Template {
    private long stallCnt;
    private long personCnt;
    private TreeMap<Long, Long> spaceCount;
    BathroomStalls(String fileName) {
        super(fileName);
    }

    @Override
    void solve() {
        stallCnt = sc.nextLong();
        personCnt = sc.nextLong();
        spaceCount = new TreeMap<>();
        spaceCount.put(stallCnt, 1L);
        Map.Entry<Long, Long> currentPivot = spaceCount.lastEntry();
        long lastInsertedSpace;
        long currentSpaceCount = 1L;
        long lastLs;
        long lastRs;
        do{
            lastInsertedSpace = currentPivot.getKey();
            lastLs = (lastInsertedSpace%2L == 0L) ? ( lastInsertedSpace/2L - 1L ):( (lastInsertedSpace-1L) / 2L);
            lastRs = lastInsertedSpace - lastLs - 1L;
            long goingPersonCount = (personCnt > currentSpaceCount)?currentSpaceCount: personCnt;
            if(spaceCount.containsKey(lastLs)) {
                spaceCount.put(lastLs, spaceCount.get(lastLs) + goingPersonCount);
            }
            else {
                spaceCount.put(lastLs, goingPersonCount);
            }
            if(spaceCount.containsKey(lastRs)) {
                spaceCount.put(lastRs, spaceCount.get(lastRs) + goingPersonCount);
            }
            else {
                spaceCount.put(lastRs, goingPersonCount);
            }
            currentSpaceCount -= goingPersonCount;
            if(currentSpaceCount < 1){
                spaceCount.pollLastEntry();
                currentPivot = spaceCount.lastEntry();
                currentSpaceCount = currentPivot.getValue();
            }

            personCnt -= goingPersonCount;
        } while(personCnt > 0L);

        System.out.println(Math.max(lastLs, lastRs) + " " + Math.min(lastLs, lastRs));
        out.println(Math.max(lastLs, lastRs) + " " + Math.min(lastLs, lastRs));
    }
}
