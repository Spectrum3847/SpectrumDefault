package org.spectrum3847.lib.util;

import java.util.List;

/**
 * Contains basic functions that are used often.
 *
 * @author richard@team254.com (Richard Lin)
 * @author brandon.gonzalez.451@gmail.com (Brandon Gonzalez)
 * @author tom.bottiglieri@gmail.com (Tom Bottiglieri)
 */
public class Util {
    // Prevent this class from being instantiated.
    private Util() {
    }

    /**
     * Limits the given input to the given magnitude.
     */
    public static double limit(double v, double limit) {
        return limit(v, limit, -limit);
    	//return (Math.abs(v) < limit) ? v : limit * (v < 0 ? -1 : 1);
    }
    
    /**
     * limits input to the max and min
     * @param v - input
     * @param max - max value
     * @param min - min value
     * @return
     */
    public static double limit(double v, double max, double min){
    	return (v > max) ? max : ((v < min) ? min : v);
    }

    public static String joinStrings(String delim, List<?> strings) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.size(); ++i) {
            sb.append(strings.get(i).toString());
            if (i < strings.size() - 1) {
                sb.append(delim);
            }
        }
        return sb.toString();
    }

}
