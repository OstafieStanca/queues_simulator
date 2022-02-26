package dataModel;

import strategyPattern.ConcreteStrategyTime;
import strategyPattern.SelectionPolicy;
import strategyPattern.Strategy;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    private Strategy strategy;
    private final List<Queue> queues;
    private final int maxNoServers;
    private final int simulationPeriod;

    public Scheduler(int maxNoServers, int simulationPeriod) {
        this.maxNoServers = maxNoServers;
        this.simulationPeriod = simulationPeriod;
        queues = new ArrayList<>();
        for (int i = 1; i <= this.maxNoServers; i++) {
            Queue queue = new Queue(this.simulationPeriod);
            queues.add(queue);
        }

        for (int i = 1; i <= this.maxNoServers; i++) {
            Thread t = new Thread(queues.get(i - 1));
            t.start();
        }
        this.changeStrategy(SelectionPolicy.SHORTEST_TIME);
    }

    public void changeStrategy(SelectionPolicy policy) {

        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ConcreteStrategyTime();
        }
    }

    public int contentQueues(List<Client> generatedClients) {
        int ok = 1;// nu mai sunt clienti de procesat
        if (generatedClients.size() == 0) {
            for (Queue q : this.queues) {
                if (q.getClientsStatus() == false) {
                    ok = 0;//mai sunt clienti de procesat
                    break;
                }
            }
        } else ok = 0;//mai sunt clienti de procesat
        return ok;
    }

    public void dispatchClient(Client t) {
        strategy.addClient(this.queues, t);
    }

    public List<Queue> getQueue() {
        return queues;
    }
}
