package ch.epfl.sweng;

import java.util.Comparator;
import java.util.Map;

public class UserComparator implements Comparator<Map.Entry<User, Integer>> {

    @Override
    public int compare(Map.Entry<User, Integer> o1, Map.Entry<User, Integer> o2) {
        int pointCMP = o1.getValue().compareTo(o2.getValue());
        if (pointCMP!=0) return -1*pointCMP;
        int userCMP = o1.getKey().getName().compareTo(o2.getKey().getName());
        return userCMP;
    }

}
