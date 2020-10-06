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


public class aoaruche_fight_victory_service extends Service {
    public aoaruche_fight_victory_service() {

    }

    public static MediaPlayer mp3 = new MediaPlayer();
    public boolean sd_is_exist=false;

    @Override
    public void onCreate(){
        super.onCreate();//繼承onCreate方法
        try{
            if(load_int==1) {
                mp3 = new MediaPlayer();
                // mp3= MediaPlayer.create(this, R.raw.startmusic);
                //  可行
                boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
                if(sdCardExist) {
                    File search_exist= getExternalFilesDir(null);
                    String search_path = (search_exist.getPath() + "victory_music.mp3");
                    File out_music = new File(search_path);
                    if(out_music.exists()) {
                        mp3.setDataSource(search_path);
                        sd_is_exist=true;
                    }
                }
                if(sd_is_exist==false) {
                    mp3.setDataSource("/data/data/com.tw.je.aoaruche/files/victory_music.mp3");
                }
                mp3.prepare();
                mp3.setLooping(false);
                mp3.start();
            }

        }catch(IllegalStateException e){
            e.printStackTrace();
            mp3 = new MediaPlayer();
            // mp3 = MediaPlayer.create(this, R.raw.startmusic);
            //  可行
            boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
            if(sdCardExist) {
                File search_exist = getExternalFilesDir(null);
                String search_path = (search_exist.getPath() + "victory_music.mp3");
                File out_music = new File(search_path);
                if (out_music.exists()) {
                    try {
                        mp3.setDataSource(search_path);
                        sd_is_exist=true;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if(sd_is_exist==false) {
                try {
                    mp3.setDataSource("/data/data/com.tw.je.aoaruche/files/victory_music.mp3");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                mp3.prepare();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mp3.setLooping(false);
            mp3.start();

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
        if(mp3!=null) {
            mp3.start();
        }
        boolean isPlaying = false;
        try {
            isPlaying = mp3.isPlaying();
        }
        catch (IllegalStateException e) {
            mp3= new MediaPlayer();
            // mp3= MediaPlayer.create(this, R.raw.startmusic);
            //  可行
            boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
            if(sdCardExist) {
                File search_exist = getExternalFilesDir(null);
                String search_path = (search_exist.getPath() + "victory_music.mp3");
                File out_music = new File(search_path);
                if (out_music.exists()) {
                    try {
                        mp3.setDataSource(search_path);
                        sd_is_exist=true;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if(sd_is_exist==false) {
                try {
                    mp3.setDataSource("/data/data/com.tw.je.aoaruche/files/victory_music.mp3");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                mp3.prepare();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mp3.setLooping(false);
            mp3.start();

        }
        if(mp3!=null) {
            mp3.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mp3, int what, int extra) {
                    // TODO Auto-generated method stub
                    // 釋放資源
                    try {

                        mp3.release();

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
            if(mp3!=null) {
                mp3.stop();
                mp3.release();
                mp3= null;
            }
        }catch(IllegalStateException e){
            e.printStackTrace();
            mp3 = new MediaPlayer();
            // mp3= MediaPlayer.create(this, R.raw.startmusic);
            //  可行
            boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
            if(sdCardExist) {
                File search_exist= getExternalFilesDir(null);;
                String search_path = (search_exist.getPath() + "victory_music.mp3");
                File out_music = new File(search_path);
                if (out_music.exists()) {
                    try {
                        mp3.setDataSource(search_path);
                        sd_is_exist=true;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if(sd_is_exist==false) {
                try {
                    mp3.setDataSource("/data/data/com.tw.je.aoaruche/files/victory_music.mp3");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                mp3.prepare();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mp3.setLooping(false);
            mp3.start();


        }
        super.onDestroy();
    }

}

