package edu.itplus.bibliosping.backend.repository;

public class RepositoryExceptioon extends RuntimeException {
    public RepositoryExceptioon(String message) {
        super(message);
    }
    public RepositoryExceptioon(String message, Throwable cause) {
        super(message, cause);
    }
}
