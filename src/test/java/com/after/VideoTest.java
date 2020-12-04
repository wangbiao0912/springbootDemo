package com.after;

import com.after.domain.AllChannelIdModel;
import com.after.domain.VideoHuiKanModel;
import com.after.domain.VideoHuiKanModel.DataBean;
import com.after00.Application;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VideoTest {

  @Autowired
  RestTemplate restTemplate;

  @Test
  public void test1() {
    // 获取会看的数据
//    String channelId = "ch00000000000000001251";
    String dateIndex = "-6";
    String dateSize = "8";
    String tVodNumPerPage = "22";
    String urlAllChannelId = "http://172.23.36.170:8080/iptvepg/frame1361/live_list.jsp?curAreaId=1&curIndex=0&areaDarkIndex=0&epg_infoEnc=rDNZhbmvLYL2WVsGxLcAyeP%2FWPWqH0tB8HBxkAAZc%2FGBZzYm4%2FbU8MzGpM%2F05SoHlsz7N8jgic0j%0AW%2F5Rkmjf7zOAQLZevoxhKASmDjmoeLETTcN677yuKvmBtGJDGpk4VbcmR7znjyL9XMaCoP1XTgjZ%0AvbTDo471EZVFEL0DvwvGoctp40K5E5vh1AMu3AhtuBB%2B1%2Bh0YvDen6FVOichIpbM%2BzfI4InNDC4W%0A1WDbEZBcf4h1wBsEG7ms%2FJ%2B2%2BOe8jZGO14F86bCQ6y4Mm5jW88AGiZT%2FzOpcxCYbYMgnRMLNdoao%0AFcH5GRFUOCTSzyOzzSTbBicJzlgQ9372J905b0tOLkAQf1kBkRNgXaNSSsGw5LFHcJ4ZnyUdKJ7m%0AJA8%2B352UppwxtvjJz8vh1HK4s%2Fo1SlSsQId3qesITVBr4axBOO72v%2FfwE8LUG8IJ0Y4P6%2BG225Uw%0A0%2BFjorjHfz%2Bi85Z3THD2Mbnp2dZhsold5yXjCTDJHGQYdu6NMVjecisMPHDZgv4Oax5aksdmf%2FJN%0A76NK0aF0gK6E2izCp6PJ3MkWzqYfwE82zZEqWopzLy2N0wT3ujuEU6aeZ5fMF3KIir2vRDkuVc5W%0AodNwlSx6lb3UqqhGSWaFB%2ButFB3wpEb1YsHgUn3FCOqjgjYZucYh%2FsscDy%2FraXqil9MOMRYpny0%3D&returnurl=http%3A%2F%2F172.23.36.170%3A8080%2Fiptvepg%2Fframe1361%2Fportal.jsp%3Fpage%3DliveRec%26focusSt%3D0%2C2%2C0%26curAreaTop%3D0";
    String header = "JSESSIONID=6EE829B33E0C630571382B652F2F482B; easyVersion=0; epg_infoEnc=rDNZhbmvLYL2WVsGxLcAyeP/WPWqH0tB8HBxkAAZc/GBZzYm4/bU8MzGpM/05SoHlsz7N8jgic0j%0AW/5Rkmjf7zOAQLZevoxhKASmDjmoeLETTcN677yuKvmBtGJDGpk4VbcmR7znjyL9XMaCoP1XTgjZ%0AvbTDo471EZVFEL0DvwvGoctp40K5E5vh1AMu3AhtuBB+1+h0YvDen6FVOichIpbM+zfI4InNDC4W%0A1WDbEZBcf4h1wBsEG7ms/J+2+Oe8jZGO14F86bCQ6y4Mm5jW88AGiZT/zOpcxCYbYMgnRMLNdoao%0AFcH5GRFUOCTSzyOzzSTbBicJzlgQ9372J905b0tOLkAQf1kBkRNgXaNSSsFIpfuly2GDCNXMZtVu%0AwWcjCnyzicdqkWX/Dx4UrKZOAoRhzTgTc/1WzSTbBicJzljMCe9JxbXQCoUUCLQqP/qsxmrXn1wg%0AbdLj/1j1qh9LQfBwcZAAGXPxgWc2JuP21PDMxqTP9OUqB5bM+zfI4InNDC4W1WDbEZAAiPWtquib%0AF5ZAY3+8NNGrF9jRMLYZKx0kvbmcUanyxG8ZYOjd4CvbKXOEgbcYJG/PR5yJDOq4avQlRVGpWqwX%0AisTJeXuLnCzE/elOtrO6Lfa7BljpNpJonA9sbawBSHX30ncKggk85/a7BljpNpJod6CwcNuGtbc%3D; token=tdf1250e31cddf4061_15";

    HttpHeaders headers = new HttpHeaders();
    headers.add("Cookie", header);
    HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
    AllChannelIdModel allChannelIdModel = new AllChannelIdModel();
    //获取所有的栏目
    ResponseEntity<String> allChannelIdResEntity = restTemplate.exchange(urlAllChannelId, HttpMethod.GET, requestEntity, String.class);
    String allChannelIdBody = allChannelIdResEntity.getBody();
    try {
      Document document = Jsoup.parse(allChannelIdBody);
      Elements e = document.getElementsByTag("script").eq(0);
      for (Element element : e) {

        /*取得JS变量数组*/
        String[] data = element.data().toString().split("channelData");

        /*取得单个JS变量*/
        for (String variable : data) {
          /*过滤variable为空的数据*/
          if (variable.contains("=")) {
            /*取到满足条件的JS变量*/
            String[] kvp = variable.split("=");
            String allChannelId = kvp[1].trim().substring(0, kvp[1].trim().length() - 1);
            allChannelIdModel = JSON.toJavaObject(JSON.parseObject(JSON.parseArray(allChannelId).get(0).toString()), AllChannelIdModel.class);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    int count = 0;
    for (int k = 0; k < allChannelIdModel.getChanList().size(); k++) {
      String channelName = allChannelIdModel.getChanList().get(k).getChannelName();
      int channelIndex = allChannelIdModel.getChanList().get(k).getChannelIndex();
      String url = "http://172.23.36.170:8080/iptvepg/frame1265/utilsData/tVodProgramList.jsp?channelId={channelId}&dateIndex={dateIndex}&dateSize={dateSize}&tVodNumPerPage={tVodNumPerPage}";
      url = url.replaceAll("\\{dateIndex}", dateIndex);
      url = url.replaceAll("\\{dateSize}", dateSize);
      url = url.replaceAll("\\{tVodNumPerPage}", tVodNumPerPage);
      url = url.replaceAll("\\{channelId}", allChannelIdModel.getChanList().get(k).getChannelID());

      ResponseEntity<String> resEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
      String videoRecommendedBody = resEntity.getBody();
//    String videoRecommendedBody = "[{\"level\":\"1\",\"data\":[\"10月20日\",\"10月21日\",\"10月22日\",\"10月23日\",\"10月24日\",\"10月25日\",\"10月26日\",\"10月27日\"],\"curDataNum\":6},{\"pageCount\":2,\"level\":\"2\",\"totalCount\":\"37\",\"curTvodNum\":\"0\",\"data\":[[{\"startTime\":\"00:00\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"00:35\",\"contentId\":\"00000000070049552142\",\"programName\":\"630晚间版\"},{\"startTime\":\"00:35\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"00:38\",\"contentId\":\"00000000070049640126\",\"programName\":\"新闻旅游气象\"},{\"startTime\":\"00:38\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"01:35\",\"contentId\":\"00000000070049640128\",\"programName\":\"网罗天下晚间版\"},{\"startTime\":\"01:35\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"01:37\",\"contentId\":\"00000000070049640130\",\"programName\":\"新闻个人品德篇\"},{\"startTime\":\"01:37\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"02:00\",\"contentId\":\"00000000070049640132\",\"programName\":\"财经壹资讯\"},{\"startTime\":\"02:00\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"02:25\",\"contentId\":\"00000000070049640134\",\"programName\":\"重庆新闻联播\"},{\"startTime\":\"02:25\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"03:42\",\"contentId\":\"00000000070049640136\",\"programName\":\"630晚间版\"},{\"startTime\":\"03:42\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"04:37\",\"contentId\":\"00000000070049640138\",\"programName\":\"网罗天下晚间版\"},{\"startTime\":\"04:37\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"05:52\",\"contentId\":\"00000000070049640140\",\"programName\":\"630晚间版\"},{\"startTime\":\"05:52\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"06:12\",\"contentId\":\"00000000070049552160\",\"programName\":\"119在行动\"},{\"startTime\":\"06:12\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"06:30\",\"contentId\":\"00000000070049640142\",\"programName\":\"财经壹资讯\"},{\"startTime\":\"06:30\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"06:55\",\"contentId\":\"00000000070049640144\",\"programName\":\"重庆新闻联播\"},{\"startTime\":\"06:55\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"07:00\",\"contentId\":\"00000000070049640146\",\"programName\":\"新闻早间气象\"},{\"startTime\":\"07:00\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"08:00\",\"contentId\":\"00000000070049552166\",\"programName\":\"第1眼新闻早间版\"},{\"startTime\":\"08:00\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"08:55\",\"contentId\":\"00000000070049640148\",\"programName\":\"网罗天下早间版\"},{\"startTime\":\"08:55\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"09:00\",\"contentId\":\"00000000070049640150\",\"programName\":\"新闻家庭美德篇\"},{\"startTime\":\"09:00\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"09:45\",\"contentId\":\"00000000070049640152\",\"programName\":\"第1眼新闻上午版\"},{\"startTime\":\"09:45\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"11:00\",\"contentId\":\"00000000070049640154\",\"programName\":\"630晚间版\"},{\"startTime\":\"11:00\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"12:00\",\"contentId\":\"00000000070049640156\",\"programName\":\"网罗天下上午版\"},{\"startTime\":\"12:00\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"12:52\",\"contentId\":\"00000000070049640158\",\"programName\":\"第1眼新闻午间版\"},{\"startTime\":\"12:52\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"13:00\",\"contentId\":\"00000000070049640160\",\"programName\":\"新闻午间气象\"},{\"startTime\":\"13:00\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"13:20\",\"contentId\":\"00000000070049640162\",\"programName\":\"财经壹资讯\"}],[{\"startTime\":\"13:20\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"14:40\",\"contentId\":\"00000000070049640164\",\"programName\":\"630晚间版\"},{\"startTime\":\"14:40\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"15:00\",\"contentId\":\"00000000070049640166\",\"programName\":\"119在行动\"},{\"startTime\":\"15:00\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"15:20\",\"contentId\":\"00000000070049640168\",\"programName\":\"财经壹资讯\"},{\"startTime\":\"15:20\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"16:20\",\"contentId\":\"00000000070049640170\",\"programName\":\"第1眼新闻下午版\"},{\"startTime\":\"16:20\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"16:40\",\"contentId\":\"00000000070049640172\",\"programName\":\"119在行动\"},{\"startTime\":\"16:40\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"18:00\",\"contentId\":\"00000000070049640174\",\"programName\":\"630晚间版\"},{\"startTime\":\"18:00\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"18:22\",\"contentId\":\"00000000070049640176\",\"programName\":\"网罗天下傍晚版\"},{\"startTime\":\"18:22\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"18:30\",\"contentId\":\"00000000070049640178\",\"programName\":\"新闻区县气象\"},{\"startTime\":\"18:30\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"20:00\",\"contentId\":\"00000000070049552192\",\"programName\":\"天天630 故事人生\"},{\"startTime\":\"20:00\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"21:00\",\"contentId\":\"00000000070049552194\",\"programName\":\"网罗天下晚间版\"},{\"startTime\":\"21:00\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"22:20\",\"contentId\":\"00000000070049640180\",\"programName\":\"630晚间版\"},{\"startTime\":\"22:20\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"22:45\",\"contentId\":\"00000000070049640182\",\"programName\":\"重庆新闻联播\"},{\"startTime\":\"22:45\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"23:00\",\"contentId\":\"00000000070049640184\",\"programName\":\"财经壹资讯\"},{\"startTime\":\"23:00\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"23:20\",\"contentId\":\"00000000070049640186\",\"programName\":\"119在行动\"},{\"startTime\":\"23:20\",\"isPlayable\":\"1\",\"channelId\":\"ch00000000000000001251\",\"endTime\":\"23:59\",\"contentId\":\"00000000070049640188\",\"programName\":\"630晚间版\"}]],\"tvodPageNum\":\"0\"}]";

      VideoHuiKanModel videoHuiKanModel = JSON.toJavaObject(JSON.parseObject(JSON.parseArray(videoRecommendedBody).get(1).toString()), VideoHuiKanModel.class);


      List<List<DataBean>> lists = videoHuiKanModel.getData();
      for (int i = 0; i < lists.size(); i++) {
        for (int i1 = 0; i1 < lists.get(i).size(); i1++) {
          try {
            // 当前信息时间为
            long diff = 0;
            DataBean dataBean = lists.get(i).get(i1);
            String beginTime = dataBean.getStartTime();
            String endTime = dataBean.getEndTime();

            try {

              String[] start1 = beginTime.split(":");
              int startAll = (Integer.parseInt(start1[0]) * 60) + Integer.parseInt(start1[1]);

              String[] end1 = endTime.split(":");

              int endHousr = Integer.parseInt(end1[0]);
              if (Integer.parseInt(start1[0]) > Integer.parseInt(end1[0])) {
                endHousr = endHousr + 24;
              }
              int endAll = (endHousr * 60) + Integer.parseInt(end1[1]);
              diff = (endAll - startAll) * 60;
            } catch (Exception e) {
              e.printStackTrace();
              log.error("时间格式错误");
              continue;
            }
            count++;
          log.info(count+"当前信息的时间为："+diff);
//          log.info(count+"当前信息的时间为："+diff);
            String newUrl = "http://172.23.36.170:8080/iptvepg/frame1265/utilsData/tVodProgramUrl.jsp?contentId={contentId}&channelId={channelId}";
            newUrl = newUrl.replaceAll("\\{contentId}", dataBean.getContentId());
            newUrl = newUrl.replaceAll("\\{channelId}", dataBean.getChannelId());

            ResponseEntity<String> videoUrlResp = restTemplate.exchange(newUrl.toString(), HttpMethod.GET, requestEntity, String.class);
            String tvodUrlBody = videoUrlResp.getBody();
//          String tvodUrlBody = "[{\"tvodUrl\":\"rtsp://172.23.35.208:554/live/ch12072117142442867381.mpg?userid=i520661736@itv&stbip=172.254.154.137&clienttype=1&mediaid=00000000040049409527&ifcharge=1&time=20201023171736+08&life=172800&usersessionid=1610554&vcdnid=vcdn001&boid=vcdn001&ContentID=00000000040049409527&srcboid=vcdn001&columnid=&backupagent=172.23.35.208:554&ctype=4&playtype=2&Drm=0&EpgId=null&programid=00000000040049409527&contname=&fathercont=ch12072117142442867381&programbegin=20201016070000+08&programend=20201016080000+08&utcprogrambegin=20201015230000+00&utcprogramend=20201016000000+00&bp=0&authid=0&tscnt=0&tstm=0&tsflow=0&ifpricereqsnd=1&stbid=031004990070321000007497816E3ACB&nodelevel=3&terminalflag=1&bitrate=0&AuthInfo=mVTtG5tnJ7hg275bO7kK8FfDqK0qFGPuXl20x6%2FYC8BPeCUQ3lgPCK45N7c70lYanVx%2FSzYQbJpmD3Zo9wj%2FkA%3D%3D&tvodtype=2&usercharge=63CA2CD3B51F421149D04480764E0670\"}]";
            JSONObject jsonObject = JSONObject.parseObject(JSON.parseArray(tvodUrlBody).get(0).toString());
            String tvodUrl = jsonObject.getString("tvodUrl");
            int videoTime = getVideoTime(tvodUrl, "/Users/wangbiao/software/ffmpeg-20200620-29ea4e1-macos64-static/bin/ffmpeg");
            if ((videoTime- diff)>=60) {
              log.warn(
                  "错误=====倒数第几天的数据=" + dateIndex + "channelName=" + channelName + "频道号：channelIndex=" + channelIndex + "---count=" + count + " 当前时间对不上，片面为：" + dataBean
                      .getProgramName() + "----栏目信息时间：" + diff + " 秒，实际：videoTime=" + videoTime + " 开始时间：" + dataBean.getStartTime() + " 结束时间" + dataBean
                      .getEndTime());
            }
//          log.info("地址为：" + tvodUrl);
          } catch (Exception e) {
            e.printStackTrace();
          }

        }
      }
    }

  }

  public static int getVideoTime(String video_path, String ffmpeg_path) {
    List<String> commands = new java.util.ArrayList<String>();
    commands.add(ffmpeg_path);
    commands.add("-t");
    commands.add("15");

    commands.add("-i");
    commands.add(video_path);
//    System.out.println(commands.toString().replace(","," "));
    try {
      ProcessBuilder builder = new ProcessBuilder();
      builder.command(commands);
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

      String audioString = "Audio: (.*?), (.*?), (.*?), (.*?), (.*?)";
      String videoString = "Video: (.*?), (.*?), (.*?), (.*?), (.*?), (.*?), (.*?)";
      Pattern audioPattern = Pattern.compile(audioString);
      Matcher audiom = audioPattern.matcher(sb.toString());
      if (audiom.find()) {
        log.info("音频类型：" + audiom.group(0) + "，赫兹：" + audiom.group(1) + "，采样率：" + audiom.group(5));
      }

      Pattern videoPattern = Pattern.compile(videoString);
      Matcher videom = videoPattern.matcher(sb.toString());
      if (videom.find()) {
        log.info("视频类型：" + videom.group(0) + "视频信息：" + videom.group(1) + "视频宽度：" + videom.group(2) + "，每秒帧数：" + videom.group(3));
      }

      //从视频信息中解析时长
//      String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
      String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (.*?)";
      Pattern pattern = Pattern.compile(regexDuration);
      Matcher m = pattern.matcher(sb.toString());
      if (m.find()) {
        int time = getTimelen(m.group(1));
        log.info(",视频时长：" + time + ", 开始时间：" + m.group(2) + ",比特率：" + m.group(3) + "kb/s");
        return time;
      }


    } catch (Exception e) {
      e.printStackTrace();
    }

    return 0;
  }

  //格式:"00:00:10.68"
  private static int getTimelen(String timelen) {
    int min = 0;
    String strs[] = timelen.split(":");
    if (strs[0].compareTo("0") > 0) {
      min += Integer.valueOf(strs[0]) * 60 * 60;//秒
    }
    if (strs[1].compareTo("0") > 0) {
      min += Integer.valueOf(strs[1]) * 60;
    }
    if (strs[2].compareTo("0") > 0) {
      min += Math.round(Float.valueOf(strs[2]));
    }
    return min;
  }
}
