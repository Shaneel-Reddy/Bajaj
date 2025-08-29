package com.bajajfinserv.webhook_challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebHookRequest {
    private String name;
    private String regNo;
    private String email;

    public WebHookRequest(String name, String regNo, String email) {
        this.name = name;
        this.regNo = regNo;
        this.email = email;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
