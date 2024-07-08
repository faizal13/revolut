package demo.loadbalancer;

import java.util.List;

public interface LoadBalanceStrategy {

    Server loadBalance(Request request, List<Server> serverList);
}
