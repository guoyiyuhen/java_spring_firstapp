package com.miiti.firstapp.web.apis;

import com.miiti.firstapp.domain.application.UserService;
import com.miiti.firstapp.domain.model.user.EmailAddressExistsException;
import com.miiti.firstapp.domain.model.user.RegistrationException;
import com.miiti.firstapp.domain.model.user.UsernameExistsException;
import com.miiti.firstapp.web.payload.RegistrationPayload;
import com.miiti.firstapp.web.results.ApiResult;
import com.miiti.firstapp.web.results.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class RegistrationApiController {

    private UserService service;

    public RegistrationApiController(UserService service) {
        this.service = service;
    }

    @PostMapping("/api/registrations")
    public ResponseEntity<ApiResult> register(
            @Valid @RequestBody RegistrationPayload payload) {
        try {
            service.register(payload.toCommand());
            return Result.created();
        } catch (RegistrationException e) {
            String errorMessage = "Registration failed";
            if (e instanceof UsernameExistsException) {
                errorMessage = "Username already exists";
            } else if (e instanceof EmailAddressExistsException) {
                errorMessage = "Email address already exists";
            }
            System.out.println("dddd cccc");
            return Result.failure(errorMessage);
        }
    }
}
