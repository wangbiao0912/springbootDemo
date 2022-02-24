package com.after.cpu;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class MovieDetailVO {
    private Vod vod;
    private List<Media> mediaList;
    @Data
    public static class Vod {
        private String title;
        private String actors;
        private String country;
        private String year;
        private String score;
        private String type;
        private String directors;
        private String tags;
        private String subTitle;
        private String viewPoint;
        private String desc;
        private String code;
        private String provider;
        private String hImg;
        private String vImg;
        private String opImg1;
        private String opImg2;
        private String displayRunTime;
        private String contentType;
        private String serviceType;
        private String opShowFlag;
        private String licensenumber;
        // 排除信息
        private static final Map<String, List<String>> excludedColumnsToInfo = new HashMap<>();
        static {
            List<String> excludedInfos = new ArrayList<>();
            excludedInfos.add("演员信息-actors");
            excludedInfos.add("导演-directors");
            excludedColumnsToInfo.put("少儿", excludedInfos);
            excludedColumnsToInfo.put("综艺", excludedInfos);
            excludedColumnsToInfo.put("电竞", excludedInfos);
            excludedColumnsToInfo.put("动漫", excludedInfos);
        }
//        剧集名称、演员、导演、发行地区、上映年份、评分、节目类型、标签、内容描述、
//        一句话看点、剧照、海报、总时长、提供商名称、资费包类型+运营角标、内容许可证。
        public Map<String, ValidContentDesc> getValidTextKeyValueMap() {
            Map<String, ValidContentDesc> ret = new HashMap<>();
            ret.put("剧集名称-title", new ValidContentDesc(title, true));
            ret.put("演员信息-actors", new ValidContentDesc(actors, true));
            ret.put("导演-directors", new ValidContentDesc(directors, true));
            ret.put("发行地区-country", new ValidContentDesc(country, true));
            // 暂不检测 #issue 843
            // ret.put("上映年份-year", new ValidContentDesc(year, true));
//            ret.put("评分-score", new ValidContentDesc(score, true));
            ret.put("节目类型-type", new ValidContentDesc(type, true));
//            ret.put("标签-tags", new ValidContentDesc(tags, true));
            ret.put("内容描述-desc", new ValidContentDesc(desc, true));
//            ret.put("一句话看点-viewPoint", new ValidContentDesc(viewPoint, true));
//            ret.put("总时长-displayRunTime", new ValidContentDesc(displayRunTime, true));
//            ret.put("提供商名称-provider", new ValidContentDesc(provider, true));
//            ret.put("资费包类型-serviceType", new ValidContentDesc(serviceType, true));

//            if (TvDetailVO.ServiceType.CHARGE.equals(serviceType)) {
//                ret.put("运营角标-opShowFlag", new ValidContentDesc(opShowFlag, true));
//            }
//            ret.put("内容许可证-licensenumber", new ValidContentDesc(licensenumber, true));

            return ret;
        }

        public Map<String, ValidContentDesc> getValidPicKeyValueMap() {
            Map<String, ValidContentDesc> ret = new HashMap<>();
            ret.put("剧照-hImg", new ValidContentDesc(hImg, true));
            ret.put("海报-vImg", new ValidContentDesc(vImg, true));
            return ret;
        }
    }
    @Data
    public static class Media {
        private String code;
    }
}
