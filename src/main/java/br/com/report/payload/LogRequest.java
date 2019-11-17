package br.com.report.payload;

import javax.validation.constraints.NotBlank;

public class LogRequest {

    @NotBlank
    private String level;
    @NotBlank
    private String origin;
    @NotBlank
    private String description;
    @NotBlank
    private String details;
    @NotBlank
    private String status;
    @NotBlank
    private String environment;
    @NotBlank
    private String userToken;

    public String getUserToken(){
        return userToken;
    }

    public String getLevel() {
        return level;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDescription() {
        return description;
    }

    public String getDetails() {
        return details;
    }

    public String getStatus() {
        return status;
    }

    public String getEnvironment() {
        return environment;
    }
}
