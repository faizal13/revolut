package demo.loadbalancer;

public class Request {

    private String requestId;

    private LoadBalanceAlgorithm loadBalanceAlgorithm;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public LoadBalanceAlgorithm getLoadBalanceAlgorithm() {
        return loadBalanceAlgorithm;
    }

    public void setLoadBalanceAlgorithm(LoadBalanceAlgorithm loadBalanceAlgorithm) {
        this.loadBalanceAlgorithm = loadBalanceAlgorithm;
    }
}
