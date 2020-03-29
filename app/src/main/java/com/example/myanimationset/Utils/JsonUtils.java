package com.example.myanimationset.Utils;

import android.content.Context;

import com.example.myanimationset.Bean.HotelEntity;
import com.google.gson.Gson;


/**
 * Created by lyd10892 on 2016/8/23.
 */

public class JsonUtils {

    public static HotelEntity analysisJsonFile(Context context, String fileName){
        String content = FileUtils.readJsonFile(context,fileName);
        Gson gson = new Gson();
        HotelEntity entity = gson.fromJson(content, HotelEntity.class);
        return  entity;

    }
}
