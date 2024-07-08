package demo.loadbalancer;

import demo.loadbalancer.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class WeightedRoundRobinLBTest {

    @Test
    void testRoundRobin(){

        WeightedRoundRobinLB roundRobinLB = new WeightedRoundRobinLB();
        Request request = new Request();
        request.setRequestId("123");
        //request.setLoadBalanceAlgorithm(LoadBalanceAlgorithm.ROUND_ROBIN);
        Server server1 = new Server("14.25.36.98", 2);
        Server server2 = new Server("14.25.36.97", 2);
        Server server3 = new Server("14.25.36.95", 2);
        List<Server> serverList = new ArrayList<>();
        serverList.add(server1);
        serverList.add(server2);
        serverList.add(server3);



        int server1Count = 0;
        int server2Count = 0;
        int server3Count = 0;

        for (int i = 0; i < 6; i++) {
            Server server = roundRobinLB.loadBalance(request, serverList);
            if (server.getIpAddress().equals("14.25.36.98")) {
                server1Count++;
            } else if (server.getIpAddress().equals("14.25.36.97")) {
                server2Count++;
            } else if (server.getIpAddress().equals("14.25.36.95")) {
                server3Count++;
            }

            System.out.println("ip address selected " + server.getIpAddress());
        }

        System.out.println("ip address 1 " + server1Count);
        System.out.println("ip address 2 " + server2Count);
        System.out.println("ip address 3  " + server3Count);


      /*  IntStream
                .range(0, 9)
                .parallel()
                .forEach(i ->
                        System.out.println(
                                "IP: " + roundRobinLB.loadBalance(request, serverList).getIpAddress()
                                        + " --- Request from Client: " + i
                                        + " --- [Thread: " + Thread.currentThread().getName() + "]")
                );*/
        //Assertions.assertEquals("14.25.36.98", server.getIpAddress());


    }

}
