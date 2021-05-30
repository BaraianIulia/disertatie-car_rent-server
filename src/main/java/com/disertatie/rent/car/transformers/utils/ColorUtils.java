//package com.disertatie.rent.car.transformers.utils;
//
//import org.springframework.stereotype.Component;
//
//import java.awt.*;
//import java.util.ArrayList;
//
//
///**
// * Java Code to get a color name from rgb/hex value/awt color
// * <p>
// * The part of looking up a color name from the rgb values is edited from
// * https://gist.github.com/nightlark/6482130#file-gistfile1-java (that has some errors) by Ryan Mast (nightlark)
// *
// * @author Xiaoxiao Li
// */
//@Component(value = "colorUtils")
//public class ColorUtilsAndroid {
//
//    /**
//     * Initialize the color list that we have.
//     */
//
//
//    /**
//     * Get the closest color name from our list
//     *
//     * @param r
//     * @param g
//     * @param b
//     * @return
//     */
//    public String getColorNameFromRgb(int r, int g, int b) {
//        ArrayList<ColorName> colorList = initColorList();
//        ColorName closestMatch = null;
//        int minMSE = Integer.MAX_VALUE;
//        int mse;
//        for (ColorName c : colorList) {
//            mse = c.computeMSE(r, g, b);
//            if (mse < minMSE) {
//                minMSE = mse;
//                closestMatch = c;
//            }
//        }
//
//        if (closestMatch != null) {
//            return closestMatch.getName();
//        } else {
//            return "No matched color name.";
//        }
//    }
//
//    /**
//     * Convert hexColor to rgb, then call getColorNameFromRgb(r, g, b)
//     *
//     * @param hexColor
//     * @return
//     */
//    public String getColorNameFromHex(int hexColor) {
//        int r = (hexColor & 0xFF0000) >> 16;
//        int g = (hexColor & 0xFF00) >> 8;
//        int b = (hexColor & 0xFF);
//        return getColorNameFromRgb(r, g, b);
//    }
//
//    public int colorToHex(Color c) {
//        return Integer.decode("0x"
//                + Integer.toHexString(c.getRGB()).substring(2));
//    }
//
//    public int stringToColor(String c) {
//        int color = Color.rgb(r,g,b);
//    }
//
//    public String getColorNameFromColor(Color color) {
//        return getColorNameFromRgb(color.getRed(), color.getGreen(),
//                color.getBlue());
//    }
//
//
//}
