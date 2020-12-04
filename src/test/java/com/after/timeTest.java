package com.after;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class timeTest {
  @Test
  public void test(){
//    //先获取到当前的时间，并且拼接成HH:mm格式的字符串。
//    Calendar c = Calendar.getInstance();
//    int hour = c.get(Calendar.HOUR_OF_DAY);
//    int minute = c.get(Calendar.MINUTE);
//    String hour_minute = hour + ":" + minute;

//把当前时间和要比较的时间转换为Date类型，目的在于得到这两个时间的毫秒值

    String beginTime="23:40";
    String endTime="00:10";
    try {



      String[] start1=beginTime.split(":");
      int startAll=(Integer.parseInt(start1[0])*60)+Integer.parseInt(start1[1]);

      String[] end1=endTime.split(":");

      int endHousr=Integer.parseInt(end1[0]);
      if (Integer.parseInt(start1[0])>Integer.parseInt(end1[0])){
        endHousr=endHousr+24;
      }
      int endAll=(endHousr*60)+Integer.parseInt(end1[1]);
      long diff = endAll -startAll;
      log.info("......秒数："+diff);

    } catch (Exception e) {
      e.printStackTrace();
      log.error("时间格式错误");
    }

//获得这两个时间的毫秒值后进行处理(因为我的需求不需要处理时间大小，所以此处没有处理，可以判断一下哪个大就用哪个作为减数。)


//此处用毫秒值除以分钟再除以毫秒既得两个时间相差的分钟数


  }
}
