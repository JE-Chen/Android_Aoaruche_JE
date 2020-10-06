/*
 * Copyright (c) 2017. JE-Chen
*/

package com.tw.je.aoaruche;



public class JE_Backpack {

    public static String what_weapon="what";
    public static String what_armor="what";

    /**
     * 背包函式
     */
    public void setWhat_weapon(String set){
        this.what_weapon=set;
    }
    public String return_what_weapon(){
        return this.what_weapon;
    }
    public void setWhat_armor(String set){
        this.what_armor=set;
    }
    public String return_what_armor(){return this.what_armor;}
    public void Set_All(String set_weapon,String set_armor){
        this.what_weapon=set_weapon;
        this.what_armor=set_armor;
    }

    /**
     *  武器區
     */

    public static long iron_sword=1;
    public long return_iron_sword(){return this.iron_sword;}
    public void set_iron_sword(long set){this.iron_sword=set;}
    public static long arch_sword=1;
    public long return_arch_sword(){return this.arch_sword;}
    public void set_arch_sword(long set){this.arch_sword=set;}
    public static long shadow_light_book=1;
    public long return_shadow_light_book(){return this.shadow_light_book;}
    public void set_shadow_light_book(long set){this.shadow_light_book=set;}

    /**
     * 防具區
     */

    public static long armor=1;
    public long return_armor(){return this.armor;}
    public void set_armor(long set){this.armor=set;}
    public static long healing_armor=1;
    public long return_healing_armor(){return this.healing_armor;}
    public void set_healing_armor(long set){this.healing_armor=set;}
    public static long time_souls=1;
    public long return_time_souls(){return this.time_souls;}
    public void set_time_souls(long set){this.time_souls=set;}

    /**
     * 道具區
     */



    /**
     * 卡片區
     */

    /**
     * 小怪
     */

    /**
     *  地區一
     */
    public static long slime = 0;
    public static long error =0;
    public static long dark_soul =0;
    public static long red_eye =0;


    /**
     * 地區一 回傳區
     */
    public long getSlime(){return this.slime;}
    public long getError(){return this.error;}
    public long getDark_soul(){return this.dark_soul;}
    public long getRed_eye(){return this.red_eye;}

    /**
     *  地區二
     */
    public static long red_eye_bow =0;
    public static long red_eye_hammer =0;
    public static long red_eye_magician =0;
    public static long red_eye_sword =0;
    public static long red_eye_warrior =0;

    /**
     * 地區二 回傳區
     */

    public long getRed_eye_bow(){return this.red_eye_bow;}
    public long getRed_eye_hammer(){return this.red_eye_hammer;}
    public long getRed_eye_magician(){return this.red_eye_magician;}
    public long getRed_eye_sword(){return this.red_eye_sword;}
    public long getRed_eye_warrior(){return this.red_eye_warrior;}

    /**
     *  地區三
     */
    public static long dark_bow =0;
    public static long dark_guard =0;
    public static long dark_hammer =0;
    public static long dark_magic =0;
    public static long dark_magician =0;
    public static long dark_normal =0;
    public static long dark_sword =0;
    public static long polluted_cross =0;

    /**
     * 地區三 回傳區
     */

    public long getDark_bow(){return this.dark_bow;}
    public long getDark_guard(){return this.dark_guard;}
    public long getDark_hammer(){return this.dark_hammer;}
    public long getDark_magic(){return this.dark_magic;}
    public long getDark_magician(){return this.dark_magician;}
    public long getDark_normal(){return this.dark_normal;}
    public long getDark_sword(){return this.dark_sword;}
    public long getPolluted_cross(){return this.polluted_cross;}

    /**
     *  地區四
     */
    public static long square_smile_blue =0;
    public static long square_smile_four =0;
    public static long square_smile_green =0;
    public static long square_smile_red =0;
    public static long square_smile_three =0;
    public static long square_smile_yellow =0;

    /**
     * 地區四 回傳區
     */

    public long getSquare_smile_blue(){return this.square_smile_blue;}
    public long getSquare_smile_four(){return this.square_smile_four;}
    public long getSquare_smile_green(){return this.square_smile_green;}
    public long getSquare_smile_red(){return this.square_smile_red;}
    public long getSquare_smile_three(){return this.square_smile_three;}
    public long getSquare_smile_yellow(){return this.square_smile_yellow;}

    /**
     *  地區五
     */
    public static long fneam =0;
    public static long fneam_tail =0;
    public static long fneam_tail_tooth =0;
    public static long fneam_tooth =0;
    public static long insect_eye =0;

    /**
     * 地區五 回傳區
     */

    public long getFneam(){return this.fneam;}
    public long getFneam_tail(){return this.fneam_tail;}
    public long getFneam_tail_tooth(){return this.fneam_tail_tooth;}
    public long getFneam_tooth(){return this.fneam_tooth;}
    public long getInsect_eye(){return this.insect_eye;}

    /**
     *  未定義
     */
    public static long dark_eye =0;
    public static long fire_angel_slime =0;
    public static long rect_eye =0;

    /**
     * 未定義回傳區
     */

    public long getDark_eye(){return this.dark_eye;}
    public long getFire_angel_slime(){return this.fire_angel_slime;}
    public long getRect_eye(){return this.rect_eye;}

    /**
     *  頭目
     */
    public static long red_eye_boss =0;
    public static long dark_balloon =0;
    public static long nothingness =0;
    public static long nothingness_2 =0;
    public static long insect_eyes =0;

    /**
     * 頭目 回傳區
     */

    public long getRed_eye_boss(){return this.red_eye_boss;}
    public long getDark_balloon(){return this.dark_balloon;}
    public long getNothingness(){return this.nothingness;}
    public long getNothingness_2(){return this.nothingness_2;}
    public long getInsect_eyes(){return this.insect_eyes;}

    /**
     *  特殊關卡
     */
    public static long nothingness_green =0;

    /**
     * 特殊關卡 回傳區
     */

    public long getNothingness_green(){return this.nothingness_green;}


}

