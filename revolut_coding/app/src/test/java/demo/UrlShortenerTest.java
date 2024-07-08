package demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UrlShortenerTest {

    @Test
    void generateShortUrlTest(){
        String longUrl = "www.demo/homepage";
        String baseUrl = "http://tiny.url/";
        UrlShortener urlShortener = new UrlShortener();
        String shortUrl = urlShortener.getShortUrl(longUrl);
        Assertions.assertNotNull(shortUrl);
        Assertions.assertTrue(shortUrl.contains(baseUrl));
    }

    @Test
    void getLongUrl(){
        String mockLongUrl = "www.demo/homepage";
        UrlShortener urlShortener = new UrlShortener();
        String shortResult = urlShortener.getShortUrl(mockLongUrl);
         String longUrl = urlShortener.getLongUrl(shortResult);
        Assertions.assertNotNull(longUrl);
        Assertions.assertEquals(mockLongUrl, longUrl);
    }

    @Test
    void maxSizeExceededTest(){
        String longUrl = "www.demo/homepage";
        String baseUrl = "http://tiny.url/";
        UrlShortener urlShortener = new UrlShortener();
        int counter = 0;
      Exception exception =  Assertions.assertThrows(IllegalStateException.class, () -> generateShortURLs(urlShortener, longUrl, counter));
      Assertions.assertEquals("maximum size reached", exception.getMessage());

    }

    private void generateShortURLs(UrlShortener urlShortener, String longUrl, int counter) {

        for(int i = 0; i < 120 ; i++){
            urlShortener.getShortUrl(longUrl+counter);
            counter++;
        }
    }


    @Test
    t1 1
    t2 1

}
