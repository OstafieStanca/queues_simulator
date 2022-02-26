package strategyPattern;

import dataModel.Client;
import dataModel.Queue;

import java.util.List;

public interface Strategy {

    void addClient(List<Queue> queues, Client t);

}
