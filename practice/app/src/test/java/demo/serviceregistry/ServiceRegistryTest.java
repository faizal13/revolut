package demo.serviceregistry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ServiceRegistryTest {

    @Test
    void registerService() throws IllegalAccessException {
        List<String> ipList = List.of("123.325.698.35", "256.365.854.98");
        ServerR server = new ServerR("service1", ipList);
        ServiceRegister registry = new ServiceRegister();
        boolean result = registry.register(server);
        Assertions.assertTrue(result);
    }

    // size should not exceed 10
    @Test
    void registerServiceExceedLimitTest() throws IllegalAccessException {
        ServiceRegister registry = new ServiceRegister();
        List<String> ipList = List.of("123.325.698.35", "256.365.854.98");
        for(int i = 0; i < 10; i++){
            ServerR server = new ServerR("service" + i, ipList);
            registry.register(server);
        }
       Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> registry.register(new ServerR("service11", new ArrayList<>())));
        Assertions.assertEquals("Limit full, cannot add more than 10", exception.getMessage());
    }

    //duplicate server addition test
    @Test
    void duplicateServiceCheckTest() throws IllegalAccessException {
        List<String> ipList = List.of("123.325.698.35", "256.365.854.98");
        ServerR server = new ServerR("service1", ipList);
        ServiceRegister registry = new ServiceRegister();
        registry.register(server);
        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> registry.register(server));
        Assertions.assertEquals("Service Already registered", exception.getMessage());
    }

    @Test
    void getServiceRandomStrategyTest(){
        List<String> ipList = List.of("123.325.698.35", "256.365.854.98");
        ServiceRegister registry = new ServiceRegister();
        for(int i = 0; i < 10; i++){
            ServerR server = new ServerR("service" + i, ipList);
            registry.register(server);
        }
        ServerR resultServer = registry.getServer(Strategy.RANDOM);
        Assertions.assertNotNull(resultServer);
    }

    @Test
    void getServiceRoundRobinStrategyTest(){
        List<String> ipList = List.of("123.325.698.35", "256.365.854.98");
        ServiceRegister registry = new ServiceRegister();
        for(int i = 0; i < 10; i++){
            ServerR server = new ServerR("service" + i, ipList);
            registry.register(server);
        }
        Assertions.assertEquals("service1", registry.getServer(Strategy.ROUND_ROBIN).getName());
        Assertions.assertEquals("service2", registry.getServer(Strategy.ROUND_ROBIN).getName());
    }




}
