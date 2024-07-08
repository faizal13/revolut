package demo.loadbalancer;

public class LoadBalancerFactory {

    public LoadBalanceStrategy getLoadBalancerInstance(LoadBalanceAlgorithm strategy){
        return switch (strategy.name()){
            case "ROUND_ROBIN" -> new RoundRobinLB();
            default -> new LeastConnectionLB();
        };

    }
}
