package graphicalUserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JTextField noOfQueues = new JTextField();
    private JTextField noOfClients = new JTextField();
    private JTextField minServiceTime = new JTextField();
    private JTextField maxServiceTime = new JTextField();
    private JTextField minArrivalTime = new JTextField();
    private JTextField maxArrivalTime = new JTextField();
    private JTextField simulationPeriod = new JTextField();
    private JButton start = new JButton("START");
    private JButton clean = new JButton("RESET");
    private JTextArea text = new JTextArea();

    public View() {
        JPanel content1 = new JPanel(new FlowLayout());
        JPanel finalContent = new JPanel();
        BoxLayout boxlayout = new BoxLayout(finalContent, BoxLayout.Y_AXIS);
        finalContent.setLayout(boxlayout);
        JPanel line1 = new JPanel(new GridLayout(1, 2));
        line1.add(new JLabel("Simulation Period "));
        line1.add(this.simulationPeriod);
        JPanel line2 = new JPanel(new GridLayout(1, 2));
        line2.add(new JLabel("Number of queues "));
        line2.add(this.noOfQueues);
        JPanel line3 = new JPanel(new GridLayout(1, 2));
        line3.add(new JLabel("Number of clients "));
        line3.add(this.noOfClients);
        JPanel line4 = new JPanel(new GridLayout(1, 3));
        line4.add(new JLabel("Arrival time "));
        line4.add(this.minArrivalTime);
        line4.add(this.maxArrivalTime);
        JPanel line5 = new JPanel(new GridLayout(1, 3));
        line5.add(new JLabel("Service Time "));
        line5.add(this.minServiceTime);
        line5.add(this.maxServiceTime);
        JPanel line7 = new JPanel(new GridLayout(1,2));
        line7.add(this.start);
        line7.add(this.clean);
        JScrollPane line6 = new JScrollPane(this.text);
        //line6.add(this.list);
        content1.add(line1);
        content1.add(line2);
        content1.add(line3);
        content1.add(line4);
        content1.add(line5);
        content1.add(line7);
        finalContent.add(content1);
        finalContent.add(line6);
        this.setContentPane(finalContent);
        this.pack();
        this.setTitle("SIMULATION APPLICATION");
        this.setSize(900, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String getNoOfQueues() {
        return noOfQueues.getText();
    }

    public void setNoOfQueues(String noOfQueues) {
        this.noOfQueues.setText(noOfQueues);
    }

    public String getNoOfClients() {
        return noOfClients.getText();
    }

    public void setNoOfClients(String noOfClients) {
        this.noOfClients.setText(noOfClients);
    }

    public String getMinServiceTime() {
        return minServiceTime.getText();
    }

    public void setMinServiceTime(String minServiceTime) {
        this.minServiceTime.setText(minServiceTime);
    }

    public String getMaxServiceTime() {
        return maxServiceTime.getText();
    }

    public void setMaxServiceTime(String maxServiceTime) {
        this.maxServiceTime.setText(maxServiceTime);
    }

    public String getMinArrivalTime() {
        return minArrivalTime.getText();
    }

    public void setMinArrivalTime(String minArrivalTime) {
        this.minArrivalTime.setText(minArrivalTime);
    }

    public String getMaxArrivalTime() {
        return maxArrivalTime.getText();
    }

    public void setMaxArrivalTime(String maxArrivalTime) {
        this.maxArrivalTime.setText(maxArrivalTime);
    }

    public String getSimulationPeriod() {
        return simulationPeriod.getText();
    }

    public void setSimulationPeriod(String simulationPeriod) {
        this.simulationPeriod.setText(simulationPeriod);
    }

    public void setText(String text) {
        this.text.append(text);
    }

    public void resetTest(){
        this.text.setText("");
    }

    void startButton(ActionListener e) {
        start.addActionListener(e);
    }

    void restartButton(ActionListener e){
        clean.addActionListener(e);
    }
}
