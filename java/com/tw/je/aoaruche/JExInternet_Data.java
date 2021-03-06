/*
 * Copyright (c) 2018.JE-Chen
 */

package com.tw.je.aoaruche;

import android.graphics.Bitmap;

public class JExInternet_Data {

    public static int Download_count=0;

    public static int Download_total=2;

    public static int Download_state=0;

    public static int can_get_start=0;

    public int return_Download_state(){return this.Download_state;}

    public void add_Download_state(){this.Download_state+=1;}

    public void set_Download_state(int set){this.Download_state=set;}

    public static  boolean can_download=true;

    public static String[] Download_Array={
            //music
            "https://drive.google.com/uc?authuser=0&id=1jEwBocjNtQ_pZyewjaIFp76Y3128Nebc&export=download",
            //picture
            "https://drive.google.com/uc?authuser=0&id=1EYCB-YFM2cAENf9glgXFx5GOEVsDewpg&export=download"
    };

    public static String[] check_music={
            "boss_fight_music.mp3",
            "fail_music.mp3",
            "fight_use_music.mp3",
            "fight_use_music_2.mp3",
            "fight_use_music_3.mp3",
            "hero_attack_music.mp3",
            "stage_music.mp3",
            "startmusic.mp3",
            "story_music.mp3",
            "upgrade_music.mp3",
            "victory_music.mp3",
            "title_music.mp3"
    };

    public static String[] chech_picture={
            "backbutton.png",
            "background_1.png",
            "background_2.png",
            "background_3.png",
            "background_4.png",
            "backpack_button_.png",
            "backpack_inside.png",
            "backpack_lattice_question.png",
            "blood_attack.png",
            "blue_circle.png",
            "choicechapter_and_origi_interface.png",
            "continue_button.png",
            "dark_balloon.png",
            "dark_bow.png",
            "dark_eye.png",
            "dark_eye_protect.png",
            "dark_guard.png",
            "dark_hammer.png",
            "dark_magician.png",
            "dark_normal.png",
            "dark_soul.png",
            "dark_sword.png",
            "default_monster.png",
            "double_attack.png",
            "error.png",
            "fight_interface.png",
            "fire_angel_slime.png",
            "fneam.png",
            "fneam_tail.png",
            "fneam_tail_tooth.png",
            "fneam_tooth.png",
            "hero_attack_1.png",
            "hero_attack_2.png",
            "hero_attack_3.png",
            "hero_attack_4.png",
            "hero_attack_5.png",
            "hero_attack_6.png",
            "hero_skill_button.png",
            "hp_bar.png",
            "hp_liquid.png",
            "hp_time_bar_bottom.png",
            "im_sure_button.png",
            "insect_eye.png",
            "insect_eyes.png",
            "logoaoaruche.png",
            "lucius_attack.png",
            "lucius_choice_skill_button.png",
            "lucius_skill_button.png",
            "magic_a.png",
            "magic_a_studio.png",
            "magic_b.png",
            "magic_b_studio.png",
            "magic_c.png",
            "magic_c_studio.png",
            "magic_d.png",
            "magic_d_studio.png",
            "magic_e.png",
            "magic_e_studio.png",
            "magic_f.png",
            "magic_f_studio.png",
            "magic_g.png",
            "magic_g_studio.png",
            "magic_h.png",
            "magic_h_studio.png",
            "magic_i.png",
            "magic_i_studio.png",
            "magic_j.png",
            "magic_j_studio.png",
            "magic_k.png",
            "magic_k_studio.png",
            "magic_l.png",
            "magic_l_studio.png",
            "magic_m.png",
            "magic_m_studio.png",
            "magic_n.png",
            "magic_n_studio.png",
            "magic_o.png",
            "magic_o_studio.png",
            "magic_p.png",
            "magic_p_studio.png",
            "magic_q.png",
            "magic_q_studio.png",
            "magic_r.png",
            "magic_r_studio.png",
            "magic_s.png",
            "magic_s_studio.png",
            "magic_t.png",
            "magic_t_studio.png",
            "magic_u.png",
            "magic_u_studio.png",
            "magic_v.png",
            "magic_v_studio.png",
            "magic_w.png",
            "magic_w_studio.png",
            "magic_x.png",
            "magic_x_studio.png",
            "magic_y.png",
            "magic_y_studio.png",
            "magic_z.png",
            "magic_z_studio.png",
            "maker.png",
            "makerbackbutton.png",
            "makerbutton.png",
            "monster_attack_1.png",
            "monster_attack_2.png",
            "monster_attack_3.png",
            "monster_attack_4.png",
            "monster_attack_5.png",
            "nothingness.png",
            "nothingness_2.png",
            "nothingness_attack.png",
            "nothingness_green.png",
            "nothingness_green_button.png",
            "nothingness_green_protect.png",
            "open_fbpage_button.png",
            "open_game_site.png",
            "pause_button.png",
            "pet_button.png",
            "pet_interface.png",
            "pet_rainbow_slime.png",
            "polluted_cross.png",
            "read_data_button.png",
            "rect_eye.png",
            "rect_eye_protect.png",
            "red_eye.png",
            "red_eye_boss.png",
            "red_eye_bow.png",
            "red_eye_hammer.png",
            "rect_eye_protect.png",
            "red_eye_magician.png",
            "red_eye_sword.png",
            "red_eye_warrior.png",
            "resetbutton.png",
            "save_data_button.png",
            "sealinside.png",
            "sealoutside.png",
            "share_game_button.png",
            "share_page_button.png",
            "skill_button.png",
            "slime.png",
            "special_attack.png",
            "special_stage_button.png",
            "special_stage_magic_circle.png",
            "square_smile_blue.png",
            "square_smile_four.png",
            "square_smile_green.png",
            "square_smile_red.png",
            "square_smile_three.png",
            "square_smile_yellow.png",
            "start_skill_button.png",
            "start_skill_button_hid.png",
            "start_skill_button_hid_1.png",
            "start_skill_button_hid_2.png",
            "start_skill_button_hid_3.png",
            "start_skill_button_hid_4.png",
            "start_skill_button_hid_5.png",
            "start_special_stage.png",
            "startbutton.png",
            "story_1_1.png",
            "story_1_1_hid.png",
            "story_1_2.png",
            "story_1_2_hid.png",
            "story_1_3.png",
            "story_1_3_hid.png",
            "story_1_4.png",
            "story_1_4_hid.png",
            "story_1_5.png",
            "story_1_5_hid.png",
            "story_1_6.png",
            "story_1_6_hid.png",
            "story_1_7.png",
            "story_1_7_hid.png",
            "story_1_8.png",
            "story_1_8_hid.png",
            "story_1_9.png",
            "story_1_9_hid.png",
            "story_1_10.png",
            "story_1_10_hid.png",
            "story_1_11.png",
            "story_1_11_hid.png",
            "story_1_12.png",
            "story_1_12_hid.png",
            "story_1_13.png",
            "story_1_13_hid.png",
            "story_1_14.png",
            "story_1_14_hid.png",
            "story_1_15.png",
            "story_1_15_hid.png",
            "story_1_16.png",
            "story_1_16_hid.png",
            "story_1_17.png",
            "story_1_17_hid.png",
            "story_1_18.png",
            "story_1_18_hid.png",
            "story_1_19.png",
            "story_1_19_hid.png",
            "story_1_20.png",
            "story_1_20_hid.png",
            "story_1_21.png",
            "story_1_21_hid.png",
            "story_1_22.png",
            "story_1_22_hid.png",
            "story_1_23.png",
            "story_1_23_hid.png",
            "story_1_24.png",
            "story_1_24_hid.png",
            "story_1_25.png",
            "story_1_25_hid.png",
            "story_1_26.png",
            "story_1_26_hid.png",
            "story_1_27.png",
            "story_1_27_hid.png",
            "story_1_28.png",
            "story_1_28_hid.png",
            "story_1_29.png",
            "story_1_29_hid.png",
            "story_1_30.png",
            "story_1_30_hid.png",
            "story_2_1.png",
            "story_2_1_hid.png",
            "story_2_2.png",
            "story_2_2_hid.png",
            "story_2_3.png",
            "story_2_3_hid.png",
            "story_2_4.png",
            "story_2_4_hid.png",
            "story_2_5.png",
            "story_2_5_hid.png",
            "story_2_6.png",
            "story_2_6_hid.png",
            "story_2_7.png",
            "story_2_7_hid.png",
            "story_2_8.png",
            "story_2_8_hid.png",
            "story_2_9.png",
            "story_2_9_hid.png",
            "story_2_10.png",
            "story_2_10_hid.png",
            "story_interface.png",
            "story_interface_magic_circle.png",
            "story_one_luo.png",
            "studio_button.png",
            "time_bar.png",
            "up_critical_lv_button.png",
            "up_dodge_lv_button.png",
            "up_exercise_lv_button.png",
            "up_great_mage_lv_button.png",
            "up_healing_lv_button.png",
            "up_hp_lv.png",
            "up_interface.png",
            "up_magic_power.png",
            "up_miracle_of_live_lv_button.png",
            "up_money_skill_lv_button.png",
            "up_skill_interface.png",
            "update_data_button.png",
            "update_what.png",
            "user_interface.png",
            "user_interface_fight.png",
            "user_interface_magic_circle.png",
            "user_interface_up_level.png",
            "wood_magic_circle.png",
            "arch_sword.png",
            "armor.png",
            "equipment_null.png",
            "healing_armor.png",
            "iron_sword.png",
            "shadow_light_book.png",
            "time_souls.png",
            "weapon_null.png",
            "backpack_mix.png",
            "backpack_back.png"
    };

    public void JExInternet_Data(){


    }

    public void setDownload_Count(int count){this.Download_count=count;}

    public void addDownload_Count(){this.Download_count+=1;}

    public long Search_Download_Counter(){return this.Download_count;}


}