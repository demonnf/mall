package com.demon.mall.Common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Encryption {
    public final static String Encryption_code="56277yt5#¥%#¥";
    public final static String User_login="Loginer";
    public static String Uploadfile;
    @Value("${Upload.file.image}")
    public void getUploadfile(String uploadfile){
        Uploadfile=uploadfile;
    }
}
