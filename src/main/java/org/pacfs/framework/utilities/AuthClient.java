package org.pacfs.framework.utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.pacfs.framework.config.Settings;

public class AuthClient {

    private static final String TOKEN_URL =
            "https://sign-in-dev.hmpps.service.justice.gov.uk/auth/oauth/token";

    public String generateToken() {

        String clientId = Settings.HMPPS_CLIENT_ID;
        String clientSecret = Settings.HMPPS_CLIENT_SECRET;

        if (clientId == null || clientId.isBlank()) {
            throw new IllegalStateException(
                    "HMPPS_CLIENT_ID environment variable is not configured"
            );
        }

        if (clientSecret == null || clientSecret.isBlank()) {
            throw new IllegalStateException(
                    "HMPPS_CLIENT_SECRET environment variable is not configured"
            );
        }

        Response response = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic(clientId, clientSecret)
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .when()
                .post(TOKEN_URL);

        System.out.println("Token response status: " + response.statusCode());

        if (response.statusCode() != 200) {
            throw new IllegalStateException(
                    "Token request failed. HTTP status: "
                            + response.statusCode()
                            + ", response: "
                            + response.asString()
            );
        }

        String accessToken = response.jsonPath().getString("access_token");

        if (accessToken == null || accessToken.isBlank()) {
            throw new IllegalStateException(
                    "Token response did not contain an access_token"
            );
        }

        return accessToken;
    }
}