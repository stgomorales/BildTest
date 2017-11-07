package com.example.stgo.bildform.APIS;

import android.content.Context;
import android.text.InputFilter;

/**
 * Created by shagos on 06-11-17.
 */

public class Utils {
    private Context context;

    private static Utils instance = null;

    /**
     * Singleton implementation;
     */

    public static Utils getInstance(Context context){
        if (instance == null)
            instance = new Utils(context);
        return instance;
    }

    private Utils(Context context) {
        this.context = context;
    }

    public InputFilter[] getIpFilter(){
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       android.text.Spanned dest, int dstart, int dend) {
                if (end > start) {
                    String destTxt = dest.toString();
                    String resultingTxt = destTxt.substring(0, dstart) + source.subSequence(start, end) + destTxt.substring(dend);
                    if (!resultingTxt.matches ("^\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3})?)?)?)?)?)?")) {
                        return "";
                    } else {
                        String[] splits = resultingTxt.split("\\.");
                        for (int i=0; i<splits.length; i++) {
                            if (Integer.valueOf(splits[i]) > 255) {
                                return "";
                            }
                        }
                    }
                }
                return null;
            }

        };
        return filters;
    }
}
