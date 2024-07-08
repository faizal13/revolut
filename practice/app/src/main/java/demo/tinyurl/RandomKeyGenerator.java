package demo.tinyurl;

import java.security.Key;
import java.security.SecureRandom;

public class RandomKeyGenerator implements KeyGenerator {

    SecureRandom random = new SecureRandom();

    private String Characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Override
    public String generateKey(int keyLength) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < keyLength; i++){
            builder.append(Characters.charAt(random.nextInt(Characters.length())));
        }
        return builder.toString();
    }
}
