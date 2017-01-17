package config;

class Environment {

    private static String baseUrl;

    static {
        baseUrl="http://transfers-portal-test-staging.live.cf.private.springer.com";
    }

    static String getBaseUrl() {
        return baseUrl;
    }
}
