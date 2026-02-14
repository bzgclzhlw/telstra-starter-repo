package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.dto.ActuatorRequest;
import au.com.telstra.simcardactivator.dto.ActuatorResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ActuatorClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String ACTUATE_URL = "http://localhost:8444/actuate";

    public ActuatorResponse actuate(String iccid) {
        ActuatorRequest payload = new ActuatorRequest(iccid);
        return restTemplate.postForObject(ACTUATE_URL, payload, ActuatorResponse.class);
    }
}

