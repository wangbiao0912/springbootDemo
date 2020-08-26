package com.after00.entity.vo;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FundRequestFundgz {


  /**
   * fundcode : 001717
   * name : 工银前沿医疗股票
   * jzrq : 2020-08-24
   * dwjz : 3.4860
   * gsz : 3.5069
   * gszzl : 0.60
   * gztime : 2020-08-25 09:47
   */

  /**
   * 基金编码
   */
  private String fundcode;

  /**
   * 名称
   */
  private String name;
  /**
   * 昨日日期
   */
  private String jzrq;
  /**
   * 昨日净值
   */
  private BigDecimal dwjz;
  /**
   * 净值
   */
  private BigDecimal gsz;
  /**
   * 净值率
   */
  private BigDecimal gszzl;
  /**
   * 净值日期点
   */
  private Date gztime;
}
