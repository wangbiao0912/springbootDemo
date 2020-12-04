package com.after.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoHuiKanModel {

  /**
   * pageCount : 2
   * level : 2
   * totalCount : 37
   * curTvodNum : 0
   * data : [[{"startTime":"00:00","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"00:35","contentId":"00000000070049552142","programName":"630晚间版"},{"startTime":"00:35","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"00:38","contentId":"00000000070049640126","programName":"新闻旅游气象"},{"startTime":"00:38","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"01:35","contentId":"00000000070049640128","programName":"网罗天下晚间版"},{"startTime":"01:35","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"01:37","contentId":"00000000070049640130","programName":"新闻个人品德篇"},{"startTime":"01:37","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"02:00","contentId":"00000000070049640132","programName":"财经壹资讯"},{"startTime":"02:00","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"02:25","contentId":"00000000070049640134","programName":"重庆新闻联播"},{"startTime":"02:25","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"03:42","contentId":"00000000070049640136","programName":"630晚间版"},{"startTime":"03:42","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"04:37","contentId":"00000000070049640138","programName":"网罗天下晚间版"},{"startTime":"04:37","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"05:52","contentId":"00000000070049640140","programName":"630晚间版"},{"startTime":"05:52","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"06:12","contentId":"00000000070049552160","programName":"119在行动"},{"startTime":"06:12","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"06:30","contentId":"00000000070049640142","programName":"财经壹资讯"},{"startTime":"06:30","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"06:55","contentId":"00000000070049640144","programName":"重庆新闻联播"},{"startTime":"06:55","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"07:00","contentId":"00000000070049640146","programName":"新闻早间气象"},{"startTime":"07:00","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"08:00","contentId":"00000000070049552166","programName":"第1眼新闻早间版"},{"startTime":"08:00","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"08:55","contentId":"00000000070049640148","programName":"网罗天下早间版"},{"startTime":"08:55","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"09:00","contentId":"00000000070049640150","programName":"新闻家庭美德篇"},{"startTime":"09:00","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"09:45","contentId":"00000000070049640152","programName":"第1眼新闻上午版"},{"startTime":"09:45","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"11:00","contentId":"00000000070049640154","programName":"630晚间版"},{"startTime":"11:00","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"12:00","contentId":"00000000070049640156","programName":"网罗天下上午版"},{"startTime":"12:00","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"12:52","contentId":"00000000070049640158","programName":"第1眼新闻午间版"},{"startTime":"12:52","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"13:00","contentId":"00000000070049640160","programName":"新闻午间气象"},{"startTime":"13:00","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"13:20","contentId":"00000000070049640162","programName":"财经壹资讯"}],[{"startTime":"13:20","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"14:40","contentId":"00000000070049640164","programName":"630晚间版"},{"startTime":"14:40","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"15:00","contentId":"00000000070049640166","programName":"119在行动"},{"startTime":"15:00","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"15:20","contentId":"00000000070049640168","programName":"财经壹资讯"},{"startTime":"15:20","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"16:20","contentId":"00000000070049640170","programName":"第1眼新闻下午版"},{"startTime":"16:20","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"16:40","contentId":"00000000070049640172","programName":"119在行动"},{"startTime":"16:40","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"18:00","contentId":"00000000070049640174","programName":"630晚间版"},{"startTime":"18:00","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"18:22","contentId":"00000000070049640176","programName":"网罗天下傍晚版"},{"startTime":"18:22","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"18:30","contentId":"00000000070049640178","programName":"新闻区县气象"},{"startTime":"18:30","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"20:00","contentId":"00000000070049552192","programName":"天天630 故事人生"},{"startTime":"20:00","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"21:00","contentId":"00000000070049552194","programName":"网罗天下晚间版"},{"startTime":"21:00","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"22:20","contentId":"00000000070049640180","programName":"630晚间版"},{"startTime":"22:20","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"22:45","contentId":"00000000070049640182","programName":"重庆新闻联播"},{"startTime":"22:45","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"23:00","contentId":"00000000070049640184","programName":"财经壹资讯"},{"startTime":"23:00","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"23:20","contentId":"00000000070049640186","programName":"119在行动"},{"startTime":"23:20","isPlayable":"1","channelId":"ch00000000000000001251","endTime":"23:59","contentId":"00000000070049640188","programName":"630晚间版"}]]
   * tvodPageNum : 0
   */

  private int pageCount;
  private String level;
  private String totalCount;
  private String curTvodNum;
  private String tvodPageNum;
  private List<List<DataBean>> data;

  @NoArgsConstructor
  @Data
  public static class DataBean {

    /**
     * startTime : 00:00
     * isPlayable : 1
     * channelId : ch00000000000000001251
     * endTime : 00:35
     * contentId : 00000000070049552142
     * programName : 630晚间版
     */

    private String startTime;
    private String isPlayable;
    private String channelId;
    private String endTime;
    private String contentId;
    private String programName;
  }
}
