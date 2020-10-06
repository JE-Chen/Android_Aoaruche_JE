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


public class aoaruche_voice_server_2 extends Service {
    public aoaruche_voice_server_2() {

    }
    public static MediaPlayer mp1 = new MediaPlayer();
    public boolean sd_is_exist=false;

    @Override
    public void onCreate(){
        super.onCreate();//繼承onCreate方法
        try{
            if(load_int==1) {
                mp1 = new MediaPlayer();
                // mp1 = MediaPlayer.create(this, R.raw.startmusic);
                //  可行
                boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
                if(sdCardExist) {
                    int rnd_fight_music =((int)(Math.random()*2));
                    File search_exist = getExternalFilesDir(null);
                    String search_path= (search_exist.getPath() + "fight_use_music.mp3");
                    if(rnd_fight_music==0) {
                         search_path = (search_exist.getPath() + "fight_use_music.mp3");
                    }else if(rnd_fight_music==1) {
                        search_path = (search_exist.getPath() + "fight_use_music_2.mp3");
                    }if(rnd_fight_music==2) {
                        search_path = (search_exist.getPath() + "fight_use_music_3.mp3");
                    }
                    File out_music = new File(search_path);
                    if(out_music.exists()) {
                        mp1.setDataSource(search_path);
                        sd_is_exist=true;
                    }
                }
                if(sd_is_exist==false) {
                    int rnd_fight_music =((int)(Math.random()*2));
                    if(rnd_fight_music==0) {
                        mp1.setDataSource("/data/data/com.tw.je.aoaruche/files/fight_use_music.mp3");
                    }else  if(rnd_fight_music==1) {
                        mp1.setDataSource("/data/data/com.tw.je.aoaruche/files/fight_use_music_2.mp3");
                    }else  if(rnd_fight_music==2) {
                        mp1.setDataSource("/data/data/com.tw.je.aoaruche/files/fight_use_music_3.mp3");
                    }

                }
                mp1.prepare();
                mp1.setLooping(true);
                mp1.start();
            }

        }catch(IllegalStateException e){
            e.printStackTrace();
            mp1 = new MediaPlayer();
            // mp1 = MediaPlayer.create(this, R.raw.startmusic);
            //  可行
            boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
            if(sdCardExist) {
                int rnd_fight_music =((int)(Math.random()*2));
                File search_exist = getExternalFilesDir(null);
                String search_path= (search_exist.getPath() + "fight_use_music.mp3");
                if(rnd_fight_music==0) {
                    search_path = (search_exist.getPath() + "fight_use_music.mp3");
                }else if(rnd_fight_music==1) {
                    search_path = (search_exist.getPath() + "fight_use_music_2.mp3");
                }if(rnd_fight_music==2) {
                    search_path = (search_exist.getPath() + "fight_use_music_3.mp3");
                }
                File out_music = new File(search_path);
                if(out_music.exists()) {
                    try {
                        mp1.setDataSource(search_path);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    sd_is_exist=true;
                }
            }
            if(sd_is_exist==false) {
                int rnd_fight_music =((int)(Math.random()*2));
                if(rnd_fight_music==0) {
                    try {
                        mp1.setDataSource("/data/data/com.tw.je.aoaruche/files/fight_use_music.mp3");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }else  if(rnd_fight_music==1) {
                    try {
                        mp1.setDataSource("/data/data/com.tw.je.aoaruche/files/fight_use_music_2.mp3");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }else  if(rnd_fight_music==2) {
                    try {
                        mp1.setDataSource("/data/data/com.tw.je.aoaruche/files/fight_use_music_3.mp3");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }
            try {
                mp1.prepare();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mp1.setLooping(true);
            mp1.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId) {
        if (mp1 != null) {
            mp1.start();
        }
        boolean isPlaying = false;
        try {
            isPlaying = mp1.isPlaying();
        } catch (IllegalStateException e) {
            mp1 = new MediaPlayer();
            // mp1 = MediaPlayer.create(this, R.raw.startmusic);
            //  可行
            boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
            if (sdCardExist) {
                int rnd_fight_music =((int)(Math.random()*2));
                File search_exist = getExternalFilesDir(null);
                String search_path = (search_exist.getPath() + "fight_use_music.mp3");
                if (rnd_fight_music == 0) {
                    search_path = (search_exist.getPath() + "fight_use_music.mp3");
                } else if (rnd_fight_music == 1) {
                    search_path = (search_exist.getPath() + "fight_use_music_2.mp3");
                }
                if (rnd_fight_music == 2) {
                    search_path = (search_exist.getPath() + "fight_use_music_3.mp3");
                }
                File out_music = new File(search_path);
                if (out_music.exists()) {
                    try {
                        mp1.setDataSource(search_path);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    sd_is_exist = true;
                }
            }
            if (sd_is_exist == false) {
                int rnd_fight_music =((int)(Math.random()*2));
                if (rnd_fight_music == 0) {
                    try {
                        mp1.setDataSource("/data/data/com.tw.je.aoaruche/files/fight_use_music.mp3");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if (rnd_fight_music == 1) {
                    try {
                        mp1.setDataSource("/data/data/com.tw.je.aoaruche/files/fight_use_music_2.mp3");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if (rnd_fight_music == 2) {
                    try {
                        mp1.setDataSource("/data/data/com.tw.je.aoaruche/files/fight_use_music_3.mp3");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }
            try {
                mp1.prepare();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mp1.setLooping(true);
            mp1.start();


            if (mp1 != null) {
                mp1.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    public boolean onError(MediaPlayer mp1, int what, int extra) {
                        // TODO Auto-generated method stub
                        // 釋放資源
                        try {

                            mp1.release();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return false;
                    }
                });
            }
        }
            return super.onStartCommand(intent, flags, startId);
        }


    @Override
    public void onDestroy(){//服務停止時停止播放音樂並釋放資源

        try{
            if(mp1!=null) {
                mp1.stop();
                mp1.release();
                mp1 = null;
            }
        }catch(IllegalStateException e){
            e.printStackTrace();
            mp1 = new MediaPlayer();
            // mp1 = MediaPlayer.create(this, R.raw.startmusic);
            //  可行
            boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
            if(sdCardExist) {
                int rnd_fight_music =((int)(Math.random()*2));
                File search_exist = getExternalFilesDir(null);
                String search_path= (search_exist.getPath() + "fight_use_music.mp3");
                if(rnd_fight_music==0) {
                    search_path = (search_exist.getPath() + "fight_use_music.mp3");
                }else if(rnd_fight_music==1) {
                    search_path = (search_exist.getPath() + "fight_use_music_2.mp3");
                }if(rnd_fight_music==2) {
                    search_path = (search_exist.getPath() + "fight_use_music_3.mp3");
                }
                File out_music = new File(search_path);
                if(out_music.exists()) {
                    try {
                        mp1.setDataSource(search_path);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    sd_is_exist=true;
                }
            }
            if(sd_is_exist==false) {
                int rnd_fight_music =((int)(Math.random()*2));
                if(rnd_fight_music==0) {
                    try {
                        mp1.setDataSource("/data/data/com.tw.je.aoaruche/files/fight_use_music.mp3");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }else  if(rnd_fight_music==1) {
                    try {
                        mp1.setDataSource("/data/data/com.tw.je.aoaruche/files/fight_use_music_2.mp3");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }else  if(rnd_fight_music==2) {
                    try {
                        mp1.setDataSource("/data/data/com.tw.je.aoaruche/files/fight_use_music_3.mp3");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }
            try {
                mp1.prepare();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mp1.setLooping(true);
            mp1.start();
        }
        super.onDestroy();
    }
}

