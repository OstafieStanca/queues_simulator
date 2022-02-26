package graphicalUserInterface;

import dataModel.SimulationManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private final View view;
    private SimulationManager gen;

    public Controller(View view) {
        this.view = view;
        view.startButton(new StartListener());
        view.restartButton(new RestartListener());
    }

    private class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int minArrivalTime = Integer.parseInt(view.getMinArrivalTime());
            int maxArrivalTime = Integer.parseInt(view.getMaxArrivalTime());
            int minServiceTime = Integer.parseInt(view.getMinServiceTime());
            int maxServiceTime = Integer.parseInt(view.getMaxServiceTime());
            int simulationPeriod = Integer.parseInt(view.getSimulationPeriod());
            int noOfQueues = Integer.parseInt(view.getNoOfQueues());
            int noOfClients = Integer.parseInt(view.getNoOfClients());
            gen = new SimulationManager(maxArrivalTime, minArrivalTime, maxServiceTime, minServiceTime, simulationPeriod, noOfClients, noOfQueues, view);
            Thread t = new Thread(gen);
            t.start();
        }
    }

    private class RestartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.resetTest();
            view.setMinArrivalTime("");
            view.setMaxArrivalTime("");
            view.setMinServiceTime("");
            view.setMaxServiceTime("");
            view.setSimulationPeriod("");
            view.setNoOfClients("");
            view.setNoOfQueues("");
        }
    }
}
