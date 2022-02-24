package com.after.redis;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;

/**
 * 缓存穿透
 * 缓存穿透是指查询一个根本不存在的数据， 缓存层和存储层都不会命中， 通常出于容错的考虑，
 * 如果从存储 层查不到数据则不写入缓存层。
 * 缓存穿透将导致不存在的数据每次请求都要到存储层去查询， 失去了缓存保护后端存储的意义。
 * 造成缓存穿透的基本原因有两个
 *    第一， 自身业务代码或者数据出现问题。
 *    第二， 一些恶意攻击、 爬虫等造成大量空命中。
 */
public class RedisTests {

    /**
     * 方法一：取redis逻辑
     * @param key
     * @return
     */
    @Disabled
    @Test
    public void get(String key) {
/*
        // 从缓存中获取数据
        String cacheValue = cache.get(key); // 缓存为空
        if (StringUtils.isBlank(cacheValue)) {
            // 从存储中获取
            String storageValue = storage.get(key);
            cache.set(key, storageValue);
            // 如果存储数据为空， 需要设置一个过期时间(300秒)
            if (storageValue == null) {
                cache.expire(key, 60 * 5);
            }
            return storageValue;
        } else { // 缓存非空 return cacheValue;
            return"";
        }*/
    }


    /**
     布隆过滤器 对于恶意攻击，向服务器请求大量不存在的数据造成的缓存穿透，还可以用布隆过滤器先做一次过滤，
     对于不 存在的数据布隆过滤器一般都能够过滤掉，不让请求再往后端发送。
     //todo 当布隆过滤器说某个值存在时，这个值可 能不存在；当它说不存在时，那就肯定不存在。
     //todo  布隆过滤器就是一个大型的位数组和几个不一样的无偏 hash 函数。
     所谓无偏就是能够把元素的 hash 值算得 比较均匀。
     向布隆过滤器中添加 key 时，会使用多个 hash 函数对 key 进行 hash 算得一个整数索引值然后对位数组长度
     进行取模运算得到一个位置，每个 hash 函数都会算得一个不同的位置。
     再把位数组的这几个位置都置为1 就 完成了 add 操作。
     向布隆过滤器询问 key 是否存在时，跟 add 一样，也会把 hash 的几个位置都算出来，看看位数组中这几个 位置是否都为 1，只要有一个位为 0，那么说明布隆过滤器中这个key 不存在。
     如果都是 1，这并不能说明这 个 key 就一定存在，只是极有可能存在，因为这些位被置为 1 可能是因为其它的 key 存在所致。
     如果这个位 数组比较稀疏，这个概率就会很大，如果这个位数组比较拥挤，这个概率就会降低。
    //todo 这种方法适用于数据命中不高、 数据相对固定、 实时性低（通常是数据集较大） 的应用场景， 代码维护较为 复杂， 但是缓存空间占用很少。
     */
}
