package com.example.pingpong.future;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.example.pingpong.future.actor.AgentActor;

/**
 * 아카의 에이전트를 보여주기 위한 메인 클래스
 * @author Baekjun Lim
 */
public class AgentMain {

	public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("TestSystem");
        ActorRef agentActor = actorSystem.actorOf(Props.create(AgentActor.class), "agentActor");
        agentActor.tell("start", ActorRef.noSender());
	}

}
