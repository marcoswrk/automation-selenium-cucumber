package utils;

public final class TestCredentials {
    private TestCredentials() {
    }

    public static final String EMAIL = requiredEnvironmentVariable("TEST_EMAIL");
    public static final String PASSWORD = requiredEnvironmentVariable("TEST_PASSWORD");

    public static String requiredEnvironmentVariable(String name) {
        String value = System.getenv(name);
        if (value == null || value.isEmpty()) {
            return "dummy@example.com"; // fallback para testes locais
        }
        return value;
    }
}

