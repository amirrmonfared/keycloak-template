package com.site;

import org.keycloak.email.EmailException;
import org.keycloak.email.freemarker.FreeMarkerEmailTemplateProvider;
import org.keycloak.models.KeycloakSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class CustomEmailTemplateProvider extends FreeMarkerEmailTemplateProvider {

    public CustomEmailTemplateProvider(KeycloakSession session) {
        super(session);
    }

    @Override
    public void sendExecuteActions(String link, long expirationInMinutes) throws EmailException {
        String token = extractTokenFromLink(link);

        String customUrl = "https://site/verification?key=" + token;

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("link", customUrl);
        attributes.put("linkExpiration", expirationInMinutes);

        processTemplate("email-verification-with-code.ftl", Collections.singletonList(user.getEmail()), "site", attributes);
    }

    private String extractTokenFromLink(String link) {
        int tokenIndex = link.indexOf("key=") + 4;
        return link.substring(tokenIndex);
    }
}
