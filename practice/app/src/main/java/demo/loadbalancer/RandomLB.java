package demo.loadbalancer;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomLB implements LoadBalanceStrategy{

    private Random random = new Random();

    @Override
    public Server loadBalance(Request request, List<Server> serverList) {
            Server server = serverList
                    .get(random.nextInt(serverList.size()));
            return server;
    }
}
