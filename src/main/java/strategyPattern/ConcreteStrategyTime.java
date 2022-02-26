package strategyPattern;

import dataModel.Client;
import dataModel.Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcreteStrategyTime implements Strategy {
    public ConcreteStrategyTime() {
    }

    @Override
    public void addClient(List<Queue> queues, Client t) {//aflam coada minima

        AtomicInteger minTime = new AtomicInteger(Integer.MAX_VALUE);
        List<Queue> queueMinim = new ArrayList<>();
        for (Queue q : queues) {
            if (minTime.get() > q.getWaitingPeriod().get()) {
                minTime.set(q.getWaitingPeriod().get());
            }
        }
        for (Queue q : queues) {
            if (q.getWaitingPeriod().get() == minTime.get()) {
                queueMinim.add(q);
            }
        }
        int minClientNumber = Integer.MAX_VALUE;
        for (Queue q : queueMinim) {
            if (q.getQueueSize() < minClientNumber) minClientNumber = q.getQueueSize();
        }

        for (Queue q : queues) {
            if (q.getQueueSize() == minClientNumber && q.getWaitingPeriod().get() == minTime.get()) {
                t.setTotalSericeTime(0);
                q.setSumWaitingPeriod(q.getWaitingPeriod().get());
                q.add(t);
                break;
            }
        }
    }
}
