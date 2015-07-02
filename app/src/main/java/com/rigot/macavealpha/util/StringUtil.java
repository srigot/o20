package com.rigot.macavealpha.util;

/**
 * Created by Seb on 11/04/2015.
 */
public class StringUtil {

    public static String integerToString(Integer val) {
        String retour = null;
        if (val != null) {
            retour = val.toString();
        }
        return retour;
    }

    public static String floatToString(Float val) {
        String retour = null;
        if (val != null) {
            retour = val.toString();
        }
        return retour;
    }

    /**
     * Transforme une chaine en entier
     *
     * @param val la chaine à transformer
     * @return L'entier ou null si la chaine n'est pas renseignée.
     */
    public static Integer StringToInteger(String val) {
        Integer retour = null;

        if ((val != null) && (!val.isEmpty())) {
            retour = Integer.parseInt(val);
        }
        return retour;
    }

    /**
     * Transforme une chaine en decimal (float)
     *
     * @param val la chaine à transformer
     * @return Le decimal ou null si la chaine n'est pas renseignée
     */
    public static Float StringToFloat(String val) {
        Float retour = null;

        if ((val != null) && (!val.isEmpty())) {
            retour = Float.parseFloat(val);
        }

        return retour;
    }

}
