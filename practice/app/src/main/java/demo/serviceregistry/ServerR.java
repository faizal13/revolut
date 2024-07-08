package demo.serviceregistry;

import java.util.List;

public class ServerR {

    private String name;

    private List<String> ipList;

    public ServerR(String name, List<String> ipList) {
        this.name = name;
        this.ipList = ipList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIpList() {
        return ipList;
    }

    public void setIpList(List<String> ipList) {
        this.ipList = ipList;
    }
}
