package demo.loadbalancer;

import java.util.Comparator;
import java.util.List;

public class LeastConnectionLB implements LoadBalanceStrategy{


    @Override
    public Server loadBalance(Request request, List<Server> serverList) {
        Server destResult =serverList.stream().filter(d -> d.isHealthy())
                .min(Comparator.comparingInt(dest -> dest.getActiveConnections()))
                .orElseThrow(() -> new RuntimeException("no healthy instance found"));
        destResult.setActiveConnections(destResult.getActiveConnections() + 1);
        return destResult;
    }
}
