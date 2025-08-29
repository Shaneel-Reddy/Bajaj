package com.bajajfinserv.webhook_challenge.model;

public class WebHookResponse {
    private String webhook;
    private String accessToken;

    // Getters and Setters
    public String getWebhook() { return webhook; }
    public void setWebhook(String webhook) { this.webhook = webhook; }
    
    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
}