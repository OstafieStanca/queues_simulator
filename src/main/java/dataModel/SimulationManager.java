package dataModel;

import graphicalUserInterface.View;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable {
    private int simulationInterval;
    private int maxServiceTime;
    private int minSerciceTime;
    private int maxArrivalTime;
    private int minArrivalTime;
    private int maxNoOfQueues;
    private int noOfClients;
    private Scheduler scheduler;
    private List<Client> generatedClients;
    private float sumTotalServiceTime = 0;
    private int peekHour;
    private View view;

    public SimulationManager(int maxArrivalTime, int minArrivalTime, int maxServiceTime, int minSerciceTime, int simulationInterval, int noOfClients, int maxNoOfQueues, View view) {
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minSerciceTime = minSerciceTime;
        this.maxServiceTime = maxServiceTime;
        this.maxNoOfQueues = maxNoOfQueues;
        this.noOfClients = noOfClients;
        this.simulationInterval = simulationInterval;
        this.scheduler = new Scheduler(this.maxNoOfQueues, this.simulationInterval);
        this.generatedClients = generateNRandomClients();
        this.peekHour = -1;
        this.view = view;
    }

    public int generateNumber(int interval, int upperBound, int lowerBound) {
        Random rand = new Random();
        int value = rand.nextInt(interval);
        while (value < lowerBound || value > upperBound) {
            value = rand.nextInt(interval);
        }
        return value;
    }

    public List<Client> generateNRandomClients() {

        List<Client> clients = new ArrayList<>();
        for (int i = 1; i <= noOfClients; i++) {

            int serviceTime = generateNumber(maxServiceTime, maxServiceTime, minSerciceTime);
            int arrivalTime = generateNumber(maxArrivalTime, maxArrivalTime, minArrivalTime);
            Client c = new Client(arrivalTime, serviceTime, i);
            clients.add(c);
        }
        Collections.sort(clients);
        return clients;

    }

    public int getQueueWithMaxWaitingCients() {
        int max = 0;
        for (Queue q : this.scheduler.getQueue()) {
            max += q.getQueueSize();
        }
        return max;
    }

    public void print(List<Queue> queues, FileWriter fileWriter, int time) throws IOException {
        fileWriter.write("\nTime " + time + "\n");
        fileWriter.write("Waiting clients: ");
        for (Client c : generatedClients) {
            fileWriter.write("( " + c.getIdClient() + " , " + c.getArrivalTime() + " , " + c.getProcessingPeriod() + ") ;");
        }
        fileWriter.write("\n");
        int i = 1;
        for (Queue q : queues) {
            if (q.getClientsStatus() == true)
                fileWriter.write("Queue" + i + ": closed \n");
            else {
                fileWriter.write("Queue" + i + ":\n ");
                for (Client t : q.getClients()) {
                    int n;
                    n = t.getProcessingPeriod();
                    fileWriter.write("(" + t.getIdClient() + " , " + t.getArrivalTime() + " , " + n + ")  ;");
                }
                fileWriter.write("\n");
            }
            i++;
        }
    }

    public void printInterface(List<Queue> queues, int time) {
        view.setText("\nTime " + time + "\n");
        view.setText("Waiting clients: ");
        for (Client c : generatedClients) {
            view.setText("( " + c.getIdClient() + " , " + c.getArrivalTime() + " , " + c.getProcessingPeriod() + ") ;");
        }
        view.setText("\n");
        int i = 1;
        for (Queue q : queues) {
            if (q.getClientsStatus() == true)
                view.setText("Queue" + i + ": closed \n");
            else {
                view.setText("Queue" + i + ":\n ");
                for (Client t : q.getClients()) {
                    int n;
                    n = t.getProcessingPeriod();
                    view.setText("(" + t.getIdClient() + " , " + t.getArrivalTime() + " , " + n + ")  ;");
                }
                view.setText("\n");
            }
            i++;
        }
    }

    public float averageWaitingTime() {
        float suma = 0;
        for (int i = 0; i < scheduler.getQueue().size(); i++) {
            suma += scheduler.getQueue().get(i).getSumWaitingPeriod();
        }
        return suma / this.noOfClients;
    }

    public float averageServiceTime() {
        return sumTotalServiceTime / this.noOfClients;
    }

    public List<Client> getGeneratedClients() {
        return generatedClients;
    }

    @Override
    public void run() {
        int currentTime = 0, maxClients = Integer.MIN_VALUE;
        try {
            String fileName = "D:\\An II sem 2\\TP\\PT2021_30227_Ostafie_Stanca_Assignment_2\\Test.txt";
            FileWriter fileWriter = new FileWriter(fileName);
            while (currentTime <= simulationInterval && scheduler.contentQueues(generatedClients) == 0) {
                try {
                    if ((generatedClients.size() != 0) && (generatedClients.get(0).getArrivalTime() == currentTime)) {
                        while (generatedClients.get(0).getArrivalTime() == currentTime) {
                            scheduler.dispatchClient(generatedClients.get(0));
                            sumTotalServiceTime += generatedClients.get(0).getTotalServiceTime();
                            generatedClients.remove(0);
                            if (generatedClients.size() == 0) break;
                        }
                    }
                    Thread.sleep(1000);
                    if (getQueueWithMaxWaitingCients() > maxClients) {
                        maxClients = getQueueWithMaxWaitingCients();
                        peekHour = currentTime;
                    }
                    //print(scheduler.getQueue(), fileWriter, currentTime);
                    printInterface(scheduler.getQueue(), currentTime);
                    currentTime++;

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            /*fileWriter.write("\nAverage Waiting Time = " + averageWaitingTime() + "\n");
            fileWriter.write("Average Service Time = " + averageServiceTime() + "\n");
            fileWriter.write("Peek Hour = " + peekHour);*/
            view.setText("\nAverage Waiting Time = " + averageWaitingTime() + "\n");
            view.setText("Average Service Time = " + averageServiceTime() + "\n");
            view.setText("Peek Hour = " + peekHour);
            //fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}