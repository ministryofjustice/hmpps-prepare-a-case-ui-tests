package org.pacfs.framework.utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class CourtCaseServiceClient {

    private static final String BASE_URL =
            "https://court-case-service-dev.apps.live-1.cloud-platform.service.justice.gov.uk";

    private final String token;

    public CourtCaseServiceClient(String token) {
        this.token = token;
    }

    public Response createScenario(Map<String, String> data) {

        String requestBody = buildRequestBody(data);

        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/db-seed/scenario")
                .then()
                .extract()
                .response();
    }

    private String buildRequestBody(Map<String, String> data) {

        return """
        {
          "clean": %s,
          "cases": [
            {
              "caseNo": "%s",
              "urn": "URN-01AB123456",
              "sourceType": "COMMON_PLATFORM",
              "caseMarkers": ["High profile", "Sensitive"],
              "comments": [],
              "defendants": [
                {
                  "defendantName": "%s",
                  "type": "PERSON",
                  "sex": "%s",
                  "name": {
                    "title": "%s",
                    "forename1": "%s",
                    "surname": "%s"
                  },
                  "address": {
                    "line1": "%s"
                  },
                  "dateOfBirth": "%s",
                  "crn": null,
                  "pnc": "PNC-0001",
                  "cro": "CRO-001",
                  "nationality1": "British",
                  "offender": {
                    "crn": "%s",
                    "pnc": "PNC-0001",
                    "cro": "CRO-001",
                    "probationStatus": "%s",
                    "awaitingPsr": %s,
                    "breach": %s,
                    "preSentenceActivity": false,
                    "suspendedSentenceOrder": false
                  }
                }
              ],
              "hearings": [
                {
                  "hearingType": "sentence",
                  "hearingEventType": "Resulted",
                  "listNo": "1",
                  "hearingDays": [
                    {
                      "day": "%s",
                      "time": "09:00:00",
                      "courtCode": "B52BB",
                      "courtRoom": "1"
                    }
                  ],
                  "defendants": [
                    {
                      "prepStatus": "NOT_STARTED",
                      "outcomeNotRequired": false,
                      "notes": [],
                      "offences": [
                        {
                          "title": "Test offence",
                          "summary": "Test offence for MAPPA",
                          "act": "Theft Act 1968",
                          "sequence": 1,
                          "listNo": 1,
                          "offenceCode": "%s",
                          "plea": {
                            "value": "GUILTY",
                            "date": "2026-09-25"
                          },
                          "verdict": {
                            "typeDescription": "Guilty",
                            "date": "2026-09-25"
                          },
                          "judicialResults": []
                        }
                      ]
                    }
                  ]
                }
              ]
            }
          ]
        }
        """.formatted(
                data.get("clean"),
                data.get("caseNo"),
                data.get("defendantName"),
                data.get("sex"),
                data.get("title"),
                data.get("forename1"),
                data.get("surname"),
                data.get("line1"),
                data.get("dateOfBirth"),
                data.get("crn"),
                data.get("probationStatus"),
                data.get("awaitingPsr"),
                data.get("breach"),
                data.get("day"),
                data.get("offenceCode")
        );
    }
}