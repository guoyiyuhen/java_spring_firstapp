package com.miiti.firstapp.domain.application;

import com.miiti.firstapp.domain.application.commands.RegistrationCommand;
import com.miiti.firstapp.domain.model.user.RegistrationException;

public interface UserService {
    void register(RegistrationCommand command) throws RegistrationException;
}
