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


public class aoaruche_fight_fail_service extends Service {
    public aoaruche_fight_fail_service() {

    }

    public static MediaPlayer mp4 = new MediaPlayer();
    public boolean sd_is_exist=false;

    @Override
    public void onCreate(){
        super.onCreate();//繼承onCreate方法
        try{
            if(load_int==1) {
                mp4 = new MediaPlayer();
                // mp4 = MediaPlayer.create(this, R.raw.startmusic);
                //  可行
                boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
                if(sdCardExist) {
                    File search_exist = getExternalFilesDir(null);
                    String search_path = (search_exist.getPath() + "fail_music.mp3");
                    File out_music = new File(search_path);
                    if(out_music.exists()) {
                        mp4.setDataSource(search_path);
                        sd_is_exist=true;
                    }
                }
                if(sd_is_exist==false) {
                    mp4.setDataSource("/data/data/com.tw.je.aoaruche/files/fail_music.mp3");
                }
                mp4.prepare();
                mp4.setLooping(false);
                mp4.start();
            }

        }catch(IllegalStateException e){
            e.printStackTrace();
            mp4 = new MediaPlayer();
            // mp4 = MediaPlayer.create(this, R.raw.startmusic);
            //  可行
            boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
            if(sdCardExist) {
                File search_exist = getExternalFilesDir(null);
                String search_path = (search_exist.getPath() + "fail_music.mp3");
                File out_music = new File(search_path);
                if (out_music.exists()) {
                    try {
                        mp4.setDataSource(search_path);
                        sd_is_exist=true;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if(sd_is_exist==false) {
                try {
                    mp4.setDataSource("/data/data/com.tw.je.aoaruche/files/fail_music.mp3");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                mp4.prepare();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mp4.setLooping(false);
            mp4.start();

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
        if(mp4!=null) {
            mp4.start();
        }
        boolean isPlaying = false;
        try {
            isPlaying = mp4.isPlaying();
        }
        catch (IllegalStateException e) {
            mp4= new MediaPlayer();
            // mp4 = MediaPlayer.create(this, R.raw.startmusic);
            //  可行
            boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
            if(sdCardExist) {
                File search_exist = getExternalFilesDir(null);
                String search_path = (search_exist.getPath() + "fail_music.mp3");
                File out_music = new File(search_path);
                if (out_music.exists()) {
                    try {
                        mp4.setDataSource(search_path);
                        sd_is_exist=true;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if(!sd_is_exist) {
                try {
                    mp4.setDataSource("/data/data/com.tw.je.aoaruche/files/fail_music.mp3");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                mp4.prepare();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mp4.setLooping(false);
            mp4.start();

        }
        if(mp4!=null) {
            mp4.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mp4, int what, int extra) {
                    // TODO Auto-generated method stub
                    // 釋放資源
                    try {

                        mp4.release();

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
            if(mp4!=null) {
                mp4.stop();
                mp4.release();
                mp4= null;
            }
        }catch(IllegalStateException e){
            e.printStackTrace();
            mp4 = new MediaPlayer();
            // mp4= MediaPlayer.create(this, R.raw.startmusic);
            //  可行
            boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
            if(sdCardExist) {
                File search_exist = getExternalFilesDir(null);
                String search_path = (search_exist.getPath() + "fail_music.mp3");
                File out_music = new File(search_path);
                if (out_music.exists()) {
                    try {
                        mp4.setDataSource(search_path);
                        sd_is_exist=true;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if(sd_is_exist==false) {
                try {
                    mp4.setDataSource("/data/data/com.tw.je.aoaruche/files/fail_music.mp3");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                mp4.prepare();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mp4.setLooping(false);
            mp4.start();


        }
        super.onDestroy();
    }
}

