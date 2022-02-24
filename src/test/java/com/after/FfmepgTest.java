package com.after;

import com.after00.Application;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FfmepgTest {

  private final String imageToCharUrl = "http://182.43.224.154:8093/predict/chinese_ocr_db_crnn_mobile";


  @Autowired
  RestTemplate restTemplate;
  @SneakyThrows
  @Test
  public void test1(){
    String ffmepgPath="/Users/wangbiao/software/ffmpeg-20200620-29ea4e1-macos64-static/bin/ffmpeg";
//    String path="rtsp://172.23.35.208:554/live/ch15062415311904217994.mpg?userid=i520661736@itv&stbip=172.254.145.66&clienttype=1&mediaid=00000000040049742683&ifcharge=1&time=20201028155208+08&life=172800&usersessionid=1880776&vcdnid=vcdn001&boid=vcdn001&ContentID=00000000040049742683&srcboid=vcdn001&columnid=&backupagent=172.23.35.208:554&ctype=4&playtype=2&Drm=0&EpgId=null&programid=00000000040049742683&contname=&fathercont=ch15062415311904217994&programbegin=20201027190000+08&programend=20201027193200+08&utcprogrambegin=20201027110000+00&utcprogramend=20201027113200+00&bp=0&authid=0&tscnt=0&tstm=0&tsflow=0&ifpricereqsnd=1&stbid=031004990070321000007497816E3ACB&nodelevel=3&terminalflag=1&bitrate=0&AuthInfo=mVTtG5tnJ7hg275bO7kK8BeAkjjJS1qkcyQDqYx0iHL2xxcMit9cl5UcdOLGpP2K7VftEptnAe3BxhkiK%2FGQ7A%3D%3D&tvodtype=2&usercharge=DE1D43024B1DAFC365F437080B7491A4";
    String imgPath="/Users/wangbiao/Downloads/temp2/";
        String path="/Users/wangbiao/Downloads/2.mp4";
    boolean s=screenImage(ffmepgPath,path,imgPath,imgPath+"out%d.jpg","350","240");
    Thread.sleep(60000);
    File file=new File(imgPath);
    File[] files= file.listFiles();
    List<String> list=new ArrayList<>();

    for (int i = 0; i < files.length; i++) {
      String base64=imageToBase64(files[i].getPath());
      list.add(base64);
      if (i%2==0){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("images", list);
        HttpHeaders headers = new HttpHeaders();
   //    headers.set("Accept-Encoding", "GZIP");
        headers.set("Content-type", "application/json; charset=UTF-8");

        headers.setAccept(Collections.singletonList(new MediaType("application","json")));

        headers.set("Accept-Encoding", "GZIP");

        headers.set("Connection", "Close");

        headers.set("Transfer-Encoding","chunked");
        HttpEntity<String> request = new HttpEntity<>(jsonObject.toJSONString(), headers);
        String imageToCharUrl = "http://182.43.224.154:8093/predict/chinese_ocr_db_crnn_mobile";
        ResponseEntity<String> response = restTemplate.postForEntity( imageToCharUrl, request , String.class );
        list.clear();
        log.info(response.getBody());
      }
    }


  }
  /**
   * 视频按帧截图
   * @param ffmpegPath   转码工具的存放路径
   * @param upFilePath   要截图的视频源文件
   * @param mediaPicPath 添加截取的图片的保存路径
   * @param width        截图的宽
   * @param height       截图的高
   * @return
   */
  public  boolean screenImage(String ffmpegPath, String upFilePath,String imgPath, String mediaPicPath, String width, String height) {
    File file=new File(imgPath);
    if (!file.exists()) {
      file.mkdirs();
    }
    File[] files = file.listFiles();
//遍历该目录下的文件对象
    for (File f: files){
      f.delete();
    }// 创建一个List集合来保存从视频中截取图片的命令
    List<String> cutpic = new ArrayList<String>();
    cutpic.add(ffmpegPath);
    cutpic.add("-i");
    cutpic.add(upFilePath); // 要截图的视频源文件
    cutpic.add("-y");
    cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
    cutpic.add("60"); // 添加持续时间为1毫秒
//    -y -t 30 -ss 0 -r 1 -f image2 out%d.jpg
    cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
    cutpic.add("1"); // 添加起始时间为第17秒

//    -r n ： 每秒截n帧
    cutpic.add("-r");
    cutpic.add("1");
//    cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
//    cutpic.add(width + "*" + height); // 添加截取的图片大小为350*240
    cutpic.add("-f");
    cutpic.add("image2");
    cutpic.add(mediaPicPath); // 添加截取的图片的保存路径
    ProcessBuilder builder = new ProcessBuilder();
    try {
      builder.command(cutpic);
      builder.redirectErrorStream(true);
      final Process p = builder.start();
      //从输入流中读取视频信息
      BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
      StringBuffer sb = new StringBuffer();
      String line = "";
      while ((line = br.readLine()) != null) {
        sb.append(line);
      }
//      System.out.println(sb.toString());
      br.close();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
  /**
   * 本地文件（图片、excel等）转换成Base64字符串
   * @param folderPath
   */
  public String imageToBase64(String folderPath) throws Exception {
    File file = new File(folderPath);
    String str = "";
    if (file.exists()) {
      BASE64Encoder encoder = new BASE64Encoder();
      InputStream in = new FileInputStream(file);
      byte[] data = new byte[in.available()];
      in.read(data);
      in.close();
      str = encoder.encode(data);
    }
    return str;
  }

}
