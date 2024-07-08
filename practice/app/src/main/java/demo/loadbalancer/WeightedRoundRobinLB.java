package demo.loadbalancer;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class WeightedRoundRobinLB implements LoadBalanceStrategy {

    private Random random;

    private int totalWeight;

    private int currentIndex;

    //AtomicInteger sum;

    private final ReentrantLock lock;

    public WeightedRoundRobinLB() {
        this.random = new Random();
        this.totalWeight = 0;
        this.currentIndex = 0;
        this.lock = new ReentrantLock();
       // this.sum =  new AtomicInteger(0);
    }

    @Override
    public Server loadBalance(Request request, List<Server> serverList) {
        lock.lock();
        try {

            if (serverList.size() == 1) {
                return serverList.iterator().next();
            }
            totalWeight = serverList.stream().mapToInt(d -> d.getWeight()).sum();
            int randomInt = random.nextInt(totalWeight);
            AtomicInteger sum = new AtomicInteger(0);
            List<Integer> cumu = serverList.stream().map(d -> sum.addAndGet(d.getWeight())).collect(Collectors.toList());
            for (int i = 0; i < cumu.size(); i++) {
                if (randomInt < cumu.get(i)) {
                    currentIndex = i;
                    break;
                }
            }
            return serverList.get(currentIndex);

        } finally {
            lock.unlock();
        }
    }


}
