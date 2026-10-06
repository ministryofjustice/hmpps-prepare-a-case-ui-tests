package org.pacfs.test.stepdefs;

import org.pacfs.framework.base.Base;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import org.pacfs.framework.utilities.AuthClient;
import org.pacfs.framework.utilities.CourtCaseServiceClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import static org.testng.Assert.assertEquals;

public class authClientStepdefs extends Base {

    private String token;
    private CourtCaseServiceClient courtCaseServiceClient;

    @Given("I have a valid court case service authentication token")
    public void generateAuthenticationToken() {

        AuthClient authClient = new AuthClient();

        token = authClient.generateToken();

        courtCaseServiceClient =
                new CourtCaseServiceClient(token);

        assert token != null && !token.isBlank();
    }

    @Given("I create a PACFS case with the following details:")
    public void createPacfsCase(DataTable dataTable) {

        Map<String, String> data =
                new HashMap<>(dataTable.asMap(String.class, String.class));

        // Replace TODAY with the actual current date
        if ("TODAY".equalsIgnoreCase(data.get("day"))) {

            String today = LocalDate.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            data.put("day", today);
        }

        var response =
                courtCaseServiceClient.createScenario(data);

        assertEquals(
                200,
                response.statusCode(),
                "PACFS test data creation failed: "
                        + response.asString()
        );
    }
}
