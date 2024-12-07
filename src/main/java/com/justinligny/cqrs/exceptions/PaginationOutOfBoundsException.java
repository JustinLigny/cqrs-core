package com.justinligny.cqrs.exceptions;

public class PaginationOutOfBoundsException extends RuntimeException {
    public PaginationOutOfBoundsException() {
        super("Requested page is out of bounds. No data found for the given page number");
    }
}
