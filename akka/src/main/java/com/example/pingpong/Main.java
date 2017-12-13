package com.example.pingpong;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static com.example.common.SpringExtension.SpringExtProvider;

public class Main {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("TestSystem");
        ActorRef ping = actorSystem.actorOf(Props.create(PingActor.class,"ping1"), "pingActor");
        ping.tell("start", ActorRef.noSender());

    }
}
