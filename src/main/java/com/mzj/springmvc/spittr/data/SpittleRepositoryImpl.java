package com.mzj.springmvc.spittr.data;

import com.mzj.springmvc.spittr.Spittle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SpittleRepositoryImpl implements SpittleRepository {
    @Override
    public List<Spittle> findSpittles(long max, int count) {
        return createSpittleList(20);
    }

    @Override
    public Spittle findOne(long spittleId) {
        return new Spittle(spittleId,"Spittle54321", new Date(),null,null);
    }

    private List<Spittle> createSpittleList(int count) {
        List<Spittle> spittles = new ArrayList<Spittle>();
        for (int i = 0; i < count; i++) {
            spittles.add(new Spittle("Spittle " + i, new Date()));
        }
        return spittles;
    }
}
