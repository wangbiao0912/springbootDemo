//package com.after;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//
//import java.io.File;
//
//
//@Slf4j
//public class test {
//
//
//    @Test
//    public void readHtml(){
//        this.pathName("/Users/wangbiao/磁盘备份");
//
//    }
//
//    public void pathName(String path){
//        File file = new File(path);
//        if (file.exists()) {
//            File[] files = file.listFiles();
//            if (files.length == 0) {
//                log.error("记录es失败，原因：{}文件夹为空！",".");
//                return;
//            } else {
//                //解析html以及其他文件
//                for (File file2 : files) {
//                    if (file2.isDirectory()) {
////                        System.out.println("文件夹:" + file2.getAbsolutePath());
//                        // 该方法重新pathNanmeTemp调用  readHtml(file2.getAbsolutePath());
//                        pathName(file2.getAbsolutePath());
//                    } else {
//                 //       log.info("编译后文件是："+file2.getName());
//                        //						if(file2.getName().toUpperCase().endsWith(".HTML")){
//                        if(file2.getName().indexOf("[www.zxit8.com 自学IT吧论坛] ")!=-1){
////                            System.out.println(file2.getName()+"文件:" + file2.getAbsolutePath());
//                            log.info(file2.getAbsolutePath()+">>>>"+file2.getPath()+(file2.getAbsolutePath().replace("[www.zxit8.com 自学IT吧论坛] ",""))+"");
//                            new File(file2.getAbsolutePath()).renameTo(new File(file2.getAbsolutePath().replace("[www.zxit8.com 自学IT吧论坛] ","")));
//                          //  new File(file2.getAbsolutePath()).delete();
//                          /*  NoverlChapterInfo noverlChapterInfo=novelUtils.novelParsingByHtml(novelInfo,pathNanmeTemp,file2,chapterInfoHashMap);
//                            if(noverlChapterInfo==null){
//                                log.error("记录入库失败，文件为空或者数据规则改变，路径：{}",pathNanmeTemp+"/"+file.getName());
//                            }else {
//                                chapterInfoHashMap.put(noverlChapterInfo.getCidx(),noverlChapterInfo);
//                            }*/
//                        }
//                    }
//                }
//            }
//        } else {
//            log.error("记录失败原因：");
//            System.out.println("文件不存在!");
//        }
//    }
//
//
//    @Test
//    public void test1(){
//        //打印行数
//        int line=10;
//        //每行打印变量，便于拼接
//        String s = "";
//        for(int i= line;i>0;i--){
//            s="";
//            for (int k = 0;k<line-i;k++){
//                s+="  ";
//            }
//            for (int j=1;j<2*i;j++){
//                s+=" "+(j>i?2*i-j:j)+"";
//            }
//            System.out.println(s);
//        }
//        System.gc();
//    }
//
//}
