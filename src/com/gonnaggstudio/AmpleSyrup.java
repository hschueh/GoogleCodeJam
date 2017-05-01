package com.gonnaggstudio;

import javafx.util.Pair;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by hschueh on 2017/4/30.
 */

public class AmpleSyrup extends Main.Template {

    AmpleSyrup(String fileName) {
        super(fileName);
    }
    double PI = Math.PI;
    int number;
    int stackNum;
    LinkedList<Pair<Long, Long>> hFirst;
    LinkedList<Pair<Long, Long>> restFirst;
    Long answer;
    @Override
    void solve() {
        number = sc.nextInt();
        stackNum = sc.nextInt();
        hFirst = new LinkedList<>();
        restFirst = new LinkedList<>();
        answer = 0L;
        for(int i = 0; i < number; ++i){
            Long r = sc.nextLong();Long h = sc.nextLong();
            hFirst.add(new Pair<Long, Long>(h, r));
            restFirst.add(new Pair<Long, Long>(h, r));
        }
        Collections.sort(hFirst, new Comparator<Pair<Long, Long>>()
        {
            public int compare( Pair<Long, Long> o1, Pair<Long, Long> o2 )
            {
                Long one = 2*o1.getKey()*o1.getValue();
                Long two = 2*o2.getKey()*o2.getValue();
                return one.compareTo(two);
            }
        });
        Long R = 0L;
        int i = 0;
        for(; i < stackNum-1 && i < hFirst.size(); ++i){
            Pair<Long, Long> p = hFirst.get(number - 1 - i);
            put(p.getValue(), p.getKey());
            restFirst.remove(p);
            R = Math.max(R, p.getValue());

        }
        Pair<Long, Long> nextCake = hFirst.get(number - 1 - i);

        Collections.sort(restFirst, new Comparator<Pair<Long, Long>>()
        {
            public int compare( Pair<Long, Long> o1, Pair<Long, Long> o2 )
            {
                Long one = o1.getValue() * o1.getValue() + o1.getValue() * o1.getKey() * 2;
                Long two = o2.getValue() * o2.getValue() + o2.getValue() * o2.getKey() * 2;
                return one.compareTo(two);
            }
        });
        Pair<Long, Long> pRBig = restFirst.get(restFirst.size() - 1);

        Long diff = (nextCake.getValue()*nextCake.getKey()*2 + R*R) - (pRBig.getValue()*pRBig.getKey()*2 + pRBig.getValue()*pRBig.getValue());
        if(R > pRBig.getValue() || diff > 0) {
            put(nextCake.getValue(), nextCake.getKey());
            answer += R * R;
        } else {
            R = pRBig.getValue();
            put(pRBig.getValue(), pRBig.getKey());
            answer += R*R;
        }





        System.out.println(String.format("%.8f", answer * PI));
        out.println(String.format("%.8f", answer * PI));
    }

    void put(Long r, Long h){
        answer += 2*r*h;
    }

}
