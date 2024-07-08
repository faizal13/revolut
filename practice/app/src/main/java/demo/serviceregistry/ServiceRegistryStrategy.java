package demo.serviceregistry;

import java.util.List;

public interface ServiceRegistryStrategy {

    ServerR getServer(List<ServerR> serverList);
}
