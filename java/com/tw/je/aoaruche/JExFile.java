/*
 * Copyright (c) 2018. JE-Chen
 */

package com.tw.je.aoaruche;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class JExFile {

    public static String Path="null";
    public static String File_name="null";
    public static File SD_Path=null;

    public void JExFile_Serach(String Path,String File_name) {

        this.Path=Path;
        this.File_name=File_name;

    }

    public void set_Path(String Path){

        this.Path=Path;

    }

    public void setFile_name(String File_name){

        this.File_name=File_name;

    }

    public void set_Path_and_File_name(String Path,String File_name){

        this.Path=Path;
        this.File_name=File_name;

    }

    public void set_outside_Path(File SD_Path){

        this.SD_Path=SD_Path;

    }

    public boolean Search_File_inside(String Path,String File_name){
        this.Path=Path;
        this.File_name=File_name;

        boolean file_exist = false;
        File exist = new File(Path);
        if(exist.getName().equals(File_name)) {
            if (exist.exists()) {
                file_exist = true;
            }else {
                file_exist = false;
            }
        }
        return file_exist;
    }

    public boolean Search_File_outside(File SD_Path,String File_name){
        this.SD_Path=SD_Path;
        this.File_name=File_name;
        boolean file_exist = false;
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if(sdCardExist) {
            String search_path = (SD_Path.getPath()+File_name);
            File search_file= new File(search_path);
            if(search_file.exists()){
                file_exist=true;
            }else {
                file_exist=false;
            }
        }
        return file_exist;
    }

    public boolean search_in_or_out_side(String Path,String File_name,File SD_Path){
        this.Path=Path;
        this.File_name=File_name;
        boolean file_exist = false;
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if(sdCardExist){
            String search_path = (SD_Path.getPath()+File_name);
            File search_out_file= new File(search_path);
            File search_in_file = new File(Path+File_name);
            if(search_out_file.exists()||search_in_file.exists()){
                file_exist=true;
            }else {
                file_exist=false;
            }
        }

        return file_exist;
    }



}


