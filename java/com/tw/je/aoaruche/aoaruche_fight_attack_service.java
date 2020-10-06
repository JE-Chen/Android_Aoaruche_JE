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


public class aoaruche_fight_attack_service extends Service {
    public aoaruche_fight_attack_service() {

    }


    public static MediaPlayer mp5 = new MediaPlayer();
    public boolean sd_is_exist=false;

    @Override
    public void onCreate(){
        super.onCreate();//繼承onCreate方法
        try{
            if(load_int==1) {
                mp5 = new MediaPlayer();
                // mp5 = MediaPlayer.create(this, R.raw.startmusic);
                //  可行
                boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
                if(sdCardExist) {
                    File search_exist = getExternalFilesDir(null);
                    String search_path = (search_exist.getPath() + "hero_attack_music.mp3");
                    File out_music = new File(search_path);
                    if(out_music.exists()) {
                        mp5.setDataSource(search_path);
                        sd_is_exist=true;
                    }
                }
                if(sd_is_exist==false) {
                    mp5.setDataSource("/data/data/com.tw.je.aoaruche/files/hero_attack_music.mp3");
                }
                mp5.prepare();
                mp5.setLooping(false);
                mp5.start();
            }

        }catch(IllegalStateException e){
            e.printStackTrace();
            mp5 = new MediaPlayer();
            // mp5 = MediaPlayer.create(this, R.raw.startmusic);
            //  可行
            boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
            if(sdCardExist) {
                File search_exist = getExternalFilesDir(null);
                String search_path = (search_exist.getPath() + "hero_attack_music.mp3");
                File out_music = new File(search_path);
                if (out_music.exists()) {
                    try {
                        mp5.setDataSource(search_path);
                        sd_is_exist=true;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if(sd_is_exist==false) {
                try {
                    mp5.setDataSource("/data/data/com.tw.je.aoaruche/files/hero_attack_music.mp3");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                mp5.prepare();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mp5.setLooping(false);
            mp5.start();

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
        if(mp5!=null) {
            mp5.start();
        }
        boolean isPlaying = false;
        try {
            isPlaying = mp5.isPlaying();
        }
        catch (IllegalStateException e) {
            mp5 = new MediaPlayer();
            // mp5 = MediaPlayer.create(this, R.raw.startmusic);
            //  可行
            boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
            if(sdCardExist) {
                File search_exist = getExternalFilesDir(null);
                String search_path = (search_exist.getPath() + "hero_attack_music.mp3");
                File out_music = new File(search_path);
                if (out_music.exists()) {
                    try {
                        mp5.setDataSource(search_path);
                        sd_is_exist=true;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if(sd_is_exist==false) {
                try {
                    mp5.setDataSource("/data/data/com.tw.je.aoaruche/files/hero_attack_music.mp3");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                mp5.prepare();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mp5.setLooping(false);
            mp5.start();

        }
        if(mp5!=null) {
            mp5.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mp5, int what, int extra) {
                    // TODO Auto-generated method stub
                    // 釋放資源
                    try {

                        mp5.release();

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
            if(mp5!=null) {
                mp5.stop();
                mp5.release();
                mp5 = null;
            }
        }catch(IllegalStateException e){
            e.printStackTrace();
            mp5 = new MediaPlayer();
            // mp5 = MediaPlayer.create(this, R.raw.startmusic);
            //  可行
            boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
            if(sdCardExist) {
                File search_exist = getExternalFilesDir(null);
                String search_path = (search_exist.getPath() + "hero_attack_music.mp3");
                File out_music = new File(search_path);
                if (out_music.exists()) {
                    try {
                        mp5.setDataSource(search_path);
                        sd_is_exist=true;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if(sd_is_exist==false) {
                try {
                    mp5.setDataSource("/data/data/com.tw.je.aoaruche/files/hero_attack_music.mp3");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                mp5.prepare();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mp5.setLooping(false);
            mp5.start();


        }
        super.onDestroy();
    }
}

