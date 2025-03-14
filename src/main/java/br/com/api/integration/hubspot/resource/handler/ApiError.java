package br.com.api.integration.hubspot.resource.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiError {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    private String message;

    private String url;

    public ApiError(String message, String url) {
        this.timestamp = LocalDateTime.now();
        this.url = url;
        this.message = message;
    }
}
