package demo.loadbalancer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class RoundRobinLB implements LoadBalanceStrategy{

    private volatile int counter;

    private final ReentrantLock lock;

    public RoundRobinLB() {
        this.lock = new ReentrantLock();
        this.counter = 0;
    }

    @Override
    public Server loadBalance(Request request, List<Server> serverList) {
        lock.lock();
        try{
            Server server = serverList.get(counter);
            counter += 1;
            if(counter == serverList.size()){
                counter = 0;
            }
            return server;
        }finally {
            lock.unlock();
        }
    }
}
