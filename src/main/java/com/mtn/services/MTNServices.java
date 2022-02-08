package com.mtn.services;


import com.google.gson.Gson;
import com.mtn.dto.APIUser;
import com.mtn.responses.APIUserResponse;
import com.mtn.responses.GetApiKeyResponse;
import com.mtn.responses.GetApiUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.mtn.constants.Constants.*;

@Service
@Slf4j
public class MTNServices {

    private WebClientService webclientService;

    @Autowired
    public void setWebclientService(WebClientService webclientService) {
        this.webclientService = webclientService;
    }
    public APIUserResponse createApiUser(APIUser apiUser) {
        APIUserResponse res = webclientService.requestWithEndpoint(domainUrl + apiUrl)
                .post()
                .header("Ocp-Apim-Subscription-Key","xxxxxxxxxxxxxxxxxxxxxxxxxx")
                .header("X-Reference-Id","83b15281-91f9-47be-8833-2c2c04579eb9")
                .body(Mono.just(apiUser), APIUser.class)
                .retrieve()
                .bodyToMono(APIUserResponse.class)
                .block();

        Gson gson = new Gson();
        String result = gson.toJson(res);
        log.info("Result: " + result);

        return res;
    }

    public GetApiUserResponse getApiUer(String referenceID) {
        GetApiUserResponse res = webclientService.requestWithEndpoint(domainUrl + apiUrl + "/" + referenceID)
                .get()
                .header("Ocp-Apim-Subscription-Key","xxxxxxxxxxxxxxxxxxxxxxxxxx")
                .retrieve()
                .bodyToMono(GetApiUserResponse.class)
                .block();
        Gson gson = new Gson();
        String result = gson.toJson(res);
        log.info("Result: " + result);

        return res;
    }

    public GetApiKeyResponse getApiKey(String referenceID) {
        GetApiKeyResponse res = webclientService.requestWithEndpoint(domainUrl + apiUrl + "/" + referenceID +"/apikey")
                .post()
                .header("Ocp-Apim-Subscription-Key","xxxxxxxxxxxxxxxxxxxxxxxxxx")
                .retrieve()
                .bodyToMono(GetApiKeyResponse.class)
                .block();
        Gson gson = new Gson();
        String result = gson.toJson(res);
        log.info("Result: " + result);

        return res;
    }


}
