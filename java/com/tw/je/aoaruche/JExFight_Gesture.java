/*
 * Copyright (c) 2018 JE-Chen
 */

package com.tw.je.aoaruche;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.Random;

import static com.tw.je.aoaruche.JExMainActivity_original.Runnable_time;
import static com.tw.je.aoaruche.JExSkill.critical_lv;

/**
 * Created by JE on 2017/7/31.
 */

public class JExFight_Gesture {

    //物件
    public  JExBitmap JB= new JExBitmap();
    public  JE_Backpack JE_Backpack=new JE_Backpack();
    public static File get_sd_path;
    public void set_get_sd_path(String path){
        get_sd_path=new File(path);
    }

    /**
     * 未用到data 區
     */
    // data 1 當 角色技能
    public static long data1=0;
    public long return_magic_skill_value(){return this.data1;}
    public void set_magic_skill_value(long value){this.data1=value;}
    //data 2 當 稱號
    public static long data2=0;
    public long return_title_value(){return this.data2;}
    //data 3 當寵物等級
    public static long data3=0;
    public long return_pet_lv_value(){return this.data3;}
    //data 4 當哪隻寵物
    public static long data4=0;
    public long return_whos_pet_value(){return this.data4;}
    public long get_pet(){return this.data4+=1;}

    public static long data5=0;
    //data 6 當特殊事件
    public static long data6=0;
    public long return_special_event_value(){return this.data6;}
    /**
     * 結束
     */
    //RISFG
    public static long coin=0;
    //回傳應該顯示在哪個區域 進度等等
    public static String what_schedule;
    //攻擊升級所需金錢存檔值
    public static long need_cash_to_up_magic_power=100;
    //生命升級所需金錢存檔值
    public static long need_cash_to_up_hp_lv=100;
    //地區儲存用變數
    public static long what_region_save=1;
    //預設攻擊等級
    public static long Magic_Power =1;
    //生命等級
    public static long hp_lv=1;
    // this is skill points
    public static long skill_points=0;
    //預設角色攻擊值
    public static long normal_attack_value=20;
    //血量預設值
    public static long hero_hp =100+(50*hp_lv);
    //怪物攻擊預設值
    public static long monster_attack_value = 0;
    //返回值 讓外部決定應該調用什麼view
    public static String return_string_choice_toast_view;
    //是否戰鬥
    public static boolean Fighting = false;
    //是否有下一隻怪
    public static long have_next_monster = 1;
    //是否可以攻擊
    public static boolean can_Attack=true;
    //戰鬥用Array
    public static String[] Fight_Array;
    //傳回目前總攻擊值- Log用
    public static long return_total = 0;
    //返回應該造成多少傷害
    public static long return_Attack = 0;
    //亂數選擇
    public static int Choice_rnd;
    //預設怪物HP
    public static long Monster_Hp = 1;
    //預設在哪區域
    public static long In_what_area = 1;
    //判斷是否需重新產生迴圈
    public static int Fight_int = 0;
    //回傳hp值
    public static String monster_hp_string;
    //Log用fight array
    public static String show_Fight_Array = "0";
    //回傳值 選擇用什麼字串來決定顯示怪物toast
    public static String R_string_file;
//哪隻怪物
    public static String whos_monster="default";
    //怪物在保護狀態?
    public static long monster_in_protect=0;
    //普通怪物是否保護?
    public static long normal_protect=0;
    //目前護頓值
    public static long monster_protect_value=0;
    //目前打破護頓值
    public static long monster_protect_click_value=0;
//目前問題解答
    public static long question=99999;
    //攻擊計數
    public static long attack_counter=0;
    //回傳攻擊記數
    public long return_attack_counter(){return this.attack_counter;}
    //增加$$
    public void addCoin(long add){
        this.coin+=add;
    }
    //升級生命
    public void addhp_lv(){
        this.hp_lv+=1;
    }
    //升級攻擊
    public void addMagic_Power(){
        this.Magic_Power+=1;
    }
    //設置$$
    public void setCoin(long coin){
        this.coin=coin;
    }
    //返回錢錢值
    public long return_coin(){
        return this.coin;
    }
    //設置應存檔地區值
    public void set_what_region_save(long what_region){
        this.what_region_save=what_region;
    }
    //返回應存檔地區值
    public long return_what_region_save(){
        return this.what_region_save;
    }
    //生命等級
    public long return_hp_lv(){
        return hp_lv;
    }
    //戰鬥完回血
    public void set_hero_hp(){
        this.hero_hp=(this.hp_lv*50)+100;
    }
    //判斷血
    public long return_hero_hp_to_choice(){return (this.hp_lv*50)+100;}
    //+血
    public long add_hero_hp(long add){return this.hero_hp+=add;}
//回傳技能點
    public long return_skill_points(){return this.skill_points;}
    //設置技能點
    public void set_skill_points(long points){this.skill_points=points;}
    //回傳攻擊
    public long return_hero_attack(){return this.normal_attack_value+(10*this.Magic_Power);}
//設置有幾隻怪物
    public void JExFight_Set_have_next_monster(long monster){this.have_next_monster=monster;}
    //設置攻擊等級
    public void JExFight_Set_Magic( long magic_power) {
        this.Magic_Power = magic_power;
    }
    //設置地區
    public void JExFight_Set_area( long what_area) {
      this.In_what_area=what_area;
    }
    //需要多少錢升級攻擊存檔回傳
    public long return_need_cash_to_up_magic_power(){
        return this.need_cash_to_up_magic_power;
    }
    //需要多少錢升級生命存檔回傳
    public long return_need_cash_to_up_hp_lv(){
        return this.need_cash_to_up_hp_lv;
    }
    //設置要多少錢升級攻擊
public void set_need_cash_to_up_magic_powerv(long cash){
    this.need_cash_to_up_magic_power=cash;
}
    //設置要多少錢升級生命
    public void set_need_cash_to_up_hp_lv(long cash){
        this.need_cash_to_up_hp_lv=cash;
    }
    //回傳剩餘多少怪物
    public long return_have_how_many_monster(){return this.have_next_monster;}
//增加技能點
 public void add_skill_points(){this.skill_points+=1;}
    public void add_skill_points(long add){this.skill_points+=add;}
//回傳地區
public long JExFight_return_area() {
    return In_what_area;
}
    //取得戰鬥陣列
    public String[] JExFight_Get_Fight_Array() {
        return Fight_Array;
    }
//回傳魔法力量
    public long JExFight_return_Magic_Power(){
        return Magic_Power;
    }
    //回傳是否保護狀態
public long return_monster_in_protect(){return this.monster_in_protect;}
//設定保護狀態
public void set_monster_in_protect(long monster_in_protect_state){this.monster_in_protect=monster_in_protect_state;}
    /**
     *data 區
     */

    public long return_data_1(){return this.data1;}
    public void set_data_1(long data_1){this.data1=data_1;}

    public long return_data_2(){return this.data2;}
    public void set_data_2(long data_2){this.data2=data_2;}

    public long return_data_3(){return this.data3;}
    public void set_data_3(long data_3){this.data3=data_3;}

    public long return_data_4(){return this.data4;}
    public void set_data_4(long data_4){this.data4=data_4;}

    public long return_data_5(){return this.data5;}
    public void set_data_5(long data_5){this.data5=data_5;}

    public long return_data_6(){return this.data6;}
    public void set_data_6(long data_6){this.data6=data_6;}

    /**
     * 結束
     */

    //亂數判斷是否取得技能點
    public void get_skill_points(int lucky){

        Random random = new Random();
        int result[] = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32
                ,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,
                70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100};
        for(int i=0; i < result.length; i ++){
            int index = random.nextInt(100);
            //交換 i 跟index的原素
            int tmp = result[index];
            result[index] = result[i];
            result[i] = tmp;
        }
        for(int i=0;i<result.length;i++) {
            Log.d("IM_SKILL_GET"+"  "+String.valueOf(i)+"  ",String.valueOf(result[i]));
        }
        for(int i=0;i<lucky;i++) {
            if(result[i]==50){
                add_skill_points(2);
            }
        }
    }

    //回傳血量
    public long return_hero_hp(){
        this.hero_hp=100+(50*this.hp_lv);
        return this.hero_hp;}

    //判斷能否升級攻擊
    public String can_up_magic_power(long cash){
        String magic_you_can="default_error";
        if(coin-cash<0){
            magic_you_can="you_can't";
        }
        if(coin-cash>=0){
            magic_you_can="you_can";
            this.coin-=cash;
            this.need_cash_to_up_magic_power+=25;
            addMagic_Power();
        }
        return magic_you_can;
    }

    //判斷能否升級生命
    public String can_up_hp_lv(long cash){
        String hp_you_can="default_error";
        if(coin-cash<0){
            hp_you_can="you_can't";
        }
        if(coin-cash>=0){
            hp_you_can="you_can";
            this.coin-=cash;
            this.need_cash_to_up_hp_lv+=25;
            addhp_lv();
        }
        return hp_you_can;
    }
    //回傳角色攻擊數值
    public long JExFighting_get_hero_attack_value( long Magic_Power) {
            return_Attack = normal_attack_value +(10* Magic_Power);
        return return_Attack;
    }

    //設置生命等級跟血量
    public void set_hp_lv(long hp_lv){
        this.hp_lv=hp_lv;
        hero_hp=(this.hp_lv*50)+100;
    }

//設置區域跟法力
    public void JExFight_set_magic_area(long area,long Magic){
        this.In_what_area=area;
        this.Magic_Power=Magic;
    }

    //回傳是哪種怪物
    public String return_whos_monster(){return this.whos_monster;}
//重置怪物判斷字串
public void reset_whos_monster(){this.whos_monster="default";}
    /*

    public  void Fight_check(){
        if (Fight_int==Fight_Array.length){
            return_total=0;

        }

    }
    */

    //顯示進度
    public String return_schedule(){

        if(this.return_what_region_save()==1)
            what_schedule="1-1";
        if(this.return_what_region_save()==2)
            what_schedule="1-2";
        if(this.return_what_region_save()==3)
            what_schedule="1-3";
        if(this.return_what_region_save()==4)
            what_schedule="1-4";
        if(this.return_what_region_save()==5)
            what_schedule="1-5";
        if(this.return_what_region_save()==6)
            what_schedule="1-6";
        if(this.return_what_region_save()==7)
            what_schedule="1-7";
        if(this.return_what_region_save()==8)
            what_schedule="1-8";
        if(this.return_what_region_save()==9)
            what_schedule="1-9";
        if(this.return_what_region_save()==10)
            what_schedule="1-10";
        if(this.return_what_region_save()==11)
            what_schedule="1-11";
        if(this.return_what_region_save()==12)
            what_schedule="1-12";
        if(this.return_what_region_save()==13)
            what_schedule="1-13";
        if(this.return_what_region_save()==14)
            what_schedule="1-14";
        if(this.return_what_region_save()==15)
            what_schedule="1-15";
        if(this.return_what_region_save()==16)
            what_schedule="1-16";
        if(this.return_what_region_save()==17)
            what_schedule="1-17";
        if(this.return_what_region_save()==18)
            what_schedule="1-18";
        if(this.return_what_region_save()==19)
            what_schedule="1-19";
        if(this.return_what_region_save()==20)
            what_schedule="1-20";
        if(this.return_what_region_save()==21)
            what_schedule="1-21";
        if(this.return_what_region_save()==22)
            what_schedule="1-22";
        if(this.return_what_region_save()==23)
            what_schedule="1-23";
        if(this.return_what_region_save()==24)
            what_schedule="1-24";
        if(this.return_what_region_save()==25)
            what_schedule="1-25";
        if(this.return_what_region_save()==26)
            what_schedule="1-26";
        if(this.return_what_region_save()==27)
            what_schedule="1-27";
        if(this.return_what_region_save()==28)
            what_schedule="1-28";
        if(this.return_what_region_save()==29)
            what_schedule="1-29";
        if(this.return_what_region_save()==30)
            what_schedule="1-30";
        if(this.return_what_region_save()==31)
            what_schedule="1-31";
        if(this.return_what_region_save()==32)
            what_schedule="2-1";
        if(this.return_what_region_save()==33)
            what_schedule="2-2";
        if(this.return_what_region_save()==34)
            what_schedule="2-3";
        if(this.return_what_region_save()==35)
            what_schedule="2-4";
        if(this.return_what_region_save()==36)
            what_schedule="2-5";
        if(this.return_what_region_save()==37)
            what_schedule="2-6";
        if(this.return_what_region_save()==38)
            what_schedule="2-7";
        if(this.return_what_region_save()==39)
            what_schedule="2-8";
        if(this.return_what_region_save()==40)
            what_schedule="2-9";
        if(this.return_what_region_save()==41)
            what_schedule="2-10";
        if(this.return_what_region_save()==42)
            what_schedule="2-11－狀態－未開放";
        return what_schedule;
    }


    //設定怪物攻擊數值

    public String monster_attack_value_fuction(String what_monster) {

if(what_monster.equals("studio_smile")){
    monster_attack_value = 0;
    this.whos_monster="studio_smile";
}
if(what_monster.equals("nothingness_story")){
    monster_attack_value=0;
    this.whos_monster="nothingness_story";
}

        if (what_monster.equals("slime")) {
            monster_attack_value = 6;
            this.whos_monster="slime";
        }
        if(what_monster.equals("error")){
            monster_attack_value = 8;
            this.whos_monster="error";
        }
        if(what_monster.equals("dark_soul")){
            monster_attack_value = 7;
            this.whos_monster="dark_soul";
        }
        if(what_monster.equals("red_eye")){
            monster_attack_value = 7;
            this.whos_monster="red_eye";
        }
        if(what_monster.equals("red_eye_warrior")){
            monster_attack_value = 12;
            this.whos_monster="red_eye_warrior";
        }
        if(what_monster.equals("red_eye_sword")){
            monster_attack_value = 10;
            this.whos_monster="red_eye_sword";
        }
        if(what_monster.equals("red_eye_bow")){
            monster_attack_value = 13;
            this.whos_monster="red_eye_bow";
        }
        if(what_monster.equals("red_eye_hammer")){
            monster_attack_value = 10;
            this.whos_monster="red_eye_hammer";
        }
        if(what_monster.equals("red_eye_magician")){
            monster_attack_value = 18;
            this.whos_monster="red_eye_magician";
        }
        if(what_monster.equals("red_eye_boss")){
            monster_attack_value = 30;
            this.whos_monster="red_eye_boss";
        }
        if (what_monster.equals("dark_normal")) {
            monster_attack_value = 20;
            this.whos_monster="dark_normal";
        }
         if (what_monster.equals("dark_guard")) {
            monster_attack_value = 22;
             this.whos_monster="dark_guard";
        }
        if (what_monster.equals("dark_sword")) {
            monster_attack_value = 25;
            this.whos_monster="dark_sword";
        }
        if (what_monster.equals("dark_bow")) {
            monster_attack_value = 30;
            this.whos_monster="dark_bow";
        }
        if (what_monster.equals("dark_magic")) {
            monster_attack_value = 30;
            this.whos_monster="dark_magic";
        }
        if (what_monster.equals("dark_hammer")) {
            monster_attack_value = 25;
            this.whos_monster="dark_hammer";
        }
        if (what_monster.equals("dark_magician")) {
            monster_attack_value = 45;
            this.whos_monster="dark_magician";
        }
        if (what_monster.equals("polluted_cross")) {
            monster_attack_value = 27;
            this.whos_monster="polluted_cross";
        }
        if (what_monster.equals("dark_balloon")) {
            monster_attack_value = 50;
            this.whos_monster="dark_balloon";
        }
        if (what_monster.equals("nothingness")) {
            monster_attack_value = 25;
            this.whos_monster="nothingness";
        }
        if (what_monster.equals("nothingness_2")) {
            monster_attack_value = 50;
            this.whos_monster="nothingness_2";
        }
        if(what_monster.equals("nothingness_green")){
            monster_attack_value = 100;
            this.whos_monster="nothingness_green";
        }
        if(what_monster.equals("dark_eye")){
            monster_attack_value = 30;
            this.whos_monster="dark_eye";
        }
        if(what_monster.equals("square_smile_blue")){
            monster_attack_value = 27;
            this.whos_monster="square_smile_blue";
        }
        if(what_monster.equals("square_smile_green")){
            monster_attack_value = 26;
            this.whos_monster="square_smile_green";
        }
        if(what_monster.equals("square_smile_red")){
            monster_attack_value = 28;
            this.whos_monster="square_smile_red";
        }
        if(what_monster.equals("square_smile_yellow")){
            monster_attack_value = 27;
            this.whos_monster="square_smile_yellow";
        }
        if(what_monster.equals("square_smile_three")){
            monster_attack_value = 30;
            this.whos_monster="square_smile_three";
        }
        if(what_monster.equals("square_smile_four")){
            monster_attack_value = 32;
            this.whos_monster="square_smile_four";
        }
        if(what_monster.equals("fneam")){
            monster_attack_value = 32;
            this.whos_monster="fneam";
        }
        if(what_monster.equals("fneam_tail")){
            monster_attack_value = 33;
            this.whos_monster="fneam_tail";
        }
        if(what_monster.equals("fneam_tooth")){
            monster_attack_value = 34;
            this.whos_monster="fneam_tooth";
        }
        if(what_monster.equals("fneam_tail_tooth")){
            monster_attack_value = 35;
            this.whos_monster="fneam_tail_tooth";
        }
        if(what_monster.equals("insect_eye")){
            monster_attack_value = 37;
            this.whos_monster="insect_eye";
        }
        if(what_monster.equals("insect_eyes")){
            monster_attack_value = 60;
            this.whos_monster="insect_eyes";
        }
        return this.whos_monster;
    }


    public void choice_monster(long monster_Hp,String show_monster_name,TextView show_hp_text,String R_string_file,String R_string_file_2){
        Monster_Hp = monster_Hp;
        monster_hp_string = String.valueOf(Monster_Hp);
        return_string_choice_toast_view = show_monster_name;
        show_hp_text.setText(R_string_file + String.valueOf(Monster_Hp)+"\n"+R_string_file_2);
    }

        //用區域來亂數決定怪物並設定血量  傳入怪物圖片
    public String JExFight_ready(ImageView Monster_View, TextView show_hp_text, String R_string_file,String R_string_file_2,String have_boss) {

        this.R_string_file = R_string_file;
        Choice_rnd = Fight_rnd();
        switch (Choice_rnd) {
            case 0:
                if (In_what_area == 1&& have_boss.equals("no")) {
                    JB.Delay_show_Bitmap("slime.png",Monster_View,10,get_sd_path);
                    choice_monster(300,"slime",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==2&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("red_eye_warrior.png",Monster_View,10,get_sd_path);
                    choice_monster(360,"red_eye_warrior",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==3&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("dark_normal.png",Monster_View,10,get_sd_path);
                    choice_monster(600,"dark_normal",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==4&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("dark_magic.png",Monster_View,10,get_sd_path);
                    choice_monster(660,"dark_magic",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==5&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("dark_eye.png",Monster_View,10,get_sd_path);
                    choice_monster(800,"dark_eye",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==6&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("fneam.png",Monster_View,10,get_sd_path);
                    choice_monster(1200,"fneam",show_hp_text,R_string_file,R_string_file_2);
                }
                break;
            case 1:
                if (In_what_area == 1&& have_boss.equals("no")) {
                    JB.Delay_show_Bitmap("error.png",Monster_View,10,get_sd_path);
                    choice_monster(270,"error",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==2&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("red_eye_sword.png",Monster_View,10,get_sd_path);
                    choice_monster(450,"red_eye_sword",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==3&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("dark_guard.png",Monster_View,10,get_sd_path);
                    choice_monster(530,"dark_guard",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==4&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("dark_magic.png",Monster_View,10,get_sd_path);
                    choice_monster(660,"dark_magic",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==5&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("square_smile_blue.png",Monster_View,10,get_sd_path);
                    choice_monster(800,"square_smile_blue",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==6&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("fneam.png",Monster_View,10,get_sd_path);
                    choice_monster(1200,"fneam",show_hp_text,R_string_file,R_string_file_2);
                }

                break;
            case 2:
                if (In_what_area == 1&& have_boss.equals("no")) {
                    JB.Delay_show_Bitmap("dark_soul.png",Monster_View,10,get_sd_path);
                    choice_monster(270,"dark_soul",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==2&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("red_eye_bow.png",Monster_View,10,get_sd_path);
                    choice_monster(360,"red_eye_bow",show_hp_text,R_string_file,R_string_file_2);
                }  else if(In_what_area==3&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("dark_sword.png",Monster_View,10,get_sd_path);
                    choice_monster(540,"dark_sword",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==4&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("dark_magic.png",Monster_View,10,get_sd_path);
                    choice_monster(660,"dark_magic",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==5&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("square_smile_green.png",Monster_View,10,get_sd_path);
                    choice_monster(830,"square_smile_green",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==6&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("fneam_tail.png",Monster_View,10,get_sd_path);
                    choice_monster(1400,"fneam_tail",show_hp_text,R_string_file,R_string_file_2);
                }

                break;
            case 3:
                if (In_what_area == 1&& have_boss.equals("no")) {
                    JB.Delay_show_Bitmap("red_eye.png",Monster_View,10,get_sd_path);
                    choice_monster(330,"red_eye",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==2&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("red_eye_hammer.png",Monster_View,10,get_sd_path);
                    choice_monster(450,"red_eye_hammer",show_hp_text,R_string_file,R_string_file_2);
                } else if(In_what_area==3&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("dark_bow.png",Monster_View,10,get_sd_path);
                    choice_monster(520,"dark_bow",show_hp_text,R_string_file,R_string_file_2);
            }else if(In_what_area==4&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("dark_magician.png",Monster_View,10,get_sd_path);
                    choice_monster(330,"dark_magician",show_hp_text,R_string_file,R_string_file_2);
                } else if(In_what_area==5&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("square_smile_red.png",Monster_View,10,get_sd_path);
                choice_monster(770,"square_smile_red",show_hp_text,R_string_file,R_string_file_2);
            }else if(In_what_area==6&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("fneam_tail.png",Monster_View,10,get_sd_path);
                    choice_monster(1400,"fneam_tail",show_hp_text,R_string_file,R_string_file_2);
                }
                break;
            case 4:
                if (In_what_area == 1&& have_boss.equals("no")) {
                    JB.Delay_show_Bitmap("slime.png",Monster_View,10,get_sd_path);
                    choice_monster(300,"slime",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==2&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("red_eye_magician.png",Monster_View,10,get_sd_path);
                    choice_monster(150,"red_eye_magician",show_hp_text,R_string_file,R_string_file_2);
                } else if(In_what_area==3&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("dark_bow.png",Monster_View,10,get_sd_path);
                    choice_monster(520,"dark_bow",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==4&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("dark_magician.png",Monster_View,10,get_sd_path);
                    choice_monster(330,"dark_magician",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==5&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("square_smile_yellow.png",Monster_View,10,get_sd_path);
                    choice_monster(820,"square_smile_yellow",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==6&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("fneam_tooth.png",Monster_View,10,get_sd_path);
                    choice_monster(1300,"fneam_tooth",show_hp_text,R_string_file,R_string_file_2);
                }
                break;
            case 5:
                if (In_what_area == 1&& have_boss.equals("no")) {
                    JB.Delay_show_Bitmap("dark_soul.png",Monster_View,10,get_sd_path);
                    choice_monster(270,"dark_soul",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==2&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("red_eye_magician.png",Monster_View,10,get_sd_path);
                    choice_monster(150,"red_eye_magician",show_hp_text,R_string_file,R_string_file_2);
                } else if(In_what_area==3&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("dark_swordpng",Monster_View,10,get_sd_path);
                    choice_monster(540,"dark_sword",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==4&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("slime.dark_magician",Monster_View,10,get_sd_path);
                    choice_monster(330,"dark_magician",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==5&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("square_smile_three.png",Monster_View,10,get_sd_path);
                    choice_monster(1000,"square_smile_three",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==6&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("insect_eye.png",Monster_View,10,get_sd_path);
                    choice_monster(1350,"insect_eye",show_hp_text,R_string_file,R_string_file_2);
                }
                break;
            case 6:
                if (In_what_area == 1&& have_boss.equals("no")) {
                    JB.Delay_show_Bitmap("error.png",Monster_View,10,get_sd_path);
                    choice_monster(270,"error",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==2&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("red_eye_hammer.png",Monster_View,10,get_sd_path);
                    choice_monster(450,"red_eye_hammer",show_hp_text,R_string_file,R_string_file_2);
                } else if(In_what_area==3&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("dark_sword.png",Monster_View,10,get_sd_path);
                    choice_monster(540,"dark_sword",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==4&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("dark_hammer.png",Monster_View,10,get_sd_path);
                    choice_monster(660,"dark_hammer",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==5&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("square_smile_three.png",Monster_View,10,get_sd_path);
                    choice_monster(1000,"square_smile_three",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==6&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("fneam_tooth.png",Monster_View,10,get_sd_path);
                    choice_monster(1300,"fneam_tooth",show_hp_text,R_string_file,R_string_file_2);
                }
                break;
            case 7:
                if (In_what_area == 1&& have_boss.equals("no")) {
                    JB.Delay_show_Bitmap("slime.png",Monster_View,10,get_sd_path);
                    choice_monster(300,"slime",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==2&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("red_eye_bow.png",Monster_View,10,get_sd_path);
                    choice_monster(360,"red_eye_bow",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==3&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("dark_guard.png",Monster_View,10,get_sd_path);
                    choice_monster(530,"dark_guard",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==4&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("dark_hammer.png",Monster_View,10,get_sd_path);
                    choice_monster(660,"dark_hammer",show_hp_text,R_string_file,R_string_file_2);
                } else if(In_what_area==5&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("square_smile_redpng",Monster_View,10,get_sd_path);
                    choice_monster(770,"square_smile_red",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==6&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("fneam_tail_tooth.png",Monster_View,10,get_sd_path);
                    choice_monster(1500,"fneam_tail_tooth",show_hp_text,R_string_file,R_string_file_2);
                }
                break;
            case 8:
                if (In_what_area == 1&& have_boss.equals("no")) {
                    JB.Delay_show_Bitmap("red_eye.png",Monster_View,10,get_sd_path);
                    choice_monster(330,"red_eye",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==2&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("red_eye_sword.png",Monster_View,10,get_sd_path);
                    choice_monster(450,"red_eye_sword",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==3&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("dark_normal.png",Monster_View,10,get_sd_path);
                    choice_monster(600,"dark_normal",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==4&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("polluted_cross.png",Monster_View,10,get_sd_path);
                    choice_monster(660,"polluted_cross",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==5&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("square_smile_green.png",Monster_View,10,get_sd_path);
                    choice_monster(830,"square_smile_green",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==6&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("insect_eye.png",Monster_View,10,get_sd_path);
                    choice_monster(1350,"insect_eye",show_hp_text,R_string_file,R_string_file_2);
                }
                break;

            case 9:
                if (In_what_area == 1 && have_boss.equals("no")) {
                    JB.Delay_show_Bitmap("slime.png",Monster_View,10,get_sd_path);
                    choice_monster(300,"slime",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==2&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("red_eye_warrior.png",Monster_View,10,get_sd_path);
                    choice_monster(360,"red_eye_warrior",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==3&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("dark_normal.png",Monster_View,10,get_sd_path);
                    choice_monster(600,"dark_normal",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==4&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("polluted_cross.png",Monster_View,10,get_sd_path);
                    choice_monster(660,"polluted_cross",show_hp_text,R_string_file,R_string_file_2);
                }else if(In_what_area==5&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("square_smile_four.png",Monster_View,10,get_sd_path);
                choice_monster(1200,"square_smile_four",show_hp_text,R_string_file,R_string_file_2);
            }else if(In_what_area==6&& have_boss.equals("no")){
                    JB.Delay_show_Bitmap("fneam_tail_tooth.png",Monster_View,10,get_sd_path);
                    choice_monster(1500,"fneam_tail_tooth",show_hp_text,R_string_file,R_string_file_2);
                }
                break;

        }
        if(have_boss.equals("have_boss")){
            JB.Delay_show_Bitmap("red_eye_boss.png",Monster_View,10,get_sd_path);
            choice_monster(600,"red_eye_boss",show_hp_text,R_string_file,R_string_file_2);
        }
        if(have_boss.equals("have_boss_1")){
            JB.Delay_show_Bitmap("dark_balloon.png",Monster_View,10,get_sd_path);
            choice_monster(1500,"dark_balloon",show_hp_text,R_string_file,R_string_file_2);
        }
        if(have_boss.equals("studio")){
            JB.Delay_show_Bitmap("slime.png",Monster_View,10,get_sd_path);
            choice_monster(2101010101,"studio_smile",show_hp_text,R_string_file,R_string_file_2);
        }
        if(have_boss.equals("nothingness")){
            JB.Delay_show_Bitmap("nothingness.png",Monster_View,10,get_sd_path);
            choice_monster(1500,"nothingness",show_hp_text,R_string_file,R_string_file_2);
        }
        if(have_boss.equals("nothingness_2")){
            JB.Delay_show_Bitmap("nothingness_2.png",Monster_View,10,get_sd_path);
            choice_monster(1500,"nothingness_2",show_hp_text,R_string_file,R_string_file_2);
        }
        if(have_boss.equals("nothingness_story")){
            JB.Delay_show_Bitmap("nothingness_2.png",Monster_View,10,get_sd_path);
            choice_monster(500000,"nothingness_story",show_hp_text,R_string_file,R_string_file_2);
        }
        if(have_boss.equals("nothingness_green")){
            JB.Delay_show_Bitmap("nothingness_green.png",Monster_View,10,get_sd_path);
            choice_monster(30000,"nothingness_green",show_hp_text,R_string_file,R_string_file_2);
        }
        if(have_boss.equals("insect_eyes")){
            JB.Delay_show_Bitmap("insect_eyes.png",Monster_View,10,get_sd_path);
            choice_monster(5000,"insect_eyes",show_hp_text,R_string_file,R_string_file_2);
        }
        show_hp_text.setVisibility(View.VISIBLE);
        Monster_View.setVisibility(View.VISIBLE);
        return return_string_choice_toast_view;
    }

//亂數開啟護頓
    public String monster_protect(){
        String protect ="no_protect";

        Random random = new Random();
        int result[] = new int[]{1,2,3,4,5};
        for(int i=0; i < result.length; i ++){ // 這個迴圈也可以只跑到result.length/2
            int index = random.nextInt(5);
            //交換 i 跟index的原素
            int tmp = result[index];
            result[index] = result[i];
            result[i] = tmp;
        }
        for(int i=0;i<result.length;i++) {
            Log.d("IM_money_skill_RNG"+"  "+String.valueOf(i)+"  ",String.valueOf(result[i]));
        }
        for(int i=0;i<1;i++) {
            if(result[i]==5){
                protect="have_protect";
            }
        }
        return protect;
    }

    //寵物攻擊
  public long pet_attack_question(){
        long pet_attack=0;
      Random random = new Random();
      int result[] = new int[]{5};
      for(int i=0; i < result.length; i ++){ // 這個迴圈也可以只跑到result.length/2
          int index = random.nextInt(5);
          //交換 i 跟index的原素
          int tmp = result[index];
          result[index] = result[i];
          result[i] = tmp;
      }
      for(int i=0;i<result.length;i++) {
          Log.d("IM_money_skill_RNG"+"  "+String.valueOf(i)+"  ",String.valueOf(result[i]));
      }
      for(int i=0;i<1;i++) {
          if(result[i]==5){
              if(data4==1&&data3!=0) {
                  pet_attack = data3 * 3;
              }
          }
      }
        return pet_attack;
  }

//取得一個新的戰鬥陣列
    public void JExFight_choice_Fight_Array() {

            Fight_Array = JE_Fight_Array();

    }


//戰鬥觀看值

    public void JExFighting_Test(String Fight_Magic) {

        if (Fight_Array[Fight_int] == Fight_Magic && Fight_int < Fight_Array.length) {
            Fight_int += 1;


            return_total += JExFighting_get_hero_attack_value(Magic_Power);



        }

    }


    public int[] Fight_rnd_array( ) {

        int sort[]=new int[10];
        int rnd;
        for ( int i=0;i<10;i++) {
            rnd = (int) (Math.random() *26); // 0~25 rnd
            sort[i]=rnd;


        }
        return sort;
    }

    public String[] JE_Fight_Array() {
        int[] Fight_array = Fight_rnd_array();
        String[] Fight_string = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (int i = 0; i < 10; i++) {
            if (Fight_array[i] == 0) {
                Fight_string[i] = "A";
            }
            if (Fight_array[i] == 1) {
                Fight_string[i] = "B";
            }
            if (Fight_array[i] == 2) {
                Fight_string[i] = "C";
            }
            if (Fight_array[i] == 3) {
                Fight_string[i] = "D";
            }
            if (Fight_array[i] == 4) {
                Fight_string[i] = "E";
            }
            if (Fight_array[i] == 5) {
                Fight_string[i] = "F";
            }
            if (Fight_array[i] == 6) {
                Fight_string[i] = "G";
            }
            if (Fight_array[i] == 7) {
                Fight_string[i] = "H";
            }
            if (Fight_array[i] == 8) {
                Fight_string[i] = "I";
            }
            if (Fight_array[i] == 9) {
                Fight_string[i] = "J";
            }
            if (Fight_array[i] == 10) {
                Fight_string[i] = "K";
            }
            if (Fight_array[i] == 11) {
                Fight_string[i] = "L";
            }
            if (Fight_array[i] == 12) {
                Fight_string[i] = "M";
            }
            if (Fight_array[i] == 13) {
                Fight_string[i] = "N";
            }
            if (Fight_array[i] == 14) {
                Fight_string[i] = "O";
            }
            if (Fight_array[i] == 15) {
                Fight_string[i] = "P";
            }
            if (Fight_array[i] == 16) {
                Fight_string[i] = "Q";
            }
            if (Fight_array[i] == 17) {
                Fight_string[i] = "R";
            }
            if (Fight_array[i] == 18) {
                Fight_string[i] = "S";
            }
            if (Fight_array[i] == 19) {
                Fight_string[i] = "T";
            }
            if (Fight_array[i] == 20) {
                Fight_string[i] = "U";
            }
            if (Fight_array[i] == 21) {
                Fight_string[i] = "V";
            }
            if (Fight_array[i] == 22) {
                Fight_string[i] = "W";
            }
            if (Fight_array[i] == 23) {
                Fight_string[i] = "X";
            }
            if (Fight_array[i] == 24) {
                Fight_string[i] = "Y";
            }
            if (Fight_array[i] == 25) {
                Fight_string[i] = "Z";
            }
        }
        show_Fight_Array = Fight_string[0] + Fight_string[1] + Fight_string[2] + Fight_string[3] + Fight_string[4] + Fight_string[5] + Fight_string[6] + Fight_string[7] + Fight_string[8] + Fight_string[9];
        return Fight_string;

    }

    public String JE_Fight_Array_Test() {
        int[] Fight_array = Fight_rnd_array();
        String[] Fight_string = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (int i = 0; i < 10; i++) {
            if (Fight_array[i] == 0) {
                Fight_string[i] = "A";
            }
            if (Fight_array[i] == 1) {
                Fight_string[i] = "B";
            }
            if (Fight_array[i] == 2) {
                Fight_string[i] = "C";
            }
            if (Fight_array[i] == 3) {
                Fight_string[i] = "D";
            }
            if (Fight_array[i] == 4) {
                Fight_string[i] = "E";
            }
            if (Fight_array[i] == 5) {
                Fight_string[i] = "F";
            }
            if (Fight_array[i] == 6) {
                Fight_string[i] = "G";
            }
            if (Fight_array[i] == 7) {
                Fight_string[i] = "H";
            }
            if (Fight_array[i] == 8) {
                Fight_string[i] = "I";
            }
            if (Fight_array[i] == 9) {
                Fight_string[i] = "J";
            }
        }
        return Fight_string[0] + Fight_string[1] + Fight_string[2] + Fight_string[3] + Fight_string[4] + Fight_string[5] + Fight_string[6] + Fight_string[7] + Fight_string[8] + Fight_string[9];

    }




    //  傳回int亂數 return int rnd
    public int Fight_rnd() {

        int rnd;
        rnd = (int) (Math.random() * 10);//0-9
        return rnd;
    }

    public int tip_rnd() {

        int rnd;
        rnd = (int) (Math.random() * 7);
        return rnd;
    }
    // 外部要用時 取用 outside use this return int rnd
    public void Fight_rnd(int Fight_rnd) {

        int rnd;
        rnd = (int) (Math.random() * 10);
        Fight_rnd = rnd;
    }


    //重置
    public void reset_all(long magic_Power,long hp_lv,long what_region_save,long coin,long need_cash_to_up_magic_power,long need_cash_to_up_hp_lv,
                          long skill_points,long data1,long data2,long data3,long data4,long data5,long data6){
        this.Magic_Power=magic_Power;
        this.hp_lv=hp_lv;
        this.what_region_save=what_region_save;
        this.coin=coin;
        this.need_cash_to_up_magic_power=need_cash_to_up_magic_power;
        this.need_cash_to_up_hp_lv=need_cash_to_up_hp_lv;
        this.skill_points=skill_points;
        this.data1=data1;
        this.data2=data2;
        this.data3=data3;
        this.data4=data4;
        this.data5=data5;
        this.data6=data6;
    }


    /**
     * 傷害計算區
     */
   //1倍
    public long return_attack_1_point_0(long total){
        long attack_1_point_0 = 0;
        String weapon =JE_Backpack.return_what_weapon();
        if(total==1) {
            attack_1_point_0 = normal_attack_value +(10* Magic_Power);
            Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_1_point_0));
        }
        if(total>=2&&total<=4) {
             attack_1_point_0 = (long) (((normal_attack_value +(10* Magic_Power))*total)*1.1);
            Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_1_point_0));
        }
        if(total>=5&&total<=7) {
            attack_1_point_0 = (long) (((normal_attack_value +(10* Magic_Power))*total)*1.2);
            Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_1_point_0));
        }
        if(total>=8&&total<=10) {
            attack_1_point_0 = (long) (((normal_attack_value +(10* Magic_Power))*total)*1.3);
            Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_1_point_0));
        }
        if(total>=11) {
            attack_1_point_0 = (long) (((normal_attack_value +(10* Magic_Power))*total)*1.4);
            Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_1_point_0));
        }
        if(weapon.equals("iron_sword")){
            attack_1_point_0+=5;
        }
        if(weapon.equals("arch_sword")){

            if(total==1) {
                attack_1_point_0 = (long) (((normal_attack_value) +(10* Magic_Power))*0.35)*2;
                Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_1_point_0));
            }
            if(total>=2&&total<=4) {
                attack_1_point_0 = (long) (((((normal_attack_value) +(10* Magic_Power))*0.35)*total)*1.1)*2;
                Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_1_point_0));
            }
            if(total>=5&&total<=7) {
                attack_1_point_0 = (long) (((((normal_attack_value) +(10* Magic_Power))*0.35)*total)*1.2)*2;
                Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_1_point_0));
            }
            if(total>=8&&total<=10) {
                attack_1_point_0 = (long) (((((normal_attack_value) +(10* Magic_Power))*0.35)*total)*1.3)*2;
                Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_1_point_0));
            }
            if(total>=11) {
                attack_1_point_0 = (long) ((((normal_attack_value +(10* Magic_Power))*0.35)*total)*1.4);
                Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_1_point_0));
            }

        }
         return attack_1_point_0;
    }


    //1.5倍
    public long return_1_point_5_attack(long total){
        long attack_1_point_5 = 0;
        String weapon =JE_Backpack.return_what_weapon();
        if(total==1) {
            attack_1_point_5 =(normal_attack_value +(10* Magic_Power))+((normal_attack_value +(10* Magic_Power))/2);
            Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_1_point_5));
        }
        if(total>=2&&total<=4) {
            attack_1_point_5 = (long) ((((normal_attack_value +(10* Magic_Power))+((normal_attack_value +(10* Magic_Power))/2))*total)*1.1);
        }
        if(total>=5&&total<=7) {
            attack_1_point_5 = (long) ((((normal_attack_value +(10* Magic_Power))+((normal_attack_value +(10* Magic_Power))/2))*total)*1.3);
            Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_1_point_5));
        }
        if(total>=8&&total<=10) {
            attack_1_point_5 = (long) ((((normal_attack_value +(10* Magic_Power))+((normal_attack_value +(10* Magic_Power))/2))*total)*1.4);
            Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_1_point_5));
        }
        if(total>=11) {
            attack_1_point_5 = (long) ((((normal_attack_value +(10* Magic_Power))+((normal_attack_value +(10* Magic_Power))/2))*total)*1.5);
            Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_1_point_5));
        }
        if(weapon.equals("iron_sword")){
            attack_1_point_5+=5;
        }
        if(weapon.equals("arch_sword")) {
            if(total==1) {
                attack_1_point_5 = (long) (((normal_attack_value +(10* Magic_Power))+((normal_attack_value +(10* Magic_Power)))*0.35)/2)*2;
                Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_1_point_5));
            }
            if(total>=2&&total<=4) {
                attack_1_point_5 = (long) (((((normal_attack_value +(10* Magic_Power))+((normal_attack_value +(10* Magic_Power))/2))*0.35)*total)*1.1)*2;
            }
            if(total>=5&&total<=7) {
                attack_1_point_5 = (long) (((((normal_attack_value +(10* Magic_Power))+((normal_attack_value +(10* Magic_Power))/2))*0.35)*total)*1.3)*2;
                Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_1_point_5));
            }
            if(total>=8&&total<=10) {
                attack_1_point_5 = (long) (((((normal_attack_value +(10* Magic_Power))+((normal_attack_value +(10* Magic_Power))/2))*0.35)*total)*1.4)*2;
                Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_1_point_5));
            }
            if(total>=11) {
                attack_1_point_5 = (long) (((((normal_attack_value +(10* Magic_Power))+((normal_attack_value +(10* Magic_Power))/2))*0.35)*total)*1.5)*2;
                Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_1_point_5));
            }
        }
        return attack_1_point_5;
    }


    //2倍
    public long return_2_point_0_attack(long total){
        long attack_2_point_0 = 0;
        String weapon =JE_Backpack.return_what_weapon();
        if(total==1) {
            attack_2_point_0 =((normal_attack_value +(10* Magic_Power))*2);
            Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_2_point_0));
        }
        if(total>=2&&total<=4) {
            attack_2_point_0 = (long) ((((normal_attack_value +(10* Magic_Power))*total)*2)*1.1);
            Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_2_point_0));
        }
        if(total>=5&&total<=7) {
            attack_2_point_0 = (long) ((((normal_attack_value +(10* Magic_Power))*total)*2)*1.3);
            Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_2_point_0));
        }
        if(total>=8&&total<=10) {
            attack_2_point_0 = (long) ((((normal_attack_value +(10* Magic_Power))*total)*2)*1.4);
            Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_2_point_0));
        }
        if(total>=11) {
            attack_2_point_0 = (long) ((((normal_attack_value +(10* Magic_Power))*total)*2)*1.5);
            Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_2_point_0));
        }
        if(weapon.equals("iron_sword")){
            attack_2_point_0+=5;
        }
        if(weapon.equals("arch_sword")) {
            if(total==1) {
                attack_2_point_0 = (long) (((normal_attack_value +(10* Magic_Power))*0.35)*2)*2;
                Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_2_point_0));
            }
            if(total>=2&&total<=4) {
                attack_2_point_0 = (long) (((((normal_attack_value +(10* Magic_Power))*0.35)*total)*2)*1.1);
                Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_2_point_0));
            }
            if(total>=5&&total<=7) {
                attack_2_point_0 = (long) (((((normal_attack_value +(10* Magic_Power))*0.35)*total)*2)*1.3);
                Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_2_point_0));
            }
            if(total>=8&&total<=10) {
                attack_2_point_0 = (long) (((((normal_attack_value +(10* Magic_Power))*0.35)*total)*2)*1.4);
                Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_2_point_0));
            }
            if(total>=11) {
                attack_2_point_0 = (long) (((((normal_attack_value +(10* Magic_Power))*0.35)*total)*2)*1.5);
                Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_2_point_0));
            }
        }


        return attack_2_point_0;
    }

    public void Time_Fight(){

        if(JE_Backpack.return_what_weapon().equals("shadow_light_book")){

            if(Runnable_time<=9999&&(Runnable_time-250)>=250) {
                Runnable_time -= 250;
            }else if(Runnable_time<=9999&&Runnable_time-250<250){
                Runnable_time=0;
            }
            if(JE_Backpack.return_what_weapon().equals("time_souls")){

                if(Runnable_time<=9999&&(Runnable_time-250)>=250) {
                    Runnable_time -= 250;
                }else if(Runnable_time<=9999&&Runnable_time-250<250){
                    Runnable_time=0;
                }

            }
        }

    }

    public long What_Defense(long Monster_Attack){

        long Defense=0;

        if(JE_Backpack.return_what_armor().equals("armor")){
            Defense=(long)(Monster_Attack*0.01);
        }

        return Monster_Attack-Defense;
    }


    public String do_you_critical(){
        String critical ="haven't_critical";
        Random random = new Random();
        int result[] = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32
                ,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,
                70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100};
        for(int i=0; i < result.length; i ++){ // 這個迴圈也可以只跑到result.length/2
            int index = random.nextInt(100);
            //交換 i 跟index的原素
            int tmp = result[index];
            result[index] = result[i];
            result[i] = tmp;
        }
        for(int i=0;i<result.length;i++) {
            Log.d("IM_critical_RNG"+"  "+String.valueOf(i)+"  ",String.valueOf(result[i]));
        }
        for(int i=0;i<(critical_lv-1)*2;i++) {
            if(result[i]==50){
                critical="have_critical";
            }
        }

        return critical;
    }

//爆擊傷害
    public long return_critical_attack(long value){
      long return_critical=value;
if(JExSkill.critical_lv>=2) {
    String critical = do_you_critical();
    if (!critical.equals("have_critical")) {
        return_critical = value;
    } else if (critical.equals("have_critical")) {
        return_critical = (value * 2);
    }
}
        return return_critical;
    }

    public long skill_healing_hp(long attack_counter){
        long healing=((normal_attack_value +(10* Magic_Power))-((normal_attack_value +(10* Magic_Power))-5))*attack_counter;
return healing;
    }


}
