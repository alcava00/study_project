package com.example.demo.timeseries;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeSeriesRepositoryTest {

    @Autowired
    private TimeSeriesRepository repository;

    @Test
    public void insert() throws Exception {
        System.out.println("units.hour.msec:" + TimeSeries.units.hour.getMsec());
        repository.insert("buyItem");
        long time=System.currentTimeMillis();
        for (int i = 0; i < 2000; i++) {
            repository.insert("buyItem");
        }
        System.out.println("time:" +(System.currentTimeMillis()-time));
    }

    @Test
    public void fetch() throws Exception {
        List<String> ls = repository.fetch("buyItem", TimeSeries.granularities.day, System.currentTimeMillis(), System.currentTimeMillis());
        for(String ob:ls){
            System.out.println("ls:"+ob);
        }
    }

    @Test
    public void getKey() throws Exception {
        long time = System.currentTimeMillis();
        System.out.println("Time:" + new Date(time));
        long roundTime = repository.roundTime(TimeSeries.granularities.min, time);
        long roundTime2 = repository.roundTime(TimeSeries.granularities.hour, time);
        long roundTime3 = repository.roundTime(TimeSeries.granularities.day, time);
        System.out.println("roundTime Time:" + new Date(roundTime));
        System.out.println("roundTime Time:" + new Date(roundTime2));
        System.out.println("roundTime Time:" + new Date(roundTime3)+":::"+roundTime3);
        System.out.println("roundTime Time:" + new Date(1512486000000L)); //1512572400000
    }

}