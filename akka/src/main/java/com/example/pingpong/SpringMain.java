package com.example.pingpong;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static com.example.common.SpringExtension.SpringExtProvider;

public class SpringMain {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext();
        ctx.scan("com.example");
        ctx.refresh();
        ActorSystem system = ctx.getBean(ActorSystem.class);
        ActorRef ping = system.actorOf(
                SpringExtProvider.get(system).props("PingActor"), "pingActor");
        ping.tell("start", ActorRef.noSender());
    }
}
