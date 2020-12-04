package com.after00.entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author wangbiao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FundList {
  private Integer id;

  /**
   * 编号
   */
  private String fundCode;

  private String fundName;

  /**
   * 份量
   */
  private BigDecimal fundShare;

  /**
   * 昨日时间
   */
  private String yesterdayTime;
  /**
   * 昨天净值
   */
  private BigDecimal yesterdayNetWorth;

  /**
   * 净值
   */
  private BigDecimal fundNetWorth;
  /**
   * 净值率
   */
  private BigDecimal netRate;

  private Date createTime;

  private Integer createName;
  /**
   * 当前收益
   */
  private BigDecimal currentYield;
  /**
   * 当前时间
   */
  private Date updateTime;

}