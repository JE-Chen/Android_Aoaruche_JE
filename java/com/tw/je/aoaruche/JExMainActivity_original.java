/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan. 
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna. 
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus. 
 * Vestibulum commodo. Ut rhoncus gravida arcu. 
 */

package com.tw.je.aoaruche;

import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
//import static com.google.ads.AdRequest.LOGTAG;
import static com.tw.je.aoaruche.JExBackpack_layout.Equipment;
import static com.tw.je.aoaruche.JExFight_Gesture.Fight_Array;
import static com.tw.je.aoaruche.JExFight_Gesture.Fight_int;
import static com.tw.je.aoaruche.JExFight_Gesture.Monster_Hp;
import static com.tw.je.aoaruche.JExFight_Gesture.attack_counter;
import static com.tw.je.aoaruche.JExFight_Gesture.have_next_monster;
import static com.tw.je.aoaruche.JExFight_Gesture.hp_lv;
import static com.tw.je.aoaruche.JExFight_Gesture.monster_attack_value;
import static com.tw.je.aoaruche.JExFight_Gesture.return_total;
import static com.tw.je.aoaruche.aoaruche_Title_voice.start_mp;
import static com.tw.je.aoaruche.aoaruche_voice_server.mp;
import static com.tw.je.aoaruche.aoaruche_voice_server_2.mp1;
import static com.tw.je.aoaruche.aoaruche_voice_server_3.mp2;

//import com.firebase.client.Firebase;
//import com.google.firebase.auth.FirebaseAuth;

public class JExMainActivity_original extends AppCompatActivity implements View.OnClickListener, GestureOverlayView.OnGesturePerformedListener, View.OnTouchListener, View.OnLongClickListener {
    //變數宣告區


    //是否第一次載入
    public static  long first_open=0;
    //版本號
    public static long version = 1;
    //kill program ?
    public static long kill_all = 0;
    //scroll state
    public static long scroll_state = 0;
    //hero survive ?
    public static long hero_survive = 1;
    // what_voice_service
    public static long what_voice_service = 0;
    //In fight
    public static long int_fight = 0;
    //回合
    public static long skill_turn=0;
    //英雄血量
    public static long hero_hp = JExFight_Gesture.hero_hp;
    //播放動畫?
    public static long in_animation = 0;
    //在哪個關卡?
    public static long stage = 1;
    //判斷back鍵該做的事
    public static long back_value = 1;
    //已經開執行緒了?
    public static long open_run = 0;
    //開啟是否可怪物攻擊的執行緒判斷
    public static long can_runnable = 1;
    //暫存計時器數值
    public static long Runnable_time = 0;
    //在哪個執行緒
    public static int in_what_Runnable = 1;
    //是否從暫停回歸?
    public static long back_of_stop = 0;
    // scroll useing
    public static int scrollview_y = 0;
    //scroll
    public static long scrollview_is_click = 0;
    //現在戰鬥執行續啟用中?
    public static long is_fight_runable = 0;
    //現在使用寵物
    public static long now_pet = 0;
    //技能可否使用計數
    public static long now_skill = 0;
    //
    public static long do_you_ten_second = 0;
    public static long do_you_pause_or_stop = 0;
    //背景切換
    public static long background_value = 0;
    //顯示
    public static long show_long = 0;
    public static String show = "";
    /**
     * 路徑區
     */
    public static String File_rute = "/data/data/com.tw.je.aoaruche/files/";
    public static String File_name = "new_player";
    public static String in_path="/data/data/com.tw.je.aoaruche/files/";
    public static File new_Player = new File(File_rute + File_name);
    public static long has_new_player = 0;
    public static long has_new_player_counter = 0;
    public static File get_sd_path= null;
    /**
     * 結束
     */
    //AES加密
    public  static int A    = 9; //      J
    public  static int A_1  = 4; //     E
    public  static int A_2  = 23;//     x
    public static int  A_3  = 2; //     C
    public  static int A_4  = 7; //     h
    public  static int A_5  = 4; //     e
    public  static int A_6  = 13;//     n
    public  static int A_7  = 26;//     _
    public  static int A_8  = 15;//     p
    public  static int A_9  = 0;//       a
    public  static int A_10 = 18;//     s
    public  static int A_11 = 18;//     s
    public  static int A_12 = 21;//    w
    public  static int A_13 = 0;//       a
    public  static int A_14 = 16;//      r
    public  static int A_15 = 3;//       d



    //載入手勢庫
    public static GestureLibrary Gesture_library;
    // 手勢讀取view
    GestureOverlayView gesture_view;
    //用來判斷是否載入完成的數字
    public static int load_int;
    //串流空間
    public static final int READ_BLOCK_SIZE = 10000;
    //存檔檔名
    public static String save_story = "memory";
    public static String skill_memory = "skill_memory";
    //戰鬥陣列
    public static String[] Fight_using_Array;
    //特別boss
    public static long special_boss = 0;
    //可以施放技能?
    public static long can_skill = 1;
    //哪個技能
    public static long what_skill = 0;
    //英雄保護
    public static long hero_protect = 0;
    //吸血攻擊
    public static long hero_healing = 0;
    //額外攻擊
    public static long hero_double = 0;
    //場次
    public static long how_much_stage = 0;
    //Firebase路徑
    public static String file_path = "https://api-project-96430662.firebaseio.com//";
    public static String file_path_what_version = "https://api-project-96430662.firebaseio.com/version";
    //哪個英雄
    public static String what_hero = "Luo";
//怪物攻擊動畫
    public static long monster_attack_view_counter=0;
    //角色攻擊動畫
    public static long hero_attack_view_counter=0;
    /**
     * 故事介面區
     */
    ImageView story_magic_circle;
    /**
     * 特殊能力區
     */
    ScrollView skill_scrollview;
    public static ImageButton skill_button;

    ImageView skill_magic_circle;
    TextView up_dodge_lv_text,
            up_miracle_of_live_lv_text,
            up_critical_lv_text,
            up_money_skill_lv_text,
            up_healing_lv_text,
            up_exercise_lv_text,
            up_great_mage_lv_text;
    TextView show_how_many_skill_points;

    ImageButton up_dodge_lv_button,
            up_miracle_of_live_lv_button,
            up_critical_lv_button,
            up_money_skill_lv_button,
            up_healing_lv_button,
            up_exercise_lv_button,
            up_great_mage_lv_button;

    //end
    //升級 area la
    //可以滑動
    TextView can_scroll_text;
    //how many coin
    TextView have_how_many_coin;
    // Attack level need cash
    TextView up_hp_lv_need_cash_text;
    // HP level need cash
    TextView up_magic_power_need_cash_text;
    // now attack
    TextView up_there_hero_attack;
    // now hp
    TextView up_there_hero_hp;
    //顯示關卡進度
    TextView schedule_text;
    //maker scorllview
    ScrollView maker_scroview;
    //戰鬥用區
    //monster attack
    ImageView monster_attack_view;
    //動畫用 imageview
    ImageView fight_animation;
    //章節用Scrollview
    ScrollView choice_chapter;
    //升級用Scrollview
    ScrollView up_choice_scrollview;
    //interface''s ScrollView
   public static  ScrollView interface_choice_scroview;
    //戰鬥結束後tip_scroview
    ScrollView tip_scroview;
    //tip_text
    TextView tip_text;
    //開啟網頁
    public static ImageButton open_game_site;
    //分享遊戲
  public static   ImageButton share_game_button;
   //背包
   public static ImageButton backpack_button;
    //背包scroll
    ScrollView backpack_scroll;
    //英雄技能
    public static ImageButton hero_skill_button;
    ScrollView magic_skill_scroll;
    ImageButton lucius_magic_skill;
    ImageButton start_skill_button;
    /**
     * 特殊關卡用區
     */
    public static ImageButton special_stage_button;
    ImageView special_stage_magic_circle;
    ScrollView special_stage_scroll;
    ImageButton nothingness_green_button;
    ScrollView special_stage_nothingness_green_scroll;
    //nothingness_green
    TextView show_nothingness_green_text;
    ImageButton start_nothingness_green_fight;
    //nothingness_green end
    /**
     * 升級用區 索引
     * 1:升級生命
     * 2:升級攻擊
     * 3:升級介面
     * 4:升級介面動畫用view
     */
    ImageButton up_hp_lv;
    ImageButton up_magic_power;

    ImageView up_interface_magic_circle;

    //  物件宣告區
    /**
     * 初始介面用圖片按鈕
     * 1 :  開始按鈕
     * 2 : 繼續按鈕
     * 3:登入按鈕
     * 4: 登入確認按鈕
     */
    ImageButton start_button, continue_button;
    /**
     * 1: 介面 Imageview
     * 2:戰鬥按鈕 升級按鈕
     * 3:章節1按鈕
     * 4:戰鬥介面
     * 5:戰鬥動畫view
     */

     //血條
    ProgressBar hp_bar;
    //怪物血條
    ProgressBar monster_hp_bar;
    //時間條
    ProgressBar time_bar;
   public static ImageView user_interface, user_interface2, user_interface3, user_interface4;
    public static ImageView user_interface_magic_circle;
    public static ImageButton user_interface_fight, user_interface_up_level;
    ImageButton fight_choice_chapter_one, fight_choice_chapter_2;
    ScrollView chapter_scroview_2;
    TextView schedule_text_2;
    // 章節1-1~5 選擇按鈕
    ImageButton story_chapter1_1, story_chapter1_2, story_chapter1_3, story_chapter1_4, story_chapter1_5;
    //1-6~10
    ImageButton story_chapter1_6, story_chapter1_7, story_chapter1_8, story_chapter1_9, story_chapter1_10;
    //1-11 ~ 1-15
    ImageButton story_chapter1_11, story_chapter1_12, story_chapter1_13, story_chapter1_14, story_chapter1_15;
    //1-16 ~ 1-20
    ImageButton story_chapter1_16, story_chapter1_17, story_chapter1_18, story_chapter1_19, story_chapter1_20;
    //1-21 ~ 1-25
    ImageButton story_chapter1_21, story_chapter1_22, story_chapter1_23, story_chapter1_24, story_chapter1_25;
    //1-26 ~ 1-30
    ImageButton story_chapter1_26, story_chapter1_27, story_chapter1_28, story_chapter1_29, story_chapter1_30;
    //2-1 ~ 2-5
    ImageButton story_chapter2_1, story_chapter2_2, story_chapter2_3, story_chapter2_4, story_chapter2_5;
    //2-6~2-10
    ImageButton story_chapter2_6, story_chapter2_7, story_chapter2_8, story_chapter2_9, story_chapter2_10;
    // 顯示該畫啥的圖片
    ImageView draw_imageview;
    //角色血量
    TextView hero_hp_show;
    //怪物圖片
    ImageView monster_view;
    //怪物血量
    TextView monster_show_hp;
    //pause button
    ImageButton pause_button;
    //share button
  public static   ImageButton open_fbpage_button;
    //製作者按鈕
    public static ImageButton maker_button;
    //share page
    public static ImageButton share_page_button;
    //studio button
    public static ImageButton studio_button;
    /**
     * 1: 初始讀取封印外部 (初始載入效果)
     * 2: 初始讀取封印內部 (初始載入效果)
     */
  public static    ImageView sealoutside, sealinside;
    /**
     * 1: 初始劇情觀看 (前情提要)
     */
    ScrollView story_scroview;
    /**
     * 1: 初始載入用 Textview
     * 2:Scroview 內的文字
     */
    public static  TextView Loading_text, original_story_text;
//更新甚麼?
    public static ImageButton  update_button;
    ScrollView update_scroll_view;
    //返回按鈕
    ImageButton back_to_main_button;
    //選擇關卡 scroview
    ScrollView chapter_scroview;
    //Toast物件
    public static Toast show_Toast;
    //技能類別
    JExSkill J_S = new JExSkill();
    //  戰鬥類別
    JExFight_Gesture J_Fight = new JExFight_Gesture();
    // 動畫類型物件類別 JA
    JExAnimation JA = new JExAnimation();
    // 功能類型物件類別 JF
    JExFunction JF = new JExFunction();
    //  JE Firebase 資料庫
    JExFirebase JE_Firebase = new JExFirebase();
    // AES加密類
    JExAES JE_AES = new JExAES();
    //File 管理類
    JExFile J_File = new JExFile();
    //解壓縮類
    JExUnZip J_UnZip= new JExUnZip();
    //網路資料類
    JExInternet_Data J_I_D = new JExInternet_Data();
    //點陣圖類
    JExBitmap JB = new JExBitmap();
    //背包類
    JE_Backpack J_Backpack = new JE_Backpack();
    /**
     * 1: 聆聽者
     * 2:帳戶
     * 3:帳戶ID
     * 4:登入用文字輸入
     */
    //  public FirebaseAuth.AuthStateListener authListener;
    // public FirebaseAuth auth;
    // public String userUID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_original);
        get_sd_path=getExternalFilesDir(null);
        J_Fight.set_get_sd_path(get_sd_path.getPath());
        Log.d("Loading", "Start");
        //載入Firebase 客戶端
        //  Firebase.setAndroidContext(this);
        //  Firebase myFirebaseRef = new Firebase(file_path);
        //顯示載入的文字
        Loading_text =  findViewById(R.id.Loading_text);
        //初始介面旋轉的封印
        sealoutside =  findViewById(R.id.sealoutside);
        sealinside =  findViewById(R.id.sealinside);
        /**
         * 1~3 選擇畫面用 interface
         */
        user_interface_magic_circle =  findViewById(R.id.user_interface_magic_circle);
        user_interface =  findViewById(R.id.user_interface);
        user_interface2 =  findViewById(R.id.user_interface2);
        user_interface3 =  findViewById(R.id.user_interface3);
        user_interface4 =  findViewById(R.id.user_interface4);
        user_interface_fight =  findViewById(R.id.user_interface_fight);
        user_interface_up_level =  findViewById(R.id.user_interface_up_level);
        start_button =  findViewById(R.id.start_button);
        get_sd_path = getExternalFilesDir(null);
        // 載入手勢庫
        Gesture_library = GestureLibraries.fromRawResource(JExMainActivity_original.this, R.raw.gestures);
        Gesture_library.load();
        gesture_view = (GestureOverlayView) findViewById(R.id.gesture_view);
        gesture_view.addOnGesturePerformedListener(this);


        //正式部分 查找ID\

        /**
         *Object la
         */
//更新甚麼?
        update_button=  findViewById(R.id.update_button);
        update_scroll_view= findViewById(R.id.update_scroll_view);
        //英雄技能
        hero_skill_button =  findViewById(R.id.hero_skill_button);
        magic_skill_scroll =  findViewById(R.id.magic_skill_scroll);
        lucius_magic_skill =  findViewById(R.id.lucius_magic_skill);
        start_skill_button =  findViewById(R.id.start_skill_button);
//背包
        backpack_button = findViewById(R.id.backpack_button);



        //分享遊戲
        share_game_button =  findViewById(R.id.share_game_button);
        //開啟網頁
        open_game_site =  findViewById(R.id.open_game_site);
        /**
         * 特殊關卡用la
         */
        nothingness_green_button =  findViewById(R.id.nothingness_green_button);
        special_stage_scroll =  findViewById(R.id.special_stage_scroll);
        special_stage_button =  findViewById(R.id.special_stage_button);

        special_stage_magic_circle =  findViewById(R.id.special_stage_magic_circle);
        special_stage_nothingness_green_scroll =  findViewById(R.id.special_stage_nothingness_green_scroll);
        //nothingness_green
        show_nothingness_green_text =  findViewById(R.id.show_nothingness_green_text);
        start_nothingness_green_fight = findViewById(R.id.start_nothingness_green_fight);

        /**
         * 故事介面la
         */

        story_magic_circle =  findViewById(R.id.story_magic_circle);
        //特殊能力區;
        skill_button =  findViewById(R.id.skill_button);
        skill_scrollview =  findViewById(R.id.skill_scrollview);

        skill_magic_circle =  findViewById(R.id.skill_magic_circle);
        up_dodge_lv_text =  findViewById(R.id.up_dodge_lv_text);
        up_miracle_of_live_lv_text =  findViewById(R.id.up_miracle_of_live_lv_text);
        up_critical_lv_text = findViewById(R.id.up_critical_lv_text);
        up_money_skill_lv_text =  findViewById(R.id.up_money_skill_lv_text);
        up_healing_lv_text = findViewById(R.id.up_healing_lv_text);
        up_exercise_lv_text =  findViewById(R.id.up_exercise_lv_text);
        up_great_mage_lv_text =  findViewById(R.id.up_great_mage_lv_text);
        up_dodge_lv_button =  findViewById(R.id.up_dodge_lv_button);
        up_miracle_of_live_lv_button =  findViewById(R.id.up_miracle_of_live_lv_button);
        up_critical_lv_button =  findViewById(R.id.up_critical_lv_button);
        up_money_skill_lv_button =  findViewById(R.id.up_money_skill_lv_button);
        up_healing_lv_button =  findViewById(R.id.up_healing_lv_button);
        up_exercise_lv_button =  findViewById(R.id.up_exercise_lv_button);
        up_great_mage_lv_button =  findViewById(R.id.up_great_mage_lv_button);
        show_how_many_skill_points =  findViewById(R.id.show_how_many_skill_points);
        //戰鬥介面


        //升級介面
        up_interface_magic_circle =  findViewById(R.id.up_interface_magic_circle);


        //studio
        studio_button =  findViewById(R.id.studio_button);
        //attack animation
        monster_attack_view = findViewById(R.id.monster_attack_view);
        //share button
        share_page_button = findViewById(R.id.share_page_button);
        open_fbpage_button =  findViewById(R.id.open_fbpage_button);
        //pause button
        pause_button =  findViewById(R.id.pause_button);
        //制作者按鈕
        maker_button =  findViewById(R.id.maker_button);
        //可以滑動
        can_scroll_text = findViewById(R.id.can_scroll_text);
        //how many coin
        have_how_many_coin =  findViewById(R.id.have_how_many_coin);
        //now attack
        up_there_hero_attack = findViewById(R.id.up_there_hero_attack);
        //now hp
        up_there_hero_hp = findViewById(R.id.up_there_hero_hp);
        // Attack level need cash
        up_hp_lv_need_cash_text =  findViewById(R.id.up_hp_lv_need_cash_text);
        // HP level need cash
        up_magic_power_need_cash_text = findViewById(R.id.up_magic_power_need_cash_text);
        //chpater use
        choice_chapter =  findViewById(R.id.choice_chapter);
        //升級按鈕
        up_choice_scrollview =  findViewById(R.id.up_choice_scrollview);
        up_hp_lv =  findViewById(R.id.up_hp_lv);
        up_magic_power =  findViewById(R.id.up_magic_power);
        /**
         *  over
         */
        //interface_choice_scroview
        interface_choice_scroview =  findViewById(R.id.interface_choice_scroview);
        //tip scroview
        tip_scroview =  findViewById(R.id.tip_scroview);
        //tip_text
        tip_text =  findViewById(R.id.tip_text);
        //schedule_text
        schedule_text =  findViewById(R.id.schedule_text);
        schedule_text_2 =  findViewById(R.id.schedule_text_2);

        //章節用按鈕
        chapter_scroview_2 = findViewById(R.id.chapter_scroview_2);
        fight_choice_chapter_one =  findViewById(R.id.fight_choice_chapter_one);
        fight_choice_chapter_2 =  findViewById(R.id.fight_choice_chapter_2);
        story_chapter1_1 =  findViewById(R.id.story_chapter1_1);
        story_chapter1_2 =  findViewById(R.id.story_chapter1_2);
        story_chapter1_3 =  findViewById(R.id.story_chapter1_3);
        story_chapter1_4 =  findViewById(R.id.story_chapter1_4);
        story_chapter1_5 =  findViewById(R.id.story_chapter1_5);
        story_chapter1_6 =  findViewById(R.id.story_chapter1_6);
        story_chapter1_7 =  findViewById(R.id.story_chapter1_7);
        story_chapter1_8 =  findViewById(R.id.story_chapter1_8);
        story_chapter1_9 =  findViewById(R.id.story_chapter1_9);
        story_chapter1_10 =  findViewById(R.id.story_chapter1_10);
        story_chapter1_11 =  findViewById(R.id.story_chapter1_11);
        story_chapter1_12 =  findViewById(R.id.story_chapter1_12);
        story_chapter1_13 =  findViewById(R.id.story_chapter1_13);
        story_chapter1_14 =  findViewById(R.id.story_chapter1_14);
        story_chapter1_15 =  findViewById(R.id.story_chapter1_15);
        story_chapter1_16 =  findViewById(R.id.story_chapter1_16);
        story_chapter1_17 =  findViewById(R.id.story_chapter1_17);
        story_chapter1_18 =  findViewById(R.id.story_chapter1_18);
        story_chapter1_19 =  findViewById(R.id.story_chapter1_19);
        story_chapter1_20 =  findViewById(R.id.story_chapter1_20);
        story_chapter1_21 =  findViewById(R.id.story_chapter1_21);
        story_chapter1_22 =  findViewById(R.id.story_chapter1_22);
        story_chapter1_23 =  findViewById(R.id.story_chapter1_23);
        story_chapter1_24 =  findViewById(R.id.story_chapter1_24);
        story_chapter1_25 =  findViewById(R.id.story_chapter1_25);
        story_chapter1_26 =  findViewById(R.id.story_chapter1_26);
        story_chapter1_27 =  findViewById(R.id.story_chapter1_27);
        story_chapter1_28 =  findViewById(R.id.story_chapter1_28);
        story_chapter1_29 =  findViewById(R.id.story_chapter1_29);
        story_chapter1_30 =  findViewById(R.id.story_chapter1_30);
        story_chapter2_1 =  findViewById(R.id.story_chapter2_1);
        story_chapter2_2 =  findViewById(R.id.story_chapter2_2);
        story_chapter2_3 =  findViewById(R.id.story_chapter2_3);
        story_chapter2_4 =  findViewById(R.id.story_chapter2_4);
        story_chapter2_5 =  findViewById(R.id.story_chapter2_5);
        story_chapter2_6 =  findViewById(R.id.story_chapter2_6);
        story_chapter2_7 =  findViewById(R.id.story_chapter2_7);
        story_chapter2_8 =  findViewById(R.id.story_chapter2_8);
        story_chapter2_9 =  findViewById(R.id.story_chapter2_9);
        story_chapter2_10 =  findViewById(R.id.story_chapter2_10);
        //這2個測試用
        //login_email=(EditText) findViewById(R.id.login_email);
        //login_passward=(EditText) findViewById(R.id.login_passward);
        //返回用按鈕
        back_to_main_button =  findViewById(R.id.back_to_main_button);
//scrollview 內的文字
        original_story_text =  findViewById(R.id.original_story_text);


        //故是用scrollview
        story_scroview =(ScrollView) findViewById(R.id.story_scroview);
//顯示該畫啥的imageview
        draw_imageview =  findViewById(R.id.draw_imageview);
//怪物圖片
        monster_view =  findViewById(R.id.monster_view);
//選擇關卡用scroview
        chapter_scroview =  findViewById(R.id.chapter_scroview);
        //怪物血量
        monster_show_hp =  findViewById(R.id.monster_show_hp);
//角色血量
        hero_hp_show =  findViewById(R.id.hero_hp_show);
//動畫用 戰鬥
        fight_animation =  findViewById(R.id.fight_animation);
//maker
        maker_scroview =  findViewById(R.id.maker_scroview);

        //血條
        hp_bar= findViewById(R.id.hp_bar);
        monster_hp_bar= findViewById(R.id.monster_hp_bar);
        time_bar= findViewById(R.id.time_bar);
        continue_button =  findViewById(R.id.continue_button);

        //設置聆聽者


        /**
         * 特殊關卡
         */
        special_stage_button.setOnClickListener(this);
        start_nothingness_green_fight.setOnClickListener(this);
        nothingness_green_button.setOnClickListener(this);
        monster_view.setOnClickListener(this);
        /**
         * 章節按鈕
         */
//英雄技能
        lucius_magic_skill.setOnClickListener(this);
        hero_skill_button.setOnClickListener(this);
        lucius_magic_skill.setOnLongClickListener(this);
        start_skill_button.setOnClickListener(this);

        //分享遊戲
        share_game_button.setOnClickListener(this);
//開啟網頁
        open_game_site.setOnClickListener(this);
        //chpater
        //背包
        backpack_button.setOnClickListener(this);

        special_stage_scroll.setOnTouchListener(this);
        special_stage_nothingness_green_scroll.setOnTouchListener(this);
        skill_scrollview.setOnTouchListener(this);
        interface_choice_scroview.setOnTouchListener(this);
        story_scroview.setOnTouchListener(this);
        choice_chapter.setOnTouchListener(this);
        chapter_scroview.setOnTouchListener(this);
        up_choice_scrollview.setOnTouchListener(this);
        tip_scroview.setOnTouchListener(this);
        maker_scroview.setOnTouchListener(this);
        update_scroll_view.setOnTouchListener(this);
        fight_choice_chapter_2.setOnClickListener(this);
        story_chapter1_1.setOnClickListener(this);
        story_chapter1_2.setOnClickListener(this);
        story_chapter1_3.setOnClickListener(this);
        story_chapter1_4.setOnClickListener(this);
        story_chapter1_5.setOnClickListener(this);
        story_chapter1_6.setOnClickListener(this);
        story_chapter1_7.setOnClickListener(this);
        story_chapter1_8.setOnClickListener(this);
        story_chapter1_9.setOnClickListener(this);
        story_chapter1_10.setOnClickListener(this);
        story_chapter1_11.setOnClickListener(this);
        story_chapter1_12.setOnClickListener(this);
        story_chapter1_13.setOnClickListener(this);
        story_chapter1_14.setOnClickListener(this);
        story_chapter1_15.setOnClickListener(this);
        story_chapter1_16.setOnClickListener(this);
        story_chapter1_17.setOnClickListener(this);
        story_chapter1_18.setOnClickListener(this);
        story_chapter1_19.setOnClickListener(this);
        story_chapter1_20.setOnClickListener(this);
        story_chapter1_21.setOnClickListener(this);
        story_chapter1_22.setOnClickListener(this);
        story_chapter1_23.setOnClickListener(this);
        story_chapter1_24.setOnClickListener(this);
        story_chapter1_25.setOnClickListener(this);
        story_chapter1_26.setOnClickListener(this);
        story_chapter1_27.setOnClickListener(this);
        story_chapter1_28.setOnClickListener(this);
        story_chapter1_29.setOnClickListener(this);
        story_chapter1_30.setOnClickListener(this);
        story_chapter2_1.setOnClickListener(this);
        story_chapter2_2.setOnClickListener(this);
        story_chapter2_3.setOnClickListener(this);
        story_chapter2_4.setOnClickListener(this);
        story_chapter2_5.setOnClickListener(this);
        story_chapter2_6.setOnClickListener(this);
        story_chapter2_7.setOnClickListener(this);
        story_chapter2_8.setOnClickListener(this);
        story_chapter2_9.setOnClickListener(this);
        story_chapter2_10.setOnClickListener(this);

        //end
        /**
         * 特殊能力用
         */
        // 特殊能力
        up_dodge_lv_button.setOnClickListener(this);
        up_miracle_of_live_lv_button.setOnClickListener(this);
        up_critical_lv_button.setOnClickListener(this);
        up_money_skill_lv_button.setOnClickListener(this);
        up_healing_lv_button.setOnClickListener(this);
        up_exercise_lv_button.setOnClickListener(this);
        up_great_mage_lv_button.setOnClickListener(this);

        //更新說明
        update_button.setOnClickListener(this);


        /**
         * 儲存區end
         */
        //studio
        studio_button.setOnClickListener(this);
        //pause button
        pause_button.setOnClickListener(this);
        //制作者按鈕
        maker_button.setOnClickListener(this);
        up_magic_power.setOnClickListener(this);
        up_hp_lv.setOnClickListener(this);
        back_to_main_button.setOnClickListener(this);
        skill_button.setOnClickListener(this);
        gesture_view.setOnTouchListener(this);
        fight_choice_chapter_one.setOnClickListener(this);
        continue_button.setOnClickListener(this);
        start_button.setOnClickListener(this);
        user_interface_fight.setOnClickListener(this);
        user_interface_up_level.setOnClickListener(this);
        open_fbpage_button.setOnClickListener(this);
        share_page_button.setOnClickListener(this);




        //動畫部分end
        Log.d("Loading", "Complete");
        Log.d("成功讀取_onCreate", "app_onCreate");
        //載入完成 開啟音樂服務

        scroll_state = 0;



        //紀錄取得
        read_memory_json(save_story);
        msave(save_story);
        read_memory_json(skill_memory);
        msave(skill_memory);
        read_memory_json(Equipment);

        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

        int largeMemoryClass = activityManager.getLargeMemoryClass();
        int memoryClass = activityManager.getMemoryClass();

        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);

        Log.d("Memory", "largeMemoryClass = " + largeMemoryClass);
        Log.d("Memory", "memoryClass = " + memoryClass);

        //網路監控 如果未連線的話，mNetworkInfo會等於null
if(first_open==0) {
    boolean net_state = false;
    boolean Check_File_music = true;
    boolean Check_File_picture = true;

    int get = 0;
    int gget = 0;

    File zip_music_inside = new File(in_path + "music.zip");
    File zip_picture_inside = new File(in_path + "picture.zip");
    File zip_music_outside = new File(get_sd_path.getPath() + "music.zip");
    File zip_picture_outside = new File(get_sd_path.getPath() + "picture.zip");

    for (int check = 0; check < JExInternet_Data.check_music.length; check++) {
        Check_File_music = J_File.search_in_or_out_side(in_path, JExInternet_Data.check_music[check], get_sd_path);
        if (!Check_File_music) {
            get += 1;
        }
    }
    if (get > 0) {
        Check_File_music = false;
    }
    for (int check = 0; check < JExInternet_Data.chech_picture.length; check++) {
        Check_File_picture = J_File.search_in_or_out_side(in_path, JExInternet_Data.chech_picture[check], get_sd_path);
        if (!Check_File_picture) {
            gget += 1;
        }
    }
    if (gget > 0) {
        Check_File_picture = false;
    }
    ConnectivityManager mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
    //如果未連線的話，mNetworkInfo會等於null
    if (mNetworkInfo != null) {

        //網路是否已連線(true or false)
        if (mNetworkInfo.isConnected()) {

            //網路是否故障有問題
            if (!mNetworkInfo.isFailover()) {

                //網路是否可使用
                if (mNetworkInfo.isAvailable()) {
                    net_state = true;
                }

            }
        }
        //網路連線方式名稱(WIFI or mobile)
        mNetworkInfo.getTypeName();
        //網路連線狀態
        mNetworkInfo.getState();


        //網路是否已連接or連線中
        mNetworkInfo.isConnectedOrConnecting();

        //網路是否在漫遊模式
        mNetworkInfo.isRoaming();
    }

    if (net_state == false) {
        check_net();
    } else if (net_state == true) {
        JE_Check_File(10);
    }

      first_open=1;  }else if(first_open==1){
    JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
    JB.Delay_show_Bitmap("user_interface_fight.png",user_interface_fight,10,get_sd_path);
    JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
    JB.Delay_show_Bitmap("special_stage_button.png",special_stage_button,10,get_sd_path);
    JB.Delay_show_Bitmap("studio_button.png",studio_button,10,get_sd_path);
    JB.Delay_show_Bitmap("update_what.png",update_button,10,get_sd_path);
    JB.Delay_show_Bitmap("user_interface_up_level.png",user_interface_up_level,10,get_sd_path);
    JB.Delay_show_Bitmap("backpack_button_.png",backpack_button,10,get_sd_path);
    JB.Delay_show_Bitmap("hero_skill_button.png",hero_skill_button,10,get_sd_path);
    JB.Delay_show_Bitmap("skill_button.png",skill_button,10,get_sd_path);
    JB.Delay_show_Bitmap("maker.png",maker_button,10,get_sd_path);
    JB.Delay_show_Bitmap("share_game_button.png",share_game_button,10,get_sd_path);
    JB.Delay_show_Bitmap("open_fbpage_button.png",open_fbpage_button,10,get_sd_path);
    JB.Delay_show_Bitmap("share_page_button.png",share_page_button,10,get_sd_path);
    JB.Delay_show_Bitmap("open_game_site.png",open_game_site,10,get_sd_path);
    JF.JExDelay_Clean_Image(sealinside,0);
    JF.JExDelay_Clean_Image(sealoutside,0);
    JF.JExDelay_disappear_TextView(Loading_text,0);
    JF.JExDelay_show_ImageView(user_interface, 0);
    JF.JExDelay_show_ImageView(user_interface_magic_circle, 0);
    JA.JE_rotate_right(user_interface_magic_circle, 15000);
    JF.JExDelay_show_scroview(interface_choice_scroview, 0);
      }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.start_skill_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                if(open_run==0&&can_runnable==1&&is_fight_runable==0) {
                    if (skill_turn >= 1) {
                        JF.showToast(JExMainActivity_original.this, getString(R.string.skill_cold) + String.valueOf(skill_turn));
                    }
                    if (J_Fight.return_magic_skill_value() == 0) {
                        JF.showToast(JExMainActivity_original.this, getString(R.string.you_not_have_choice));
                    } else if (J_Fight.return_magic_skill_value() == 1 && can_skill == 1 && skill_turn == 0) {
                        JB.Delay_show_Bitmap("start_skill_button_hid.png",start_skill_button,10,get_sd_path);
                        what_skill = 1;
                        skill_turn = 6;
                    } else if (J_Fight.return_magic_skill_value() == 2 && can_skill == 1 && skill_turn == 0) {
                        JB.Delay_show_Bitmap("start_skill_button_hid.png",start_skill_button,10,get_sd_path);
                        what_skill = 2;
                        hero_double = 2;
                        skill_turn = 6;
                    } else if (J_Fight.return_magic_skill_value() == 3 && can_skill == 1 && skill_turn == 0) {
                        JB.Delay_show_Bitmap("start_skill_button_hid.png",start_skill_button,10,get_sd_path);
                        what_skill = 3;
                        hero_healing = 1;
                        skill_turn = 6;
                    } else if (J_Fight.return_magic_skill_value() == 4 && can_skill == 1 && skill_turn == 0) {
                        JB.Delay_show_Bitmap("start_skill_button_hid.png",start_skill_button,10,get_sd_path);
                        hero_protect = 1;
                        skill_turn = 6;
                    }
                }else {
                    JF.show_toast_top(JExMainActivity_original.this,getString(R.string.cnat_skill));
                }
                break;
            }


            //開始按鈕 出現故事前敘Scrollview 跟 繼續按鈕
            case R.id.start_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                JB.Delay_show_Bitmap("user_interface_magic_circle.png",story_magic_circle,10,get_sd_path);
                JB.Delay_show_Bitmap("continue_button.png",continue_button,10,get_sd_path);
                JA.clean_and_stop_am_Gone(sealoutside);
                JA.clean_and_stop_am_Gone(sealinside);
                JA.clean_and_stop_am(Loading_text);
                JA.stop_am(user_interface);
                JA.clean_and_stop_am_Gone(user_interface2);
                JA.clean_and_stop_am_Gone(user_interface3);
                JA.clean_and_stop_am_Gone(user_interface4);
                JF.JExDelay_show_ImageView(story_magic_circle, 0);
                JA.JE_rotate_right(story_magic_circle, 3000);
                JF.JExDelay_show_scroview(story_scroview, 0);
                JF.JExDelay_show_Textview(original_story_text, 0);
                JF.JExDelay_disappear_Imagebutton(start_button, 0);
                JF.JExDelay_show_Imagebutton(continue_button, 0);

                System.gc();
                JF.Scroll_thread_mini(20, story_scroview);
                Intent stop_title_voice = new Intent(JExMainActivity_original.this,aoaruche_Title_voice.class);
                JExMainActivity_original.this.stopService(stop_title_voice);
                start_mp.stop();
                Intent start_music = new Intent(JExMainActivity_original.this,aoaruche_voice_server.class);
                JExMainActivity_original.this.startService(start_music);
                what_voice_service=1;
                break;
            }

            //繼續按鈕載入準備前介面
            case R.id.continue_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                boolean outside_new_player = false;
                if (has_new_player == 0) {

                    boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);

                    if(sdCardExist) {
                        File outside_new_player_sd_path_get = getExternalFilesDir(null);
                        String outside_new_player_sd_path = (outside_new_player_sd_path_get.getPath() + "new_player");
                        File outside_new_player_exist = new File(outside_new_player_sd_path);
                        if(outside_new_player_exist.exists()) {
                            outside_new_player=true;
                        }else {
                            outside_new_player=false;
                        }
                    }

                    if (!new_Player.exists()||outside_new_player==true) {
                        Log.d("New_Player", "is_new");
                        msave_new_player("new_player", "not_new_player");
                        has_new_player = 1;
                        original_story_text.setText(getString(R.string.studio_text) + "\n\n" + getString(R.string.dont_look_studio_text));
                        JF.JExDelay_disappear_Scrollview(up_choice_scrollview, 0);
                        continue_button.setEnabled(false);
                        JF.JExDelay_change_text(original_story_text, 10000, getString(R.string.studio_text));
                        JF.JExDelay_Enable_ImageButton(continue_button, 10000);
                    } else if (new_Player.exists()||outside_new_player==true) {
                        JB.Delay_show_Bitmap("user_interface_fight.png",user_interface_fight,10,get_sd_path);
                        JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
                        JB.Delay_show_Bitmap("special_stage_button.png",special_stage_button,10,get_sd_path);
                        JB.Delay_show_Bitmap("studio_button.png",studio_button,10,get_sd_path);
                        JB.Delay_show_Bitmap("update_what.png",update_button,10,get_sd_path);
                        JB.Delay_show_Bitmap("user_interface_up_level.png",user_interface_up_level,10,get_sd_path);
                        JB.Delay_show_Bitmap("backpack_button_.png",backpack_button,10,get_sd_path);
                        JB.Delay_show_Bitmap("hero_skill_button.png",hero_skill_button,10,get_sd_path);
                        JB.Delay_show_Bitmap("skill_button.png",skill_button,10,get_sd_path);
                        JB.Delay_show_Bitmap("maker.png",maker_button,10,get_sd_path);
                        JB.Delay_show_Bitmap("share_game_button.png",share_game_button,10,get_sd_path);
                        JB.Delay_show_Bitmap("open_fbpage_button.png",open_fbpage_button,10,get_sd_path);
                        JB.Delay_show_Bitmap("share_page_button.png",share_page_button,10,get_sd_path);
                        JB.Delay_show_Bitmap("open_game_site.png",open_game_site,10,get_sd_path);
                        Log.d("New_Player", "not_new");
                        JA.clean_and_stop_am(story_magic_circle);
                        JF.JExDelay_Clean_Image(story_magic_circle, 0);
                        JF.JExDelay_disappear_Scrollview(story_scroview, 0);
                        JF.JExDelay_disappear_Imagebutton(continue_button, 0);
                        JF.JExDelay_show_ImageView(user_interface, 0);
                        JF.JExDelay_show_ImageView(user_interface_magic_circle, 0);
                        JA.JE_rotate_right(user_interface_magic_circle, 15000);
                        JF.JExDelay_show_scroview(interface_choice_scroview, 0);
                        JF.JExDelay_show_Imagebutton(user_interface_fight, 0);
                        JF.JExDelay_show_Imagebutton(user_interface_up_level, 0);

                        scroll_state = 0;
                    }
                } else if (has_new_player == 1) {
                    JA.clean_and_stop_am(story_magic_circle);
                    JF.JExDelay_Clean_Image(story_magic_circle, 0);
                    JF.JExDelay_disappear_Scrollview(story_scroview, 0);
                    JF.JExDelay_disappear_Imagebutton(continue_button, 0);


                    new_player_fight(0, 0);

                }
                break;
            }
            //背包按鈕
            case  R.id.backpack_button:{
                if (JF.isFastDoubleClick()) {
                    return;
                }
                    Intent back_pack_intent = new Intent(JExMainActivity_original.this, JExBackpack_layout.class);
                    JExMainActivity_original.this.startActivity(back_pack_intent);
                JA.clean_and_stop_am(user_interface_magic_circle);
                JF.JExDelay_Clean_Image(user_interface_magic_circle, 0);
                JF.JExDelay_disappear_Scrollview(interface_choice_scroview, 0);
                System_GC_Main();


                break;
            }
            //練習關卡按鈕
            case R.id.studio_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                JA.clean_and_stop_am(user_interface_magic_circle);
                JF.JExDelay_Clean_Image(user_interface_magic_circle, 0);
                JF.JExDelay_disappear_Scrollview(interface_choice_scroview, 0);
                System_GC_Main();
                has_new_player = 1;
                has_new_player_counter = 0;

                new_player_fight(0, 0);
                break;
            }
            //制作者按鈕
            case R.id.maker_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                System_GC_Main();
                JB.Delay_show_Bitmap("story_interface_magic_circle.png",story_magic_circle,10,get_sd_path);
                JB.Delay_show_Bitmap("backbutton.png",back_to_main_button,10,get_sd_path);
                JF.JExDelay_show_ImageView(story_magic_circle, 0);
                JA.JE_rotate_right(story_magic_circle, 3000);
                JA.clean_and_stop_am(user_interface_magic_circle);
                JF.JExDelay_Clean_Image(user_interface_magic_circle, 0);
                JF.JExDelay_disappear_Scrollview(interface_choice_scroview, 0);
                JF.Scroll_thread_mini(20, maker_scroview);
                maker_button.setVisibility(View.INVISIBLE);
                maker_scroview.setVisibility(View.VISIBLE);
                JF. Scroll_thread_mini(150,maker_scroview);
                back_value = 3;
                back_to_main_button.setVisibility(View.VISIBLE);
                break;
            }

            case R.id.open_fbpage_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                Uri uri = Uri.parse("https://www.facebook.com/%E8%89%BE%E5%A5%A7%E5%9F%83%E7%9B%A7%E5%88%87-JE-Chen-136773287049780/");
                Intent open_fbpage = new Intent(Intent.ACTION_VIEW, uri);
                JExMainActivity_original.this.startActivity(open_fbpage);
                break;
            }
            case R.id.share_page_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                String share_string = "https://www.facebook.com/%E8%89%BE%E5%A5%A7%E5%9F%83%E7%9B%A7%E5%88%87-JE-Chen-136773287049780/";
                share.putExtra(Intent.EXTRA_TEXT, share_string);
                try {
                    if (JF.have_this_file(JExMainActivity_original.this,"com.facebook.katana") == true) {
                        share.setPackage("com.facebook.katana");
                        JExMainActivity_original.this.startActivity(share);
                    } else if (JF.have_this_file(JExMainActivity_original.this,"com.facebook.katana") == false) {
                        JF.showToast(JExMainActivity_original.this, getString(R.string.not_fb));
                    }
                } catch (ActivityNotFoundException fuck) {
                    Log.d("ASSHOLE_share_to_fb", getString(R.string.not_fb));
                }

                break;
            }
            case R.id.open_game_site: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                Uri uri = Uri.parse("https://sites.google.com/view/aoaruche-tw-je/%E9%A6%96%E9%A0%81");
                Intent open_game_site = new Intent(Intent.ACTION_VIEW, uri);
                JExMainActivity_original.this.startActivity(open_game_site);
                break;
            }
            case R.id.share_game_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                String share_string = "https://play.google.com/store/apps/details?id=com.tw.je.aoaruche";
                share.putExtra(Intent.EXTRA_TEXT, share_string);
                try {
                    if (JF.have_this_file(JExMainActivity_original.this,"com.facebook.katana") == true) {
                        share.setPackage("com.facebook.katana");
                        JExMainActivity_original.this.startActivity(share);
                    } else if (JF.have_this_file(JExMainActivity_original.this,"com.facebook.katana") == false) {
                        JF.showToast(JExMainActivity_original.this, getString(R.string.not_fb));
                    }
                } catch (ActivityNotFoundException fuck) {
                    Log.d("ASSHOLE_share_to_fb", getString(R.string.not_fb));
                }

                break;
            }



            case R.id.hero_skill_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                System_GC_Main();
                JB.Delay_show_Bitmap("backbutton.png",back_to_main_button,10,get_sd_path);
                JB.Delay_show_Bitmap("lucius_choice_skill_button.png",lucius_magic_skill,10,get_sd_path);
                JF.JExDelay_show_scroview(magic_skill_scroll, 0);
                JA.clean_and_stop_am(user_interface_magic_circle);
                JF.JExDelay_Clean_Image(user_interface_magic_circle, 0);
                JF.JExDelay_disappear_Scrollview(interface_choice_scroview, 0);
                back_value = 9;
                back_to_main_button.setVisibility(View.VISIBLE);

                break;
            }


            case R.id.lucius_magic_skill: {

                new AlertDialog.Builder(JExMainActivity_original.this)
                        .setCancelable(false)
                        .setTitle(getString(R.string.you_sure_choice_skill_title))
                        .setMessage(getString(R.string.you_sure_choice_skill_messenger))
                        .setIcon(R.drawable.logoaoaruche)
                        .setPositiveButton(getString(R.string.determine),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //第一個 選擇 p n
                                        new AlertDialog.Builder(JExMainActivity_original.this)
                                                .setCancelable(false)
                                                .setTitle(getString(R.string.choice_skill_title))
                                                .setMessage(getString(R.string.choice_skill_messenger_1))
                                                .setIcon(R.drawable.logoaoaruche)
                                                .setPositiveButton(getString(R.string.choice_skill_post_1),
                                                        new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                //第二個 選擇 p n
                                                                new AlertDialog.Builder(JExMainActivity_original.this)
                                                                        .setCancelable(false)
                                                                        .setTitle(getString(R.string.choice_skill_title))
                                                                        .setMessage(getString(R.string.choice_skill_messenger_2_p))
                                                                        .setIcon(R.drawable.logoaoaruche)
                                                                        .setPositiveButton(getString(R.string.choice_skill_post_2_p),
                                                                                new DialogInterface.OnClickListener() {
                                                                                    @Override
                                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                                        new AlertDialog.Builder(JExMainActivity_original.this)
                                                                                                .setCancelable(false)
                                                                                                .setTitle(getString(R.string.choice_skill_title_End))
                                                                                                .setMessage(getString(R.string.choice_skill_reverse_destiny))
                                                                                                .setIcon(R.drawable.logoaoaruche)
                                                                                                .setPositiveButton(getString(R.string.determine),
                                                                                                        new DialogInterface.OnClickListener() {
                                                                                                            @Override
                                                                                                            public void onClick(DialogInterface dialog, int which) {
                                                                                                                J_Fight.set_magic_skill_value(1);
                                                                                                            }
                                                                                                        }).show();


                                                                                    }
                                                                                })
                                                                        .setNeutralButton(getString(R.string.choice_skill_nega_2_p), new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialog, int which) {
                                                                                new AlertDialog.Builder(JExMainActivity_original.this)
                                                                                        .setCancelable(false)
                                                                                        .setTitle(getString(R.string.choice_skill_title_End))
                                                                                        .setMessage(getString(R.string.choice_skill_destiny))
                                                                                        .setIcon(R.drawable.logoaoaruche)
                                                                                        .setPositiveButton(getString(R.string.determine),
                                                                                                new DialogInterface.OnClickListener() {
                                                                                                    @Override
                                                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                                                        J_Fight.set_magic_skill_value(2);
                                                                                                    }
                                                                                                }).show();

                                                                            }
                                                                        })
                                                                        .show();

                                                            }
                                                        })
                                                .setNeutralButton(getString(R.string.choice_skill_nega_1), new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        //第二個 選擇 p n
                                                        new AlertDialog.Builder(JExMainActivity_original.this)
                                                                .setCancelable(false)
                                                                .setTitle(getString(R.string.choice_skill_title))
                                                                .setMessage(getString(R.string.choice_skill_messenger_2_n))
                                                                .setIcon(R.drawable.logoaoaruche)
                                                                .setPositiveButton(getString(R.string.choice_skill_post_2_n),
                                                                        new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialog, int which) {
                                                                                new AlertDialog.Builder(JExMainActivity_original.this)
                                                                                        .setCancelable(false)
                                                                                        .setTitle(getString(R.string.choice_skill_title_End))
                                                                                        .setMessage(getString(R.string.choice_skill_ordinary_no_ordinary))
                                                                                        .setIcon(R.drawable.logoaoaruche)
                                                                                        .setPositiveButton(getString(R.string.determine),
                                                                                                new DialogInterface.OnClickListener() {
                                                                                                    @Override
                                                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                                                        J_Fight.set_magic_skill_value(3);
                                                                                                    }
                                                                                                }).show();

                                                                            }
                                                                        })
                                                                .setNeutralButton(getString(R.string.choice_skill_nega_2_n), new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                        new AlertDialog.Builder(JExMainActivity_original.this)
                                                                                .setCancelable(false)
                                                                                .setTitle(getString(R.string.choice_skill_title_End))
                                                                                .setMessage(getString(R.string.choice_skill_ordinary))
                                                                                .setIcon(R.drawable.logoaoaruche)
                                                                                .setPositiveButton(getString(R.string.determine),
                                                                                        new DialogInterface.OnClickListener() {
                                                                                            @Override
                                                                                            public void onClick(DialogInterface dialog, int which) {
                                                                                                J_Fight.set_magic_skill_value(4);
                                                                                            }
                                                                                        }).show();
                                                                    }
                                                                })
                                                                .show();
                                                    }
                                                })
                                                .show();

                                    }
                                })
                        .setNeutralButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
                msave(save_story);
                read_memory_json(save_story);
                msave(skill_memory);
                read_memory_json(skill_memory);

                break;
            }


//選擇章節的戰鬥按鈕
            case R.id.user_interface_fight: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                back_value = 1;
                System_GC_Main();
                JB.Delay_show_Bitmap("story_one_luo.png",fight_choice_chapter_one,10,get_sd_path);
                JB.Delay_show_Bitmap("lucius_skill_button.png",fight_choice_chapter_2,10,get_sd_path);
                JB.Delay_show_Bitmap("backbutton.png",back_to_main_button,10,get_sd_path);
                JF.JExDelay_disappear_Scrollview(interface_choice_scroview, 0);
                JF.JExDelay_show_Imagebutton(fight_choice_chapter_one, 0);
                JF.JExDelay_show_Imagebutton(fight_choice_chapter_2, 0);
                JF.JExDelay_show_scroview(choice_chapter, 0);
                back_to_main_button.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.user_interface_up_level: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                System_GC_Main();
                JB.Delay_show_Bitmap("special_attack.png",up_interface_magic_circle,10,get_sd_path);
                JB.Delay_show_Bitmap("up_magic_power.png",up_magic_power,10,get_sd_path);
                JB.Delay_show_Bitmap("up_hp_lv.png",up_hp_lv,10,get_sd_path);
                JB.Delay_show_Bitmap("backbutton.png",back_to_main_button,10,get_sd_path);
                JF.JExDelay_show_ImageView(up_interface_magic_circle, 0);
                JA.JE_rotate_right(up_interface_magic_circle, 3000);
                back_value = 1;
                JA.clean_and_stop_am(user_interface_magic_circle);
                JF.JExDelay_Clean_Image(user_interface_magic_circle, 0);
                JF.JExDelay_disappear_Scrollview(interface_choice_scroview, 0);
                JF.JExDelay_show_scroview(up_choice_scrollview, 0);
                back_to_main_button.setVisibility(View.VISIBLE);
                can_scroll_text.setText(getString(R.string.can_scroll));
                have_how_many_coin.setText(getString(R.string.how_many_coin) + String.valueOf(J_Fight.return_coin()) + getString(R.string.coin));
                up_magic_power_need_cash_text.setText(getString(R.string.Up_nedd_cash) + String.valueOf(J_Fight.return_need_cash_to_up_magic_power()));
                up_hp_lv_need_cash_text.setText(getString(R.string.Up_nedd_cash) + String.valueOf(J_Fight.return_need_cash_to_up_hp_lv()));
                up_there_hero_hp.setText(getString(R.string.Hero_Now_HP) + String.valueOf(J_Fight.return_hero_hp()));
                up_there_hero_attack.setText(getString(R.string.Hero_Now_Attack) + String.valueOf(J_Fight.return_hero_attack()));
                break;
            }
            case R.id.up_magic_power: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                String can_up_magic_power = J_Fight.can_up_magic_power(J_Fight.return_need_cash_to_up_magic_power());
                if (can_up_magic_power.equals("you_can")) {
                    Log.d("up_magic_power", "success");
                    have_how_many_coin.setText(getString(R.string.how_many_coin) + String.valueOf(J_Fight.return_coin()) + getString(R.string.coin));
                    up_magic_power_need_cash_text.setText(getString(R.string.Up_nedd_cash) + String.valueOf(J_Fight.return_need_cash_to_up_magic_power()));
                    up_there_hero_attack.setText(getString(R.string.Hero_Now_Attack) + String.valueOf(J_Fight.return_hero_attack()));
                    msave(save_story);
                    read_memory_json(save_story);
                }
                if (can_up_magic_power.equals("you_can't")) {
                    Log.d("up_magic_power", "fail");
                    msave(save_story);
                    read_memory_json(save_story);
                }
                if (can_up_magic_power.equals("default_error")) {
                    Log.d("up_magic_power", "error");
                    msave(save_story);
                    read_memory_json(save_story);
                }
                break;
            }
            case R.id.up_hp_lv: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                String can_up_hp = J_Fight.can_up_hp_lv(J_Fight.return_need_cash_to_up_hp_lv());
                if (can_up_hp.equals("you_can")) {
                    Log.d("up_hp_lv", "success");
                    have_how_many_coin.setText(getString(R.string.how_many_coin) + String.valueOf(J_Fight.return_coin()) + getString(R.string.coin));
                    up_hp_lv_need_cash_text.setText(getString(R.string.Up_nedd_cash) + String.valueOf(J_Fight.return_need_cash_to_up_hp_lv()));
                    up_there_hero_hp.setText(getString(R.string.Hero_Now_HP) + String.valueOf(J_Fight.return_hero_hp()));
                    msave(save_story);
                    read_memory_json(save_story);
                }
                if (can_up_hp.equals("you_can't")) {
                    Log.d("up_hp_lv", "fail");
                    msave(save_story);
                    read_memory_json(save_story);
                }
                if (can_up_hp.equals("default_error")) {
                    Log.d("up_hp_lv", "error");
                    msave(save_story);
                    read_memory_json(save_story);
                }
                break;
            }

            case R.id.fight_choice_chapter_one: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                back_value = 1;
                JF.JExDelay_disappear_Imagebutton(fight_choice_chapter_one, 0);
                show_chapter_button(J_Fight.return_what_region_save());
                JF.JExDelay_show_scroview(chapter_scroview, 0);
                JF.JExDelay_disappear_Scrollview(choice_chapter, 0);
                JA.clean_and_stop_am(user_interface_magic_circle);
                JF.JExDelay_Clean_Image(user_interface_magic_circle, 0);
                schedule_text.setText(getString(R.string.schedule_text) + J_Fight.return_schedule());

                break;
            }

            case R.id.fight_choice_chapter_2: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                back_value = 1;
                JF.JExDelay_disappear_Imagebutton(fight_choice_chapter_2, 0);
                JF.JExDelay_show_scroview(chapter_scroview_2, 0);
                JF.JExDelay_disappear_Scrollview(choice_chapter, 0);
                show_chapter_button(J_Fight.return_what_region_save());
                JA.clean_and_stop_am(user_interface_magic_circle);
                JF.JExDelay_Clean_Image(user_interface_magic_circle, 0);
                schedule_text_2.setText(getString(R.string.schedule_text) + J_Fight.return_schedule());


                break;
            }
            case R.id.update_button:{
                if (JF.isFastDoubleClick()) {
                    return;
                }
                JB.Delay_show_Bitmap("backbutton.png",back_to_main_button,10,get_sd_path);
                JB.Delay_show_Bitmap("background_1.png",user_interface,10,get_sd_path);
                JB.Delay_show_Bitmap("background_2.png",user_interface2,10,get_sd_path);
                JB.Delay_show_Bitmap("background_3.png",user_interface3,10,get_sd_path);
                JB.Delay_show_Bitmap("background_4.png",user_interface4,10,get_sd_path);

                back_value = 1;
                JF.JExDelay_disappear_Scrollview(interface_choice_scroview, 0);
                JF.JExDelay_show_scroview(update_scroll_view, 0);
                JA.clean_and_stop_am(user_interface_magic_circle);
                user_interface.setVisibility(View.VISIBLE);
                user_interface2.setVisibility(View.VISIBLE);
                user_interface3.setVisibility(View.VISIBLE);
                user_interface4.setVisibility(View.VISIBLE);
                JA.JE_fade(user_interface, 250);
                JA.JE_fade(user_interface2, 500);
                JA.JE_fade(user_interface3, 750);
                JA.JE_fade(user_interface4, 1000);
                back_to_main_button.setVisibility(View.VISIBLE);
                JF.Scroll_thread_mini(20,update_scroll_view);

                break;
            }
            case R.id.skill_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                JB.Delay_show_Bitmap("up_dodge_lv_button.png",up_dodge_lv_button,10,get_sd_path);
                JB.Delay_show_Bitmap("up_miracle_of_live_lv_button.png",up_miracle_of_live_lv_button,10,get_sd_path);
                JB.Delay_show_Bitmap("up_critical_lv_button.png",up_critical_lv_button,10,get_sd_path);
                JB.Delay_show_Bitmap("up_money_skill_lv_button.png",up_money_skill_lv_button,10,get_sd_path);
                JB.Delay_show_Bitmap("up_healing_lv_button.png",up_healing_lv_button,10,get_sd_path);
                JB.Delay_show_Bitmap("up_exercise_lv_button.png",up_exercise_lv_button,10,get_sd_path);
                JB.Delay_show_Bitmap("up_great_mage_lv_button.png",up_great_mage_lv_button,10,get_sd_path);
                JB.Delay_show_Bitmap("wood_magic_circle.png",skill_magic_circle,10,get_sd_path);
                JB.Delay_show_Bitmap("backbutton.png",back_to_main_button,10,get_sd_path);
                J_S.set_up_money_skill_lv();
                J_S.set_up_critical_lv();
                J_S.set_up_dodge_lv();
                J_S.set_up_great_mage_lv();
                J_S.set_up_healing_lv();
                J_S.set_up_miracle_of_live_lv();
                J_S.set_up_exercise_lv();
                show_how_many_skill_points.setText(getString(R.string.show_how_many_skill_points) + "\n" + J_Fight.return_skill_points());
                JF.JExDelay_show_scroview(skill_scrollview, 0);
                JF.JExDelay_show_Imagebutton(back_to_main_button, 0);
                JF.JExDelay_disappear_Scrollview(interface_choice_scroview, 0);
                up_dodge_lv_text.setText(getString(R.string.up_dodge_lv_text) + "\n" + J_S.return_up_dodge_lv() + "\n"
                        + getString(R.string.up_dodge_lv_text_2) + "\n" + (J_S.return_dodge_lv() - 1));
                up_miracle_of_live_lv_text.setText(getString(R.string.up_miracle_of_live_lv_text) + "\n" + J_S.return_up_miracle_of_live_lv() + "\n"
                        + getString(R.string.up_miracle_of_live_lv_text_2) + "\n" + (J_S.return_miracle_of_live_lv() - 1));
                up_critical_lv_text.setText(getString(R.string.up_critical_lv_text) + "\n" + J_S.return_up_critical_lv() + "\n"
                        + getString(R.string.up_critical_lv_text_2) + "\n" + (J_S.return_critical_lv() - 1));
                up_money_skill_lv_text.setText(getString(R.string.up_money_skill_lv_text) + "\n" + J_S.return_up_money_skill_lv() + "\n"
                        + getString(R.string.up_money_skill_lv_text_2) + "\n" + (J_S.return_money_skill_lv() - 1));
                up_healing_lv_text.setText(getString(R.string.up_healing_lv_text) + "\n" + J_S.return_up_healing_lv() + "\n"
                        + getString(R.string.up_healing_lv_text_2) + "\n" + (J_S.return_healing_lv() - 1));
                up_exercise_lv_text.setText(getString(R.string.up_exercise_lv_text) + "\n" + J_S.return_up_exercise_lv() + "\n"
                        + getString(R.string.up_exercise_lv_text_2) + "\n" + (J_S.return_exercise_lv() - 1));
                up_great_mage_lv_text.setText(getString(R.string.up_great_mage_lv_text) + "\n" + J_S.return_up_great_mage_lv() + "\n"
                        + getString(R.string.up_great_mage_lv_text_2) + "\n" + (J_S.return_great_mage_lv() - 1));
                JA.clean_and_stop_am(user_interface_magic_circle);
                JF.JExDelay_Clean_Image(user_interface_magic_circle, 0);


                JF.JExDelay_show_ImageView(skill_magic_circle, 0);
                JA.JE_rotate_right(skill_magic_circle, 10000);
                back_value = 5;
                msave(save_story);
                read_memory_json(save_story);
                msave(skill_memory);
                read_memory_json(skill_memory);

                break;
            }
            case R.id.up_dodge_lv_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                show_how_many_skill_points.setText(getString(R.string.show_how_many_skill_points) + "\n" + J_Fight.return_skill_points());
                String can_question = J_S.add_dodge_lv(J_Fight.return_skill_points());
                up_dodge_lv_text.setText(getString(R.string.up_dodge_lv_text) + "\n" + J_S.return_up_dodge_lv() + "\n"
                        + getString(R.string.up_dodge_lv_text_2) + "\n" + (J_S.return_dodge_lv() - 1));
                msave(skill_memory);
                read_memory_json(skill_memory);
                msave(save_story);
                read_memory_json(save_story);
                break;
            }
            case R.id.up_miracle_of_live_lv_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                show_how_many_skill_points.setText(getString(R.string.show_how_many_skill_points) + "\n" + J_Fight.return_skill_points());
                String can_question = J_S.add_miracle_of_live_lv(J_Fight.return_skill_points());
                up_miracle_of_live_lv_text.setText(getString(R.string.up_miracle_of_live_lv_text) + "\n" + J_S.return_up_miracle_of_live_lv() + "\n"
                        + getString(R.string.up_miracle_of_live_lv_text_2) + "\n" + (J_S.return_miracle_of_live_lv() - 1));
                msave(skill_memory);
                read_memory_json(skill_memory);
                msave(save_story);
                read_memory_json(save_story);
                break;
            }
            case R.id.up_critical_lv_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                show_how_many_skill_points.setText(getString(R.string.show_how_many_skill_points) + "\n" + J_Fight.return_skill_points());
                String can_question = J_S.add_critical_lv(J_Fight.return_skill_points());
                up_critical_lv_text.setText(getString(R.string.up_critical_lv_text) + "\n" + J_S.return_up_critical_lv() + "\n"
                        + getString(R.string.up_critical_lv_text_2) + "\n" + (J_S.return_critical_lv() - 1));
                msave(skill_memory);
                read_memory_json(skill_memory);
                msave(save_story);
                read_memory_json(save_story);
                break;
            }
            case R.id.up_money_skill_lv_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                show_how_many_skill_points.setText(getString(R.string.show_how_many_skill_points) + "\n" + J_Fight.return_skill_points());
                String can_question = J_S.add_money_skill_lv(J_Fight.return_skill_points());
                up_money_skill_lv_text.setText(getString(R.string.up_money_skill_lv_text) + "\n" + J_S.return_up_money_skill_lv() + "\n"
                        + getString(R.string.up_money_skill_lv_text_2) + "\n" + (J_S.return_money_skill_lv() - 1));
                msave(skill_memory);
                read_memory_json(skill_memory);
                msave(save_story);
                read_memory_json(save_story);
                break;
            }
            case R.id.up_healing_lv_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                show_how_many_skill_points.setText(getString(R.string.show_how_many_skill_points) + "\n" + J_Fight.return_skill_points());
                String can_question = J_S.add_healing_lv(J_Fight.return_skill_points());
                up_healing_lv_text.setText(getString(R.string.up_healing_lv_text) + "\n" + J_S.return_up_healing_lv() + "\n"
                        + getString(R.string.up_healing_lv_text_2) + "\n" + (J_S.return_healing_lv() - 1));
                msave(skill_memory);
                read_memory_json(skill_memory);
                msave(save_story);
                read_memory_json(save_story);
                break;
            }
            case R.id.up_exercise_lv_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                show_how_many_skill_points.setText(getString(R.string.show_how_many_skill_points) + "\n" + J_Fight.return_skill_points());
                String can_question = J_S.add_exercise_lv(J_Fight.return_skill_points());
                up_exercise_lv_text.setText(getString(R.string.up_exercise_lv_text) + "\n" + J_S.return_up_exercise_lv() + "\n"
                        + getString(R.string.up_exercise_lv_text_2) + "\n" + (J_S.return_exercise_lv() - 1));
                msave(skill_memory);
                read_memory_json(skill_memory);
                msave(save_story);
                read_memory_json(save_story);
                show_how_many_skill_points.setText(getString(R.string.show_how_many_skill_points) + "\n" + J_Fight.return_skill_points());
                break;
            }
            case R.id.up_great_mage_lv_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                show_how_many_skill_points.setText(getString(R.string.show_how_many_skill_points) + "\n" + J_Fight.return_skill_points());
                String can_question = J_S.add_great_mage_lv(J_Fight.return_skill_points());
                up_great_mage_lv_text.setText(getString(R.string.up_great_mage_lv_text) + "\n" + J_S.return_up_great_mage_lv() + "\n"
                        + getString(R.string.up_great_mage_lv_text_2) + "\n" + (J_S.return_great_mage_lv() - 1));
                msave(skill_memory);
                read_memory_json(skill_memory);
                msave(save_story);
                read_memory_json(save_story);
                show_how_many_skill_points.setText(getString(R.string.show_how_many_skill_points) + "\n" + J_Fight.return_skill_points());
                break;
            }

            case R.id.special_stage_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                JB.Delay_show_Bitmap("backbutton.png",back_to_main_button,10,get_sd_path);
                JB.Delay_show_Bitmap("special_stage_magic_circle.png",special_stage_magic_circle,10,get_sd_path);
                JB.Delay_show_Bitmap("nothingness_green_button.png",nothingness_green_button,10,get_sd_path);
                JF.JExDelay_show_ImageView(special_stage_magic_circle, 0);
                JA.JE_rotate_right(special_stage_magic_circle, 3000);
                JA.clean_and_stop_am(user_interface_magic_circle);
                JF.JExDelay_Clean_Image(user_interface_magic_circle, 0);
                JF.JExDelay_disappear_Scrollview(interface_choice_scroview, 0);
                JF.JExDelay_show_scroview(special_stage_scroll, 0);
                back_value = 2;
                back_to_main_button.setVisibility(View.VISIBLE);

                break;
            }
            case R.id.nothingness_green_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                JB.Delay_show_Bitmap("backbutton.png",back_to_main_button,10,get_sd_path);
                JB.Delay_show_Bitmap("start_special_stage.png",start_nothingness_green_fight,10,get_sd_path);
                JF.JExDelay_disappear_Scrollview(special_stage_scroll, 0);
                JF.JExDelay_show_scroview(special_stage_nothingness_green_scroll, 0);
                back_to_main_button.setVisibility(View.VISIBLE);
                back_value = 6;
                break;
            }

            case R.id.start_nothingness_green_fight: {
                JF.JExDelay_disappear_Scrollview(special_stage_nothingness_green_scroll, 0);
                JA.clean_and_stop_am(special_stage_magic_circle);
                JF.JExDelay_Clean_Image(special_stage_magic_circle, 0);
                JA.JE_rotate_left(monster_view, 15000);

                back_to_original(1000001, 0, 0, 5);

                break;
            }

            case R.id.back_to_main_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                back_event(back_value);
                break;
            }

            case R.id.pause_button: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                if(open_run==0&&can_runnable==1&&is_fight_runable==0) {

                    onPause();
                    new AlertDialog.Builder(JExMainActivity_original.this)
                            .setCancelable(false)
                            .setTitle(getString(R.string.leave_fight))
                            .setMessage(getString(R.string.click_to_leave) + "\n" + getString(R.string.hold_stop))
                            .setIcon(R.drawable.logoaoaruche)
                            .setPositiveButton(getString(R.string.determine),
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Log.d("離開戰鬥", "離開戰鬥");
                                            JB.Delay_show_Bitmap("backbutton.png",back_to_main_button,10,get_sd_path);

                                            has_new_player = 0;
                                            gesture_view.setVisibility(View.INVISIBLE);
                                            monster_show_hp.setVisibility(View.INVISIBLE);
                                            JA.clean_and_stop_am(monster_view);
                                            monster_view.setVisibility(View.INVISIBLE);
                                            draw_imageview.setVisibility(View.INVISIBLE);
                                            pause_button.setVisibility(View.INVISIBLE);
                                            JB.Delay_show_Bitmap("start_skill_button.png",start_skill_button,10,get_sd_path);
                                            start_skill_button.setVisibility(View.INVISIBLE);
                                            hero_hp_show.setText(R.string.Tactical_retreat);
                                            hero_hp_show.setTextSize(30);
                                            hp_bar.setVisibility(View.INVISIBLE);
                                            monster_hp_bar.setVisibility(View.INVISIBLE);
                                            time_bar.setVisibility(View.INVISIBLE);
                                            time_bar.setMax(10000);
                                            time_bar.setProgress(10000);
                                            J_Fight.set_hero_hp();
                                            JF.JExDelay_show_Imagebutton(back_to_main_button, 0);
                                            JA.clean_and_stop_am(fight_animation);
                                            special_boss = 0;
                                            Intent fail_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_fail_service.class);
                                            JExMainActivity_original.this.startService(fail_voice);
                                            J_Fight.reset_whos_monster();
                                            open_run = 0;
                                            can_runnable = 1;
                                            is_fight_runable = 0;
                                            hero_healing = 0;
                                            hero_protect = 0;
                                            hero_double = 0;
                                            int_fight = 0;
                                            what_skill = 0;
                                            back_value = 1;
                                            scroll_state = 0;
                                            now_skill = 0;
                                            skill_turn=0;
                                            how_much_stage += 1;
                                            onResume();
                                        }
                                    })
                            .setNeutralButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.d("繼續戰鬥", "繼續戰鬥");

                                    onResume();
                                }
                            })
                            .show();
                }else {

                }

                break;
            }


            case R.id.story_chapter1_1: {
                if (JF.isFastDoubleClick()) {
                    return;
                }
                new AlertDialog.Builder(JExMainActivity_original.this)
                        .setCancelable(false)
                        .setTitle(getString(R.string.tip))
                        .setMessage(getString(R.string.win_1_1_text))
                        .setIcon(R.drawable.logoaoaruche)
                        .setPositiveButton(getString(R.string.determine),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                        .show();


                back_to_original(1, 1, 1,  0);

                break;
            }

            case R.id.story_chapter1_2: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(2, 1, 1,  0);
                break;
            }
            case R.id.story_chapter1_3: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(3, 1, 1,  0);
                break;
            }
            case R.id.story_chapter1_4: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(4, 1, 2, 0);
                break;
            }
            case R.id.story_chapter1_5: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(5, 1, 2, 0);
                break;
            }
            case R.id.story_chapter1_6: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(6, 1, 2, 0);
                break;
            }
            case R.id.story_chapter1_7: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(7, 2, 2, 0);
                break;
            }
            case R.id.story_chapter1_8: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(8, 2, 2,  0);
                break;
            }
            case R.id.story_chapter1_9: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(9, 2, 2,  0);
                break;
            }
            case R.id.story_chapter1_10: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(10, 2, 0,  1);
                break;
            }
            case R.id.story_chapter1_11: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(11, 3, 1,  0);
                break;
            }
            case R.id.story_chapter1_12: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(12, 3, 1, 0);
                break;
            }
            case R.id.story_chapter1_13: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(13, 3, 1, 0);
                break;
            }
            case R.id.story_chapter1_14: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(14, 3, 1, 0);
                break;
            }
            case R.id.story_chapter1_15: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(15, 3, 1, 0);
                break;
            }
            case R.id.story_chapter1_16: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(16, 3, 2, 0);
                break;
            }
            case R.id.story_chapter1_17: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(17, 3, 2,  0);
                break;
            }
            case R.id.story_chapter1_18: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(18, 3, 2,  0);
                break;
            }
            case R.id.story_chapter1_19: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(19, 3, 3, 0);
                break;
            }
            case R.id.story_chapter1_20: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(20, 4, 0,  2);
                break;
            }
            case R.id.story_chapter1_21: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(21, 4, 3,  0);
                break;
            }
            case R.id.story_chapter1_22: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(22, 4, 3, 0);
                break;
            }
            case R.id.story_chapter1_23: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(23, 4, 3, 0);
                break;
            }
            case R.id.story_chapter1_24: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(24, 4, 3, 0);
                break;
            }
            case R.id.story_chapter1_25: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(25, 4, 3, 0);
                break;
            }
            case R.id.story_chapter1_26: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(26, 4, 4, 0);
                break;
            }
            case R.id.story_chapter1_27: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(27, 4, 4,  0);
                break;
            }
            case R.id.story_chapter1_28: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(28, 4, 4, 0);
                break;
            }
            case R.id.story_chapter1_29: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(29, 4, 5,  0);
                break;
            }
            case R.id.story_chapter1_30: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(30, 4, 1,  3);
                break;
            }
            case R.id.story_chapter2_1: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(31, 5, 1, 0);
                break;
            }
            case R.id.story_chapter2_2: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(32, 5, 1, 0);
                break;
            }
            case R.id.story_chapter2_3: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(33, 5, 2,  0);
                break;
            }
            case R.id.story_chapter2_4: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(34, 5, 2,  0);
                break;
            }
            case R.id.story_chapter2_5: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(35, 5, 3,  0);
                break;
            }
            case R.id.story_chapter2_6: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(36, 6, 2, 0);
                break;
            }
            case R.id.story_chapter2_7: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(37, 6, 2,  0);
                break;
            }
            case R.id.story_chapter2_8: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(38, 6, 2, 0);
                break;
            }
            case R.id.story_chapter2_9: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(39, 6, 3, 0);
                break;
            }
            case R.id.story_chapter2_10: {
                if (JF.isFastDoubleClick()) {
                    return;
                }

                back_to_original(40, 6, 0, 4);
                break;
            }

        }

    }


    public void boss_fight_music() {
        what_voice_service = 3;
        Intent stop1 = new Intent(JExMainActivity_original.this, aoaruche_voice_server.class);
        JExMainActivity_original.this. stopService(stop1);
        Intent stop2 = new Intent(JExMainActivity_original.this, aoaruche_voice_server_2.class);
        JExMainActivity_original.this. stopService(stop2);
        Intent start = new Intent(JExMainActivity_original.this, aoaruche_voice_server_3.class);
        JExMainActivity_original.this.startService(start);
    }

    public void fight_music() {
        what_voice_service = 2;
        Intent stop1 = new Intent(JExMainActivity_original.this, aoaruche_voice_server.class);
        JExMainActivity_original.this.stopService(stop1);
        Intent stop2 = new Intent(JExMainActivity_original.this, aoaruche_voice_server_3.class);
        JExMainActivity_original.this. stopService(stop2);
        Intent start = new Intent(JExMainActivity_original.this, aoaruche_voice_server_2.class);
        JExMainActivity_original.this.startService(start);
    }

    public void normal_music() {
        what_voice_service = 1;
        Intent stop1 = new Intent(JExMainActivity_original.this, aoaruche_voice_server_2.class);
        JExMainActivity_original.this. stopService(stop1);
        Intent stop2 = new Intent(JExMainActivity_original.this, aoaruche_voice_server_3.class);
        JExMainActivity_original.this.stopService(stop2);
        Intent start = new Intent(JExMainActivity_original.this, aoaruche_voice_server.class);
        JExMainActivity_original.this.startService(start);

    }

    public void back_event(long event) {
        if (event == 999999) {
            JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_fight.png",user_interface_fight,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
            JB.Delay_show_Bitmap("special_stage_button.png",special_stage_button,10,get_sd_path);
            JB.Delay_show_Bitmap("studio_button.png",studio_button,10,get_sd_path);
            JB.Delay_show_Bitmap("update_what.png",update_button,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_up_level.png",user_interface_up_level,10,get_sd_path);
            JB.Delay_show_Bitmap("backpack_button_.png",backpack_button,10,get_sd_path);
            JB.Delay_show_Bitmap("hero_skill_button.png",hero_skill_button,10,get_sd_path);
            JB.Delay_show_Bitmap("skill_button.png",skill_button,10,get_sd_path);
            JB.Delay_show_Bitmap("maker.png",maker_button,10,get_sd_path);
            JB.Delay_show_Bitmap("share_game_button.png",share_game_button,10,get_sd_path);
            JB.Delay_show_Bitmap("open_fbpage_button.png",open_fbpage_button,10,get_sd_path);
            JB.Delay_show_Bitmap("share_page_button.png",share_page_button,10,get_sd_path);
            JB.Delay_show_Bitmap("open_game_site.png",open_game_site,10,get_sd_path);
            JF.JExDelay_disappear_TextView(hero_hp_show, 0);
            JF.JExDelay_show_ImageView(user_interface, 0);
            JA.JE_rotate_right(user_interface_magic_circle, 15000);
            JF.JExDelay_show_ImageView(user_interface_magic_circle, 0);
            JF.JExDelay_show_scroview(interface_choice_scroview, 0);
            JF.JExDelay_disappear_Scrollview(story_scroview, 0);
            pause_button.setVisibility(View.INVISIBLE);
            how_much_stage += 1;
            start_skill_button.setVisibility(View.INVISIBLE);
            back_to_main_button.setVisibility(View.INVISIBLE);
            tip_scroview.setVisibility(View.INVISIBLE);
            all_scorll_rest();
            normal_music();
            int_fight = 0;
            scroll_state = 0;
            in_animation = 0;
            scrollview_is_click = 0;
         System.gc();

        } else if (event == 1) {
            JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_fight.png",user_interface_fight,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
            JB.Delay_show_Bitmap("special_stage_button.png",special_stage_button,10,get_sd_path);
            JB.Delay_show_Bitmap("studio_button.png",studio_button,10,get_sd_path);
            JB.Delay_show_Bitmap("update_what.png",update_button,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_up_level.png",user_interface_up_level,10,get_sd_path);
            JB.Delay_show_Bitmap("backpack_button_.png",backpack_button,10,get_sd_path);
            JB.Delay_show_Bitmap("hero_skill_button.png",hero_skill_button,10,get_sd_path);
            JB.Delay_show_Bitmap("skill_button.png",skill_button,10,get_sd_path);
            JB.Delay_show_Bitmap("maker.png",maker_button,10,get_sd_path);
            JB.Delay_show_Bitmap("share_game_button.png",share_game_button,10,get_sd_path);
            JB.Delay_show_Bitmap("open_fbpage_button.png",open_fbpage_button,10,get_sd_path);
            JB.Delay_show_Bitmap("share_page_button.png",share_page_button,10,get_sd_path);
            JB.Delay_show_Bitmap("open_game_site.png",open_game_site,10,get_sd_path);
            JA.clean_and_stop_am(user_interface);
            JA.clean_and_stop_am(user_interface2);
            JA.clean_and_stop_am(user_interface3);
            JA.clean_and_stop_am(user_interface4);
            JF.JExDelay_disappear_Scrollview(update_scroll_view, 0);
            JA.clean_and_stop_am(up_interface_magic_circle);
            JF.JExDelay_Clean_Image(up_interface_magic_circle, 0);
            JF.JExDelay_show_ImageView(user_interface, 0);
            JA.JE_rotate_right(user_interface_magic_circle, 15000);
            JF.JExDelay_show_ImageView(user_interface_magic_circle, 0);
            JF.JExDelay_disappear_Scrollview(up_choice_scrollview, 0);
            JF.JExDelay_disappear_Imagebutton(fight_choice_chapter_one, 0);
            JF.JExDelay_show_scroview(interface_choice_scroview, 0);
            JF.JExDelay_disappear_Scrollview(chapter_scroview, 0);
            JF.JExDelay_disappear_Scrollview(chapter_scroview_2, 0);
            JF.JExDelay_disappear_Scrollview(skill_scrollview, 0);
            JF.JExDelay_disappear_Scrollview(choice_chapter, 0);
            pause_button.setVisibility(View.INVISIBLE);
            start_skill_button.setVisibility(View.INVISIBLE);
            back_to_main_button.setVisibility(View.INVISIBLE);
            tip_scroview.setVisibility(View.INVISIBLE);
            hero_hp_show.setVisibility(View.INVISIBLE);
            all_scorll_rest();
            J_Fight.set_hero_hp();
            normal_music();
            int_fight = 0;
            scroll_state = 0;
            scrollview_is_click = 0;
            System.gc();

        } else if (event == 2) {
            JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_fight.png",user_interface_fight,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
            JB.Delay_show_Bitmap("special_stage_button.png",special_stage_button,10,get_sd_path);
            JB.Delay_show_Bitmap("studio_button.png",studio_button,10,get_sd_path);
            JB.Delay_show_Bitmap("update_what.png",update_button,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_up_level.png",user_interface_up_level,10,get_sd_path);
            JB.Delay_show_Bitmap("backpack_button_.png",backpack_button,10,get_sd_path);
            JB.Delay_show_Bitmap("hero_skill_button.png",hero_skill_button,10,get_sd_path);
            JB.Delay_show_Bitmap("skill_button.png",skill_button,10,get_sd_path);
            JB.Delay_show_Bitmap("maker.png",maker_button,10,get_sd_path);
            JB.Delay_show_Bitmap("share_game_button.png",share_game_button,10,get_sd_path);
            JB.Delay_show_Bitmap("open_fbpage_button.png",open_fbpage_button,10,get_sd_path);
            JB.Delay_show_Bitmap("share_page_button.png",share_page_button,10,get_sd_path);
            JB.Delay_show_Bitmap("open_game_site.png",open_game_site,10,get_sd_path);
            JA.clean_and_stop_am(special_stage_magic_circle);
            special_stage_magic_circle.setVisibility(View.INVISIBLE);
            JA.clean_and_stop_am(user_interface_magic_circle);
            JF.JExDelay_disappear_Scrollview(special_stage_scroll, 0);
            JF.JExDelay_show_scroview(interface_choice_scroview, 0);
            back_to_main_button.setVisibility(View.INVISIBLE);
            JF.JExDelay_show_ImageView(user_interface, 0);
            JF.JExDelay_show_ImageView(user_interface_magic_circle, 0);
            JA.JE_rotate_right(user_interface_magic_circle, 15000);
            all_scorll_rest();
            normal_music();
            scrollview_is_click = 0;
            System.gc();
        } else if (event == 3) {
            JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_fight.png",user_interface_fight,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
            JB.Delay_show_Bitmap("special_stage_button.png",special_stage_button,10,get_sd_path);
            JB.Delay_show_Bitmap("studio_button.png",studio_button,10,get_sd_path);
            JB.Delay_show_Bitmap("update_what.png",update_button,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_up_level.png",user_interface_up_level,10,get_sd_path);
            JB.Delay_show_Bitmap("backpack_button_.png",backpack_button,10,get_sd_path);
            JB.Delay_show_Bitmap("hero_skill_button.png",hero_skill_button,10,get_sd_path);
            JB.Delay_show_Bitmap("skill_button.png",skill_button,10,get_sd_path);
            JB.Delay_show_Bitmap("maker.png",maker_button,10,get_sd_path);
            JB.Delay_show_Bitmap("share_game_button.png",share_game_button,10,get_sd_path);
            JB.Delay_show_Bitmap("open_fbpage_button.png",open_fbpage_button,10,get_sd_path);
            JB.Delay_show_Bitmap("share_page_button.png",share_page_button,10,get_sd_path);
            JB.Delay_show_Bitmap("open_game_site.png",open_game_site,10,get_sd_path);
            JA.clean_and_stop_am(story_magic_circle);
            JF.JExDelay_Clean_Image(story_magic_circle, 0);
            user_interface.setVisibility(View.VISIBLE);
            maker_button.setVisibility(View.VISIBLE);
            JF.JExDelay_disappear_TextView(hero_hp_show, 0);
            JF.JExDelay_show_ImageView(user_interface, 0);
            JF.JExDelay_show_ImageView(user_interface_magic_circle, 0);
            JA.JE_rotate_right(user_interface_magic_circle, 15000);
            JF.JExDelay_disappear_Scrollview(maker_scroview, 0);
            JF.JExDelay_show_scroview(interface_choice_scroview, 0);
            back_to_main_button.setVisibility(View.INVISIBLE);
            all_scorll_rest();
            normal_music();
            int_fight = 0;
            scroll_state = 0;
            scrollview_is_click = 0;
            System.gc();
        } else if (event == 4) {
            JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_fight.png",user_interface_fight,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
            JB.Delay_show_Bitmap("special_stage_button.png",special_stage_button,10,get_sd_path);
            JB.Delay_show_Bitmap("studio_button.png",studio_button,10,get_sd_path);
            JB.Delay_show_Bitmap("update_what.png",update_button,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_up_level.png",user_interface_up_level,10,get_sd_path);
            JB.Delay_show_Bitmap("backpack_button_.png",backpack_button,10,get_sd_path);
            JB.Delay_show_Bitmap("hero_skill_button.png",hero_skill_button,10,get_sd_path);
            JB.Delay_show_Bitmap("skill_button.png",skill_button,10,get_sd_path);
            JB.Delay_show_Bitmap("maker.png",maker_button,10,get_sd_path);
            JB.Delay_show_Bitmap("share_game_button.png",share_game_button,10,get_sd_path);
            JB.Delay_show_Bitmap("open_fbpage_button.png",open_fbpage_button,10,get_sd_path);
            JB.Delay_show_Bitmap("share_page_button.png",share_page_button,10,get_sd_path);
            JB.Delay_show_Bitmap("open_game_site.png",open_game_site,10,get_sd_path);
            JA.clean_and_stop_am(story_magic_circle);
            JF.JExDelay_Clean_Image(story_magic_circle, 0);
            JF.JExDelay_show_scroview(interface_choice_scroview, 0);
            user_interface.setVisibility(View.VISIBLE);
            JF.JExDelay_show_ImageView(user_interface_magic_circle, 0);
            JA.JE_rotate_right(user_interface_magic_circle, 15000);
            back_to_main_button.setVisibility(View.INVISIBLE);
            all_scorll_rest();
            normal_music();
            scroll_state = 0;
            scrollview_is_click = 0;
            System.gc();
        } else if (event == 5) {
            JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_fight.png",user_interface_fight,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
            JB.Delay_show_Bitmap("special_stage_button.png",special_stage_button,10,get_sd_path);
            JB.Delay_show_Bitmap("studio_button.png",studio_button,10,get_sd_path);
            JB.Delay_show_Bitmap("update_what.png",update_button,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_up_level.png",user_interface_up_level,10,get_sd_path);
            JB.Delay_show_Bitmap("backpack_button_.png",backpack_button,10,get_sd_path);
            JB.Delay_show_Bitmap("hero_skill_button.png",hero_skill_button,10,get_sd_path);
            JB.Delay_show_Bitmap("skill_button.png",skill_button,10,get_sd_path);
            JB.Delay_show_Bitmap("maker.png",maker_button,10,get_sd_path);
            JB.Delay_show_Bitmap("share_game_button.png",share_game_button,10,get_sd_path);
            JB.Delay_show_Bitmap("open_fbpage_button.png",open_fbpage_button,10,get_sd_path);
            JB.Delay_show_Bitmap("share_page_button.png",share_page_button,10,get_sd_path);
            JB.Delay_show_Bitmap("open_game_site.png",open_game_site,10,get_sd_path);
            JA.clean_and_stop_am(skill_magic_circle);
            JF.JExDelay_Clean_Image(skill_magic_circle, 0);
            JF.JExDelay_disappear_Scrollview(skill_scrollview, 0);
            JF.JExDelay_show_scroview(interface_choice_scroview, 0);
            user_interface.setVisibility(View.VISIBLE);
            JF.JExDelay_show_ImageView(user_interface_magic_circle, 0);
            JA.JE_rotate_right(user_interface_magic_circle, 15000);
            back_to_main_button.setVisibility(View.INVISIBLE);
            all_scorll_rest();
            normal_music();
            scroll_state = 0;
            scrollview_is_click = 0;
     ; System.gc();
        } else if (event == 6) {
            JB.Delay_show_Bitmap("nothingness_green_button.png",nothingness_green_button,10,get_sd_path);
            JF.JExDelay_disappear_Scrollview(special_stage_nothingness_green_scroll, 0);
            JF.JExDelay_show_scroview(special_stage_scroll, 0);
            all_scorll_rest();
            normal_music();
            back_value = 2;
            scrollview_is_click = 0;
            System.gc();
        }else if (event == 9) {
            JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_fight.png",user_interface_fight,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
            JB.Delay_show_Bitmap("special_stage_button.png",special_stage_button,10,get_sd_path);
            JB.Delay_show_Bitmap("studio_button.png",studio_button,10,get_sd_path);
            JB.Delay_show_Bitmap("update_what.png",update_button,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_up_level.png",user_interface_up_level,10,get_sd_path);
            JB.Delay_show_Bitmap("backpack_button_.png",backpack_button,10,get_sd_path);
            JB.Delay_show_Bitmap("hero_skill_button.png",hero_skill_button,10,get_sd_path);
            JB.Delay_show_Bitmap("skill_button.png",skill_button,10,get_sd_path);
            JB.Delay_show_Bitmap("maker.png",maker_button,10,get_sd_path);
            JB.Delay_show_Bitmap("share_game_button.png",share_game_button,10,get_sd_path);
            JB.Delay_show_Bitmap("open_fbpage_button.png",open_fbpage_button,10,get_sd_path);
            JB.Delay_show_Bitmap("share_page_button.png",share_page_button,10,get_sd_path);
            JB.Delay_show_Bitmap("open_game_site.png",open_game_site,10,get_sd_path);
            JF.JExDelay_disappear_Scrollview(magic_skill_scroll, 0);
            JF.JExDelay_disappear_Imagebutton(back_to_main_button, 0);
            JF.JExDelay_show_scroview(interface_choice_scroview, 0);
            JF.JExDelay_show_ImageView(user_interface, 0);
            JF.JExDelay_show_ImageView(user_interface_magic_circle, 0);
            JA.JE_rotate_right(user_interface_magic_circle, 3000);
            all_scorll_rest();
            normal_music();
            scrollview_is_click = 0;
            System.gc();
        }

        scrollview_is_click = 0;
        all_scorll_rest();
        Intent stop_mp3 = new Intent(JExMainActivity_original.this, aoaruche_fight_victory_service.class);
        JExMainActivity_original.this.stopService(stop_mp3);
        Intent stop_mp4 = new Intent(JExMainActivity_original.this, aoaruche_fight_fail_service.class);
        JExMainActivity_original.this.stopService(stop_mp4);
        Intent stop_mp5 = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
        JExMainActivity_original.this.stopService(stop_mp5);
    }

    public void all_scorll_rest() {
        JF.scroll_reset(update_scroll_view, scrollview_y);
        JF.scroll_reset(special_stage_scroll, scrollview_y);
        JF.scroll_reset(special_stage_nothingness_green_scroll, scrollview_y);
        JF.scroll_reset(skill_scrollview, scrollview_y);
        JF.scroll_reset(interface_choice_scroview, scrollview_y);
        JF.scroll_reset(story_scroview, scrollview_y);
        JF.scroll_reset(choice_chapter, scrollview_y);
        JF.scroll_reset(chapter_scroview, scrollview_y);
        JF.scroll_reset(chapter_scroview_2, scrollview_y);
        JF.scroll_reset(up_choice_scrollview, scrollview_y);
        JF.scroll_reset(tip_scroview, scrollview_y);
        JF.scroll_reset(maker_scroview, scrollview_y);

        scrollview_y = 0;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {

            case R.id.gesture_view: {
                if (JF.isFastDoubleClick()) {
                    return true;
                }
                if (can_runnable == 1 && open_run == 0 && is_fight_runable == 0) {
                    can_runnable = 0;
                    open_run = 1;
                    is_fight_runable = 1;
                    Fight_Runnable(Runnable_time);
                    break;
                }
              /* if(can_runnable==0&&open_run==0){
                   can_runnable=1;
                   break;
               }*/
            }
            case R.id.update_scroll_view:{
                if (JF.isFastDoubleClick()) {
                    return false;
                }
                scrollview_is_click = 1;

                break;
            }


            case R.id.special_stage_scroll: {
                if (JF.isFastDoubleClick()) {
                    return false;
                }
                scrollview_is_click = 1;

                break;
            }
            case R.id.special_stage_nothingness_green_scroll: {
                if (JF.isFastDoubleClick()) {
                    return false;
                }
                scrollview_is_click = 1;

                break;
            }
            case R.id.skill_scrollview: {
                if (JF.isFastDoubleClick()) {
                    return false;
                }
                scrollview_is_click = 1;

                break;
            }
            case R.id.interface_choice_scroview: {
                if (JF.isFastDoubleClick()) {
                    return false;
                }
                scrollview_is_click = 1;

                break;
            }
            case R.id.story_scroview: {
                if (JF.isFastDoubleClick()) {
                    return false;
                }
                scrollview_is_click = 1;

                break;
            }
            case R.id.choice_chapter: {
                if (JF.isFastDoubleClick()) {
                    return false;
                }
                scrollview_is_click = 1;

                break;
            }
            case R.id.chapter_scroview: {
                if (JF.isFastDoubleClick()) {
                    return false;
                }
                scrollview_is_click = 1;

                break;
            }
            case R.id.up_choice_scrollview: {
                if (JF.isFastDoubleClick()) {
                    return false;
                }
                scrollview_is_click = 1;

                break;
            }
            case R.id.tip_scroview: {
                if (JF.isFastDoubleClick()) {
                    return false;
                }
                scrollview_is_click = 1;

                break;
            }
            case R.id.maker_scroview: {
                if (JF.isFastDoubleClick()) {
                    return false;
                }
                scrollview_is_click = 1;

                break;
            }
        }
        return false;
    }

    @Override
    public boolean onLongClick(View v) {

        switch (v.getId()) {

            case R.id.lucius_magic_skill: {
                if (JF.isFastDoubleClick()) {
                    return false;
                }
                if (J_Fight.return_magic_skill_value() == 0) {
                    JF.showToast(JExMainActivity_original.this, getString(R.string.not_choice_skill));
                } else if (J_Fight.return_magic_skill_value() == 1) {

                    new AlertDialog.Builder(JExMainActivity_original.this)
                            .setCancelable(false)
                            .setTitle(getString(R.string.choice_skill_title_End))
                            .setMessage(getString(R.string.choice_skill_reverse_destiny_Introduction))
                            .setIcon(R.drawable.logoaoaruche)
                            .setPositiveButton(getString(R.string.determine),
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    }).show();
                    break;
                } else if (J_Fight.return_magic_skill_value() == 2) {

                    new AlertDialog.Builder(JExMainActivity_original.this)
                            .setCancelable(false)
                            .setTitle(getString(R.string.choice_skill_title_End))
                            .setMessage(getString(R.string.choice_skill_destiny_Introduction))
                            .setIcon(R.drawable.logoaoaruche)
                            .setPositiveButton(getString(R.string.determine),
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    }).show();
                    break;
                } else if (J_Fight.return_magic_skill_value() == 3) {

                    new AlertDialog.Builder(JExMainActivity_original.this)
                            .setCancelable(false)
                            .setTitle(getString(R.string.choice_skill_title_End))
                            .setMessage(getString(R.string.choice_skill_ordinary_no_ordinary_Introduction))
                            .setIcon(R.drawable.logoaoaruche)
                            .setPositiveButton(getString(R.string.determine),
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    }).show();
                    break;
                } else if (J_Fight.return_magic_skill_value() == 4) {

                    new AlertDialog.Builder(JExMainActivity_original.this)
                            .setCancelable(false)
                            .setTitle(getString(R.string.choice_skill_title_End))
                            .setMessage(getString(R.string.choice_skill_ordinary_Introduction))
                            .setIcon(R.drawable.logoaoaruche)
                            .setPositiveButton(getString(R.string.determine),
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    }).show();
                    break;
                }
                break;
            }
        }
        return false;
    }

    //手勢庫
    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = Gesture_library.recognize(gesture);
        Prediction prediction = predictions.get(0);
        if (prediction.score >= 3.0) {
            if (prediction.name.equals("Magic_K")) {
                Gesture_perform("Magic_K");
            }
        }
        if (prediction.score >= 3.0) {
            if (prediction.name.equals("Magic_W")) {
                Gesture_perform("Magic_W");
            }
            if (prediction.name.equals("Magic_O")) {
                Gesture_perform("Magic_O");
            }
            if (prediction.name.equals("Magic_G")) {
                Gesture_perform("Magic_G");
            }
            if (prediction.name.equals("Magic_B")) {
                Gesture_perform("Magic_B");
            }
        }
        if (prediction.score >= 3.4) {
            if (prediction.name.equals("Magic_A")) {
                Gesture_perform("Magic_A");
            }
            if (prediction.name.equals("Magic_C")) {
                Gesture_perform("Magic_C");
            }
            if (prediction.name.equals("Magic_D")) {
                Gesture_perform("Magic_D");
            }
            if (prediction.name.equals("Magic_E")) {
                Gesture_perform("Magic_E");
            }
            if (prediction.name.equals("Magic_F")) {
                Gesture_perform("Magic_F");
            }

            if (prediction.name.equals("Magic_H")) {
                Gesture_perform("Magic_H");
            }
            if (prediction.name.equals("Magic_I")) {
                Gesture_perform("Magic_I");
            }
            if (prediction.name.equals("Magic_J")) {
                Gesture_perform("Magic_J");
            }

            if (prediction.name.equals("Magic_L")) {
                Gesture_perform("Magic_L");
            }
            if (prediction.name.equals("Magic_M")) {
                Gesture_perform("Magic_M");
            }
            if (prediction.name.equals("Magic_N")) {
                Gesture_perform("Magic_N");
            }

            if (prediction.name.equals("Magic_P")) {
                Gesture_perform("Magic_P");
            }
            if (prediction.name.equals("Magic_Q")) {
                Gesture_perform("Magic_Q");
            }
            if (prediction.name.equals("Magic_R")) {
                Gesture_perform("Magic_R");
            }
            if (prediction.name.equals("Magic_S")) {
                Gesture_perform("Magic_S");
            }
            if (prediction.name.equals("Magic_T")) {
                Gesture_perform("Magic_T");
            }
            if (prediction.name.equals("Magic_U")) {
                Gesture_perform("Magic_U");
            }
            if (prediction.name.equals("Magic_V")) {
                Gesture_perform("Magic_V");
            }

            if (prediction.name.equals("Magic_Y")) {
                Gesture_perform("Magic_Y");
            }
            if (prediction.name.equals("Magic_X")) {
                Gesture_perform("Magic_X");
            }
            if (prediction.name.equals("Magic_Z")) {
                Gesture_perform("Magic_Z");
            }
        }
    }

    //手勢庫 end
    public void Gesture_perform(String Gesture) {
        if (Gesture.equals("Magic_A")) {
            if (Fight_Array[Fight_int].equals("A")) {
                Log.d("Gesture", "Magic_A");
                J_Fight.JExFighting_Test("A");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);
            }
        }
        if (Gesture.equals("Magic_B")) {
            if (Fight_Array[Fight_int].equals("B")) {
                Log.d("Gesture", "Magic_B");
                J_Fight.JExFighting_Test("B");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);
            }
        }
        if (Gesture.equals("Magic_C")) {
            if (Fight_Array[Fight_int].equals("C")) {
                Log.d("Gesture", "Magic_C");
                J_Fight.JExFighting_Test("C");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);

            }
        }
        if (Gesture.equals("Magic_D")) {
            if (Fight_Array[Fight_int].equals("D")) {
                Log.d("Gesture", "Magic_D");
                J_Fight.JExFighting_Test("D");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);

            }
        }
        if (Gesture.equals("Magic_E")) {
            if (Fight_Array[Fight_int].equals("E")) {
                Log.d("Gesture", "Magic_E");
                J_Fight.JExFighting_Test("E");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);

            }
        }
        if (Gesture.equals("Magic_F")) {
            if (Fight_Array[Fight_int].equals("F")) {
                Log.d("Gesture", "Magic_F");
                J_Fight.JExFighting_Test("F");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);

            }
        }
        if (Gesture.equals("Magic_G")) {
            if (Fight_Array[Fight_int].equals("G")) {
                Log.d("Gesture", "Magic_G");
                J_Fight.JExFighting_Test("G");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);

            }
        }
        if (Gesture.equals("Magic_H")) {
            if (Fight_Array[Fight_int].equals("H")) {
                Log.d("Gesture", "Magic_H");
                J_Fight.JExFighting_Test("H");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);

            }
        }
        if (Gesture.equals("Magic_I")) {
            if (Fight_Array[Fight_int].equals("I")) {
                Log.d("Gesture", "Magic_I");
                J_Fight.JExFighting_Test("I");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);

            }
        }
        if (Gesture.equals("Magic_J")) {
            if (Fight_Array[Fight_int].equals("J")) {
                Log.d("Gesture", "Magic_J");
                J_Fight.JExFighting_Test("J");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);

            }
        }
        if (Gesture.equals("Magic_K")) {
            if (Fight_Array[Fight_int].equals("K")) {
                Log.d("Gesture", "Magic_K");
                J_Fight.JExFighting_Test("K");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);

            }
        }
        if (Gesture.equals("Magic_L")) {
            if (Fight_Array[Fight_int].equals("L")) {
                Log.d("Gesture", "Magic_L");
                J_Fight.JExFighting_Test("L");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);

            }
        }
        if (Gesture.equals("Magic_M")) {
            if (Fight_Array[Fight_int].equals("M")) {
                Log.d("Gesture", "Magic_M");
                J_Fight.JExFighting_Test("M");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);

            }
        }
        if (Gesture.equals("Magic_N")) {
            if (Fight_Array[Fight_int].equals("N")) {
                Log.d("Gesture", "Magic_N");
                J_Fight.JExFighting_Test("N");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);

            }
        }
        if (Gesture.equals("Magic_O")) {
            if (Fight_Array[Fight_int].equals("O")) {
                Log.d("Gesture", "Magic_O");
                J_Fight.JExFighting_Test("O");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);

            }
        }
        if (Gesture.equals("Magic_P")) {
            if (Fight_Array[Fight_int].equals("P")) {
                Log.d("Gesture", "Magic_P");
                J_Fight.JExFighting_Test("P");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);

            }
        }
        if (Gesture.equals("Magic_Q")) {
            if (Fight_Array[Fight_int].equals("Q")) {
                Log.d("Gesture", "Magic_Q");
                J_Fight.JExFighting_Test("Q");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);

            }
        }
        if (Gesture.equals("Magic_R")) {
            if (Fight_Array[Fight_int].equals("R")) {
                Log.d("Gesture", "Magic_R");
                J_Fight.JExFighting_Test("R");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);
            }
        }
        if (Gesture.equals("Magic_S")) {
            if (Fight_Array[Fight_int].equals("S")) {
                Log.d("Gesture", "Magic_S");
                J_Fight.JExFighting_Test("S");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);
            }
        }
        if (Gesture.equals("Magic_T")) {
            if (Fight_Array[Fight_int].equals("T")) {
                Log.d("Gesture", "Magic_T");
                J_Fight.JExFighting_Test("T");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this. startService(attack_voice);
            }
        }
        if (Gesture.equals("Magic_U")) {
            if (Fight_Array[Fight_int].equals("U")) {
                Log.d("Gesture", "Magic_U");
                J_Fight.JExFighting_Test("U");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);
            }
        }
        if (Gesture.equals("Magic_V")) {
            if (Fight_Array[Fight_int].equals("V")) {
                Log.d("Gesture", "Magic_V");
                J_Fight.JExFighting_Test("V");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);
            }
        }
        if (Gesture.equals("Magic_W")) {
            if (Fight_Array[Fight_int].equals("W")) {
                Log.d("Gesture", "Magic_W");
                J_Fight.JExFighting_Test("W");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);
            }
        }
        if (Gesture.equals("Magic_X")) {
            if (Fight_Array[Fight_int].equals("X")) {
                Log.d("Gesture", "Magic_X");
                J_Fight.JExFighting_Test("X");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);
            }
        }
        if (Gesture.equals("Magic_Y")) {
            if (Fight_Array[Fight_int].equals("Y")) {
                Log.d("Gesture", "Magic_Y");
                J_Fight.JExFighting_Test("Y");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);
            }
        }
        if (Gesture.equals("Magic_Z")) {
            if (Fight_Array[Fight_int].equals("Z")) {
                Log.d("Gesture", "Magic_Z");
                J_Fight.JExFighting_Test("Z");

                test_fight();
                Intent attack_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
                JExMainActivity_original.this.startService(attack_voice);
            }
        }
        choice_draw_view();

        if (Fight_int == Fight_Array.length) {

            Fight_int = 0;
            return_total = 0;
            if (Monster_Hp != 0) {
                J_Fight.JExFight_choice_Fight_Array();
                Fight_using_Array = J_Fight.JExFight_Get_Fight_Array();
                if (Fight_int == Fight_using_Array.length) {
                    Fight_int = 0;
                }
                while_length_choice_draw_view();
            }

        }
        Log_state();
    }


//按下手機返回鍵

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK)) {   //確定按下退出鍵
            onPause();
            new AlertDialog.Builder(JExMainActivity_original.this)
                    .setCancelable(false)
                    .setTitle(getString(R.string.sure_leave))
                    .setMessage(getString(R.string.click_sure_leave))
                    .setIcon(R.drawable.logoaoaruche)
                    .setPositiveButton(getString(R.string.determine),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.d("onKeyDown", "Close_the_app");
                                    kill_all = 1;
                                    JExMainActivity_original.this.finish();
                                }
                            })
                    .setNeutralButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d("onKeyDown", "Close_the_app");
                            onResume();
                        }
                    })
                    .show();
            return true;

        }

            return super.onKeyDown(keyCode, event);

    }

    // 暫停事件
    @Override
    protected void onPause() {

        do_you_pause_or_stop = 1;
        Log.d("Super_Event", "Pause_the_app");
        if (load_int == 1 && what_voice_service == 0) {
            if (start_mp.isPlaying()) {
                start_mp.pause();
            }
        }
        if (load_int == 1 && what_voice_service == 1) {
            if(mp.isPlaying()) {
                mp.pause();
            }
        } else if (load_int == 1 && what_voice_service == 2) {
            if(mp1.isPlaying()) {
                mp1.pause();
            }
        } else if (load_int == 1 && what_voice_service == 3) {
            if(mp2.isPlaying()) {
                mp2.pause();
            }
        }
        if (load_int == 1) {
            Intent stop_mp3 = new Intent(JExMainActivity_original.this, aoaruche_fight_victory_service.class);
            JExMainActivity_original.this.stopService(stop_mp3);
            Intent stop_mp4 = new Intent(JExMainActivity_original.this, aoaruche_fight_fail_service.class);
            JExMainActivity_original.this.stopService(stop_mp4);
            Intent stop_mp5 = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
            JExMainActivity_original.this.stopService(stop_mp5);
        }
        back_of_stop = 1;
        super.onPause();
        msave(save_story);
        read_memory_json(save_story);
        msave(skill_memory);
        read_memory_json(skill_memory);


    }

    //關閉事件
    @Override
    protected void onDestroy() {
        Log.d("Super_Event", "Destory_the_app");
        if (load_int == 1) {
            Intent stop_mp3 = new Intent(JExMainActivity_original.this, aoaruche_fight_victory_service.class);
            JExMainActivity_original.this.stopService(stop_mp3);
            Intent stop_mp4 = new Intent(JExMainActivity_original.this, aoaruche_fight_fail_service.class);
            JExMainActivity_original.this.stopService(stop_mp4);
            Intent stop_mp5 = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
            JExMainActivity_original.this.stopService(stop_mp5);
        }
        if (load_int == 1 && what_voice_service == 0) {
            if (start_mp.isPlaying()) {
                start_mp.stop();
            }
            Intent stop_voice_sever = new Intent(JExMainActivity_original.this, aoaruche_Title_voice.class);
            JExMainActivity_original.this.stopService(stop_voice_sever);
        } else if (load_int == 1 && what_voice_service == 1) {
            if(mp.isPlaying()) {
                mp.stop();
            }
            Intent stop_voice_sever = new Intent(JExMainActivity_original.this, aoaruche_voice_server.class);
            JExMainActivity_original.this.stopService(stop_voice_sever);
        } else if (load_int == 1 && what_voice_service == 2) {
            if(mp1.isPlaying()) {
                mp1.stop();
            }
            Intent stop_voice_sever = new Intent(JExMainActivity_original.this, aoaruche_voice_server_2.class);
            JExMainActivity_original.this.stopService(stop_voice_sever);
        } else if (load_int == 1 && what_voice_service == 3) {
            if(mp2.isPlaying()) {
                mp2.stop();
            }
            Intent stop_voice_sever = new Intent(JExMainActivity_original.this, aoaruche_voice_server_3.class);
            JExMainActivity_original.this.stopService(stop_voice_sever);
        }
        back_of_stop = 1;
        //auth.signOut();
        msave(save_story);
        read_memory_json(save_story);
        msave(skill_memory);
        read_memory_json(skill_memory);
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();

    }

    //暫停事件
    @Override
    protected void onStop() {
        do_you_pause_or_stop = 1;
        Log.d("Super_Event", "Stop_the_app");
        if (load_int == 0) {
            Intent stop1 = new Intent(JExMainActivity_original.this, aoaruche_voice_server.class);
            JExMainActivity_original.this.stopService(stop1);
            Intent stop2 = new Intent(JExMainActivity_original.this, aoaruche_voice_server_2.class);
            JExMainActivity_original.this.stopService(stop2);
            Intent stop3 = new Intent(JExMainActivity_original.this, aoaruche_Title_voice.class);
            JExMainActivity_original.this.stopService(stop2);
        }
        if (load_int == 1) {
            Intent stop_mp3 = new Intent(JExMainActivity_original.this, aoaruche_fight_victory_service.class);
            JExMainActivity_original.this.stopService(stop_mp3);
            Intent stop_mp4 = new Intent(JExMainActivity_original.this, aoaruche_fight_fail_service.class);
            JExMainActivity_original.this.stopService(stop_mp4);
            Intent stop_mp5 = new Intent(JExMainActivity_original.this, aoaruche_fight_attack_service.class);
            JExMainActivity_original.this.stopService(stop_mp5);
        }
        if (load_int == 1 && what_voice_service == 0) {
            if (start_mp.isPlaying()) {
                start_mp.pause();
            }
        }
        if (load_int == 1 && what_voice_service == 1) {
            if(mp.isPlaying()) {
                mp.pause();
            }
        } else if (load_int == 1 && what_voice_service == 2) {
            if(mp1.isPlaying()) {
                mp1.pause();
            }
        } else if (load_int == 1 && what_voice_service == 3) {
            if(mp2.isPlaying()) {
                mp2.pause();
            }
        }
        back_of_stop = 1;
        super.onStop();
        //auth.removeAuthStateListener(authListener);

        msave(save_story);
        read_memory_json(save_story);
        msave(skill_memory);
        read_memory_json(skill_memory);
    }

    //繼續事件
    @Override
    protected void onResume() {
        Log.d("Super_Event", "Resume_the_app");
        super.onResume();

        do_you_pause_or_stop = 0;
        if (load_int == 1 && what_voice_service == 0) {
            Intent start_voice = new Intent(JExMainActivity_original.this, aoaruche_Title_voice.class);
            JExMainActivity_original.this.startService(start_voice);
            mp.start();
        } else if (load_int == 1 && what_voice_service == 1) {
            Intent start_voice = new Intent(JExMainActivity_original.this, aoaruche_voice_server.class);
            JExMainActivity_original.this.startService(start_voice);
            mp.start();
        } else if (load_int == 1 && what_voice_service == 2) {
            Intent start_voice = new Intent(JExMainActivity_original.this, aoaruche_voice_server_2.class);
            JExMainActivity_original.this.startService(start_voice);
            mp1.start();
        } else if (load_int == 1 && what_voice_service == 3) {
            Intent start_voice = new Intent(JExMainActivity_original.this, aoaruche_voice_server_3.class);
            JExMainActivity_original.this.startService(start_voice);
            mp2.start();
        }
        //auth.addAuthStateListener(authListener);

        read_memory_json(save_story);
        msave(save_story);
        read_memory_json(skill_memory);
        msave(skill_memory);

    }

    void msave(String File_name) {

        try {
            FileOutputStream out = openFileOutput(File_name, MODE_PRIVATE);
            OutputStreamWriter sw = new OutputStreamWriter(out);
            if (File_name.equals(save_story)) {
                String what_region_save = String.valueOf(J_Fight.return_what_region_save());
                String Magic_Power_save = String.valueOf(J_Fight.JExFight_return_Magic_Power());
                String hp_lv = String.valueOf(J_Fight.return_hp_lv());
                String need_cash_to_up_magic_power = String.valueOf(J_Fight.return_need_cash_to_up_magic_power());
                String need_cash_to_up_hp_lv = String.valueOf(J_Fight.return_need_cash_to_up_hp_lv());
                String coin = String.valueOf(J_Fight.return_coin());
                String skill_points = String.valueOf(J_Fight.return_skill_points());
                String data1 = String.valueOf(J_Fight.return_data_1());
                String data2 = String.valueOf(J_Fight.return_data_2());
                String data3 = String.valueOf(J_Fight.return_data_3());
                String data4 = String.valueOf(J_Fight.return_data_4());
                String data5 = String.valueOf(J_Fight.return_data_5());
                String data6 = String.valueOf(J_Fight.return_data_6());
                String json_save_String = "{" + "\r\n" +
                        "\"aoaruche_memory\"" + ": [" + "\r\n" +
                        "{" + "\r\n" +
                        "\"Magic_Power\":" + Magic_Power_save + "," + "\r\n" +
                        "\"what_region_save\":" + what_region_save + "," + "\r\n" +
                        "\"hp_lv\":" + hp_lv + "," + "\r\n" +
                        "\"Coin\":" + coin + "," + "\r\n" +
                        "\"UP_Magic_Power\":" + need_cash_to_up_magic_power + "," + "\r\n" +
                        "\"UP_Hp_Lv\":" + need_cash_to_up_hp_lv + "," + "\r\n" +
                        "\"Skill_Points\":" + skill_points + "," + "\r\n" +
                        "\"data1\":" + data1 + "," + "\r\n" +
                        "\"data2\":" + data2 + "," + "\r\n" +
                        "\"data3\":" + data3 + "," + "\r\n" +
                        "\"data4\":" + data4 + "," + "\r\n" +
                        "\"data5\":" + data5 + "," + "\r\n" +
                        "\"data6\":" + data6 + "\r\n" +
                        "}" + "\r\n" +
                        "]" + "\r\n" +
                        "}";
                String KeyAES ="";
                KeyAES += JE_AES.Passward(A,1);
                KeyAES += JE_AES.Passward(A_1,1);
                KeyAES += JE_AES.Passward(A_2,2);
                KeyAES += JE_AES.Passward(A_3,1);
                KeyAES += JE_AES.Passward(A_4,2);
                KeyAES += JE_AES.Passward(A_5,2);
                KeyAES += JE_AES.Passward(A_6,2);
                KeyAES += JE_AES.Passward(A_7,2);
                KeyAES += JE_AES.Passward(A_8,1);
                KeyAES += JE_AES.Passward(A_9,2);
                KeyAES += JE_AES.Passward(A_10,2);
                KeyAES += JE_AES.Passward(A_11,2);
                KeyAES += JE_AES.Passward(A_12,2);
                KeyAES += JE_AES.Passward(A_13,2);
                KeyAES += JE_AES.Passward(A_14,2);
                KeyAES += JE_AES.Passward(A_15,2);

                String save_data_encrypt = JE_AES.encrypt128(json_save_String,KeyAES);
                Log.e("AES_DATA_MAIN",save_data_encrypt);
                sw.write(save_data_encrypt);
            }

            if (File_name.equals(skill_memory)) {
                String money_skill_lv = String.valueOf(J_S.return_money_skill_lv());
                String critical_lv = String.valueOf(J_S.return_critical_lv());
                String healing_lv = String.valueOf(J_S.return_healing_lv());
                String dodge_lv = String.valueOf(J_S.return_dodge_lv());
                String miracle_of_live_lv = String.valueOf(J_S.return_miracle_of_live_lv());
                String great_mage_lv = String.valueOf(J_S.return_great_mage_lv());
                String exercise_lv = String.valueOf(J_S.return_exercise_lv());
                String json_skill_save = "{" + "\r\n" +
                        "\"skill_save_main\"" + ": [" + "\r\n" +
                        "{" + "\r\n" +
                        "\"money_skill_lv\":" + money_skill_lv + "," + "\r\n" +
                        "\"critical_lv\":" + critical_lv + "," + "\r\n" +
                        "\"healing_lv\":" + healing_lv + "," + "\r\n" +
                        "\"dodge_lv\":" + dodge_lv + "," + "\r\n" +
                        "\"miracle_of_live_lv\":" + miracle_of_live_lv + "," + "\r\n" +
                        "\"great_mage_lv\":" + great_mage_lv + "," + "\r\n" +
                        "\"exercise_lv\":" + exercise_lv + "\r\n" +
                        "}" + "\r\n" +
                        "]" + "\r\n" +
                        "}";
                String KeyAES ="";
                KeyAES += JE_AES.Passward(A,1);
                KeyAES += JE_AES.Passward(A_1,1);
                KeyAES += JE_AES.Passward(A_2,2);
                KeyAES += JE_AES.Passward(A_3,1);
                KeyAES += JE_AES.Passward(A_4,2);
                KeyAES += JE_AES.Passward(A_5,2);
                KeyAES += JE_AES.Passward(A_6,2);
                KeyAES += JE_AES.Passward(A_7,2);
                KeyAES += JE_AES.Passward(A_8,1);
                KeyAES += JE_AES.Passward(A_9,2);
                KeyAES += JE_AES.Passward(A_10,2);
                KeyAES += JE_AES.Passward(A_11,2);
                KeyAES += JE_AES.Passward(A_12,2);
                KeyAES += JE_AES.Passward(A_13,2);
                KeyAES += JE_AES.Passward(A_14,2);
                KeyAES += JE_AES.Passward(A_15,2);
                String save_data_encrypt = JE_AES.encrypt128(json_skill_save,KeyAES);
                Log.e("AES_DATA_SKILL",save_data_encrypt);
                sw.write(save_data_encrypt);
            }
//開始輸出

            sw.flush();

//關閉輸出

            sw.close();
        }

//宣告 IOException ex

        catch (IOException ex) {

//並讓IOException ex 執行 ex.printStackTrace();

            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void msave_new_player(String File_name, String File_string) {

        try {
            FileOutputStream out = openFileOutput(File_name, MODE_PRIVATE);
            OutputStreamWriter sw = new OutputStreamWriter(out);
            //   \r\n 是換行儲存

            sw.write(File_string);
//開始輸出
            sw.flush();
//關閉輸出
            sw.close();
        }
//宣告 IOException ex
        catch (IOException ex) {
//並讓IOException ex 執行 ex.printStackTrace();
            ex.printStackTrace();
        }
    }

    //載入JSON內容
    public void read_memory_json(String file_name) {
        try {
            String json_test = load_JSON(file_name);
            if (json_test == null) {
                Log.d("JSON錯誤", "錯誤");
            }
            if (json_test != null) {
                JSONObject reader = new JSONObject(json_test);
                if (file_name.equals(save_story)) {
                    JSONArray json_read_array = reader.getJSONArray("aoaruche_memory");
                    for (int i = 0; i < json_read_array.length(); i++) {
                        try {
                            JSONObject json_reader = json_read_array.getJSONObject(i);
                            long Magic_Power = json_reader.getLong("Magic_Power");
                            long hp_lv = json_reader.getLong("hp_lv");
                            long what_region_save = json_reader.getLong("what_region_save");
                            long coin = json_reader.getLong("Coin");
                            long UP_Magic_Power = json_reader.getLong("UP_Magic_Power");
                            long UP_Hp_Lv = json_reader.getLong("UP_Hp_Lv");
                            long Skill_Points = json_reader.getLong("Skill_Points");
                            long data1 = json_reader.getLong("data1");
                            long data2 = json_reader.getLong("data2");
                            long data3 = json_reader.getLong("data3");
                            long data4 = json_reader.getLong("data4");
                            long data5 = json_reader.getLong("data5");
                            long data6 = json_reader.getLong("data6");
                            J_Fight.set_what_region_save(what_region_save);
                            J_Fight.set_hp_lv(hp_lv);
                            J_Fight.JExFight_Set_Magic(Magic_Power);
                            J_Fight.setCoin(coin);
                            J_Fight.set_need_cash_to_up_hp_lv(UP_Hp_Lv);
                            J_Fight.set_need_cash_to_up_magic_powerv(UP_Magic_Power);
                            J_Fight.set_skill_points(Skill_Points);
                            J_Fight.set_data_1(data1);
                            J_Fight.set_data_2(data2);
                            J_Fight.set_data_3(data3);
                            J_Fight.set_data_4(data4);
                            J_Fight.set_data_5(data5);
                            J_Fight.set_data_6(data6);
                            Log.d("成功讀取", "魔法等級" + String.valueOf(Magic_Power) + "\n" + "生命等級" + String.valueOf(hp_lv) + "\n" + "區域" + String.valueOf(what_region_save) + "\n"
                                    + "錢的數量" + String.valueOf(coin) + "\n" + "升級生命要的錢" + String.valueOf(UP_Hp_Lv) + "\n" + "升級攻擊要的錢" + String.valueOf(UP_Magic_Power) + "\n" +
                                    "技能點" + String.valueOf(Skill_Points) + "\n" + "data1\t" + String.valueOf(data1) + "\n" + "data2\t" + String.valueOf(data2) + "\n" + "data3\t" + String.valueOf(data3)
                                    + "\n" + "data4\t" + String.valueOf(data4) + "\n" + "data5\t" + String.valueOf(data5) + "\n" + "data6\t" + String.valueOf(data6));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (file_name.equals(skill_memory)) {
                    JSONArray json_read_array = reader.getJSONArray("skill_save_main");
                    for (int i = 0; i < json_read_array.length(); i++) {
                        try {
                            JSONObject json_reader = json_read_array.getJSONObject(i);
                            long money_skill_lv = json_reader.getLong("money_skill_lv");
                            long critical_lv = json_reader.getLong("critical_lv");
                            long healing_lv = json_reader.getLong("healing_lv");
                            long dodge_lv = json_reader.getLong("dodge_lv");
                            long miracle_of_live_lv = json_reader.getLong("miracle_of_live_lv");
                            long great_mage_lv = json_reader.getLong("great_mage_lv");
                            long exercise_lv = json_reader.getLong("exercise_lv");
                            J_S.setMoney_skill_lv(money_skill_lv);
                            J_S.setCritical_lv(critical_lv);
                            J_S.setHealing_lv(healing_lv);
                            J_S.setDodge_lv(dodge_lv);
                            J_S.set_Miracle_of_live_lv(miracle_of_live_lv);
                            J_S.setGreat_mage_lv(great_mage_lv);
                            J_S.set_Exercise_lv(exercise_lv);
                            Log.d("SKILL_READ", "賺錢能力" + String.valueOf(J_S.return_money_skill_lv()) + "\n" +
                                    "爆擊能力" + String.valueOf(J_S.return_exercise_lv()) + "\n" +
                                    "攻擊回血能力" + String.valueOf(J_S.return_healing_lv()) + "\n" +
                                    "迴避能力" + String.valueOf(J_S.return_dodge_lv()) + "\n" +
                                    "復活能力" + String.valueOf(J_S.return_miracle_of_live_lv() + "\n"
                                    + "大法師修鍊" + String.valueOf(J_S.return_great_mage_lv())) + "\n" +
                                    "身心鍛鍊" + String.valueOf(J_S.return_exercise_lv()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }else if(file_name.equals(Equipment)){
                    JSONArray json_read_array = reader.getJSONArray("Equipment");
                    for (int i = 0; i < json_read_array.length(); i++) {
                        try {
                            JSONObject json_reader = json_read_array.getJSONObject(i);
                            String weapong = json_reader.getString("what_weapon");
                            String armor = json_reader.getString("what_armor");
                            J_Backpack.setWhat_weapon(weapong);
                            J_Backpack.setWhat_armor(armor);
                            Log.d(Equipment, "read_memory_json: "+weapong);
                            Log.d(Equipment, "read_memory_json: "+armor);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //載入JSON所需函數
    public String load_JSON(String file_name) {
        String json = null;
        try {

            FileInputStream in = openFileInput(file_name);
            InputStreamReader sr = new InputStreamReader(in);
            char[] buff = new char[READ_BLOCK_SIZE];
            String readtest = " ";
            int count;
            while ((count = sr.read(buff)) > 0) {
                String s = String.copyValueOf(buff, 0, count);
                readtest += s;
                buff = new char[READ_BLOCK_SIZE];
            }
            sr.close();
            String KeyAES ="";
            KeyAES += JE_AES.Passward(A,1);
            KeyAES += JE_AES.Passward(A_1,1);
            KeyAES += JE_AES.Passward(A_2,2);
            KeyAES += JE_AES.Passward(A_3,1);
            KeyAES += JE_AES.Passward(A_4,2);
            KeyAES += JE_AES.Passward(A_5,2);
            KeyAES += JE_AES.Passward(A_6,2);
            KeyAES += JE_AES.Passward(A_7,2);
            KeyAES += JE_AES.Passward(A_8,1);
            KeyAES += JE_AES.Passward(A_9,2);
            KeyAES += JE_AES.Passward(A_10,2);
            KeyAES += JE_AES.Passward(A_11,2);
            KeyAES += JE_AES.Passward(A_12,2);
            KeyAES += JE_AES.Passward(A_13,2);
            KeyAES += JE_AES.Passward(A_14,2);
            KeyAES += JE_AES.Passward(A_15,2);

            String gg = JE_AES.decrypt128(readtest,KeyAES);
Log.e("AES",gg);
json=gg;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }




  /*  void mread(String File_name) {
//開啟檔案輸入 並宣告in = 開啟檔案輸入 ()內必須為上次打的字串變數名


            try {
                FileInputStream in = openFileInput(File_name);
                InputStreamReader sr = new InputStreamReader(in);
                char[] buff = new char[READ_BLOCK_SIZE];
                String readtest = " ";
                int count;
                while ((count = sr.read(buff)) > 0) {
                    String s = String.copyValueOf(buff, 0, count);
                    readtest += s;
                    buff = new char[READ_BLOCK_SIZE];
                }
                sr.close();
//要取出放這邊
Log.d("讀取到的值",readtest);
            } catch (IOException read) {
                read.printStackTrace();
            }}
*/


    //戰鬥前初始化
    public void back_to_original(long stage_value, long area, long have_next_monster ,long boss_fight) {
        back_value = 999999;

        if (boss_fight == 0) {
            JF.JExDelay_Enable_Gesture_Overlay_View(gesture_view, 0);
            Log.e("eeeeeeeeee",String.valueOf(gesture_view.isEnabled()));
            stage = stage_value;
            J_Fight.JExFight_Set_area(area);
            J_Fight.JExFight_Set_have_next_monster(have_next_monster);
            choice_draw_view();
          String Animation = J_Fight.monster_attack_value_fuction((J_Fight.JExFight_ready(monster_view, monster_show_hp, getString(R.string.Monster_HP), getString(R.string.how_many_monster), "no")));
                if(Animation.equals("insect_eye")||Animation.equals("error")){
               JA.JE_rotate_right(monster_view,5000);
            }else if(Animation.equals("fneam")||Animation.equals("fneam_tail")||Animation.equals("fneam_tooth")||Animation.equals("fneam_tail_tooth")
                        ||Animation.equals("dark_soul")||Animation.equals("polluted_cross")){
                    JA.JE_fade_monster(monster_view,3000);
                }
            back_to_process();
            open_run = 0;
            can_runnable = 1;
            fight_music();
            int_fight = 1;

        } else if (boss_fight == 1) {
            JF.JExDelay_Enable_Gesture_Overlay_View(gesture_view, 0);
            stage = stage_value;
            J_Fight.JExFight_Set_area(area);
            J_Fight.JExFight_Set_have_next_monster(have_next_monster);

            J_Fight.monster_attack_value_fuction((J_Fight.JExFight_ready(monster_view, monster_show_hp, getString(R.string.Monster_HP), getString(R.string.how_many_monster), "have_boss")));
            back_to_process_boss();
            open_run = 0;
            can_runnable = 1;
            boss_fight_music();
            int_fight = 1;
        } else if (boss_fight == 2) {
            JF.JExDelay_Enable_Gesture_Overlay_View(gesture_view, 0);
            back_to_process_boss();
            stage = stage_value;
            J_Fight.JExFight_Set_area(area);
            J_Fight.JExFight_Set_have_next_monster(have_next_monster);

            J_Fight.monster_attack_value_fuction((J_Fight.JExFight_ready(monster_view, monster_show_hp, getString(R.string.Monster_HP), getString(R.string.how_many_monster), "have_boss_1")));
            open_run = 0;
            can_runnable = 1;
            boss_fight_music();
            int_fight = 1;
        } else if (boss_fight == 3) {
            JF.JExDelay_Enable_Gesture_Overlay_View(gesture_view, 0);

            JA.JE_rotate_right(monster_view, 5000);
            stage = stage_value;
            J_Fight.JExFight_Set_area(area);
            J_Fight.JExFight_Set_have_next_monster(have_next_monster);

            J_Fight.monster_attack_value_fuction((J_Fight.JExFight_ready(monster_view, monster_show_hp, getString(R.string.Monster_HP), getString(R.string.how_many_monster), "nothingness")));
            back_to_process_boss();
            open_run = 0;
            can_runnable = 1;
            boss_fight_music();
            int_fight = 1;
            special_boss = 1;
        } else if (boss_fight == 4) {
            JF.JExDelay_Enable_Gesture_Overlay_View(gesture_view, 0);

            JA.JE_rotate_right(monster_view, 5000);
            stage = stage_value;
            J_Fight.JExFight_Set_area(area);
            J_Fight.JExFight_Set_have_next_monster(have_next_monster);

            J_Fight.monster_attack_value_fuction((J_Fight.JExFight_ready(monster_view, monster_show_hp, getString(R.string.Monster_HP), getString(R.string.how_many_monster), "insect_eyes")));
            back_to_process_boss();
            open_run = 0;
            can_runnable = 1;
            boss_fight_music();
            int_fight = 1;
        }
        else if (boss_fight == 5) {
            JF.JExDelay_Enable_Gesture_Overlay_View(gesture_view, 0);
            stage = stage_value;
            J_Fight.JExFight_Set_area(area);
            J_Fight.JExFight_Set_have_next_monster(have_next_monster);

            J_Fight.monster_attack_value_fuction((J_Fight.JExFight_ready(monster_view, monster_show_hp, getString(R.string.Monster_HP), getString(R.string.how_many_monster), "nothingness_green")));
            back_to_process_boss();
            open_run = 0;
            can_runnable = 1;
            boss_fight_music();
            int_fight = 1;
            special_boss = 2;
        }
    }


    public void new_player_fight(long area, long have_next_monster) {
        new AlertDialog.Builder(JExMainActivity_original.this)
                .setCancelable(false)
                .setTitle(getString(R.string.new_dialog_title))
                .setMessage(getString(R.string.new_dialog_messenge))
                .setIcon(R.drawable.logoaoaruche)
                .setPositiveButton(getString(R.string.determine),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                .show();
        back_to_process();
        J_Fight.JExFight_Set_area(area);
        J_Fight.JExFight_Set_have_next_monster(have_next_monster);
        JF.JExDelay_show_GestureOverlayView(gesture_view, 0);
        J_Fight.monster_attack_value_fuction((J_Fight.JExFight_ready(monster_view, monster_show_hp, getString(R.string.Monster_HP), getString(R.string.how_many_monster), "studio")));
        open_run = 0;
        can_runnable = 1;
        fight_music();
        int_fight = 1;

    }

    public void back_to_process() {
        Runnable_time=0;
        hero_hp = JExFight_Gesture.hero_hp;
        Fight_int = 0;
        return_total = 0;
        J_Fight.JExFight_choice_Fight_Array();
        Fight_using_Array = J_Fight.JExFight_Get_Fight_Array();
        Fight_int = 0;
        while_length_choice_draw_view();
        Log_state();
        JB.Delay_show_Bitmap("pause_button.png",pause_button,10,get_sd_path);
        pause_button.setVisibility(View.VISIBLE);
       JB.Delay_show_Bitmap("start_skill_button.png",start_skill_button,10,get_sd_path);
        clean_chapter_button();
        JF.JExDelay_disappear_Scrollview(chapter_scroview, 0);
        JF.JExDelay_disappear_Scrollview(chapter_scroview_2, 0);
        JF.JExDelay_disappear_Scrollview(choice_chapter, 0);
        J_Fight.JExFight_choice_Fight_Array();
        Fight_using_Array = J_Fight.JExFight_Get_Fight_Array();
        draw_imageview.setVisibility(View.VISIBLE);
        while_length_choice_draw_view();
        hero_hp_show.setVisibility(View.VISIBLE);
        hero_hp_show.setTextSize(25);
        hero_hp_show.setText( String.valueOf(hero_hp));
        start_skill_button.setVisibility(View.VISIBLE);
        gesture_view.setGestureColor(getResources().getColor(R.color.JExyellow));
        hp_bar.setVisibility(View.VISIBLE);
        monster_hp_bar.setVisibility(View.VISIBLE);
        time_bar.setVisibility(View.VISIBLE);
        time_bar.setMax(10000);
        time_bar.setProgress(10000);
       String get_hp_string = String.valueOf(hero_hp);
       int get_hp_int =Integer.valueOf(get_hp_string);
        String get_monster_hp_string = String.valueOf(Monster_Hp);
        int get_monster_hp_int =Integer.valueOf(get_monster_hp_string);
        monster_hp_bar.setMax(get_monster_hp_int);
        monster_hp_bar.setProgress(get_monster_hp_int);
        hp_bar.setMax(get_hp_int);
        hp_bar.setProgress(get_hp_int);
    }

    public void back_to_process_boss() {
Runnable_time=0;
        hero_hp = JExFight_Gesture.hero_hp;
        JB.Delay_show_Bitmap("pause_button.png",pause_button,10,get_sd_path);
        pause_button.setVisibility(View.VISIBLE);
      JB.Delay_show_Bitmap("start_skill_button.png",start_skill_button,10,get_sd_path);
        clean_chapter_button();
        JF.JExDelay_disappear_Scrollview(chapter_scroview, 0);
        JF.JExDelay_disappear_Scrollview(chapter_scroview_2, 0);
        JF.JExDelay_disappear_Scrollview(choice_chapter, 0);
        J_Fight.JExFight_choice_Fight_Array();
        Fight_using_Array = J_Fight.JExFight_Get_Fight_Array();
        draw_imageview.setVisibility(View.VISIBLE);
        while_length_choice_draw_view();
        hero_hp_show.setVisibility(View.VISIBLE);
        hero_hp_show.setTextSize(25);
        hero_hp_show.setText( String.valueOf(hero_hp));
        start_skill_button.setVisibility(View.VISIBLE);
        gesture_view.setGestureColor(getResources().getColor(R.color.JExRed_light_Red));
        hp_bar.setVisibility(View.VISIBLE);
        monster_hp_bar.setVisibility(View.VISIBLE);
        time_bar.setVisibility(View.VISIBLE);
        time_bar.setMax(10000);
        time_bar.setProgress(10000);
        String get_monster_hp_string = String.valueOf(Monster_Hp);
        int get_monster_hp_int =Integer.valueOf(get_monster_hp_string);
        monster_hp_bar.setMax(get_monster_hp_int);
        monster_hp_bar.setProgress(get_monster_hp_int);
        String get_hp_string = String.valueOf(hero_hp);
        int get_hp_int =Integer.valueOf(get_hp_string);
        hp_bar.setMax(get_hp_int);
        hp_bar.setProgress(get_hp_int);
    }


    //替換關卡按鈕用
    public void clean_chapter_button() {
        chapter_scroview.setVisibility(View.INVISIBLE);
        back_to_main_button.setVisibility(View.INVISIBLE);
    }

    //顯示關卡按鈕用
    public void show_chapter_button(long what_memory) {
        String get = String.valueOf(what_memory);
        Integer i = Integer.parseInt(get);
        if (i == 41) {
            i = 40;
        }
        choice_enable(i, 40);
        JB.Delay_show_Bitmap("backbutton.png",back_to_main_button,10,get_sd_path);
        back_to_main_button.setVisibility(View.VISIBLE);
        back_value = 1;

    }

    public void choice_enable(int for_enable, int for_no_enable) {

        ImageButton button_array[] = {story_chapter1_1, story_chapter1_2, story_chapter1_3, story_chapter1_4, story_chapter1_5, story_chapter1_6
                , story_chapter1_7, story_chapter1_8, story_chapter1_9, story_chapter1_10, story_chapter1_11, story_chapter1_12, story_chapter1_13
                , story_chapter1_14, story_chapter1_15, story_chapter1_16, story_chapter1_17, story_chapter1_18, story_chapter1_19, story_chapter1_20
                , story_chapter1_21, story_chapter1_22, story_chapter1_23, story_chapter1_24, story_chapter1_25, story_chapter1_26, story_chapter1_27
                , story_chapter1_28, story_chapter1_29, story_chapter1_30, story_chapter2_1, story_chapter2_2, story_chapter2_3, story_chapter2_4,
                story_chapter2_5, story_chapter2_6, story_chapter2_7, story_chapter2_8, story_chapter2_9, story_chapter2_10};

        String[] File_name_normal={"story_1_1.png","story_1_2.png","story_1_3.png","story_1_4.png","story_1_5.png","story_1_6.png","story_1_7.png"
                ,"story_1_8.png","story_1_9.png","story_1_10.png","story_1_11.png","story_1_12.png","story_1_13.png","story_1_14.png",
                "story_1_15.png","story_1_16.png","story_1_17.png","story_1_18.png","story_1_19.png","story_1_20.png","story_1_21.png",
                "story_1_22.png","story_1_23.png","story_1_24.png","story_1_25.png","story_1_26.png","story_1_27.png","story_1_28.png",
                "story_1_29.png","story_1_30.png", "story_2_1.png","story_2_2.png","story_2_3.png","story_2_4.png","story_2_5.png",
                "story_2_6.png","story_2_7.png","story_2_8.png","story_2_9.png","story_2_10.png"
        };
        String[] File_name_hid={"story_1_1_hid.png","story_1_2_hid.png","story_1_3_hid.png","story_1_4_hid.png","story_1_5_hid.png",
                "story_1_6_hid.png","story_1_7_hid.png","story_1_8_hid.png","story_1_9_hid.png","story_1_10_hid.png",
                "story_1_11_hid.png","story_1_12_hid.png","story_1_13_hid.png","story_1_14_hid.png","story_1_15_hid.png"
                ,"story_1_16_hid.png","story_1_17_hid.png","story_1_18_hid.png","story_1_19_hid.png","story_1_20_hid.png"
                ,"story_1_21_hid.png","story_1_22_hid.png","story_1_23_hid.png","story_1_24_hid.png","story_1_25_hid.png","story_1_26_hid.png",
                "story_1_27_hid.png","story_1_28_hid.png", "story_1_29_hid.png","story_1_30_hid.png", "story_2_1_hid.png","story_2_2_hid.png",
                "story_2_3_hid.png","story_2_4_hid.png","story_2_5_hid.png","story_2_6_hid.png", "story_2_7_hid.png","story_2_8_hid.png"
                ,"story_2_9_hid.png","story_2_10_hid.png"
        };
        for (int i = 0; i < for_enable; i++) {
            button_array[i].setEnabled(true);
           JB.Delay_show_Bitmap(File_name_normal[i],button_array[i],10,get_sd_path);
        }
        for (int j = for_enable; j < for_no_enable; j++) {
            button_array[j].setEnabled(false);
            JB.Delay_show_Bitmap(File_name_hid[j],button_array[j],10,get_sd_path);
        }
    }

    //戰鬥用函數
    public void test_fight() {
attack_counter+=1;
        Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack_counter));
        JF.show_toast_top(JExMainActivity_original.this,String.valueOf(attack_counter)+"COMBO");
        J_Fight.Time_Fight();

    }

    public void save_stage() {

        //加錢區
        if (stage == 1) {
            stage_victory(4,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_1_text));

        }
        if (stage == 2) {
            stage_victory(4,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_2_text));
        }
        if (stage == 3) {
            victory_event(6);
            J_Fight.get_skill_points(50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_3_text));
            JF.Scroll_thread_mini(20, tip_scroview);
        }
        if (stage == 4) {
            stage_victory(6,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_4_text));
        }
        if (stage == 5) {
            stage_victory(6,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_5_text));
        }
        if (stage == 6) {
            stage_victory(6,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_6_text));
        }
        if (stage == 7) {
            stage_victory(6,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_7_text));
        }
        if (stage == 8) {
            stage_victory(8,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_8_text));
        }
        if (stage == 9) {
            stage_victory(10,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_9_text));
        }
        if (stage == 10) {
            victory_event(12);
            J_Fight.get_skill_points(100);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_10_text));
            JF.Scroll_thread_mini(20, tip_scroview);
        }
        if (stage == 11) {
            stage_victory(14,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_11_text));
        }
        if (stage == 12) {
            stage_victory(14,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_12_text));
        }
        if (stage == 13) {
            stage_victory(14,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_13_text));
        }
        if (stage == 14) {
            stage_victory(14,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_14_text));
        }
        if (stage == 15) {
            stage_victory(14,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_15_text));
        }
        if (stage == 16) {
            stage_victory(16,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_16_text));
        }
        if (stage == 17) {
            stage_victory(16,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_17_text));
        }
        if (stage == 18) {
            stage_victory(16,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_18_text));
        }
        if (stage == 19) {
            stage_victory(16,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_19_text));
        }
        if (stage == 20) {
            stage_victory(20,100);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_20_text));
        }
        if (stage == 21) {
            stage_victory(20,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_21_text));
        }
        if (stage == 22) {
            stage_victory(20,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_22_text));
        }
        if (stage == 23) {
            stage_victory(20,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_23_text));}
        if (stage == 24) {
            stage_victory(20,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_24_text));
        }
        if (stage == 25) {
            stage_victory(20,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_25_text));
        }
        if (stage == 26) {
            stage_victory(20,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_26_text));
        }
        if (stage == 27) {
            stage_victory(20,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_27_text));
        }
        if (stage == 28) {
            stage_victory(20,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_28_text));
        }
        if (stage == 29) {
            stage_victory(20,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_29_text));
        }
        if (stage == 30) {
            stage_victory(25,100);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_30_text));
        }
        if (stage == 31) {
            stage_victory(20,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_31_text));
        }
        if (stage == 32) {
            stage_victory(20,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_2_1_text));
        }
        if (stage == 33) {
            stage_victory(20,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_2_2_text));
        }
        if (stage == 34) {
            stage_victory(20,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_2_3_text));
        }
        if (stage == 35) {
            stage_victory(20,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_2_4_text));
        }
        if (stage == 36) {
            stage_victory(20,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_2_5_text));
        }
        if (stage == 37) {
            stage_victory(20,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_2_6_text));
        }
        if (stage == 38) {
            stage_victory(20,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_2_7_text));
        }
        if (stage == 39) {
            stage_victory(20,50);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_2_8_text));
        }
        if (stage == 40) {
            stage_victory(30,100);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.win_1_31_text));
        }
        if (stage == 1000001) {
            victory_event(1000);
            J_Fight.add_skill_points(20);
            tip_text.setText(choice_tip("error_don't_choice_tip") + "\n\n" + getString(R.string.nothingness_green_win_text));
            JF.Scroll_thread_mini(20, tip_scroview);
        }


        //存檔區
        if (stage == 1 && J_Fight.return_what_region_save() == 1) {
            stage_region_save(2);
        }
        if (stage == 2 && J_Fight.return_what_region_save() == 2) {
            stage_region_save(3);
        }
        if (stage == 3 && J_Fight.return_what_region_save() == 3) {
            stage_region_save(4);
        }
        if (stage == 4 && J_Fight.return_what_region_save() == 4) {
            stage_region_save(5);
        }
        if (stage == 5 && J_Fight.return_what_region_save() == 5) {
            stage_region_save(6);
        }
        if (stage == 6 && J_Fight.return_what_region_save() == 6) {
            stage_region_save(7);
        }
        if (stage == 7 && J_Fight.return_what_region_save() == 7) {
            stage_region_save(8);
        }
        if (stage == 8 && J_Fight.return_what_region_save() == 8) {
            stage_region_save(9);
        }
        if (stage == 9 && J_Fight.return_what_region_save() == 9) {
            stage_region_save(10);
        }
        if (stage == 10 && J_Fight.return_what_region_save() == 10) {
            stage_region_save(11);
        }
        if (stage == 11 && J_Fight.return_what_region_save() == 11) {
            stage_region_save(12);
        }
        if (stage == 12 && J_Fight.return_what_region_save() == 12) {
            stage_region_save(13);
        }
        if (stage == 13 && J_Fight.return_what_region_save() == 13) {
            stage_region_save(14);
        }
        if (stage == 14 && J_Fight.return_what_region_save() == 14) {
            stage_region_save(15);
        }
        if (stage == 15 && J_Fight.return_what_region_save() == 15) {
            stage_region_save(16);
        }
        if (stage == 16 && J_Fight.return_what_region_save() == 16) {
            stage_region_save(17);
        }
        if (stage == 17 && J_Fight.return_what_region_save() == 17) {
            stage_region_save(18);
        }
        if (stage == 18 && J_Fight.return_what_region_save() == 18) {
            stage_region_save(19);
        }
        if (stage == 19 && J_Fight.return_what_region_save() == 19) {
            stage_region_save(20);
        }
        if (stage == 20 && J_Fight.return_what_region_save() == 20) {
            stage_region_save(21);
        }
        if (stage == 21 && J_Fight.return_what_region_save() == 21) {
            stage_region_save(22);
        }
        if (stage == 22 && J_Fight.return_what_region_save() == 22) {
            stage_region_save(23);
        }
        if (stage == 23 && J_Fight.return_what_region_save() == 23) {
            stage_region_save(24);
        }
        if (stage == 24 && J_Fight.return_what_region_save() == 24) {
            stage_region_save(25);
        }
        if (stage == 25 && J_Fight.return_what_region_save() == 25) {
            stage_region_save(26);
        }
        if (stage == 26 && J_Fight.return_what_region_save() == 26) {
            stage_region_save(27);
        }
        if (stage == 27 && J_Fight.return_what_region_save() == 27) {
            stage_region_save(28);
        }
        if (stage == 28 && J_Fight.return_what_region_save() == 28) {
            stage_region_save(29);
        }
        if (stage == 29 && J_Fight.return_what_region_save() == 29) {
            stage_region_save(30);
        }
        if (stage == 30 && J_Fight.return_what_region_save() == 30) {
            stage_region_save(31);
        }
        if (stage == 31 && J_Fight.return_what_region_save() == 31) {
            stage_region_save(32);
        }
        if (stage == 32 && J_Fight.return_what_region_save() == 32) {
            stage_region_save(33);
        }
        if (stage == 33 && J_Fight.return_what_region_save() == 33) {
            stage_region_save(34);
        }
        if (stage == 34 && J_Fight.return_what_region_save() == 34) {
            stage_region_save(35);
        }
        if (stage == 35 && J_Fight.return_what_region_save() == 35) {
            stage_region_save(36);
        }
        if (stage == 36 && J_Fight.return_what_region_save() == 36) {
            stage_region_save(37);
        }
        if (stage == 37 && J_Fight.return_what_region_save() == 37) {
            stage_region_save(38);
        }
        if (stage == 38 && J_Fight.return_what_region_save() == 38) {
            stage_region_save(39);
        }
        if (stage == 39 && J_Fight.return_what_region_save() == 39) {
            stage_region_save(40);
        }

    }


    public void stage_victory(long add_coin,int get_skill_points){
        victory_event(add_coin);
        J_Fight.get_skill_points(get_skill_points);
        JF. Scroll_thread_mini(20, tip_scroview);
    }

    public void stage_region_save(long save_stage){
        J_Fight.set_what_region_save(save_stage);
        msave(save_story);
        read_memory_json(save_story);
    }

    public void victory_event(long add_coin) {
        String can_add_money = "haven't_money";
        tip_scroview.setVisibility(View.VISIBLE);
        if (J_S.return_money_skill_lv() >= 2) {
            can_add_money = J_S.do_you_money();
        }
        if (can_add_money.equals("haven't_money")) {
            J_Fight.addCoin(add_coin);
        } else if (can_add_money.equals("have_money")) {
            J_Fight.addCoin((add_coin * 2));
            Log.d("SPECIAL_SKILL", "加錢");
        }
        msave(save_story);
        read_memory_json(save_story);
        msave(skill_memory);
        read_memory_json(skill_memory);
    }

    //關卡結束後小提示 隨機選擇
    public String choice_tip(String what_Strin_for_tip) {
        int what_tip = J_Fight.tip_rnd();
        if (what_tip == 0) {
            what_Strin_for_tip = getString(R.string.tip_1_1);
        }
        if (what_tip == 1) {
            what_Strin_for_tip = getString(R.string.tip_1_2);
        }
        if (what_tip == 2) {
            what_Strin_for_tip = getString(R.string.tip_1_3);
        }
        if (what_tip == 3) {
            what_Strin_for_tip = getString(R.string.tip_1_4);
        }
        if (what_tip == 4) {
            what_Strin_for_tip = getString(R.string.tip_1_5);
        }
        if (what_tip == 5) {
            what_Strin_for_tip = getString(R.string.tip_1_6);
        }
        if (what_tip == 6) {
            what_Strin_for_tip = getString(R.string.tip_1_7);
        }
        return what_Strin_for_tip;
    }

    //選擇要顯示畫啥的
    public void choice_draw_view() {
        if (Fight_int == 1 || Fight_int == 2 || Fight_int == 3 || Fight_int == 4 || Fight_int == 5 || Fight_int == 6 || Fight_int == 7 || Fight_int == 8 || Fight_int == 9) {
            if (Fight_using_Array[Fight_int].equals("A")) {
                if (has_new_player == 0) {
                   JB.Delay_show_Bitmap("magic_a.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_a_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("B")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_b.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_b_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("C")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_c.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_c_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("D")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_d.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_d_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("E")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_e.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_e_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("F")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_f.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_f_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("G")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_g.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_g_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("H")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_h.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_h_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("I")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_i.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_i_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("J")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_j.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_j_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("K")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_k.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_k_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("L")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_l.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_l_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("M")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_m.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_m_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("N")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_n.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_n_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("O")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_o.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_o_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("P")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_p.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_p_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("Q")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_q.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_q_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("R")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_r.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_r_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("S")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_s.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_s_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("T")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_t.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_t_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("U")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_u.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_u_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("V")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_v.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_v_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("W")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_w.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_w_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("X")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_x.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_x_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("Y")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_y.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_y_studio.png",draw_imageview,10,get_sd_path);
                }
            }
            if (Fight_using_Array[Fight_int].equals("Z")) {
                if (has_new_player == 0) {
                    JB.Delay_show_Bitmap("magic_z.png",draw_imageview,10,get_sd_path);
                } else if (has_new_player == 1) {
                    JB.Delay_show_Bitmap("magic_z_studio.png",draw_imageview,10,get_sd_path);
                }
            }

        }

    }

    //選擇一個新的關卡要用這個 還有陣列到達最後一個時重選
    public void while_length_choice_draw_view() {
        if (Fight_using_Array[Fight_int].equals("A")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_a.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_a_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("B")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_b.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_b_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("C")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_c.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_c_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("D")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_d.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_d_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("E")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_e.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_e_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("F")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_f.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_f_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("G")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_g.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_g_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("H")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_h.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_h_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("I")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_i.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_i_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("J")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_j.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_j_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("K")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_k.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_k_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("L")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_l.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_l_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("M")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_m.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_m_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("N")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_n.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_n_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("O")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_o.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_o_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("P")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_p.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_p_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("Q")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_q.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_q_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("R")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_r.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_r_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("S")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_s.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_s_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("T")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_t.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_t_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("U")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_u.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_u_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("V")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_v.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_v_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("W")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_w.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_w_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("X")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_x.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_x_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("Y")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_y.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_y_studio.png",draw_imageview,10,get_sd_path);
            }
        }
        if (Fight_using_Array[Fight_int].equals("Z")) {
            if (has_new_player == 0) {
                JB.Delay_show_Bitmap("magic_z.png",draw_imageview,10,get_sd_path);
            } else if (has_new_player == 1) {
                JB.Delay_show_Bitmap("magic_z_studio.png",draw_imageview,10,get_sd_path);
            }
        }

    }

    //主介面至其他介面的回收
    public void System_GC_Main(){

        JB.Recycle_Bitmap(user_interface_magic_circle);
        JB.Recycle_Bitmap(special_stage_button);
        JB.Recycle_Bitmap(studio_button);
        JB.Recycle_Bitmap(update_button);
        JB.Recycle_Bitmap(backpack_button);
        JB.Recycle_Bitmap(hero_skill_button);
        JB.Recycle_Bitmap(skill_button);
        JB.Recycle_Bitmap(maker_button);
        JB.Recycle_Bitmap(share_game_button);
        JB.Recycle_Bitmap(open_fbpage_button);
        JB.Recycle_Bitmap(share_page_button);
        JB.Recycle_Bitmap(open_game_site);
        System.gc();

    }

    //Log出戰鬥數值
    public void Log_state() {

        Log.d("Array", JExFight_Gesture.show_Fight_Array);
        String show_Fight_int = String.valueOf(Fight_int);
        String show_return_total = String.valueOf(JExFight_Gesture.return_total);
        Log.d("Fight_int", show_Fight_int);
        Log.d("Total", show_return_total);


    }


    //Toast初始值 跟下面函數show_toast_with_view相配合
    public void show_story(View view) {
        if (show_Toast == null) {
            show_Toast = new Toast(JExMainActivity_original.this);
            show_Toast.setView(view);
            show_Toast.setGravity(Gravity.CENTER, 0, 0);
            show_Toast.setDuration(Toast.LENGTH_SHORT);
        } else {
            show_Toast.setView(view);
            show_Toast.setGravity(Gravity.CENTER, 0, 0);
            show_Toast.setDuration(Toast.LENGTH_SHORT);
        }
        JF.JExDelay_Story_Toast(show_Toast, 0);
        gesture_view.setVisibility(View.VISIBLE);
    }





    public void Fight_Runnable(final long time) {
        final Handler mHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
//延遲完後要幹嘛:
                if (time == 10000&&is_fight_runable==1) {

                    String critical = "haven't_critical";
                    String healing = "haven't_healing";
                    if (Monster_Hp != 0) {

                        if (has_new_player == 1 && has_new_player_counter <= 2) {
                            JF.showToast(JExMainActivity_original.this, getString(R.string.you_in_the_stage_Exercise));
                            has_new_player_counter += 1;
                        }

                        if(what_skill==0){
                            long attack=  J_Fight.return_attack_1_point_0(attack_counter);
                            Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack));
                            Monster_Hp -= attack;
                            JF.show_toast_top(JExMainActivity_original.this, getString(R.string.hunt_monster) +
                                    attack
                                    + getString(R.string.hunt_monster_1));
                            do_you_pause_or_stop = 0;
                        }

                        if (J_Fight.return_monster_in_protect() == 0) {
                            if (J_S.return_critical_lv() >= 2) {
                                critical = J_S.do_you_critical();
                            }

                            if (what_skill == 2 && hero_double > 0) {
                                long attack= J_Fight.return_1_point_5_attack(attack_counter);
                                Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack));
                                Monster_Hp -= attack;
                                JF.show_toast_top(JExMainActivity_original.this, getString(R.string.hunt_monster) +
                                        attack
                                        + getString(R.string.hunt_monster_1));
                                hero_double -= 1;
                                do_you_pause_or_stop = 0;
                                if (hero_double <= 0) {
                                    what_skill = 0;
                                    do_you_pause_or_stop = 0;
                                }
                            }

                            if (what_skill == 1) {
                                long attack= J_Fight.return_2_point_0_attack(attack_counter);
                                Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack));
                                Monster_Hp -= attack;


                                JF.show_toast_top(JExMainActivity_original.this, getString(R.string.hunt_monster) +
                                        attack

                                        + getString(R.string.hunt_monster_1));
                                what_skill = 0;
                                do_you_pause_or_stop = 0;
                            }
                            if (J_S.return_healing_lv() >= 2) {
                                if (hero_hp + J_S.do_you_healing(attack_counter) >= J_Fight.return_hero_hp_to_choice()) {
                                    hero_hp = (hp_lv * 50) + 100;
                                    Log.d("SPECIAL_SKILL", "回滿血");
                                } else {
                                    hero_hp += J_S.do_you_healing(attack_counter);
                                    Log.d("SPECIAL_SKILL", "回復血");
                                }
                            }
                            if (J_Backpack.return_what_armor().equals("healing_armor")) {
                                if (hero_hp + 2 >= J_Fight.return_hero_hp_to_choice()) {
                                    hero_hp = (hp_lv * 50) + 100;
                                    Log.d("Healing_Armor", "回滿血");
                                } else {
                                    hero_hp +=2;
                                    Log.d("Healing_Armor", "回復血");
                                }
                            }
                            if (what_skill == 3 && hero_healing > 0) {
                                if (hero_hp + J_Fight.skill_healing_hp(attack_counter) >= J_Fight.return_hero_hp_to_choice()) {
                                    hero_hp = (hp_lv * 50) + 100;
                                    long attack=  J_Fight.return_attack_1_point_0(attack_counter);
                                    Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack));
                                    Monster_Hp -= attack;
                                    JF.show_toast_top(JExMainActivity_original.this, getString(R.string.hunt_monster) +
                                            attack
                                            + getString(R.string.hunt_monster_1));
                                    Log.d("SPECIAL_SKILL", "回滿血");
                                    hero_healing -= 1;
                                    do_you_pause_or_stop = 0;
                                    if (hero_healing <= 0) {
                                        what_skill = 0;
                                    }
                                } else {
                                    hero_hp += J_Fight.skill_healing_hp(attack_counter);
                                    long attack=  J_Fight.return_attack_1_point_0(attack_counter);
                                    Log.d("ATTACK_VALUE_DEBUG",String.valueOf(attack));
                                    Monster_Hp -= attack;
                                    JF.show_toast_top(JExMainActivity_original.this, getString(R.string.hunt_monster) +
                                            attack
                                            + getString(R.string.hunt_monster_1));
                                    Log.d("SPECIAL_SKILL", "回復血");
                                    hero_healing -= 1;
                                    do_you_pause_or_stop = 0;
                                    if (hero_healing <= 0) {
                                        what_skill = 0;
                                        do_you_pause_or_stop = 0;
                                    }
                                }
                            }

                        } else if (J_Fight.return_monster_in_protect() == 1) {
                            Log.d("保護狀態", "怪物在保護狀態");

                        }
                        if (has_new_player == 1 && Monster_Hp < 2101010001) {
                            Monster_Hp = 2101010101;
                        }
                        Log.d("MONSTER_HP", String.valueOf(Monster_Hp));
                        monster_show_hp.setText(getString(R.string.Monster_HP) + String.valueOf(Monster_Hp) + "\n" + getString(R.string.how_many_monster) );
                        if (Monster_Hp <= 0 && have_next_monster <= 0) {

                            Monster_Hp = 0;
                            Log.d(getString(R.string.monster_all_died), "monster_all_died");


                            gesture_view.setVisibility(View.INVISIBLE);
                            monster_show_hp.setVisibility(View.INVISIBLE);
                            monster_hp_bar.setVisibility(View.INVISIBLE);
                            JA.clean_and_stop_am(monster_view);
                            monster_view.setVisibility(View.INVISIBLE);
                            draw_imageview.setVisibility(View.INVISIBLE);
                            pause_button.setVisibility(View.INVISIBLE);
                            JB.Delay_show_Bitmap("start_skill_button.png",start_skill_button,10,get_sd_path);
                            JB.Delay_show_Bitmap("backbutton.png",back_to_main_button,10,get_sd_path);
                            start_skill_button.setVisibility(View.INVISIBLE);
                            hero_hp_show.setText(R.string.stage_victory);
                            hero_hp_show.setTextSize(25);
                            hp_bar.setVisibility(View.INVISIBLE);
                            monster_hp_bar.setVisibility(View.INVISIBLE);
                            time_bar.setVisibility(View.INVISIBLE);
                            J_Fight.set_hero_hp();
                            JF.JExDelay_show_Imagebutton(back_to_main_button, 0);
                            JA.clean_and_stop_am(fight_animation);
                            Intent victory_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_victory_service.class);
                            JExMainActivity_original.this.startService(victory_voice);
                            save_stage();
                            open_run = 0;
                            can_runnable = 1;
                            is_fight_runable = 0;
                            int_fight = 0;
                            what_skill = 0;
                            back_value = 999999;
                            scroll_state = 0;
                            hero_healing = 0;
                            hero_protect = 0;
                            hero_double = 0;
                            scrollview_is_click = 0;
                            skill_turn=0;
                            now_skill=0;

                            JB.Delay_show_Bitmap("monster_attack_1.png",monster_attack_view,10,get_sd_path);
                            JB.Delay_show_Bitmap("backbutton.png",back_to_main_button,10,get_sd_path);
                            if (special_boss == 2) {
                                special_boss = 0;
                            }
                        }

                        if (Monster_Hp <= 0 && have_next_monster >= 1) {
                            if (Fight_int == Fight_using_Array.length) {
                                Fight_int = 0;
                            }
                            do_you_pause_or_stop = 1;
                            J_Fight.reset_whos_monster();
                            have_next_monster -= 1;
                            J_Fight.JExFight_choice_Fight_Array();
                            Fight_using_Array = J_Fight.JExFight_Get_Fight_Array();

                            while_length_choice_draw_view();
                            if (special_boss == 1) {
                                J_Fight.monster_attack_value_fuction((J_Fight.JExFight_ready(monster_view, monster_show_hp, getString(R.string.Monster_HP), getString(R.string.how_many_monster), "nothingness_2")));
                                special_boss = 0;
                                String get_monster_hp_string = String.valueOf(Monster_Hp);
                                int get_monster_hp_int =Integer.valueOf(get_monster_hp_string);
                                monster_hp_bar.setMax(get_monster_hp_int);
                                monster_hp_bar.setProgress(get_monster_hp_int);
                            } else if (special_boss == 0) {
                                String Animation = J_Fight.monster_attack_value_fuction((J_Fight.JExFight_ready(monster_view, monster_show_hp, getString(R.string.Monster_HP), getString(R.string.how_many_monster), "no")));
                                if(Animation.equals("insect_eye")||Animation.equals("error")){
                                    JA.JE_rotate_right(monster_view,5000);
                                }else if(Animation.equals("fneam")||Animation.equals("fneam_tail")||Animation.equals("fneam_tooth")||Animation.equals("fneam_tail_tooth")
                                        ||Animation.equals("dark_soul")||Animation.equals("polluted_cross")){
                                    JA.JE_fade_monster(monster_view,3000);
                                }else{
                                    JA.stop_am(monster_view);
                                }
                                String get_monster_hp_string = String.valueOf(Monster_Hp);
                                int get_monster_hp_int =Integer.valueOf(get_monster_hp_string);
                                monster_hp_bar.setMax(get_monster_hp_int);
                                monster_hp_bar.setProgress(get_monster_hp_int);
                            }
                            open_run = 0;
                            can_runnable = 1;
                            is_fight_runable = 0;
                            int_fight = 1;
                        }
                        if(Monster_Hp>0) {
                            String get_monster_hp_string = String.valueOf(Monster_Hp);
                            int get_monster_hp_int =Integer.valueOf(get_monster_hp_string);
                            monster_hp_bar.setProgress(get_monster_hp_int);
                            hero_hp_show.setText(String.valueOf(hero_hp));
                            hero_hp_show.setTextSize(25);
                            String get_hp_string = String.valueOf(hero_hp);
                            int get_hp_int = Integer.valueOf(get_hp_string);
                            hp_bar.setProgress(get_hp_int);
                            JA.hero_attack_animation(60, fight_animation);
                        }
attack_counter=0;
                    }
                    String get_monster_hp_string = String.valueOf(Monster_Hp);
                    int get_monster_hp_int =Integer.valueOf(get_monster_hp_string);
                    monster_hp_bar.setProgress(get_monster_hp_int);
                    if(gesture_view.isGesturing()) {
                        gesture_view.cancelGesture();
                    }
gesture_view.setEnabled(false);
                    Fight_monster_attack(3000);
                    Runnable_time=0;


                    JA.Monster_attack_animation(2000, monster_attack_view);
                    time_bar.setMax(10000);
                    time_bar.setProgress(10000);

                }else if(time<=10000&&is_fight_runable==1){
                    Runnable_time+=25;
                    String get_time_string=String.valueOf(Runnable_time);
                   int get_time_int = Integer.parseInt(get_time_string);
                    time_bar.setProgress(10000-get_time_int);
                    Fight_Runnable(Runnable_time);
            }
            }
            };

        new Thread(new Runnable() {
            public void run() {
                try {

                    Thread.sleep(25);

                    Message msg = Message.obtain();
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void Fight_monster_attack(final long time) {
        final Handler mHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //延遲完後要幹嘛:
                String dodge = "haven't_dodge";
                if (kill_all == 0) {
                    if (hero_hp > 0 && can_runnable == 0 && Monster_Hp > 0) {
                        if (J_S.return_dodge_lv() >= 2) {
                            dodge = J_S.do_you_dodge();
                        }
                        if (dodge.equals("haven't_dodge") && hero_protect <= 0) {
                            hero_hp -= (J_Fight.What_Defense(monster_attack_value));
                            JF.show_toast_top(JExMainActivity_original.this, getString(R.string.Hero_Now_HP) + String.valueOf(hero_hp));
                        } else if (dodge.equals("have_dodge") && hero_protect <= 0) {
                            hero_hp -= 0;
                            Log.d("SPECIAL_SKILL", "閃避");
                        }


                        hero_hp_show.setTextSize(25);
                        hero_hp_show.setText(String.valueOf(hero_hp));
                        String get_hp_string = String.valueOf(hero_hp);
                        int get_hp_int = Integer.valueOf(get_hp_string);
                        hp_bar.setProgress(get_hp_int);
                    }

                    if (hero_hp <= 0) {
                        String miracle_of_live = "haven't_miracle";
                        if (J_S.return_miracle_of_live_lv() >= 2) {
                            miracle_of_live = J_S.do_you_miracle();
                        }
                        if (miracle_of_live.equals("have_miracle")) {
                            JExFight_Gesture.hero_hp = (hp_lv * 100) + 50;
                            JF.showToast(JExMainActivity_original.this, getString(R.string.miracle));
                            Log.d("SPECIAL_SKILL", "奇蹟發生");

                            String get_hp_string = String.valueOf(hero_hp);
                            int get_hp_int = Integer.valueOf(get_hp_string);
                            hp_bar.setProgress(get_hp_int);

                        }
                        Log.d(getString(R.string.Hero_died), "Hero_died");


                        gesture_view.setVisibility(View.INVISIBLE);
                        monster_show_hp.setVisibility(View.INVISIBLE);
                        monster_hp_bar.setVisibility(View.INVISIBLE);
                        JA.clean_and_stop_am(monster_view);
                        draw_imageview.setVisibility(View.INVISIBLE);
                        pause_button.setVisibility(View.INVISIBLE);
                        JB.Delay_show_Bitmap("start_skill_button.png",start_skill_button,10,get_sd_path);
                        start_skill_button.setVisibility(View.INVISIBLE);
                        monster_attack_view.setVisibility(View.INVISIBLE);
                        hero_hp_show.setTextSize(30);
                        hero_hp_show.setText(R.string.you_are_die);
                        hp_bar.setVisibility(View.INVISIBLE);
                        monster_hp_bar.setVisibility(View.INVISIBLE);
                        time_bar.setVisibility(View.INVISIBLE);
                        time_bar.setMax(10000);
                        time_bar.setProgress(10000);
                        is_fight_runable = 0;
                        scrollview_is_click = 0;
                        what_skill = 0;
                        hero_healing = 0;
                        hero_protect = 0;
                        hero_double = 0;
                        how_much_stage += 1;
                        skill_turn=0;
                        now_skill = 0;

                        JB.Delay_show_Bitmap("backbutton.png",back_to_main_button,10,get_sd_path);
                        if (int_fight == 1) {
                            JF.JExDelay_show_Imagebutton(back_to_main_button, 0);
                            Intent fail_voice = new Intent(JExMainActivity_original.this, aoaruche_fight_fail_service.class);
                            JExMainActivity_original.this.startService(fail_voice);
                            int_fight = 0;
                        }
                        if (special_boss == 2) {
                            special_boss = 0;
                        }
                        hero_survive = 0;
                    }
                    if (hero_protect >= 0) {
                        hero_protect -= 1;
                    }

                    if (Monster_Hp > 0 && hero_hp > 0 && can_runnable == 0 && open_run == 1) {
                        open_run = 0;
                        can_runnable = 1;
                        is_fight_runable=0;

                    }

                    Log.d("Fight_Final", "Runnable");
                    in_what_Runnable = 10;

                    if (Monster_Hp < 0 && hero_hp > 0 && can_runnable == 0 && open_run == 1 && have_next_monster <= 0) {

                        open_run = 0;
                        can_runnable = 1;
                        is_fight_runable=0;
                    }
                }
                if(skill_turn>=1){
                    skill_turn-=1;
                    if(skill_turn==0){
                      JB.Delay_show_Bitmap("start_skill_button.png",start_skill_button,10,get_sd_path);
                    }
                }
                String get_monster_hp_string = String.valueOf(Monster_Hp);
                int get_monster_hp_int =Integer.valueOf(get_monster_hp_string);
                monster_hp_bar.setProgress(get_monster_hp_int);
                J_Fight.JExFight_choice_Fight_Array();
                Fight_using_Array = J_Fight.JExFight_Get_Fight_Array();
                Fight_int = 0;
                while_length_choice_draw_view();
                Log_state();
                gesture_view.setEnabled(true);
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




/*
    public void createUser(final String email, final String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                }
                                String message =
                                        task.isSuccessful() ? getString(R.string.success_register) : getString(R.string.fail_registed);
                                new AlertDialog.Builder(JExMainActivity_original.this)
                                        .setCancelable(false)
                                        .setIcon(R.mipmap.logo_aoaruche)
                                        .setMessage(message)
                                        .setPositiveButton(getString(R.string.determine), null)
                                        .show();
                            }

                        });
    }
*/
/*
    public void login_firebase(final String email, final String password) {
        new AlertDialog.Builder(JExMainActivity_original.this)
                .setCancelable(false)
                .setTitle(getString(R.string.warning))
                .setIcon(R.mipmap.logo_aoaruche)
                .setMessage(getString(R.string.no_account))
                .setPositiveButton(getString(R.string.registered),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                createUser(email, password);

                            }
                        })
                .setNeutralButton(getString(R.string.chancel), null)
                .show();
    }
*/

public void JE_Check_File( final long time) {
    final Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            ConnectivityManager mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();


            boolean net_state=false;

            boolean Check_File_music = true;
            boolean Check_File_picture = true;

            int get = 0;
            int gget = 0;

            File zip_music_inside = new File(in_path + "music.zip");
            File zip_picture_inside = new File(in_path + "picture.zip");
            File zip_music_outside = new File(get_sd_path.getPath()+ "music.zip");
            File zip_picture_outside = new File(get_sd_path.getPath()+ "picture.zip");

            for (int check = 0; check < JExInternet_Data.check_music.length; check++) {
                Check_File_music = J_File.search_in_or_out_side(in_path, JExInternet_Data.check_music[check],get_sd_path);
                if (!Check_File_music) {
                    get += 1;
                }
            }
            if (get > 0) {
                Check_File_music = false;
            }
            for (int check = 0; check < JExInternet_Data.chech_picture.length; check++) {
                Check_File_picture = J_File.search_in_or_out_side(in_path, JExInternet_Data.chech_picture[check],get_sd_path);
                if (!Check_File_picture) {
                    gget += 1;
                }
            }
            if (gget > 0) {
                Check_File_picture = false;
            }


             if(J_I_D.return_Download_state()==0&&!zip_music_inside.exists()&&!zip_music_outside.exists())
             {
                 Handler picture_handle = new Handler();
                 JExDownload_file JE_Download_music = new JExDownload_file(JExMainActivity_original.this,picture_handle);
                 JE_Download_music.execute(JExInternet_Data.Download_Array[0],"music.zip");
                 J_I_D.setDownload_Count(1);
                 J_I_D.set_Download_state(1);
             }else if(zip_music_inside.exists()||zip_music_outside.exists()){
                if(J_I_D.return_Download_state()==0) {
                    J_I_D.set_Download_state(2);
                }
             }
            if(J_I_D.return_Download_state()==2&&!zip_picture_inside.exists()&&!zip_music_outside.exists())
            {
                Handler picture_handle = new Handler();
                JExDownload_file JE_Download_picture = new JExDownload_file(JExMainActivity_original.this,picture_handle);
                JE_Download_picture.execute(JExInternet_Data.Download_Array[1],"picture.zip");
                J_I_D.set_Download_state(3);
            }
            if(zip_music_inside.exists()||zip_music_outside.exists()){
                 if(Check_File_music==false) {
                     try {
                         if(zip_music_inside.exists()) {
                             J_UnZip.upZipFile(zip_music_inside, in_path);
                         }else if(zip_music_outside.exists()){
                             J_UnZip.upZipFile(zip_music_outside, get_sd_path.getPath());
                         }
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }
            }
            if(zip_picture_inside.exists()||zip_picture_outside.exists()){
                if(Check_File_picture==false) {
                    try {
                        if(zip_picture_inside.exists()) {
                            J_UnZip.upZipFile(zip_picture_inside, in_path);
                        }else if(zip_picture_outside.exists()){
                            J_UnZip.upZipFile(zip_picture_outside, get_sd_path.getPath());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            for (int check = 0; check < JExInternet_Data.check_music.length; check++) {
                Check_File_music = J_File.search_in_or_out_side(in_path, JExInternet_Data.check_music[check],get_sd_path);
                if (!Check_File_music) {
                    get += 1;
                }
            }

            if (get > 0) {
                Check_File_music = false;
            }
            for (int check = 0; check < JExInternet_Data.chech_picture.length; check++) {
                Check_File_picture = J_File.search_in_or_out_side(in_path, JExInternet_Data.chech_picture[check],get_sd_path);
                if (!Check_File_picture) {
                    gget += 1;
                }
            }
            if (gget > 0) {
                Check_File_picture = false;
            }

            if(Check_File_music&&Check_File_picture){
                if(zip_music_inside.exists()&&zip_picture_inside.exists()) {
                    J_I_D.set_Download_state(4);
                    Log.wtf("終結循環", String.valueOf(J_I_D.return_Download_state()));
                    load_int = 1;
                    Intent start_game = new Intent(JExMainActivity_original.this, aoaruche_Title_voice.class);
                    JExMainActivity_original.this.startService(start_game);
                    JB.Delay_show_Bitmap("background_1.png",user_interface,10,get_sd_path);
                    JB.Delay_show_Bitmap("background_2.png",user_interface2,10,get_sd_path);
                    JB.Delay_show_Bitmap("background_3.png",user_interface3,10,get_sd_path);
                    JB.Delay_show_Bitmap("background_4.png",user_interface4,10,get_sd_path);
                    JB.Delay_show_Bitmap("startbutton.png",start_button,10,get_sd_path);
                    user_interface2.setVisibility(View.VISIBLE);
                    user_interface3.setVisibility(View.VISIBLE);
                    user_interface4.setVisibility(View.VISIBLE);
                    //動畫部分
                    JA.JE_fade(Loading_text, 2000);
                    JA.JE_rotate_right(sealoutside, 2000);
                    JA.JE_rotate_left(sealinside, 1500);
                    JA.JE_fade(user_interface, 250);
                    JA.JE_fade(user_interface2, 500);
                    JA.JE_fade(user_interface3, 750);
                    JA.JE_fade(user_interface4, 1000);
                    JF.JExDelay_show_Imagebutton(start_button, 5000);
                    JA.JExAnimation_Delay_change_text(Loading_text, 5000, R.string.Loading_complete);
                }else if(zip_music_outside.exists()&&zip_picture_outside.exists()){
                    J_I_D.set_Download_state(4);
                    Log.wtf("終結循環", String.valueOf(J_I_D.return_Download_state()));
                    load_int = 1;
                    Intent start_game = new Intent(JExMainActivity_original.this, aoaruche_Title_voice.class);
                    JExMainActivity_original.this.startService(start_game);
                    JB.Delay_show_Bitmap("background_1.png",user_interface,10,get_sd_path);
                    JB.Delay_show_Bitmap("background_2.png",user_interface2,10,get_sd_path);
                    JB.Delay_show_Bitmap("background_3.png",user_interface3,10,get_sd_path);
                    JB.Delay_show_Bitmap("background_4.png",user_interface4,10,get_sd_path);
                    JB.Delay_show_Bitmap("startbutton.png",start_button,10,get_sd_path);
                    JB.Delay_show_Bitmap("sealinside.png",sealinside,10,get_sd_path);
                    JB.Delay_show_Bitmap("sealoutside.png",sealinside,10,get_sd_path);
                    user_interface2.setVisibility(View.VISIBLE);
                    user_interface3.setVisibility(View.VISIBLE);
                    user_interface4.setVisibility(View.VISIBLE);
                    //動畫部分
                    JA.JE_fade(Loading_text, 2000);
                    JA.JE_rotate_right(sealoutside, 2000);
                    JA.JE_rotate_left(sealinside, 1500);
                    JA.JE_fade(user_interface, 250);
                    JA.JE_fade(user_interface2, 500);
                    JA.JE_fade(user_interface3, 750);
                    JA.JE_fade(user_interface4, 1000);
                    JF.JExDelay_show_Imagebutton(start_button, 5000);
                    JA.JExAnimation_Delay_change_text(Loading_text, 5000, R.string.Loading_complete);
                }
            }

            if(J_I_D.return_Download_state()<4){
                JE_Check_File(100);
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

public void check_net(){

    new AlertDialog.Builder(JExMainActivity_original.this)
            .setCancelable(false)
            .setTitle(getString(R.string.need_download))
            .setMessage(getString(R.string.need_download_messenge) )
            .setIcon(R.drawable.logoaoaruche)
            .setPositiveButton(getString(R.string.retry),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            boolean net_state =false;
                            ConnectivityManager mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                            //如果未連線的話，mNetworkInfo會等於null
                            if(mNetworkInfo != null)
                            {

                                //網路是否已連線(true or false)
                                if(mNetworkInfo.isConnected()){

                                    //網路是否故障有問題
                                    if(!mNetworkInfo.isFailover()){

                                        //網路是否可使用
                                        if(mNetworkInfo.isAvailable()){
                                            net_state=true;
                                        }

                                    }
                                }
                                //網路連線方式名稱(WIFI or mobile)
                                mNetworkInfo.getTypeName();
                                //網路連線狀態
                                mNetworkInfo.getState();


                                //網路是否已連接or連線中
                                mNetworkInfo.isConnectedOrConnecting();

                                //網路是否在漫遊模式
                                mNetworkInfo.isRoaming();
                            }

                            if(net_state==false){
                                check_net();

                            }else if(net_state==true){
                                JE_Check_File(10);
                            }

                        }
                    })
            .setNeutralButton(getString(R.string.get_out), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d("無網路下載", "退出");
                    kill_all = 1;
                    JExMainActivity_original.this.finish();
                }
            })
            .show();

}
public void backpack_back(){
    JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
    JB.Delay_show_Bitmap("user_interface_fight.png",user_interface_fight,10,get_sd_path);
    JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
    JB.Delay_show_Bitmap("special_stage_button.png",special_stage_button,10,get_sd_path);
    JB.Delay_show_Bitmap("studio_button.png",studio_button,10,get_sd_path);
    JB.Delay_show_Bitmap("update_what.png",update_button,10,get_sd_path);
    JB.Delay_show_Bitmap("user_interface_up_level.png",user_interface_up_level,10,get_sd_path);
    JB.Delay_show_Bitmap("backpack_button_.png",backpack_button,10,get_sd_path);
    JB.Delay_show_Bitmap("hero_skill_button.png",hero_skill_button,10,get_sd_path);
    JB.Delay_show_Bitmap("skill_button.png",skill_button,10,get_sd_path);
    JB.Delay_show_Bitmap("maker.png",maker_button,10,get_sd_path);
    JB.Delay_show_Bitmap("share_game_button.png",share_game_button,10,get_sd_path);
    JB.Delay_show_Bitmap("open_fbpage_button.png",open_fbpage_button,10,get_sd_path);
    JB.Delay_show_Bitmap("share_page_button.png",share_page_button,10,get_sd_path);
    JB.Delay_show_Bitmap("open_game_site.png",open_game_site,10,get_sd_path);
    JF.JExDelay_Clean_Image(sealinside,0);
    JF.JExDelay_Clean_Image(sealoutside,0);
    JF.JExDelay_disappear_TextView(Loading_text,0);
    JF.JExDelay_show_ImageView(user_interface, 0);
    JF.JExDelay_show_ImageView(user_interface_magic_circle, 0);
    JA.JE_rotate_right(user_interface_magic_circle, 15000);
    JF.JExDelay_show_scroview(interface_choice_scroview, 0);
}

}













