package org.example.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueExtractUtil {
    public static String fetchPrice(String price){
        Pattern valuePattern = Pattern.compile(".*\\$([0-9.]+)");
        Matcher matcher = valuePattern.matcher(price);
        if(matcher.find()){
            return matcher.group(1);
        }

        return "not_found";
    }
}
