package org.prime;

import org.json.simple.JSONObject;

/**
 * Created by Golam Rahman Tushar on 5/29/2017.
 */
public final class Utility {
    private Utility() {}

    public static boolean isValidTag(String tagName) {

//        if ( tagName == "select" ||
//             tagName == "button" ||
//             tagName == "image" ||
//             tagName == "li" ||
//             tagName == "ul" ||
//             tagName == "input" ||
//             tagName == "image" ||
//             tagName == "option" ||
//             tagName == "label") {
//            return false;
//        }

        return true;
    }

    public static String removeNonPrintableChars(String args) {
        return args.replaceAll("\\P{Print}", " ");
    }

    public static String removeSpaces(String arg) {
//        Pattern whitespace = Pattern.compile("\\s+");
//        Matcher matcher = whitespace.matcher(arg);
//        String result = "";
//        if (matcher.find()) {
//            result = matcher.replaceAll(" ");
//        }
//        return result;
        return arg.trim().replaceAll(" +", " ");
    }

    public static String removeSpecialCharacters(String param) {
        String result = param;
        result = result.replaceAll("&", "&amp;");
        result = result.replaceAll("<", "&lt;");
        result = result.replaceAll(">", "&gt;");
        result = result.replaceAll("\"", "&quot;");
        result = result.replaceAll("'", "&apos;");
        return result;
    }

    public static String getRidOfSpecialChar(String param) {
       return JSONObject.escape(param);
    }
}
