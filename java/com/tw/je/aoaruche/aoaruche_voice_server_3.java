/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan. 
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna. 
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus. 
 * Vestibulum commodo. Ut rhoncus gravida arcu. 
 */

package com.tw.je.aoaruche;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;


import java.io.File;
import java.io.IOException;

import static com.tw.je.aoaruche.JExMainActivity_original.load_int;


public class aoaruche_voice_server_3 extends Service {
    public aoaruche_voice_server_3() {

    }


    public static MediaPlayer mp2 = new MediaPlayer();
    public boolean sd_is_exist=false;

    @Override
    public void onCreate(){
        super.onCreate();//繼承onCreate方法
        try{
            if(load_int==1) {
                mp2 = new MediaPlayer();
                // mp1 = MediaPlayer.create(this, R.raw.startmusic);
                //  可行
                boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
                if(sdCardExist) {
                    File search_exist= getExternalFilesDir(null);
                    String search_path = (search_exist.getPath() + "boss_fight_music.mp3");
                    File out_music = new File(search_path);
                    if(out_music.exists()) {
                        mp2.setDataSource(search_path);
                        sd_is_exist=true;
                    }
                }
                if(sd_is_exist==false) {
                    mp2.setDataSource("/data/data/com.tw.je.aoaruche/files/boss_fight_music.mp3");
                }
                mp2.prepare();
                mp2.setLooping(true);
                mp2.start();
            }

        }catch(IllegalStateException e){
            e.printStackTrace();
            mp2 = new MediaPlayer();
            // mp2 = MediaPlayer.create(this, R.raw.startmusic);
            //  可行
            boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
            if(sdCardExist) {
                File search_exist = getExternalFilesDir(null);
                String search_path = (search_exist.getPath() + "boss_fight_music.mp3");
                File out_music = new File(search_path);
                if (out_music.exists()) {
                    try {
                        mp2.setDataSource(search_path);
                        sd_is_exist=true;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if(sd_is_exist==false) {
                try {
                    mp2.setDataSource("/data/data/com.tw.je.aoaruche/files/boss_fight_music.mp3");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                mp2.prepare();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mp2.setLooping(true);
            mp2.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        if(mp2!=null) {
            mp2.start();
        }
        boolean isPlaying = false;
        try {
            isPlaying = mp2.isPlaying();
        }
        catch (IllegalStateException e) {
            mp2 = new MediaPlayer();
            // mp2 = MediaPlayer.create(this, R.raw.startmusic);
            //  可行
            boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
            if(sdCardExist) {
                File search_exist = getExternalFilesDir(null);
                String search_path = (search_exist.getPath() + "boss_fight_music.mp3");
                File out_music = new File(search_path);
                if (out_music.exists()) {
                    try {
                        mp2.setDataSource(search_path);
                        sd_is_exist=true;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if(sd_is_exist==false) {
                try {
                    mp2.setDataSource("/data/data/com.tw.je.aoaruche/files/boss_fight_music.mp3");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                mp2.prepare();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mp2.setLooping(true);
            mp2.start();

        }
        if(mp2!=null) {
            mp2.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mp1, int what, int extra) {
                    // TODO Auto-generated method stub
                    // 釋放資源
                    try {

                        mp2.release();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            });
        }
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy(){//服務停止時停止播放音樂並釋放資源

        try{
            if(mp2!=null) {
                mp2.stop();
                mp2.release();
                mp2 = null;
            }
        }catch(IllegalStateException e){
            e.printStackTrace();
            mp2 = new MediaPlayer();
            // mp2 = MediaPlayer.create(this, R.raw.startmusic);
            //  可行
            boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
            if(sdCardExist) {
                File search_exist = getExternalFilesDir(null);
                String search_path = (search_exist.getPath() + "boss_fight_music.mp3");
                File out_music = new File(search_path);
                if (out_music.exists()) {
                    try {
                        mp2.setDataSource(search_path);
                        sd_is_exist=true;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if(sd_is_exist==false) {
                try {
                    mp2.setDataSource("/data/data/com.tw.je.aoaruche/files/boss_fight_music.mp3");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                mp2.prepare();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mp2.setLooping(true);
            mp2.start();


        }
        super.onDestroy();
    }

}

