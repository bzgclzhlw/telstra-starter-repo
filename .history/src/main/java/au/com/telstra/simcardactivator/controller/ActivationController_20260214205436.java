package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.dto.ActivationRequest;
import au.com.telstra.simcardactivator.dto.ActuatorResponse;
import au.com.telstra.simcardactivator.service.ActuatorClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class ActivationController {

    private static final Logger log = LoggerFactory.getLogger(ActivationController.class);
    private final ActuatorClient actuatorClient;

    public ActivationController(ActuatorClient actuatorClient) {
        this.actuatorClient = actuatorClient;
    }

    @PostMapping("/activate")
    public ActuatorResponse activate(@RequestBody ActivationRequest request) {
        ActuatorResponse res = actuatorClient.actuate(request.getIccid());

        boolean ok = (res != null && res.isSuccess());
        log.info("Activation {} for iccid={}, customerEmail={}",
                ok ? "SUCCESS" : "FAILED",
                request.getIccid(),
                request.getCustomerEmail());

        // 直接把 actuator 的结果返回，方便你测试
        return res == null ? failure() : res;
    }

    private ActuatorResponse failure() {
        ActuatorResponse r = new ActuatorResponse();
        r.setSuccess(false);
        return r;
    }
}
