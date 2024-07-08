package demo.loadbalancer;

public class Server {

    private String ipAddress;

    private int activeConnections;

    private boolean isHealthy;

    private int weight;

    public Server(String ipAddress, int weight) {
        this.ipAddress = ipAddress;
        this.activeConnections = 0;
        this.isHealthy = true;
        this.weight = weight;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getActiveConnections() {
        return activeConnections;
    }

    public void setActiveConnections(int activeConnections) {
        this.activeConnections = activeConnections;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
