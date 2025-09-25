package github;

import common.ConfigReader;

public class GitHubTokenManager {

    private static String token;
    private static long expiryTime;

    public static synchronized String getToken() {
        long now = System.currentTimeMillis();
        if (token == null || now >= expiryTime) {
            fetchNewToken();
        }
        return token;
    }

    private static void fetchNewToken() {
        String pat = ConfigReader.get("github.token");
        if (pat == null || pat.isEmpty()) {
            throw new RuntimeException("GitHub PAT not found in config.properties");
        }

        // Save token in memory
        token = pat;

        // Simulate expiry using fake TTL from config
        int ttl = ConfigReader.getInt("github.token.ttlSeconds", 3600);
        expiryTime = System.currentTimeMillis() + (ttl * 1000L);

        System.out.println("DEBUG: Loaded GitHub token, will expire in " + ttl + " seconds");
    }

    public static synchronized void refreshToken() {
        token = null;
        expiryTime = 0;
    }
}
