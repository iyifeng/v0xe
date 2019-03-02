package com.v0ex.em;

import java.util.Set;

/**
 * 使用EnumSet代替位域
 *
 * @author bugcoder
 */
public class Text {

//    public static final int STYLE_BOLD = 1 << 0;
//
//    public static final int STYLE_ITALIC = 1 << 1;
//
//    public static final int STYLE_UNDERLINE = 1 << 2;
//
//    public static final int STYLE_STRIKETHROUGH =1 << 3;
//
//    public void applyStyles(int styles){
//    }


    public enum Style{ BOLD,ITALIC,UNDERLINE,STRIKETHROUGH}

    /**
     * 许多set集合都适用，但是EnumSet是最清晰的选择
     * @param styles
     */
    public void applyStyles(Set<Style> styles){}
}
