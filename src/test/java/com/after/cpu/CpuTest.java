package com.after.cpu;

import java.util.Map;

public class CpuTest {

    public static void main(String[] aggres) {
//        int num = 0;
//        long start = System.currentTimeMillis() / 1000;
//        while (true) {
//            num = num + 1;
//            if (num == Integer.MAX_VALUE) {
//                System.out.println("reset");
//                num = 0;
//            }
//            if ((System.currentTimeMillis() / 1000) - start > 1000) {
//                return;
//            }
//        }
        String text="222";

//        text.getClass().getDeclaredFields().getClass().getName();

        MovieDetailVO movieDetailVO = new MovieDetailVO();
        MovieDetailVO.Vod vod=new MovieDetailVO.Vod();
        vod.setTitle("222");
        vod.setType("333");
        vod.setHImg("1111");
        movieDetailVO.setVod(vod);

        for (Map.Entry<String, ValidContentDesc> per : movieDetailVO.getVod().getValidTextKeyValueMap().entrySet()) {
//                先不做拦截处理，除非耗性能处在处理
//                for (BackEndDialItemTaskRespose backEndDialItemTaskRespose : taskParam.getBackEndDialItemList()) {
//                    if (per.getValue().getVal().equals(backEndDialItemTaskRespose.getDialFiledEn())){
//            curParam.setFieldName();
//            per.getValue().getClass().getDeclaredField("")
//            curParam.setDialPosition(DialPositionEnums.VOD.getName());
//            validService.validText(curParam, taskParam, per.getValue().getVal(), per.getValue().isRequired());
            System.out.println("12222");
            per.getKey();
//                        break;
//                    }
//                }

        }
    }




}
