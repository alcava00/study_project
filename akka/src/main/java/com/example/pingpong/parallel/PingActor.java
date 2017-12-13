package com.example.pingpong.parallel;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.RoundRobinPool;
import akka.routing.SmallestMailboxPool;
import scala.concurrent.duration.Duration;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class PingActor extends AbstractActorWithStash {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef childRouter;
    private int count = -1;

    final SupervisorStrategy strategy =
            new OneForOneStrategy(5, Duration.create(1, TimeUnit.MINUTES), Collections.<Class<? extends Throwable>>singletonList(Exception.class));

    public PingActor() {
//        SmallestMailboxPool(5)
        childRouter = getContext().actorOf(
                new SmallestMailboxPool(5).withSupervisorStrategy(strategy) .props(Props.create(Ping1Actor.class)), "ping1Actor");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class, msg -> {
            if ("done".equals(msg)) {
                System.out.println("done "+count);
                if (count == 10) {
                    count = -1;
                    unstashAll();
                } else {
                    count++;
                }
            } else if (("start".equals(msg) && count < 0)) {
                count=1;
                for (int i = 0; i < 10; i++) {
                    childRouter.tell(i, getSelf());
                }
            }else{
                stash();
            }
        }).build();


    }

}
