package dataModel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable {

    private final BlockingQueue<Client> clients;
    private final AtomicInteger waitingPeriod;
    private int timeCounter = 0;
    private final int simulationPeriod;
    private float sumWaitingPeriod = 0;

    public Queue(int simulationPeriod) {
        this.clients = new LinkedBlockingQueue<>();
        this.waitingPeriod = new AtomicInteger();
        this.simulationPeriod = simulationPeriod;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public float getSumWaitingPeriod() {
        return sumWaitingPeriod;
    }

    public void setSumWaitingPeriod(float waitingPeriod) {
        this.sumWaitingPeriod += waitingPeriod;
    }

    public void add(Client newClient) {
        try {
            clients.add(newClient);
            this.waitingPeriod.addAndGet(newClient.getProcessingPeriod());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public synchronized void run() {

        while (this.clients.isEmpty() == false || timeCounter < simulationPeriod) {
            try {
                if (this.clients.isEmpty() == false) {
                    Client c = this.clients.element();
                    int clientTimeService = c.getProcessingPeriod();
                    for (int i = 0; i < clientTimeService; i++) {
                        int newServiceTime = c.getProcessingPeriod() - 1;
                        Thread.sleep(1000);
                        c.setProcessingPeriod(newServiceTime);
                        timeCounter++;
                        this.waitingPeriod.decrementAndGet();
                    }
                    this.clients.poll();
                } else {
                    timeCounter++;
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public boolean getClientsStatus() {
        return this.clients.isEmpty();
    }

    public int getQueueSize() {
        return clients.size();
    }

    public BlockingQueue<Client> getClients() {
        return this.clients;
    }


}
