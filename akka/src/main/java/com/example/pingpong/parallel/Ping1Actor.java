package com.example.pingpong.parallel;

import akka.actor.AbstractActor;
import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.duration.Duration;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Ping1Actor extends AbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);


    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder().match(Integer.class, msg -> {
            log.info("Ping1Actor({}) received {}", hashCode(), msg);
            work(msg);
            getSender().tell("done", self());
        }).build();
    }

    private void work(Integer n) throws Exception {
        log.info("Ping1Actor({}) working on {}", hashCode(), n);
        Thread.sleep(10);
        log.info("Ping1Actor({}) completed on {}", hashCode(), n);
    }
}
