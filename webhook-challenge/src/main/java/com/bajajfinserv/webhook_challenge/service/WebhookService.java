package com.bajajfinserv.webhook_challenge.service;

import com.bajajfinserv.webhook_challenge.model.WebHookRequest;
import com.bajajfinserv.webhook_challenge.model.WebHookResponse;
import com.bajajfinserv.webhook_challenge.model.SolutionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService implements CommandLineRunner {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public void run(String... args) throws Exception {
        try {
            WebHookResponse webhookResponse = generateWebhook();
            String sqlSolution = solveSqlProblem();
            submitSolution(webhookResponse.getWebhook(), webhookResponse.getAccessToken(), sqlSolution);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private WebHookResponse generateWebhook() {
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
        
        WebHookRequest request = new WebHookRequest("L Shaneel Reddy", "22BLC1164", "shaneelreddy2004@gmail.com");
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<WebHookRequest> entity = new HttpEntity<>(request, headers);
        
        ResponseEntity<WebHookResponse> response = restTemplate.exchange(
            url, HttpMethod.POST, entity, WebHookResponse.class);

        return response.getBody();
    }
    
    private String solveSqlProblem() {
        String sqlQuery = """
            SELECT 
                e1.EMP_ID,
                e1.FIRST_NAME,
                e1.LAST_NAME,
                d.DEPARTMENT_NAME,
                COUNT(e2.EMP_ID) as YOUNGER_EMPLOYEES_COUNT
            FROM EMPLOYEE e1
            JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID
            LEFT JOIN EMPLOYEE e2 ON e1.DEPARTMENT = e2.DEPARTMENT 
                AND e2.DOB > e1.DOB
            GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME
            ORDER BY e1.EMP_ID DESC
            """;
        
        return sqlQuery.trim();
    }
    
    private void submitSolution(String webhookUrl, String accessToken, String sqlQuery) {
        SolutionRequest solutionRequest = new SolutionRequest(sqlQuery);
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);
        headers.set("Content-Type", "application/json");
        
        HttpEntity<SolutionRequest> entity = new HttpEntity<>(solutionRequest, headers);
        
        restTemplate.exchange(webhookUrl, HttpMethod.POST, entity, String.class);
    }
}