package com.mzj.springmvc.spittr.data;

import com.mzj.springmvc.spittr.Spittle;

import java.util.List;

/**
 * @Auther: mazhongjia
 * @Date: 2020/3/31 12:55
 * @Version: 1.0
 */
public interface SpittleRepository {
    /**
     * 查询spittle列表（分页方式查询）
     *
     * @param max Spittle列表中Spittle的ID最大值
     * @param count 要返回的列表中Spittle数量
     * @return
     */
    List<Spittle> findSpittles(long max, int count);

    /**
     * 根据spittle的ID查找Spittle
     * @param spittleId
     * @return
     */
    Spittle findOne(long spittleId);
}
