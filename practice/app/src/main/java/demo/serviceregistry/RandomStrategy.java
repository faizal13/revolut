package demo.serviceregistry;

import java.util.List;
import java.util.Random;

public class RandomStrategy implements ServiceRegistryStrategy{

    Random random = new Random();

    @Override
    public ServerR getServer(List<ServerR> serverList) {
        return serverList.get(random.nextInt(serverList.size()));
    }
}
