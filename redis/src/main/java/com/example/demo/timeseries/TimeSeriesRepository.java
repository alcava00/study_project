package com.example.demo.timeseries;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class TimeSeriesRepository implements InitializingBean{

    @Resource(name = "template")
    private ValueOperations<String, String> valueOperations;

//    @Autowired
//    private RedisTemplate<String,String> template;

//    private ValueOperations<String, String> valueOperations;

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    public void insert(String nameSpace) {
        insert(nameSpace, System.currentTimeMillis());
    }

    public List<String> fetch(String nameSpace, TimeSeries.granularities granularitie, long beginTimestamp, long endTimestamp) {
        List ls = new ArrayList();
        for (long i = beginTimestamp; i <= endTimestamp; i += granularitie.getDuration()) {
            ls.add(getKey(nameSpace, granularitie, i));
        }
        return valueOperations.multiGet(ls);
    }

    public void insert(String nameSpace, long timeStampSec) {
        for (TimeSeries.granularities granularities : TimeSeries.granularities.values()) {
            String key = getKey(nameSpace, granularities, timeStampSec);
            if (valueOperations.get(key) == null && granularities.getTtl() != 0) {
                valueOperations.set(key, "1", granularities.getTtl(), TimeUnit.MILLISECONDS);
            } else { //!!!!
                valueOperations.increment(key, 1L);
            }
        }
    }


    public String getKey(String nameSpace, TimeSeries.granularities granularities, long timeStampSec) {
        long key = roundTime(granularities, timeStampSec);
        DecimalFormat df = new DecimalFormat("#####");
        return nameSpace + ":" + df.format(key) + ":" + granularities.getName();
    }

    public long roundTime(TimeSeries.granularities granularities, long timeStampSec) {
        long time = (long) (Math.floor(timeStampSec / granularities.getDuration()) * granularities.getDuration());
        if (granularities == TimeSeries.granularities.day) {
            time -= (60 * 1000 * 60 * 9);
        }
        return time;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
//        this.valueOperations=template.opsForValue();
    }
}
