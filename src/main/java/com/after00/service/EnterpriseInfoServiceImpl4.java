package com.after00.service;

import com.after00.entity.EnterpriseInfo;
import com.after00.service.impl.EnterpriseServiceImpl;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 缓存穿透
 * 击穿：恶意攻击  大量查询不存在的记录
 *
 * 解决方法：布隆过滤器
 */

@CacheConfig(cacheNames = "enterprise")
//@Service("EnterpriseInfoServiceImpl4")
public class EnterpriseInfoServiceImpl4 extends EnterpriseServiceImpl {
    private BloomFilter<String> bf = null; //等效成一个set集合
    List<EnterpriseInfo> enterpriseInfos = new ArrayList<>();

    @PostConstruct //对象创建后，自动调用本方法
    public void init() {//在bean初始化完成后，实例化bloomFilter,并加载数据
        List<EnterpriseInfo> baseResponse = this.enterpriseInfos;

        //当成一个SET----- 占内存，比hashset占得小很多
        bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), baseResponse.size());// 32个
        for (EnterpriseInfo p : baseResponse) {
            bf.put(p.getId());
        }
    }

    @Cacheable
    public EnterpriseInfo detail(Integer provinceid) {
        //先判断布隆过滤器中是否存在该值，值存在才允许访问缓存和数据库
        if (!bf.mightContain(provinceid.toString())) {
            System.out.println("非法访问--------" + System.currentTimeMillis());
            return null;
        }
        System.out.println("数据库中得到数据--------" + System.currentTimeMillis());
        EnterpriseInfo provinces = super.queryEnterpriseById(provinceid);
        enterpriseInfos.add(provinces);
        return provinces;
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public EnterpriseInfo update(EnterpriseInfo entity) {
        super.updateEnterprise(entity);
        return entity;
    }

//    @CacheEvict(value = "province",key = "#entity.provinceid")
//    public EnterpriseInfo add(EnterpriseInfo entity) {
//        super.(entity);
//        return entity;
//    }


    @CacheEvict("province")
    public void delete(Integer id) {
        super.deleteEnterPrise(id);
    }
}


