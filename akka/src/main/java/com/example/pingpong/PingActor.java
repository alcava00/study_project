package com.example.pingpong;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("PingActor")
@Scope("prototype")
public class PingActor extends AbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private ActorRef pong;

    private String name;

    public PingActor(String name) {
        this.name = name;
    }

    @Override
    public void preStart() {
        System.out.println();

        this.pong = context().actorOf(Props.create(PongActor.class, getSelf()), "pongActor");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, msg -> {
                    log.info("Ping received({}), {}",name, msg);
                    pong.tell("ping", getSelf());
                })
                .build();
    }
}