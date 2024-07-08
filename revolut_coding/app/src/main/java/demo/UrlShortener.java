package demo;

import javax.print.attribute.URISyntax;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UrlShortener {

    static final String baseUrl = "http://tiny.url/";

    private Map<String, String> shortToLongMap;

    private Map<String, String> longToShortMap;

    static final int maxSize = 100;

   AtomicInteger counter;

    public UrlShortener() {
        this.shortToLongMap = new ConcurrentHashMap<>();
        this.longToShortMap = new ConcurrentHashMap<>();
        this.counter = new AtomicInteger(0);
    }

    public String getShortUrl(String longUrl) {

        if(shortToLongMap.entrySet().size() > maxSize){
            throw new IllegalStateException("maximum size reached");
        }

        if(longToShortMap.containsKey(longUrl)){
            return longToShortMap.get(longUrl);
        }
        StringBuilder builder = new StringBuilder();
        builder.append(baseUrl);
       builder.append(counter.incrementAndGet());
       String shortUrl = builder.toString();
       shortToLongMap.put(shortUrl,longUrl);
       longToShortMap.put(longUrl,shortUrl);

       //felipe silva
        return shortUrl;
    }

    public String getLongUrl(String shortResult) {

       return shortToLongMap.get(shortResult);

    }
}
