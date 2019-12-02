package com.after00.mapper;


import com.after00.entity.EnterpriseInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangbiao
 */
@Repository
public interface EnterpriseInfoMapper {
    /**
     * 通过名字查询用户信息
     */
    EnterpriseInfo findEnterpriseById(@Param("id") int id);

    int deleteEnterPrise(@Param("id") int id);

    int updateEnterprise(EnterpriseInfo enterpriseInfo);

    List<EnterpriseInfo> queryAllEnterprisePage();
}
