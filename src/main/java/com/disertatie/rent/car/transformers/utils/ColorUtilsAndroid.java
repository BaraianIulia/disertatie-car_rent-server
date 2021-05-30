package com.disertatie.rent.car.transformers.utils;

import android.graphics.Color;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component(value = "colorUtilsAndroid")
public class ColorUtilsAndroid {

    private static final double RED_LUMA = 0.299;
    private static final double GREEN_LUMA = 0.587;
    private static final double BLUE_LUMA = 0.587;
    private static final double DARKNESS_RESISTANCE = 0.5;

    public static ArrayList<ColorUtilsAndroid.ColorName> colorList = new ArrayList<ColorUtilsAndroid.ColorName>();
    {
        initColorList();
    }
    private ArrayList<ColorUtilsAndroid.ColorName> initColorList() {

        colorList.add(new ColorUtilsAndroid.ColorName("AliceBlue", 0xF0, 0xF8, 0xFF));
        colorList.add(new ColorUtilsAndroid.ColorName("AntiqueWhite", 0xFA, 0xEB, 0xD7));
        colorList.add(new ColorUtilsAndroid.ColorName("Aqua", 0x00, 0xFF, 0xFF));
        colorList.add(new ColorUtilsAndroid.ColorName("Aquamarine", 0x7F, 0xFF, 0xD4));
        colorList.add(new ColorUtilsAndroid.ColorName("Azure", 0xF0, 0xFF, 0xFF));
        colorList.add(new ColorUtilsAndroid.ColorName("Beige", 0xF5, 0xF5, 0xDC));
        colorList.add(new ColorUtilsAndroid.ColorName("Bisque", 0xFF, 0xE4, 0xC4));
        colorList.add(new ColorUtilsAndroid.ColorName("Black", 0x00, 0x00, 0x00));
        colorList.add(new ColorUtilsAndroid.ColorName("BlanchedAlmond", 0xFF, 0xEB, 0xCD));
        colorList.add(new ColorUtilsAndroid.ColorName("Blue", 0x00, 0x00, 0xFF));
        colorList.add(new ColorUtilsAndroid.ColorName("BlueViolet", 0x8A, 0x2B, 0xE2));
        colorList.add(new ColorUtilsAndroid.ColorName("Brown", 0xA5, 0x2A, 0x2A));
        colorList.add(new ColorUtilsAndroid.ColorName("BurlyWood", 0xDE, 0xB8, 0x87));
        colorList.add(new ColorUtilsAndroid.ColorName("CadetBlue", 0x5F, 0x9E, 0xA0));
        colorList.add(new ColorUtilsAndroid.ColorName("Chartreuse", 0x7F, 0xFF, 0x00));
        colorList.add(new ColorUtilsAndroid.ColorName("Chocolate", 0xD2, 0x69, 0x1E));
        colorList.add(new ColorUtilsAndroid.ColorName("Coral", 0xFF, 0x7F, 0x50));
        colorList.add(new ColorUtilsAndroid.ColorName("CornflowerBlue", 0x64, 0x95, 0xED));
        colorList.add(new ColorUtilsAndroid.ColorName("Cornsilk", 0xFF, 0xF8, 0xDC));
        colorList.add(new ColorUtilsAndroid.ColorName("Crimson", 0xDC, 0x14, 0x3C));
        colorList.add(new ColorUtilsAndroid.ColorName("Cyan", 0x00, 0xFF, 0xFF));
        colorList.add(new ColorUtilsAndroid.ColorName("DarkBlue", 0x00, 0x00, 0x8B));
        colorList.add(new ColorUtilsAndroid.ColorName("DarkCyan", 0x00, 0x8B, 0x8B));
        colorList.add(new ColorUtilsAndroid.ColorName("DarkGoldenRod", 0xB8, 0x86, 0x0B));
        colorList.add(new ColorUtilsAndroid.ColorName("DarkGray", 0xA9, 0xA9, 0xA9));
        colorList.add(new ColorUtilsAndroid.ColorName("DarkGreen", 0x00, 0x64, 0x00));
        colorList.add(new ColorUtilsAndroid.ColorName("DarkKhaki", 0xBD, 0xB7, 0x6B));
        colorList.add(new ColorUtilsAndroid.ColorName("DarkMagenta", 0x8B, 0x00, 0x8B));
        colorList.add(new ColorUtilsAndroid.ColorName("DarkOliveGreen", 0x55, 0x6B, 0x2F));
        colorList.add(new ColorUtilsAndroid.ColorName("DarkOrange", 0xFF, 0x8C, 0x00));
        colorList.add(new ColorUtilsAndroid.ColorName("DarkOrchid", 0x99, 0x32, 0xCC));
        colorList.add(new ColorUtilsAndroid.ColorName("DarkRed", 0x8B, 0x00, 0x00));
        colorList.add(new ColorUtilsAndroid.ColorName("DarkSalmon", 0xE9, 0x96, 0x7A));
        colorList.add(new ColorUtilsAndroid.ColorName("DarkSeaGreen", 0x8F, 0xBC, 0x8F));
        colorList.add(new ColorUtilsAndroid.ColorName("DarkSlateBlue", 0x48, 0x3D, 0x8B));
        colorList.add(new ColorUtilsAndroid.ColorName("DarkSlateGray", 0x2F, 0x4F, 0x4F));
        colorList.add(new ColorUtilsAndroid.ColorName("DarkTurquoise", 0x00, 0xCE, 0xD1));
        colorList.add(new ColorUtilsAndroid.ColorName("DarkViolet", 0x94, 0x00, 0xD3));
        colorList.add(new ColorUtilsAndroid.ColorName("DeepPink", 0xFF, 0x14, 0x93));
        colorList.add(new ColorUtilsAndroid.ColorName("DeepSkyBlue", 0x00, 0xBF, 0xFF));
        colorList.add(new ColorUtilsAndroid.ColorName("DimGray", 0x69, 0x69, 0x69));
        colorList.add(new ColorUtilsAndroid.ColorName("DodgerBlue", 0x1E, 0x90, 0xFF));
        colorList.add(new ColorUtilsAndroid.ColorName("FireBrick", 0xB2, 0x22, 0x22));
        colorList.add(new ColorUtilsAndroid.ColorName("FloralWhite", 0xFF, 0xFA, 0xF0));
        colorList.add(new ColorUtilsAndroid.ColorName("ForestGreen", 0x22, 0x8B, 0x22));
        colorList.add(new ColorUtilsAndroid.ColorName("Fuchsia", 0xFF, 0x00, 0xFF));
        colorList.add(new ColorUtilsAndroid.ColorName("Gainsboro", 0xDC, 0xDC, 0xDC));
        colorList.add(new ColorUtilsAndroid.ColorName("GhostWhite", 0xF8, 0xF8, 0xFF));
        colorList.add(new ColorUtilsAndroid.ColorName("Gold", 0xFF, 0xD7, 0x00));
        colorList.add(new ColorUtilsAndroid.ColorName("GoldenRod", 0xDA, 0xA5, 0x20));
        colorList.add(new ColorUtilsAndroid.ColorName("Gray", 0x80, 0x80, 0x80));
        colorList.add(new ColorUtilsAndroid.ColorName("Green", 0x00, 0x80, 0x00));
        colorList.add(new ColorUtilsAndroid.ColorName("GreenYellow", 0xAD, 0xFF, 0x2F));
        colorList.add(new ColorUtilsAndroid.ColorName("HoneyDew", 0xF0, 0xFF, 0xF0));
        colorList.add(new ColorUtilsAndroid.ColorName("HotPink", 0xFF, 0x69, 0xB4));
        colorList.add(new ColorUtilsAndroid.ColorName("IndianRed", 0xCD, 0x5C, 0x5C));
        colorList.add(new ColorUtilsAndroid.ColorName("Indigo", 0x4B, 0x00, 0x82));
        colorList.add(new ColorUtilsAndroid.ColorName("Ivory", 0xFF, 0xFF, 0xF0));
        colorList.add(new ColorUtilsAndroid.ColorName("Khaki", 0xF0, 0xE6, 0x8C));
        colorList.add(new ColorUtilsAndroid.ColorName("Lavender", 0xE6, 0xE6, 0xFA));
        colorList.add(new ColorUtilsAndroid.ColorName("LavenderBlush", 0xFF, 0xF0, 0xF5));
        colorList.add(new ColorUtilsAndroid.ColorName("LawnGreen", 0x7C, 0xFC, 0x00));
        colorList.add(new ColorUtilsAndroid.ColorName("LemonChiffon", 0xFF, 0xFA, 0xCD));
        colorList.add(new ColorUtilsAndroid.ColorName("LightBlue", 0xAD, 0xD8, 0xE6));
        colorList.add(new ColorUtilsAndroid.ColorName("LightCoral", 0xF0, 0x80, 0x80));
        colorList.add(new ColorUtilsAndroid.ColorName("LightCyan", 0xE0, 0xFF, 0xFF));
        colorList.add(new ColorUtilsAndroid.ColorName("LightGoldenRodYellow", 0xFA, 0xFA, 0xD2));
        colorList.add(new ColorUtilsAndroid.ColorName("LightGray", 0xD3, 0xD3, 0xD3));
        colorList.add(new ColorUtilsAndroid.ColorName("LightGreen", 0x90, 0xEE, 0x90));
        colorList.add(new ColorUtilsAndroid.ColorName("LightPink", 0xFF, 0xB6, 0xC1));
        colorList.add(new ColorUtilsAndroid.ColorName("LightSalmon", 0xFF, 0xA0, 0x7A));
        colorList.add(new ColorUtilsAndroid.ColorName("LightSeaGreen", 0x20, 0xB2, 0xAA));
        colorList.add(new ColorUtilsAndroid.ColorName("LightSkyBlue", 0x87, 0xCE, 0xFA));
        colorList.add(new ColorUtilsAndroid.ColorName("LightSlateGray", 0x77, 0x88, 0x99));
        colorList.add(new ColorUtilsAndroid.ColorName("LightSteelBlue", 0xB0, 0xC4, 0xDE));
        colorList.add(new ColorUtilsAndroid.ColorName("LightYellow", 0xFF, 0xFF, 0xE0));
        colorList.add(new ColorUtilsAndroid.ColorName("Lime", 0x00, 0xFF, 0x00));
        colorList.add(new ColorUtilsAndroid.ColorName("LimeGreen", 0x32, 0xCD, 0x32));
        colorList.add(new ColorUtilsAndroid.ColorName("Linen", 0xFA, 0xF0, 0xE6));
        colorList.add(new ColorUtilsAndroid.ColorName("Magenta", 0xFF, 0x00, 0xFF));
        colorList.add(new ColorUtilsAndroid.ColorName("Maroon", 0x80, 0x00, 0x00));
        colorList.add(new ColorUtilsAndroid.ColorName("MediumAquaMarine", 0x66, 0xCD, 0xAA));
        colorList.add(new ColorUtilsAndroid.ColorName("MediumBlue", 0x00, 0x00, 0xCD));
        colorList.add(new ColorUtilsAndroid.ColorName("MediumOrchid", 0xBA, 0x55, 0xD3));
        colorList.add(new ColorUtilsAndroid.ColorName("MediumPurple", 0x93, 0x70, 0xDB));
        colorList.add(new ColorUtilsAndroid.ColorName("MediumSeaGreen", 0x3C, 0xB3, 0x71));
        colorList.add(new ColorUtilsAndroid.ColorName("MediumSlateBlue", 0x7B, 0x68, 0xEE));
        colorList.add(new ColorUtilsAndroid.ColorName("MediumSpringGreen", 0x00, 0xFA, 0x9A));
        colorList.add(new ColorUtilsAndroid.ColorName("MediumTurquoise", 0x48, 0xD1, 0xCC));
        colorList.add(new ColorUtilsAndroid.ColorName("MediumVioletRed", 0xC7, 0x15, 0x85));
        colorList.add(new ColorUtilsAndroid.ColorName("MidnightBlue", 0x19, 0x19, 0x70));
        colorList.add(new ColorUtilsAndroid.ColorName("MintCream", 0xF5, 0xFF, 0xFA));
        colorList.add(new ColorUtilsAndroid.ColorName("MistyRose", 0xFF, 0xE4, 0xE1));
        colorList.add(new ColorUtilsAndroid.ColorName("Moccasin", 0xFF, 0xE4, 0xB5));
        colorList.add(new ColorUtilsAndroid.ColorName("NavajoWhite", 0xFF, 0xDE, 0xAD));
        colorList.add(new ColorUtilsAndroid.ColorName("Navy", 0x00, 0x00, 0x80));
        colorList.add(new ColorUtilsAndroid.ColorName("OldLace", 0xFD, 0xF5, 0xE6));
        colorList.add(new ColorUtilsAndroid.ColorName("Olive", 0x80, 0x80, 0x00));
        colorList.add(new ColorUtilsAndroid.ColorName("OliveDrab", 0x6B, 0x8E, 0x23));
        colorList.add(new ColorUtilsAndroid.ColorName("Orange", 0xFF, 0xA5, 0x00));
        colorList.add(new ColorUtilsAndroid.ColorName("OrangeRed", 0xFF, 0x45, 0x00));
        colorList.add(new ColorUtilsAndroid.ColorName("Orchid", 0xDA, 0x70, 0xD6));
        colorList.add(new ColorUtilsAndroid.ColorName("PaleGoldenRod", 0xEE, 0xE8, 0xAA));
        colorList.add(new ColorUtilsAndroid.ColorName("PaleGreen", 0x98, 0xFB, 0x98));
        colorList.add(new ColorUtilsAndroid.ColorName("PaleTurquoise", 0xAF, 0xEE, 0xEE));
        colorList.add(new ColorUtilsAndroid.ColorName("PaleVioletRed", 0xDB, 0x70, 0x93));
        colorList.add(new ColorUtilsAndroid.ColorName("PapayaWhip", 0xFF, 0xEF, 0xD5));
        colorList.add(new ColorUtilsAndroid.ColorName("PeachPuff", 0xFF, 0xDA, 0xB9));
        colorList.add(new ColorUtilsAndroid.ColorName("Peru", 0xCD, 0x85, 0x3F));
        colorList.add(new ColorUtilsAndroid.ColorName("Pink", 0xFF, 0xC0, 0xCB));
        colorList.add(new ColorUtilsAndroid.ColorName("Plum", 0xDD, 0xA0, 0xDD));
        colorList.add(new ColorUtilsAndroid.ColorName("PowderBlue", 0xB0, 0xE0, 0xE6));
        colorList.add(new ColorUtilsAndroid.ColorName("Purple", 0x80, 0x00, 0x80));
        colorList.add(new ColorUtilsAndroid.ColorName("Red", 0xFF, 0x00, 0x00));
        colorList.add(new ColorUtilsAndroid.ColorName("RosyBrown", 0xBC, 0x8F, 0x8F));
        colorList.add(new ColorUtilsAndroid.ColorName("RoyalBlue", 0x41, 0x69, 0xE1));
        colorList.add(new ColorUtilsAndroid.ColorName("SaddleBrown", 0x8B, 0x45, 0x13));
        colorList.add(new ColorUtilsAndroid.ColorName("Salmon", 0xFA, 0x80, 0x72));
        colorList.add(new ColorUtilsAndroid.ColorName("SandyBrown", 0xF4, 0xA4, 0x60));
        colorList.add(new ColorUtilsAndroid.ColorName("SeaGreen", 0x2E, 0x8B, 0x57));
        colorList.add(new ColorUtilsAndroid.ColorName("SeaShell", 0xFF, 0xF5, 0xEE));
        colorList.add(new ColorUtilsAndroid.ColorName("Sienna", 0xA0, 0x52, 0x2D));
        colorList.add(new ColorUtilsAndroid.ColorName("Silver", 0xC0, 0xC0, 0xC0));
        colorList.add(new ColorUtilsAndroid.ColorName("SkyBlue", 0x87, 0xCE, 0xEB));
        colorList.add(new ColorUtilsAndroid.ColorName("SlateBlue", 0x6A, 0x5A, 0xCD));
        colorList.add(new ColorUtilsAndroid.ColorName("SlateGray", 0x70, 0x80, 0x90));
        colorList.add(new ColorUtilsAndroid.ColorName("Snow", 0xFF, 0xFA, 0xFA));
        colorList.add(new ColorUtilsAndroid.ColorName("SpringGreen", 0x00, 0xFF, 0x7F));
        colorList.add(new ColorUtilsAndroid.ColorName("SteelBlue", 0x46, 0x82, 0xB4));
        colorList.add(new ColorUtilsAndroid.ColorName("Tan", 0xD2, 0xB4, 0x8C));
        colorList.add(new ColorUtilsAndroid.ColorName("Teal", 0x00, 0x80, 0x80));
        colorList.add(new ColorUtilsAndroid.ColorName("Thistle", 0xD8, 0xBF, 0xD8));
        colorList.add(new ColorUtilsAndroid.ColorName("Tomato", 0xFF, 0x63, 0x47));
        colorList.add(new ColorUtilsAndroid.ColorName("Turquoise", 0x40, 0xE0, 0xD0));
        colorList.add(new ColorUtilsAndroid.ColorName("Violet", 0xEE, 0x82, 0xEE));
        colorList.add(new ColorUtilsAndroid.ColorName("Wheat", 0xF5, 0xDE, 0xB3));
        colorList.add(new ColorUtilsAndroid.ColorName("White", 0xFF, 0xFF, 0xFF));
        colorList.add(new ColorUtilsAndroid.ColorName("WhiteSmoke", 0xF5, 0xF5, 0xF5));
        colorList.add(new ColorUtilsAndroid.ColorName("Yellow", 0xFF, 0xFF, 0x00));
        colorList.add(new ColorUtilsAndroid.ColorName("YellowGreen", 0x9A, 0xCD, 0x32));
        return colorList;
    }
    /**
     * Method that checks whether a color is light or dark depending on its luma value
     *
     * @param hexColor Color we want to check
     * @return true if the color is closer to dark, false otherwise
     */
    public static boolean isDarkColor(String hexColor) {
        int color = getIntColor(hexColor);
        double luma = 1 - (RED_LUMA * Color.red(color) + GREEN_LUMA * Color.green(color) + BLUE_LUMA * Color.blue(color)) / 255;
        return luma >= DARKNESS_RESISTANCE;
    }

    /**
     * Returns int value of hex color
     *
     * @param hexColor Hexadecimal color
     * @return int color
     * @see <a href="https://developer.android.com/reference/android/graphics/Color.html#parseColor(java.lang.String)"</a> for valid hex color formats.
     */
    public static int getIntColor(@NonNull String hexColor) {
        return Color.parseColor((hexColor.contains("#") ? "#" : "") + hexColor);
    }

    public static String colorToHex(String color){
        ColorName colorName = colorList.stream().filter(x->x.getName().equals(color)).collect(Collectors.toList()).get(0);
        return String.format("#%02x%02x%02x", colorName.r, colorName.g, colorName.b);
    }
    /**
     * SubClass of ColorUtils. In order to lookup color name
     *
     * @author Xiaoxiao Li
     */
    public class ColorName {
        public int r, g, b;
        public String name;

        public ColorName(String name, int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
            this.name = name;
        }

        public int computeMSE(int pixR, int pixG, int pixB) {
            return (int) (((pixR - r) * (pixR - r) + (pixG - g) * (pixG - g) + (pixB - b)
                    * (pixB - b)) / 3);
        }

        public int getR() {
            return r;
        }

        public int getG() {
            return g;
        }

        public int getB() {
            return b;
        }

        public String getName() {
            return name;
        }
    }

}


