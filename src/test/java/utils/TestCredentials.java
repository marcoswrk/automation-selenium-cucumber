package utils;

public final class TestCredentials {
    private TestCredentials() {
    }

    public static final String EMAIL = requiredEnvironmentVariable("TEST_EMAIL");
    public static final String PASSWORD = requiredEnvironmentVariable("TEST_PASSWORD");

    private static String requiredEnvironmentVariable(String name) {
        String value = System.getenv(name);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                "Configure a variável de ambiente " + name + " antes de executar os testes."
            );
        }
        return value;
    }
}
