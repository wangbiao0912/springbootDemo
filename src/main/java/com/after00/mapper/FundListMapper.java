package com.after00.mapper;

import com.after00.entity.FundList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FundListMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(FundList record);

  int insertSelective(FundList record);

  FundList selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(FundList record);

  int updateByPrimaryKey(FundList record);


  List<FundList> getTodayEarningsByUserId(@Param("userId") Integer userId);

  FundList selectByCodeAndUserId(@Param("userId") Integer userId, @Param("fundCode") String fundCode);
}