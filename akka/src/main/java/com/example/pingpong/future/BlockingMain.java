package com.example.pingpong.future;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.example.pingpong.future.actor.BlockingActor;

/**
 * 아카의 Future를 이용해서 blocking 동작을 보여주는  메인 클래스
 * @author Baekjun Lim
 */
public class BlockingMain {

	public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("TestSystem");
        ActorRef blockingActor = actorSystem.actorOf(Props.create(BlockingActor.class), "blockingActor");
        blockingActor.tell(10, ActorRef.noSender());
        blockingActor.tell("hello", ActorRef.noSender());
	}

}
