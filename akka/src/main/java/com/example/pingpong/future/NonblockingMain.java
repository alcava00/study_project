package com.example.pingpong.future;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.example.pingpong.future.actor.NonblockingActor;

/**
 * 아카의 Future를 이용해서 non-blocking 동작을 보여주는  메인 클래스
 * @author Baekjun Lim
 */
public class NonblockingMain {

	public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("TestSystem");
        ActorRef nonblockingActor = actorSystem.actorOf(Props.create(NonblockingActor.class), "nonblockingActor");
        nonblockingActor.tell(10, ActorRef.noSender());
        nonblockingActor.tell("hello", ActorRef.noSender());

	}

}
