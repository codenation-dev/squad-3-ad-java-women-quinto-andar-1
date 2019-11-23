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
    private int event;
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

    public int getEvent() {
        return event;
    }

    public LogRequest(){

    }

    public LogRequest(@NotBlank String level, @NotBlank String origin, @NotBlank String description, @NotBlank String details, @NotBlank String status, @NotBlank String environment, @NotBlank int event, @NotBlank String userToken) {
        this.level = level;
        this.origin = origin;
        this.description = description;
        this.details = details;
        this.status = status;
        this.environment = environment;
        this.event = event;
        this.userToken = userToken;
    }
}
