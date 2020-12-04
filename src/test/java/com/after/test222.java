package com.after;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StringUtils;

@Slf4j

public class test222 {

  @Test
  public void test1(){
    String path="rtp://235.254.196.1:7980";
//    String path="http://182.43.229.186:8085/o3074gpeo7k.p701.1.mp4";
//    String path="rtsp://172.23.35.208:554/live/ch15062415311904217994.mpg?userid=i52015044123@itv&stbip=172.24.110.196&clienttype=1&mediaid=00000000040049893557&ifcharge=1&time=20201103164643+08&life=172800&usersessionid=2720739&vcdnid=vcdn001&boid=vcdn001&ContentID=00000000040049893557&srcboid=vcdn001&columnid=&backupagent=172.23.35.208:554&ctype=4&playtype=2&Drm=0&EpgId=null&programid=00000000040049893557&contname=&fathercont=ch15062415311904217994&programbegin=20201102131900+08&programend=20201102141000+08&utcprogrambegin=20201102051900+00&utcprogramend=20201102061000+00&bp=0&authid=0&tscnt=0&tstm=0&tsflow=0&ifpricereqsnd=1&stbid=331004990070321000007497816E38DF&nodelevel=3&terminalflag=1&bitrate=0&AuthInfo=EQViisVBXPAvBbDHGpgSLv%2BfwsItqIWpQzwLarKIDrBiLmR50H8ZNKC%2BScx9o7CX2SWCL5UqFrL62m14OltHdg%3D%3D&tvodtype=2&usercharge=0D16EEC1BC79B5452A0D7F762B09623A";
    getVideoTime(path,"/Users/wangbiao/software/ffmpeg-20200620-29ea4e1-macos64-static/bin/ffmpeg");
  }
  public static int getVideoTime(String video_path, String ffmpeg_path) {
    List<String> commands = new java.util.ArrayList<String>();
    commands.add(ffmpeg_path);

    if (video_path.contains("rtsp://")){
      commands.add("-stimeout");
      commands.add("5000000");
    }

    commands.add("-i");
    commands.add(video_path);
    System.out.println(commands.toString().replace(","," "));
    try {
      ProcessBuilder builder = new ProcessBuilder();
      builder.command(commands);
      //合并输出流和错误流
      builder.redirectErrorStream(true);

      final Process p = builder.start();

      //从输入流中读取视频信息
      BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
      StringBuffer sb = new StringBuffer();
      String line = "";

      long start1=System.currentTimeMillis();

      ScheduledExecutorService executorService = null;
      RequestTimerTask  timerTask= null;
      try {
        executorService = new ScheduledThreadPoolExecutor(1);

//        Timer t = new Timer();
//        TimerTask killer = new TimeoutProcessKiller(p);
//        t.schedule(killer, 5000);
        timerTask = new RequestTimerTask(p);
        executorService.scheduleAtFixedRate(timerTask, 1000,1000 * 30, TimeUnit.MILLISECONDS);

      } catch (Exception e) {
        e.printStackTrace();
      }

      int count1=0;
      while ((line = br.readLine()) != null) {
        count1++;
        System.out.println(count1);
        if (!StringUtils.isEmpty(line))
          sb.append(line+" \n");
//        if (System.currentTimeMillis()-start1>=10000){
//          log.error("shisnsdhjkfldkgjfskldfgsd");
//          break;
//        }
      }
      int result=p.waitFor();
      System.out.println(result);
      System.out.println(sb.toString());
      log.info(sb.toString().contains("No such file or directory")+"...");
      br.close();




      String audioString="Audio: (.*?), (.*?), (.*?), (.*?), (.*?)";
      String videoString="Video: (.*?), (.*?), (.*?), (.*?), (.*?), (.*?), (.*?)";
      Pattern audioPattern = Pattern.compile(audioString);
      Matcher audiom = audioPattern.matcher(sb.toString());
      if (audiom.find()) {
        log.info("音频类型：" + audiom.group(0) + "，赫兹：" + audiom.group(1) + "，采样率：" + audiom.group(5));
      }

      Pattern  videoPattern = Pattern.compile(videoString);
      Matcher videom = videoPattern.matcher(sb.toString());
      if (videom.find()) {
        log.info("视频类型：" + videom.group(0) + "视频信息：" + videom.group(1) + "视频宽度：" + videom.group(2)+"，每秒帧数：" + videom.group(3) );
      }


      //从视频信息中解析时长
//      String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
      String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (.*?)";
      Pattern pattern = Pattern.compile(regexDuration);
      Matcher m = pattern.matcher(sb.toString());
      if (m.find()) {
        int time = getTimelen(m.group(1));
        log.info(video_path+",视频时长："+time+", 开始时间："+m.group(2)+",比特率："+m.group(3)+"kb/s");
        return time;
      }


    } catch (Exception e) {
      e.printStackTrace();
    }

    return 0;
  }

  //格式:"00:00:10.68"
  private static int getTimelen(String timelen){
    int min= 0;
    if ("N/A".equals(timelen)) {
      return min;
    }
    try {
      min = 0;
      String[] strs = timelen.split(":");
      if (strs[0].compareTo("0") > 0) {
        min+=Integer.valueOf(strs[0])*60*60;//秒
      }
      if(strs[1].compareTo("0")>0){
        min+=Integer.valueOf(strs[1])*60;
      }
      if(strs[2].compareTo("0")>0){
        min+=Math.round(Float.valueOf(strs[2]));
      }
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return min;
  }
}
