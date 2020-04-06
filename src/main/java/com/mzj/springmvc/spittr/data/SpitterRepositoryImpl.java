package com.mzj.springmvc.spittr.data;

import com.mzj.springmvc.spittr.Spitter;

/**
 * @Auther: mazhongjia
 * @Date: 2020/4/6 13:07
 * @Version: 1.0
 */
public class SpitterRepositoryImpl implements SpitterRepository{
    @Override
    public Spitter save(Spitter spitter) {
        return spitter;
    }

    @Override
    public Spitter findByUsername(String username) {
        return null;
    }
}
