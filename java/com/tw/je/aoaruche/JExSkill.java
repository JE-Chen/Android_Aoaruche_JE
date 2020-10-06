/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan. 
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna. 
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus. 
 * Vestibulum commodo. Ut rhoncus gravida arcu. 
 */

package com.tw.je.aoaruche;

import android.util.Log;

import java.util.Random;

public class JExSkill{

    /**
     * 能力特區 Lucius
     */
    //賺錢
    public static long money_skill_lv=1;
    public void setMoney_skill_lv(long money_skill_lv){this.money_skill_lv=money_skill_lv;}
    public static long up_money_skill_lv=15;
    public void set_up_money_skill_lv(){this.up_money_skill_lv=15+(money_skill_lv*5);}
    public long return_money_skill_lv(){return this.money_skill_lv;}
    public long return_up_money_skill_lv(){return this.up_money_skill_lv;}

    public String do_you_money(){
        String money ="haven't_money";

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
            Log.d("IM_money_skill_RNG"+"  "+String.valueOf(i)+"  ",String.valueOf(result[i]));
        }
        for(int i=0;i<money_skill_lv-1;i++) {
            if(result[i]==50){
                money="have_money";
            }
        }

        return money;
    }

    public String add_money_skill_lv(long points){
        String can_up ="default";
        if(points-up_money_skill_lv>=0&&money_skill_lv<=20){

            this.money_skill_lv+=1;
            can_up="can";
            JExFight_Gesture.skill_points-=this.up_money_skill_lv;
            set_up_money_skill_lv();
        }
        else if(points-up_money_skill_lv<0||money_skill_lv>=21){
            can_up="can't";
        }
        return can_up;
    }


    //爆擊
    public static long critical_lv=1;
    public void setCritical_lv(long critical_lv){this.critical_lv=critical_lv;}
    public static long up_critical_lv=15;
    public void set_up_critical_lv(){this.up_critical_lv=15+(critical_lv*5);}
    public long return_critical_lv(){return this.critical_lv;}
    public long return_up_critical_lv(){return this.up_critical_lv;}
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

    public String add_critical_lv(long points){
        String can_up ="default";
        if(points-up_critical_lv>=0&&critical_lv<=25){

            this.critical_lv+=1;
            can_up="can";
            JExFight_Gesture.skill_points-=this.up_critical_lv;
            set_up_critical_lv();
        }
        else if(points-up_critical_lv<0||critical_lv>=26){
            can_up="can't";
        }
        return can_up;
    }

    //攻擊後回血
    public static long healing_lv=1;
    public void setHealing_lv(long healing_lv){this.healing_lv=healing_lv;}
    public static long up_healing_lv=15;
    public void set_up_healing_lv(){this.up_healing_lv=15+(healing_lv*5);}
    public long return_healing_lv(){return this.healing_lv;}
    public long return_up_healing_lv(){return this.up_healing_lv;}

    public long do_you_healing(long attack_counter){
long healing =(healing_lv-1)+attack_counter;

        return healing;
    }

    public String add_healing_lv(long points){
        String can_up ="default";
        if(points-up_healing_lv>=0&&healing_lv<=25){

            this.healing_lv+=1;
            can_up="can";
            JExFight_Gesture.skill_points-=this.up_healing_lv;
            set_up_healing_lv();
        }
        else if(points-up_healing_lv<0||healing_lv>=26){
            can_up="can't";
        }
        return can_up;
    }

    //閃避
    public static long dodge_lv=1;
    public void setDodge_lv(long dodge_lv){this.dodge_lv=dodge_lv;}
    public static long up_dodge_lv=15;
    public void set_up_dodge_lv(){this.up_dodge_lv=15+(dodge_lv*5);}
    public long return_dodge_lv(){return this.dodge_lv;}
    public long return_up_dodge_lv(){return this.up_dodge_lv;}
    public String do_you_dodge(){
        String dodge ="haven't_dodge";
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
            Log.d("IM_dodge_RNG"+"  "+String.valueOf(i)+"  ",String.valueOf(result[i]));
        }
        for(int i=0;i<dodge_lv-1;i++) {
            if(result[i]==50){
                dodge="have_dodge";
            }
        }

        return dodge;
    }

    public String add_dodge_lv(long points){
        String can_up ="default";
        if(points-up_dodge_lv>=0&&dodge_lv<=25){

            this.dodge_lv+=1;
            can_up="can";
            JExFight_Gesture.skill_points-=this.up_dodge_lv;
            set_up_dodge_lv();
        }
        else if(points-up_dodge_lv<0||dodge_lv>=26){
            can_up="can't";
        }
        return can_up;
    }

    //復活
    public static long miracle_of_live_lv=1;
    public void set_Miracle_of_live_lv(long miracle_of_live_lv){this.miracle_of_live_lv=miracle_of_live_lv;}
    public static long up_miracle_of_live_lv=15;
    public void set_up_miracle_of_live_lv(){this.up_miracle_of_live_lv=15+(miracle_of_live_lv*5);}
    public long return_miracle_of_live_lv(){return this.miracle_of_live_lv;}
    public long return_up_miracle_of_live_lv(){return this.up_miracle_of_live_lv;}
    public String do_you_miracle(){
        String miracle ="haven't_miracle";
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
            Log.d("IM_miracle_RNG"+"  "+String.valueOf(i)+"  ",String.valueOf(result[i]));
        }
        for(int i=0;i<miracle_of_live_lv-1;i++) {
            if(result[i]==50){
                miracle="have_miracle";
            }
        }

        return miracle;
    }

    public String add_miracle_of_live_lv(long points){
        String can_up ="default";
        if(points-up_miracle_of_live_lv>=0&&miracle_of_live_lv<=5){

            this.miracle_of_live_lv+=1;
            can_up="can";
            JExFight_Gesture.skill_points-=this.up_miracle_of_live_lv;
            set_up_miracle_of_live_lv();
        }
        else if(points-up_miracle_of_live_lv<0||miracle_of_live_lv>=6){
            can_up="can't";
        }
        return can_up;
    }

//大法師
public static long great_mage_lv=1;
    public void setGreat_mage_lv(long great_mage_lv){this.great_mage_lv=great_mage_lv;}
    public static long up_great_mage_lv=15;
    public void set_up_great_mage_lv(){this.up_great_mage_lv=15+(great_mage_lv*5);}
    public long return_great_mage_lv(){return this.great_mage_lv;}
    public long return_up_great_mage_lv(){return this.up_great_mage_lv;}
    public String add_great_mage_lv(long points){
        String can_up ="default";
        if(points-up_great_mage_lv>=0){

            this.great_mage_lv+=1;
            JExFight_Gesture.Magic_Power+=1;
            can_up="can";
            JExFight_Gesture.skill_points-=this.up_great_mage_lv;
            set_up_great_mage_lv();
        }
        else if(points-up_great_mage_lv<0){
            can_up="can't";
        }
        return can_up;
    }

//身體鍛鍊
public static long exercise_lv=1;
    public void set_Exercise_lv(long exercise_lv){this.exercise_lv=exercise_lv;}
    public static long up_exercise_lv=15;
    public void set_up_exercise_lv(){this.up_exercise_lv=15+(exercise_lv*5);}
    public long return_exercise_lv(){return this.exercise_lv;}
    public long return_up_exercise_lv(){return this.up_exercise_lv;}
    public String add_exercise_lv(long points){
        String can_up ="default";
        if(points-up_exercise_lv>=0){

            this.exercise_lv+=1;
            JExFight_Gesture.hp_lv+=1;
            can_up="can";
            JExFight_Gesture.skill_points-=this.up_exercise_lv;
            set_up_exercise_lv();
        }
        else if(points-up_exercise_lv<0){
            can_up="can't";
        }
        return can_up;
    }



    public void reset_this_all(long exercise_lv,long great_mage_lv,long miracle_of_live_lv,long dodge_lv,long healing_lv,long critical_lv,long money_skill_lv){
        this.exercise_lv=exercise_lv;
        this.great_mage_lv=great_mage_lv;
        this.miracle_of_live_lv=miracle_of_live_lv;
        this.dodge_lv=dodge_lv;
        this.healing_lv=healing_lv;
        this.critical_lv=critical_lv;
        this.money_skill_lv=money_skill_lv;
    }

        }