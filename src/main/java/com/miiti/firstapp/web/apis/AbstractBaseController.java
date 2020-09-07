package com.miiti.firstapp.web.apis;

import com.miiti.firstapp.domain.application.commands.AnonymousCommand;
import com.miiti.firstapp.domain.application.commands.UserCommand;
import com.miiti.firstapp.domain.model.user.SimpleUser;
import com.miiti.firstapp.utils.RequestUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.Assert;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractBaseController extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                        .maxAge(3600)
                        .allowCredentials(true);
    }

    void addTriggeredBy(UserCommand command, HttpServletRequest request) {
        Assert.notNull(request.getUserPrincipal(), "User principal must be present in the request");
        UsernamePasswordAuthenticationToken userPrincipal = (UsernamePasswordAuthenticationToken) request.getUserPrincipal();
        SimpleUser currentUser = (SimpleUser) userPrincipal.getPrincipal();
        command.triggeredBy(currentUser.getUserId(), RequestUtils.getIpAddress(request));
    }

    void addTriggeredBy(AnonymousCommand command, HttpServletRequest request) {
        command.triggeredBy(RequestUtils.getIpAddress(request));
    }
}
