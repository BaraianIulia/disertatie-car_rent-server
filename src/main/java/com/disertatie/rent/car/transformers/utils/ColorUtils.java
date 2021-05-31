package com.disertatie.rent.car.transformers.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component(value = "colorUtilsAndroid")
public class ColorUtils {

    public static ArrayList<ColorUtils.ColorName> colorList = new ArrayList<ColorUtils.ColorName>();

    {
        initColorList();
    }

    private ArrayList<ColorUtils.ColorName> initColorList() {

        colorList.add(new ColorUtils.ColorName("AliceBlue", 0xF0, 0xF8, 0xFF));
        colorList.add(new ColorUtils.ColorName("AntiqueWhite", 0xFA, 0xEB, 0xD7));
        colorList.add(new ColorUtils.ColorName("Aqua", 0x00, 0xFF, 0xFF));
        colorList.add(new ColorUtils.ColorName("Aquamarine", 0x7F, 0xFF, 0xD4));
        colorList.add(new ColorUtils.ColorName("Azure", 0xF0, 0xFF, 0xFF));
        colorList.add(new ColorUtils.ColorName("Beige", 0xF5, 0xF5, 0xDC));
        colorList.add(new ColorUtils.ColorName("Bisque", 0xFF, 0xE4, 0xC4));
        colorList.add(new ColorUtils.ColorName("Black", 0x00, 0x00, 0x00));
        colorList.add(new ColorUtils.ColorName("BlanchedAlmond", 0xFF, 0xEB, 0xCD));
        colorList.add(new ColorUtils.ColorName("Blue", 0x00, 0x00, 0xFF));
        colorList.add(new ColorUtils.ColorName("BlueViolet", 0x8A, 0x2B, 0xE2));
        colorList.add(new ColorUtils.ColorName("Brown", 0xA5, 0x2A, 0x2A));
        colorList.add(new ColorUtils.ColorName("BurlyWood", 0xDE, 0xB8, 0x87));
        colorList.add(new ColorUtils.ColorName("CadetBlue", 0x5F, 0x9E, 0xA0));
        colorList.add(new ColorUtils.ColorName("Chartreuse", 0x7F, 0xFF, 0x00));
        colorList.add(new ColorUtils.ColorName("Chocolate", 0xD2, 0x69, 0x1E));
        colorList.add(new ColorUtils.ColorName("Coral", 0xFF, 0x7F, 0x50));
        colorList.add(new ColorUtils.ColorName("CornflowerBlue", 0x64, 0x95, 0xED));
        colorList.add(new ColorUtils.ColorName("Cornsilk", 0xFF, 0xF8, 0xDC));
        colorList.add(new ColorUtils.ColorName("Crimson", 0xDC, 0x14, 0x3C));
        colorList.add(new ColorUtils.ColorName("Cyan", 0x00, 0xFF, 0xFF));
        colorList.add(new ColorUtils.ColorName("DarkBlue", 0x00, 0x00, 0x8B));
        colorList.add(new ColorUtils.ColorName("DarkCyan", 0x00, 0x8B, 0x8B));
        colorList.add(new ColorUtils.ColorName("DarkGoldenRod", 0xB8, 0x86, 0x0B));
        colorList.add(new ColorUtils.ColorName("DarkGray", 0xA9, 0xA9, 0xA9));
        colorList.add(new ColorUtils.ColorName("DarkGreen", 0x00, 0x64, 0x00));
        colorList.add(new ColorUtils.ColorName("DarkKhaki", 0xBD, 0xB7, 0x6B));
        colorList.add(new ColorUtils.ColorName("DarkMagenta", 0x8B, 0x00, 0x8B));
        colorList.add(new ColorUtils.ColorName("DarkOliveGreen", 0x55, 0x6B, 0x2F));
        colorList.add(new ColorUtils.ColorName("DarkOrange", 0xFF, 0x8C, 0x00));
        colorList.add(new ColorUtils.ColorName("DarkOrchid", 0x99, 0x32, 0xCC));
        colorList.add(new ColorUtils.ColorName("DarkRed", 0x8B, 0x00, 0x00));
        colorList.add(new ColorUtils.ColorName("DarkSalmon", 0xE9, 0x96, 0x7A));
        colorList.add(new ColorUtils.ColorName("DarkSeaGreen", 0x8F, 0xBC, 0x8F));
        colorList.add(new ColorUtils.ColorName("DarkSlateBlue", 0x48, 0x3D, 0x8B));
        colorList.add(new ColorUtils.ColorName("DarkSlateGray", 0x2F, 0x4F, 0x4F));
        colorList.add(new ColorUtils.ColorName("DarkTurquoise", 0x00, 0xCE, 0xD1));
        colorList.add(new ColorUtils.ColorName("DarkViolet", 0x94, 0x00, 0xD3));
        colorList.add(new ColorUtils.ColorName("DeepPink", 0xFF, 0x14, 0x93));
        colorList.add(new ColorUtils.ColorName("DeepSkyBlue", 0x00, 0xBF, 0xFF));
        colorList.add(new ColorUtils.ColorName("DimGray", 0x69, 0x69, 0x69));
        colorList.add(new ColorUtils.ColorName("DodgerBlue", 0x1E, 0x90, 0xFF));
        colorList.add(new ColorUtils.ColorName("FireBrick", 0xB2, 0x22, 0x22));
        colorList.add(new ColorUtils.ColorName("FloralWhite", 0xFF, 0xFA, 0xF0));
        colorList.add(new ColorUtils.ColorName("ForestGreen", 0x22, 0x8B, 0x22));
        colorList.add(new ColorUtils.ColorName("Fuchsia", 0xFF, 0x00, 0xFF));
        colorList.add(new ColorUtils.ColorName("Gainsboro", 0xDC, 0xDC, 0xDC));
        colorList.add(new ColorUtils.ColorName("GhostWhite", 0xF8, 0xF8, 0xFF));
        colorList.add(new ColorUtils.ColorName("Gold", 0xFF, 0xD7, 0x00));
        colorList.add(new ColorUtils.ColorName("GoldenRod", 0xDA, 0xA5, 0x20));
        colorList.add(new ColorUtils.ColorName("Gray", 0x80, 0x80, 0x80));
        colorList.add(new ColorUtils.ColorName("Green", 0x00, 0x80, 0x00));
        colorList.add(new ColorUtils.ColorName("GreenYellow", 0xAD, 0xFF, 0x2F));
        colorList.add(new ColorUtils.ColorName("HoneyDew", 0xF0, 0xFF, 0xF0));
        colorList.add(new ColorUtils.ColorName("HotPink", 0xFF, 0x69, 0xB4));
        colorList.add(new ColorUtils.ColorName("IndianRed", 0xCD, 0x5C, 0x5C));
        colorList.add(new ColorUtils.ColorName("Indigo", 0x4B, 0x00, 0x82));
        colorList.add(new ColorUtils.ColorName("Ivory", 0xFF, 0xFF, 0xF0));
        colorList.add(new ColorUtils.ColorName("Khaki", 0xF0, 0xE6, 0x8C));
        colorList.add(new ColorUtils.ColorName("Lavender", 0xE6, 0xE6, 0xFA));
        colorList.add(new ColorUtils.ColorName("LavenderBlush", 0xFF, 0xF0, 0xF5));
        colorList.add(new ColorUtils.ColorName("LawnGreen", 0x7C, 0xFC, 0x00));
        colorList.add(new ColorUtils.ColorName("LemonChiffon", 0xFF, 0xFA, 0xCD));
        colorList.add(new ColorUtils.ColorName("LightBlue", 0xAD, 0xD8, 0xE6));
        colorList.add(new ColorUtils.ColorName("LightCoral", 0xF0, 0x80, 0x80));
        colorList.add(new ColorUtils.ColorName("LightCyan", 0xE0, 0xFF, 0xFF));
        colorList.add(new ColorUtils.ColorName("LightGoldenRodYellow", 0xFA, 0xFA, 0xD2));
        colorList.add(new ColorUtils.ColorName("LightGray", 0xD3, 0xD3, 0xD3));
        colorList.add(new ColorUtils.ColorName("LightGreen", 0x90, 0xEE, 0x90));
        colorList.add(new ColorUtils.ColorName("LightPink", 0xFF, 0xB6, 0xC1));
        colorList.add(new ColorUtils.ColorName("LightSalmon", 0xFF, 0xA0, 0x7A));
        colorList.add(new ColorUtils.ColorName("LightSeaGreen", 0x20, 0xB2, 0xAA));
        colorList.add(new ColorUtils.ColorName("LightSkyBlue", 0x87, 0xCE, 0xFA));
        colorList.add(new ColorUtils.ColorName("LightSlateGray", 0x77, 0x88, 0x99));
        colorList.add(new ColorUtils.ColorName("LightSteelBlue", 0xB0, 0xC4, 0xDE));
        colorList.add(new ColorUtils.ColorName("LightYellow", 0xFF, 0xFF, 0xE0));
        colorList.add(new ColorUtils.ColorName("Lime", 0x00, 0xFF, 0x00));
        colorList.add(new ColorUtils.ColorName("LimeGreen", 0x32, 0xCD, 0x32));
        colorList.add(new ColorUtils.ColorName("Linen", 0xFA, 0xF0, 0xE6));
        colorList.add(new ColorUtils.ColorName("Magenta", 0xFF, 0x00, 0xFF));
        colorList.add(new ColorUtils.ColorName("Maroon", 0x80, 0x00, 0x00));
        colorList.add(new ColorUtils.ColorName("MediumAquaMarine", 0x66, 0xCD, 0xAA));
        colorList.add(new ColorUtils.ColorName("MediumBlue", 0x00, 0x00, 0xCD));
        colorList.add(new ColorUtils.ColorName("MediumOrchid", 0xBA, 0x55, 0xD3));
        colorList.add(new ColorUtils.ColorName("MediumPurple", 0x93, 0x70, 0xDB));
        colorList.add(new ColorUtils.ColorName("MediumSeaGreen", 0x3C, 0xB3, 0x71));
        colorList.add(new ColorUtils.ColorName("MediumSlateBlue", 0x7B, 0x68, 0xEE));
        colorList.add(new ColorUtils.ColorName("MediumSpringGreen", 0x00, 0xFA, 0x9A));
        colorList.add(new ColorUtils.ColorName("MediumTurquoise", 0x48, 0xD1, 0xCC));
        colorList.add(new ColorUtils.ColorName("MediumVioletRed", 0xC7, 0x15, 0x85));
        colorList.add(new ColorUtils.ColorName("MidnightBlue", 0x19, 0x19, 0x70));
        colorList.add(new ColorUtils.ColorName("MintCream", 0xF5, 0xFF, 0xFA));
        colorList.add(new ColorUtils.ColorName("MistyRose", 0xFF, 0xE4, 0xE1));
        colorList.add(new ColorUtils.ColorName("Moccasin", 0xFF, 0xE4, 0xB5));
        colorList.add(new ColorUtils.ColorName("NavajoWhite", 0xFF, 0xDE, 0xAD));
        colorList.add(new ColorUtils.ColorName("Navy", 0x00, 0x00, 0x80));
        colorList.add(new ColorUtils.ColorName("OldLace", 0xFD, 0xF5, 0xE6));
        colorList.add(new ColorUtils.ColorName("Olive", 0x80, 0x80, 0x00));
        colorList.add(new ColorUtils.ColorName("OliveDrab", 0x6B, 0x8E, 0x23));
        colorList.add(new ColorUtils.ColorName("Orange", 0xFF, 0xA5, 0x00));
        colorList.add(new ColorUtils.ColorName("OrangeRed", 0xFF, 0x45, 0x00));
        colorList.add(new ColorUtils.ColorName("Orchid", 0xDA, 0x70, 0xD6));
        colorList.add(new ColorUtils.ColorName("PaleGoldenRod", 0xEE, 0xE8, 0xAA));
        colorList.add(new ColorUtils.ColorName("PaleGreen", 0x98, 0xFB, 0x98));
        colorList.add(new ColorUtils.ColorName("PaleTurquoise", 0xAF, 0xEE, 0xEE));
        colorList.add(new ColorUtils.ColorName("PaleVioletRed", 0xDB, 0x70, 0x93));
        colorList.add(new ColorUtils.ColorName("PapayaWhip", 0xFF, 0xEF, 0xD5));
        colorList.add(new ColorUtils.ColorName("PeachPuff", 0xFF, 0xDA, 0xB9));
        colorList.add(new ColorUtils.ColorName("Peru", 0xCD, 0x85, 0x3F));
        colorList.add(new ColorUtils.ColorName("Pink", 0xFF, 0xC0, 0xCB));
        colorList.add(new ColorUtils.ColorName("Plum", 0xDD, 0xA0, 0xDD));
        colorList.add(new ColorUtils.ColorName("PowderBlue", 0xB0, 0xE0, 0xE6));
        colorList.add(new ColorUtils.ColorName("Purple", 0x80, 0x00, 0x80));
        colorList.add(new ColorUtils.ColorName("Red", 0xFF, 0x00, 0x00));
        colorList.add(new ColorUtils.ColorName("RosyBrown", 0xBC, 0x8F, 0x8F));
        colorList.add(new ColorUtils.ColorName("RoyalBlue", 0x41, 0x69, 0xE1));
        colorList.add(new ColorUtils.ColorName("SaddleBrown", 0x8B, 0x45, 0x13));
        colorList.add(new ColorUtils.ColorName("Salmon", 0xFA, 0x80, 0x72));
        colorList.add(new ColorUtils.ColorName("SandyBrown", 0xF4, 0xA4, 0x60));
        colorList.add(new ColorUtils.ColorName("SeaGreen", 0x2E, 0x8B, 0x57));
        colorList.add(new ColorUtils.ColorName("SeaShell", 0xFF, 0xF5, 0xEE));
        colorList.add(new ColorUtils.ColorName("Sienna", 0xA0, 0x52, 0x2D));
        colorList.add(new ColorUtils.ColorName("Silver", 0xC0, 0xC0, 0xC0));
        colorList.add(new ColorUtils.ColorName("SkyBlue", 0x87, 0xCE, 0xEB));
        colorList.add(new ColorUtils.ColorName("SlateBlue", 0x6A, 0x5A, 0xCD));
        colorList.add(new ColorUtils.ColorName("SlateGray", 0x70, 0x80, 0x90));
        colorList.add(new ColorUtils.ColorName("Snow", 0xFF, 0xFA, 0xFA));
        colorList.add(new ColorUtils.ColorName("SpringGreen", 0x00, 0xFF, 0x7F));
        colorList.add(new ColorUtils.ColorName("SteelBlue", 0x46, 0x82, 0xB4));
        colorList.add(new ColorUtils.ColorName("Tan", 0xD2, 0xB4, 0x8C));
        colorList.add(new ColorUtils.ColorName("Teal", 0x00, 0x80, 0x80));
        colorList.add(new ColorUtils.ColorName("Thistle", 0xD8, 0xBF, 0xD8));
        colorList.add(new ColorUtils.ColorName("Tomato", 0xFF, 0x63, 0x47));
        colorList.add(new ColorUtils.ColorName("Turquoise", 0x40, 0xE0, 0xD0));
        colorList.add(new ColorUtils.ColorName("Violet", 0xEE, 0x82, 0xEE));
        colorList.add(new ColorUtils.ColorName("Wheat", 0xF5, 0xDE, 0xB3));
        colorList.add(new ColorUtils.ColorName("White", 0xFF, 0xFF, 0xFF));
        colorList.add(new ColorUtils.ColorName("WhiteSmoke", 0xF5, 0xF5, 0xF5));
        colorList.add(new ColorUtils.ColorName("Yellow", 0xFF, 0xFF, 0x00));
        colorList.add(new ColorUtils.ColorName("YellowGreen", 0x9A, 0xCD, 0x32));
        return colorList;
    }

    public static boolean isDarkColor(String color) {
        ColorName colorName = colorList.stream().filter(x -> x.getName().equals(color)).collect(Collectors.toList()).get(0);

        //   double luma = 0.2126 * colorName.r + 0.7152 * colorName.g + 0.0722 * colorName.b;
        double darkness = 1 - (0.299 * colorName.r + 0.587 * colorName.g + 0.114 * colorName.b) / 255;

        if (darkness < 0.5) {
            return false; // It's a light color
        } else {
            return true; // It's a dark color
        }
    }

    public static String colorToHex(String color) {
        ColorName colorName = colorList.stream().filter(x -> x.getName().equals(color)).collect(Collectors.toList()).get(0);
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


