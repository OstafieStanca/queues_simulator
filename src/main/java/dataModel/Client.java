package dataModel;

public class Client implements Comparable<Client> {

    private final int idClient;
    private int arrivalTime;
    private int processingPeriod;
    private int totalServiceTime;

    public Client(int arrivalTime, int processingPeriod, int idClient) {
        this.arrivalTime = arrivalTime;
        this.processingPeriod = processingPeriod;
        this.idClient = idClient;
        this.totalServiceTime = 0;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getProcessingPeriod() {
        return processingPeriod;
    }

    public void setProcessingPeriod(int processingPeriod) {
        this.processingPeriod = processingPeriod;
    }

    public void setTotalSericeTime(int waitingPeriod) {
        this.totalServiceTime = this.processingPeriod + waitingPeriod;
    }

    public int getTotalServiceTime() {
        return totalServiceTime;
    }

    public int getIdClient() {
        return idClient;
    }

    @Override
    public int compareTo(Client o) {
        if (this.arrivalTime > o.getArrivalTime())
            return 1;
        else if (this.arrivalTime < o.getArrivalTime())
            return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", arrivalTime=" + arrivalTime +
                ", processingPeriod=" + processingPeriod +
                '}';
    }
}
