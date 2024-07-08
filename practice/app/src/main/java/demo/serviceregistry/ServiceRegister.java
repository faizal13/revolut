package demo.serviceregistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ServiceRegister {

    private List<ServerR> serverList;

    private int maxSize;

    public ServiceRegister() {
        this.serverList = new CopyOnWriteArrayList<>();
        this.maxSize = 10;
    }

    public synchronized boolean register(ServerR server) {
        if (serverList.size() >= maxSize) {
            throw new IllegalStateException("Limit full, cannot add more than 10");
        }
        if (serverList.stream().anyMatch(s -> s.getName().equals(server.getName()))) {
            throw new IllegalStateException("Service Already registered");
        }
        return serverList.add(server);
    }

    public ServerR getServer(Strategy strategy) {
        return new StrategyFactory().loadStrategy(strategy).getServer(serverList);
    }
}
