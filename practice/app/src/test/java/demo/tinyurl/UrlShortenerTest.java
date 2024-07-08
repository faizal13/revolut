package demo.tinyurl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UrlShortenerTest {

    UrlShortener urlShortener;

    int keyLength;

    String baseUrl;

    @BeforeEach
    void setUp(){
        keyLength = 6;
        baseUrl = "http://tiny.url/";
        urlShortener =  UrlShortener.getInstance();
    }

    @Test
    void getShortUrlTest(){
        String originalUrl = "www.bombaygrills.com/homepage";

        String shortUrl = urlShortener.getShortUrl(originalUrl);
        System.out.println("short url ==== " + shortUrl);
        Assertions.assertNotEquals(originalUrl.length(), shortUrl.length());
        Assertions.assertEquals(keyLength, shortUrl.substring(baseUrl.length()).length());
    }

    // check duplicateshort url for same long url
    @Test
    void duplicationShortUrlValidationTest(){
        String originalUrl = "www.bombaygrills.com/homepage";
        String shortUrl1 = urlShortener.getShortUrl(originalUrl);
        String shortUrl2 = urlShortener.getShortUrl(originalUrl);
        Assertions.assertEquals(shortUrl1, shortUrl2);
    }

    // no same short url for different original url
    @Test
    void differentOriginaldifferentShortUrlTest(){
        String originalUrl1 = "www.bombaygrills.com/homepage";
        String originalUrl2 = "www.bombaygrills.com/aboutUs";
        String shortUrl1 = urlShortener.getShortUrl(originalUrl1);
        String shortUrl2 = urlShortener.getShortUrl(originalUrl2);
        Assertions.assertNotEquals(shortUrl1, shortUrl2);
    }

    // retrieve original url
    @Test
    void retrieveOriginalUrl() throws Exception {
        String originalUrl = "www.bombaygrills.com/homepage";
        String shortUrl = urlShortener.getShortUrl(originalUrl);
        String longUrl = urlShortener.retrieveUrl(shortUrl);
        Assertions.assertEquals(originalUrl, longUrl);
    }

    // retrieve original url
    @Test
    void retrieveOriginalUrlException() throws Exception {
        Assertions.assertThrows(Exception.class,
                () -> urlShortener.retrieveUrl("shortUrl"));
    }
}
