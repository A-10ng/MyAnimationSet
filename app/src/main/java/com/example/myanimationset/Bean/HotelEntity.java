package com.example.myanimationset.Bean;

import java.util.ArrayList;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2020/03/30
 * desc:
 */
public class HotelEntity {

    public ArrayList<TagsEntity> allTagsList;

    public class TagsEntity {
        public String tagsName;
        public ArrayList<TagInfo> tagInfoList;

        public class TagInfo {
            public String tagName;
        }
    }

}
