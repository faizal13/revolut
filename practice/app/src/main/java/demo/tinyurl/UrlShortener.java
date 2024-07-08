package demo.tinyurl;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class UrlShortener {

    private static UrlShortener urlShortenerInstance = null;

    private int keylength;


    private Map<String, String> shortToOriginalMap;

    private Map<String, String> OriginalToShortUrlMap;

    private  KeyGenerator keyGenerator;


    private UrlShortener() {
        this.keylength = 6;
        this.shortToOriginalMap = new ConcurrentHashMap<>();
        this.OriginalToShortUrlMap= new ConcurrentHashMap<>();
        this.keyGenerator = new RandomKeyGenerator();

    }

    public static UrlShortener getInstance(){
        if(urlShortenerInstance == null){
            synchronized (UrlShortener.class){
                if(urlShortenerInstance == null){
                    urlShortenerInstance =  new UrlShortener();
                }
            }

        }
        return urlShortenerInstance;
    }

    public synchronized String getShortUrl(String originalUrl) {
        String shortUrl;
        if(OriginalToShortUrlMap.get(originalUrl) != null){
            shortUrl = OriginalToShortUrlMap.get(originalUrl);
        } else {
            String key = keyGenerator.generateKey(this.keylength);
            shortUrl = Constants.baseUrl + key;

            OriginalToShortUrlMap.put(originalUrl, shortUrl);
            shortToOriginalMap.putIfAbsent(shortUrl, originalUrl);
        }

        return shortUrl;
    }

    public String retrieveUrl(String shortUrl) throws Exception {
        return Optional.of(shortToOriginalMap.get(shortUrl)).orElseThrow(() -> new Exception("invalid short url "));
    }
}
