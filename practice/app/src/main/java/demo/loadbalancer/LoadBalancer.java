package demo.loadbalancer;

import java.util.*;

public class LoadBalancer {

    private static LoadBalancer instance;

    List<Server> serverList;

    private int maxServers;

    LoadBalancerFactory loadBalancerStrategyFactory;

    public void register(Server server){
        if(serverList.size() < maxServers && !serverList.contains(server))
        serverList.add(server);
    }

    private LoadBalancer() {
        this.serverList = new ArrayList<>();
        this.loadBalancerStrategyFactory = new LoadBalancerFactory();
        this.maxServers = 10;
    }

    public static LoadBalancer getInstance(){
        if(instance == null){
            return new LoadBalancer();
        }
        return instance;
    }

      public Server getServer(Request request) {
          return loadBalancerStrategyFactory.getLoadBalancerInstance(request.getLoadBalanceAlgorithm()).loadBalance(request, serverList);
    }
}
