/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.tw.je.aoaruche;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static com.tw.je.aoaruche.JExMainActivity_original.A;
import static com.tw.je.aoaruche.JExMainActivity_original.A_1;
import static com.tw.je.aoaruche.JExMainActivity_original.A_10;
import static com.tw.je.aoaruche.JExMainActivity_original.A_11;
import static com.tw.je.aoaruche.JExMainActivity_original.A_12;
import static com.tw.je.aoaruche.JExMainActivity_original.A_13;
import static com.tw.je.aoaruche.JExMainActivity_original.A_14;
import static com.tw.je.aoaruche.JExMainActivity_original.A_15;
import static com.tw.je.aoaruche.JExMainActivity_original.A_2;
import static com.tw.je.aoaruche.JExMainActivity_original.A_3;
import static com.tw.je.aoaruche.JExMainActivity_original.A_4;
import static com.tw.je.aoaruche.JExMainActivity_original.A_5;
import static com.tw.je.aoaruche.JExMainActivity_original.A_6;
import static com.tw.je.aoaruche.JExMainActivity_original.A_7;
import static com.tw.je.aoaruche.JExMainActivity_original.A_8;
import static com.tw.je.aoaruche.JExMainActivity_original.A_9;
import static com.tw.je.aoaruche.JExMainActivity_original.Loading_text;
import static com.tw.je.aoaruche.JExMainActivity_original.READ_BLOCK_SIZE;
import static com.tw.je.aoaruche.JExMainActivity_original.backpack_button;
import static com.tw.je.aoaruche.JExMainActivity_original.get_sd_path;
import static com.tw.je.aoaruche.JExMainActivity_original.hero_skill_button;
import static com.tw.je.aoaruche.JExMainActivity_original.interface_choice_scroview;
import static com.tw.je.aoaruche.JExMainActivity_original.maker_button;
import static com.tw.je.aoaruche.JExMainActivity_original.open_fbpage_button;
import static com.tw.je.aoaruche.JExMainActivity_original.open_game_site;
import static com.tw.je.aoaruche.JExMainActivity_original.sealinside;
import static com.tw.je.aoaruche.JExMainActivity_original.sealoutside;
import static com.tw.je.aoaruche.JExMainActivity_original.share_game_button;
import static com.tw.je.aoaruche.JExMainActivity_original.share_page_button;
import static com.tw.je.aoaruche.JExMainActivity_original.skill_button;
import static com.tw.je.aoaruche.JExMainActivity_original.special_stage_button;
import static com.tw.je.aoaruche.JExMainActivity_original.studio_button;
import static com.tw.je.aoaruche.JExMainActivity_original.update_button;
import static com.tw.je.aoaruche.JExMainActivity_original.user_interface;
import static com.tw.je.aoaruche.JExMainActivity_original.user_interface_fight;
import static com.tw.je.aoaruche.JExMainActivity_original.user_interface_magic_circle;
import static com.tw.je.aoaruche.JExMainActivity_original.user_interface_up_level;

public class JExBackpack_layout extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    public JExAnimation JA=new JExAnimation();
    public JExFunction JF = new JExFunction();
    public JExAES JE_AES = new JExAES();
    public JE_Backpack J_BP = new JE_Backpack();
    public JExBitmap JB= new JExBitmap();
    public static final String BackPack = "aoaruche_back";
    public static final String Equipment ="Equipment";
   ImageView back_pack_background,backpack_back;
   ImageButton iron_sword,arch_sword,shadow_light_book;
    ImageButton armor,healing_armor,time_souls;
    TextView equipment_textview,weapon_textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backpack_layout);
        backpack_back=findViewById(R.id.backpack_back);
        back_pack_background=findViewById(R.id.back_pack_background);
        iron_sword=findViewById(R.id.iron_sword);
        arch_sword=findViewById(R.id.arch_sword);
        shadow_light_book=findViewById(R.id.shadow_light_book);
        armor=findViewById(R.id.armor);
        healing_armor=findViewById(R.id.healing_armor);
        time_souls=findViewById(R.id.time_souls);
        equipment_textview=findViewById(R.id.equipment_textview);
        weapon_textview=findViewById(R.id.weapon_textview);
        backpack_back.setOnClickListener(this);
        iron_sword.setOnClickListener(this);
        iron_sword.setOnLongClickListener(this);
        arch_sword.setOnClickListener(this);
        arch_sword.setOnLongClickListener(this);
        shadow_light_book.setOnClickListener(this);
        shadow_light_book.setOnLongClickListener(this);
        armor.setOnClickListener(this);
        armor.setOnLongClickListener(this);
        healing_armor.setOnClickListener(this);
        healing_armor.setOnLongClickListener(this);
        time_souls.setOnClickListener(this);
        time_souls.setOnLongClickListener(this);
        equipment_textview.setText(R.string.long_click_to_ex);
        weapon_textview.setText(R.string.long_click_to_ex);
        read_memory_json(BackPack);
        msave(BackPack);
        read_memory_json(Equipment);
        msave(Equipment);

        JB.Delay_show_Bitmap("backpack_mix.png",back_pack_background,10,get_sd_path);
        JB.Delay_show_Bitmap("backpack_back.png",backpack_back,10,get_sd_path);
        if(JE_Backpack.iron_sword==1){
            JB.Delay_show_Bitmap("iron_sword.png",iron_sword,10,get_sd_path);
        }else {
            JB.Delay_show_Bitmap("weapon_null.png",iron_sword,10,get_sd_path);
        }
        if(JE_Backpack.arch_sword==1){
            JB.Delay_show_Bitmap("arch_sword.png",arch_sword,10,get_sd_path);
        }else {
            JB.Delay_show_Bitmap("weapon_null.png",arch_sword,10,get_sd_path);
        }
        if(JE_Backpack.shadow_light_book==1){
            JB.Delay_show_Bitmap("shadow_light_book.png",shadow_light_book,10,get_sd_path);
        }else {
            JB.Delay_show_Bitmap("weapon_null.png",shadow_light_book,10,get_sd_path);
        }
        if(JE_Backpack.armor==1){
            JB.Delay_show_Bitmap("armor.png",armor,10,get_sd_path);
        }else {
            JB.Delay_show_Bitmap("equipment_null.png",armor,10,get_sd_path);
        }
        if(JE_Backpack.healing_armor==1){
            JB.Delay_show_Bitmap("healing_armor.png",healing_armor,10,get_sd_path);
        }else {
            JB.Delay_show_Bitmap("equipment_null.png",healing_armor,10,get_sd_path);
        }
        if(JE_Backpack.time_souls==1){
            JB.Delay_show_Bitmap("time_souls.png",time_souls,10,get_sd_path);
        }else {
            JB.Delay_show_Bitmap("equipment_null.png",time_souls,10,get_sd_path);
        }


    }
    @Override
    protected void onPause(){
        super.onPause();
        msave(BackPack);
        read_memory_json(BackPack);
        msave(Equipment);
        read_memory_json(Equipment);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        msave(BackPack);
        read_memory_json(BackPack);
        msave(Equipment);
        read_memory_json(Equipment);
        JB.Recycle_Bitmap(back_pack_background);
        JB.Recycle_Bitmap(iron_sword);
        JB.Recycle_Bitmap(arch_sword);
        JB.Recycle_Bitmap(shadow_light_book);
        JB.Recycle_Bitmap(healing_armor);
        JB.Recycle_Bitmap(time_souls);
        JB.Recycle_Bitmap(backpack_back);

    }
    @Override
    protected void onStop(){
        super.onStop();
        msave(BackPack);
        read_memory_json(BackPack);
        msave(Equipment);
        read_memory_json(Equipment);

    }
    @Override
    protected void onResume(){
        super.onResume();
        read_memory_json(BackPack);
        msave(BackPack);
        read_memory_json(Equipment);
        msave(Equipment);
    }

  public void msave(String File_name) {

        try {
            FileOutputStream out = openFileOutput(File_name, MODE_PRIVATE);
            OutputStreamWriter sw = new OutputStreamWriter(out);
            if (File_name.equals(BackPack)) {
                String json_save_String ="{" + "\r\n" +
                        "\"aoaruche_backpack\"" + ": [" + "\r\n" +
                        "{" + "\r\n" +
                        "\"iron_sword\":" +String.valueOf(J_BP.return_iron_sword())+ "," + "\r\n" +
                        "\"arch_sword\":" +String.valueOf(J_BP.return_arch_sword())+ "," + "\r\n" +
                        "\"shadow_light_book\":" +String.valueOf(J_BP.return_shadow_light_book())+","+ "\r\n" +
                        "\"armor\":" +String.valueOf(J_BP.return_armor())+","+ "\r\n" +
                        "\"healing_armor\":" +String.valueOf(J_BP.return_healing_armor())+","+ "\r\n" +
                        "\"time_souls\":" +String.valueOf(J_BP.return_time_souls())+ "\r\n" +
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
                Log.e("AES_BACKPACK",save_data_encrypt);
                sw.write(save_data_encrypt);
            }else if(File_name.equals(Equipment)){
                String json_save_String ="{" + "\r\n" +
                        "\"Equipment\"" + ": [" + "\r\n" +
                        "{" + "\r\n" +
                        "\"what_weapon\":" +J_BP.return_what_weapon()+ "," + "\r\n" +
                        "\"what_armor\":" +J_BP.return_what_armor()+ "\r\n" +
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
                Log.e("AES_BACKPACK",save_data_encrypt);
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


    //載入JSON內容
    public void read_memory_json(String file_name) {
        try {
            String json_test = load_JSON(file_name);
            if (json_test == null) {
                Log.d("JSON錯誤", "錯誤");
            }
            if (json_test != null) {
                JSONObject reader = new JSONObject(json_test);
                if (file_name.equals(BackPack)) {
                    JSONArray json_read_array = reader.getJSONArray("aoaruche_backpack");
                    for (int i = 0; i < json_read_array.length(); i++) {
                        try {
                            JSONObject json_reader = json_read_array.getJSONObject(i);
                            long iron_sword =json_reader.getLong("iron_sword");
                            long arch_sword=json_reader.getLong("arch_sword");
                            long shadow_light_book=json_reader.getLong("shadow_light_book");
                            long armor=json_reader.getLong("armor");
                            long healing_armor=json_reader.getLong("healing_armor");
                            long time_souls=json_reader.getLong("time_souls");
                            J_BP.set_iron_sword(iron_sword);
                            J_BP.set_arch_sword(arch_sword);
                            J_BP.set_shadow_light_book(shadow_light_book);
                            J_BP.set_armor(armor);
                            J_BP.set_healing_armor(healing_armor);
                            J_BP.set_time_souls(time_souls);
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
                            J_BP.setWhat_weapon(weapong);
                            J_BP.setWhat_armor(armor);
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

    @Override
    public void onClick(View view) {


        switch (view.getId()){

            case R.id.backpack_back:{

                JExBackpack_layout.this.finish();
                JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
                JB.Delay_show_Bitmap("user_interface_fight.png",user_interface_fight,10,get_sd_path);
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
                break;
            }

            case R.id.iron_sword:{
                new AlertDialog.Builder(JExBackpack_layout.this)
                        .setCancelable(false)
                        .setTitle(getString(R.string.choice))
                        .setMessage(getString(R.string.choice_this_weapon) )
                        .setIcon(R.drawable.logoaoaruche)
                        .setPositiveButton(getString(R.string.sure_choice_this),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        J_BP.setWhat_weapon("iron_sword");
                                        msave(Equipment);
                                        read_memory_json(Equipment);
                                    }
                                })
                        .setNeutralButton(getString(R.string.not_choice_this), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
                break;
            }
            case R.id.arch_sword:{
                new AlertDialog.Builder(JExBackpack_layout.this)
                        .setCancelable(false)
                        .setTitle(getString(R.string.choice))
                        .setMessage(getString(R.string.choice_this_weapon) )
                        .setIcon(R.drawable.logoaoaruche)
                        .setPositiveButton(getString(R.string.sure_choice_this),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        J_BP.setWhat_weapon("arch_sword");
                                        msave(Equipment);
                                        read_memory_json(Equipment);
                                    }
                                })
                        .setNeutralButton(getString(R.string.not_choice_this), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
                break;
            }
            case R.id.shadow_light_book:{
                new AlertDialog.Builder(JExBackpack_layout.this)
                        .setCancelable(false)
                        .setTitle(getString(R.string.choice))
                        .setMessage(getString(R.string.choice_this_weapon) )
                        .setIcon(R.drawable.logoaoaruche)
                        .setPositiveButton(getString(R.string.sure_choice_this),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        J_BP.setWhat_weapon("shadow_light_book");
                                        msave(Equipment);
                                        read_memory_json(Equipment);
                                    }
                                })
                        .setNeutralButton(getString(R.string.not_choice_this), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
                break;
            }
            case R.id.armor:{
                new AlertDialog.Builder(JExBackpack_layout.this)
                        .setCancelable(false)
                        .setTitle(getString(R.string.choice))
                        .setMessage(getString(R.string.choice_this_armor) )
                        .setIcon(R.drawable.logoaoaruche)
                        .setPositiveButton(getString(R.string.sure_choice_this),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        J_BP.setWhat_armor("armor");
                                        msave(Equipment);
                                        read_memory_json(Equipment);
                                    }
                                })
                        .setNeutralButton(getString(R.string.not_choice_this), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
                break;
            }
            case R.id.healing_armor:{
                new AlertDialog.Builder(JExBackpack_layout.this)
                        .setCancelable(false)
                        .setTitle(getString(R.string.choice))
                        .setMessage(getString(R.string.choice_this_armor) )
                        .setIcon(R.drawable.logoaoaruche)
                        .setPositiveButton(getString(R.string.sure_choice_this),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        J_BP.setWhat_armor("healing_armor");
                                        msave(Equipment);
                                        read_memory_json(Equipment);
                                    }
                                })
                        .setNeutralButton(getString(R.string.not_choice_this), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
                break;
            }
            case R.id.time_souls:{
                new AlertDialog.Builder(JExBackpack_layout.this)
                        .setCancelable(false)
                        .setTitle(getString(R.string.choice))
                        .setMessage(getString(R.string.choice_this_armor) )
                        .setIcon(R.drawable.logoaoaruche)
                        .setPositiveButton(getString(R.string.sure_choice_this),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        J_BP.setWhat_armor("time_souls");
                                        msave(Equipment);
                                        read_memory_json(Equipment);
                                    }
                                })
                        .setNeutralButton(getString(R.string.not_choice_this), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }
            break;
        }

    }

    @Override
    public boolean onLongClick(View view) {

        switch (view.getId()){

            case R.id.iron_sword:{

                weapon_textview.setText(R.string.iron_sword);

                break;
            }
            case R.id.arch_sword:{

                weapon_textview.setText(R.string.arch_sword);

                break;
            }
            case R.id.shadow_light_book:{

                weapon_textview.setText(R.string.shadow_light_book);

                break;
            }
            case R.id.armor:{

                equipment_textview.setText(R.string.armor);

                break;
            }
            case R.id.healing_armor:{

                equipment_textview.setText(R.string.healing_armor);

                break;
            }
            case R.id.time_souls:{

                equipment_textview.setText(R.string.time_souls);

                break;
            }
        }
        return false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {//捕捉返回鍵
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
         JExBackpack_layout.this.finish();
            JB.Delay_show_Bitmap("user_interface_magic_circle.png",user_interface_magic_circle,10,get_sd_path);
            JB.Delay_show_Bitmap("user_interface_fight.png",user_interface_fight,10,get_sd_path);
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
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
