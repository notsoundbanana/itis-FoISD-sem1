package ru.kpfu.itis.chemaev.net.util;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryUtil {
    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {
        if (cloudinary == null) {
            Map<String, String> configMap = new HashMap<>();
            configMap.put("cloud_name", "dz9iuqgpf");
            configMap.put("api_key", "523745671618526");
            configMap.put("api_secret", "bgsazXbNo6wIXf9Z4-AF9Oflu98");
            cloudinary = new Cloudinary(configMap);
        }
        return cloudinary;
    }
}
