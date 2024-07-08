package demo.serviceregistry;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RoundrobinStrategy implements ServiceRegistryStrategy{

    private static volatile RoundrobinStrategy roundrobinStrategyInstance ;
    AtomicInteger counter;
    ReentrantReadWriteLock reentrantReadWriteLock;


    private RoundrobinStrategy(){
        this.counter = new AtomicInteger(0);
        this.reentrantReadWriteLock = new ReentrantReadWriteLock();
    }

     public static RoundrobinStrategy getInstance(){
        if(roundrobinStrategyInstance == null){
                synchronized (RoundrobinStrategy.class){
                    if(roundrobinStrategyInstance == null){
                    roundrobinStrategyInstance = new RoundrobinStrategy();
                }
            }

        }
        return roundrobinStrategyInstance;
     }

    @Override
    public ServerR getServer(List<ServerR> serverList) {
        reentrantReadWriteLock.readLock().lock();
        try{
            if(counter.get() > serverList.size() -1){
                counter.set(0);
            }
            return serverList.get(counter.incrementAndGet());
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }
}
