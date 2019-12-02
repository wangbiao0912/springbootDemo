package com.after00.utils;

public class Constants {
    public static final String ORDER_SENDING = "0"; //发送中

    public static final String ORDER_SEND_SUCCESS = "1"; //成功

    public static final String ORDER_SEND_FAILURE = "2"; //失败
    public static final String ORDER_SEND_SUCCES = "3"; //成功
    public static final String ORDER_SEND_AWAY = "4"; //丢弃任务

    public static final int ORDER_TIMEOUT = 1; /*分钟超时单位：min*/
    // mq
    public static final String MQ_SINGLE_STEP_PRO = "pro";
    public static final String MQ_SINGLE_STEP_TRANS = "trans";
    public static final String MQ_SINGLE_STEP_DEAL = "deal";
    public static final String MQ_SINGLE_STEP_CANCELLATION = "cancellation";
    public static final String MQ_SINGLE_TYPE_1 = "1"; //市内联单
    public static final String MQ_SINGLE_TYPE_2 = "2"; //跨省联单
    /**
     * 日期格式化
     */
    public static final String DATE_FORMAT_TYPE = "yyyy-MM-dd HH:mm:ss";
}
