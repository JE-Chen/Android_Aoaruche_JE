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


public class aoaruche_voice_server extends Service {
    public aoaruche_voice_server() {

    }

    public static MediaPlayer mp = new MediaPlayer();
    public boolean sd_is_exist=false;

    @Override
    public void onCreate(){
        super.onCreate();//繼承onCreate方法
        try{
            if(load_int==1) {
                mp = new MediaPlayer();
              // mp = MediaPlayer.create(this, R.raw.startmusic);
              //  可行
                boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
                if(sdCardExist) {
                    File search_exist = getExternalFilesDir(null);
                    String search_path = (search_exist.getPath() + "startmusic.mp3");
                    File out_music = new File(search_path);
                    if(out_music.exists()) {
                        mp.setDataSource(search_path);
                        sd_is_exist=true;
                    }
                }
if(sd_is_exist==false) {
    mp.setDataSource("/data/data/com.tw.je.aoaruche/files/startmusic.mp3");
}
                mp.prepare();
                mp.setLooping(true);
                mp.start();
            }

    }catch(IllegalStateException e){
        e.printStackTrace();
            mp = new MediaPlayer();
            // mp = MediaPlayer.create(this, R.raw.startmusic);
            //  可行
            boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
            if(sdCardExist) {
                File search_exist = getExternalFilesDir(null);
                String search_path = (search_exist.getPath() + "startmusic.mp3");
                File out_music = new File(search_path);
                if (out_music.exists()) {
                    try {
                        mp.setDataSource(search_path);
                        sd_is_exist=true;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if(sd_is_exist==false) {
                try {
                    mp.setDataSource("/data/data/com.tw.je.aoaruche/files/startmusic.mp3");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                mp.prepare();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mp.setLooping(true);
            mp.start();

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
        if(mp!=null) {
            mp.start();
        }
        boolean isPlaying = false;
        try {
            isPlaying = mp.isPlaying();
        }
        catch (IllegalStateException e) {
            mp = new MediaPlayer();
            // mp = MediaPlayer.create(this, R.raw.startmusic);
            //  可行
            boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
            if(sdCardExist) {
                File search_exist = getExternalFilesDir(null);
                String search_path = (search_exist.getPath() + "startmusic.mp3");
                File out_music = new File(search_path);
                if (out_music.exists()) {
                    try {
                        mp.setDataSource(search_path);
                        sd_is_exist=true;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if(sd_is_exist==false) {
                try {
                    mp.setDataSource("/data/data/com.tw.je.aoaruche/files/startmusic.mp3");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                mp.prepare();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mp.setLooping(true);
            mp.start();

        }
        if(mp!=null) {
            mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    // TODO Auto-generated method stub
                    // 釋放資源
                    try {

                        mp.release();

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
            if(mp!=null) {
                mp.stop();
                mp.release();
                mp = null;
            }
        }catch(IllegalStateException e){
            e.printStackTrace();
            mp = new MediaPlayer();
            // mp = MediaPlayer.create(this, R.raw.startmusic);
            //  可行
            boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
            if(sdCardExist) {
                File search_exist = getExternalFilesDir(null);
                String search_path = (search_exist.getPath() + "startmusic.mp3");
                File out_music = new File(search_path);
                if (out_music.exists()) {
                    try {
                        mp.setDataSource(search_path);
                        sd_is_exist=true;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if(sd_is_exist==false) {
                try {
                    mp.setDataSource("/data/data/com.tw.je.aoaruche/files/startmusic.mp3");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                mp.prepare();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mp.setLooping(true);
            mp.start();


        }
        super.onDestroy();
    }

}

