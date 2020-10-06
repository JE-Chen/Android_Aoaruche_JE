/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan. 
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna. 
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus. 
 * Vestibulum commodo. Ut rhoncus gravida arcu. 
 */

package com.tw.je.aoaruche;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import static com.tw.je.aoaruche.JExMainActivity_original.get_sd_path;
import static com.tw.je.aoaruche.JExMainActivity_original.hero_attack_view_counter;
import static com.tw.je.aoaruche.JExMainActivity_original.monster_attack_view_counter;

/**
 * Created by JE on 2017/7/12.
 * Animation  use library
 *  name : JE x Animatoin
 *  Type : Animation
 */

public class JExAnimation{
public static Animation am_right;
    public static Animation am_left;
    public static Animation am_fade;
    public  JExBitmap JB = new JExBitmap();

    public void dealy_right_rotate(final ImageView rtimage,final long time,final  long rttime) {
        final Handler mHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
//延遲完後要幹嘛:

                am_right = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                am_right.setDuration(rttime);

                am_right.setRepeatCount(Animation.INFINITE);
                rtimage.startAnimation(am_right);
                am_right.startNow();
            }
        };
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(time);
                    Message msg = Message.obtain();
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }
    //轉動 動畫 右 圖片
    public void JE_rotate_right(ImageView rtimage,int rttime){

        am_right = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        am_right.setDuration(rttime);

        am_right.setRepeatCount(Animation.INFINITE);
        rtimage.startAnimation(am_right);
        am_right.startNow();


    }
    //轉動 動畫 左 圖片
    public void JE_rotate_left(ImageView rtimage,int rttime){

        am_left = new RotateAnimation(360,0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        am_left.setDuration(rttime);

        am_left.setRepeatCount(Animation.INFINITE);
        rtimage.startAnimation(am_left);
        am_left.startNow();


    }

    public void dealy_fade(final ImageView fade_image,final long time,final  long fatime) {
        final Handler mHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
//延遲完後要幹嘛:

                am_fade = new AlphaAnimation(0.1f, 1.0f);
                am_fade.setDuration(fatime);
                am_fade.setRepeatCount(Animation.INFINITE);
                am_fade.setRepeatMode(Animation.REVERSE);
                fade_image.setAnimation(am_fade);
                am_fade.start();
            }
        };
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(time);
                    Message msg = Message.obtain();
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }
    //閃爍動畫 圖片 fade one image
    public void JE_fade(ImageView fade_image,int fatime){

        am_fade = new AlphaAnimation(0.1f, 1.0f);
        am_fade.setDuration(fatime);
        am_fade.setRepeatCount(Animation.INFINITE);
        am_fade.setRepeatMode(Animation.REVERSE);
        fade_image.setAnimation(am_fade);
        am_fade.start();

    }
    //閃爍動畫 文字 fade one textview
    public void JE_fade(TextView fade_text, int fatime){

        am_fade = new AlphaAnimation(0.1f, 1.0f);
        am_fade.setDuration(fatime);
        am_fade.setRepeatCount(Animation.INFINITE);
        am_fade.setRepeatMode(Animation.REVERSE);
        fade_text.setAnimation(am_fade);
        am_fade.start();

    }
    //閃爍動畫 怪物
    public void JE_fade_monster(ImageView fade_image,int fatime){

        am_fade = new AlphaAnimation(0.5f, 1.0f);
        am_fade.setDuration(fatime);
        am_fade.setRepeatCount(Animation.INFINITE);
        am_fade.setRepeatMode(Animation.REVERSE);
        fade_image.setAnimation(am_fade);
        am_fade.start();

    }

    //停止動畫 stop_Animation one image
    public void stop_am(ImageView im){
        im.clearAnimation();

    }
    //停止動畫stop_Animation one textview
    public void stop_am(TextView text){
        text.clearAnimation();

    }

    //停止並隱藏
   public void clean_and_stop_am(ImageView image){
       image.clearAnimation();
       image.setVisibility(View.INVISIBLE);
   }

    public void dealy_clean_and_stop_am(final ImageView image,final long time) {
        final Handler mHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
//延遲完後要幹嘛:

                image.clearAnimation();
                image.setVisibility(View.INVISIBLE);
            }
        };
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(time);
                    Message msg = Message.obtain();
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }
//停止動畫且清掉圖片 stop_Animation one image and clean image
    public void clean_and_stop_am_Gone(ImageView image){
        image.clearAnimation();
        image.setVisibility(View.GONE);
    }
    //停止動畫且清掉文字
    public void clean_and_stop_am(TextView text ){
        text.clearAnimation();
        text.setVisibility(View.GONE);
    }
    //延遲並清除動畫整數 R.id 1 圖片1 時間 1
    public void JExAnimation_Delay_Clean_am(final ImageView image1,final long time) {
        final Handler mHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
//延遲完後要幹嘛:

              image1.clearAnimation();
                image1.setVisibility(View.GONE);
            }
        };
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(time);
                    Message msg = Message.obtain();
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    //延遲並清除動畫 圖片 2 時間 1
    public void JExAnimation_Delay_Clean_am(final ImageView image1,final ImageView image2,final long time) {
        final Handler mHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
//延遲完後要幹嘛:

                image1.clearAnimation();
                image2.clearAnimation();
                image1.setVisibility(View.GONE);
                image2.setVisibility(View.GONE);
            }
        };
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(time);
                    Message msg = Message.obtain();
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    //延遲並清除動畫 改變文字 文字1 時間1
    public void JExAnimation_Delay_change_text(final TextView text, final long time, final int textset) {
        final Handler mHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
//延遲完後要幹嘛:

                text.clearAnimation();
                 text.setText(textset);
            }
        };
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(time);
                    Message msg = Message.obtain();
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    //怪物攻擊動畫
    public void Monster_attack_animation(final long time, final ImageView view_image1) {
        final Handler mHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //延遲完後要幹嘛:
                if (monster_attack_view_counter == 0) {
                    view_image1.setVisibility(View.VISIBLE);
                    JB.Delay_show_Bitmap("monster_attack_2.png",view_image1,10,get_sd_path);
                    monster_attack_view_counter += 1;
                    Monster_attack_animation(time-1800, view_image1);
                } else if (monster_attack_view_counter == 1) {
                    JB.Delay_show_Bitmap("monster_attack_3.png",view_image1,10,get_sd_path);
                    monster_attack_view_counter += 1;
                    Monster_attack_animation(time, view_image1);
                } else if (monster_attack_view_counter == 2) {
                    JB.Delay_show_Bitmap("monster_attack_4.png",view_image1,10,get_sd_path);
                    monster_attack_view_counter += 1;
                    Monster_attack_animation(time, view_image1);
                } else if (monster_attack_view_counter == 3) {
                    JB.Delay_show_Bitmap("monster_attack_5.png",view_image1,10,get_sd_path);
                    Monster_attack_animation(time, view_image1);
                    monster_attack_view_counter += 1;
                } else if (monster_attack_view_counter == 4) {
                    monster_attack_view_counter = 0;
                    view_image1.setVisibility(View.INVISIBLE);
                }



            }
        };
        new Thread(new Runnable() {
            public void run() {
                try {

                    Thread.sleep(time);

                    Message msg = Message.obtain();
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }



  // 英雄攻擊動畫
    public void hero_attack_animation(final long time, final ImageView view_image) {
        final Handler mHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //延遲完後要幹嘛:
                if (hero_attack_view_counter == 0) {
                    view_image.setVisibility(View.VISIBLE);
                    JB.Delay_show_Bitmap("hero_attack_1.png",view_image,1,get_sd_path);
                    hero_attack_view_counter += 1;
                    hero_attack_animation(time, view_image);
                } else if (hero_attack_view_counter == 1) {
                    JB.Delay_show_Bitmap("hero_attack_2.png",view_image,1,get_sd_path);
                    hero_attack_view_counter += 1;
                    hero_attack_animation(time, view_image);
                } else if (hero_attack_view_counter == 2) {
                    JB.Delay_show_Bitmap("hero_attack_3.png",view_image,1,get_sd_path);
                    hero_attack_view_counter += 1;
                    hero_attack_animation(time, view_image);
                } else if (hero_attack_view_counter == 3) {
                    JB.Delay_show_Bitmap("hero_attack_4.png",view_image,1,get_sd_path);
                    hero_attack_animation(time, view_image);
                    hero_attack_view_counter += 1;
                } else if (hero_attack_view_counter == 4) {
                    JB.Delay_show_Bitmap("hero_attack_5.png",view_image,10,get_sd_path);
                    hero_attack_animation(time, view_image);
                    hero_attack_view_counter += 1;
                }else if (hero_attack_view_counter == 5) {
                    JB.Delay_show_Bitmap("hero_attack_6.png",view_image,1,get_sd_path);
                    hero_attack_animation(time, view_image);
                    hero_attack_view_counter += 1;
                }else if (hero_attack_view_counter == 6) {
                    hero_attack_view_counter = 0;
                    view_image.setVisibility(View.INVISIBLE);
                }

            }
        };
        new Thread(new Runnable() {
            public void run() {
                try {

                    Thread.sleep(time);

                    Message msg = Message.obtain();
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }


}


