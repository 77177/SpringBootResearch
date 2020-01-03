package com.epam.integration;

import com.epam.Main;
import com.epam.controller.ModelController;
import com.epam.model.Model;
import com.epam.modeldto.ModelDto;
import com.google.gson.Gson;
import com.jayway.jsonpath.spi.mapper.JsonSmartMappingProvider;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.authentication.FormAuthScheme;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Main.class)
@ActiveProfiles(value = {"local"})
public class TestRest {

    @LocalServerPort
    private int portNumber;

    private Gson gson;

    @Before
    public void Setup() {
        RestAssured.port = portNumber;
        RestAssured.baseURI = "https://localhost";
        RestAssured.useRelaxedHTTPSValidation();
        gson = new Gson();
        FormAuthScheme formAuthScheme = new FormAuthScheme();
        formAuthScheme.setUserName("username");
        formAuthScheme.setPassword("password");
        FormAuthConfig formAuthConfig = new FormAuthConfig("/login", "username", "password");
        formAuthScheme.setConfig(formAuthConfig);
        RestAssured.authentication = formAuthScheme;
    }

    @Test
    public void testPostAndGet() {
        ModelDto modelDto = new ModelDto();
        modelDto.setText("qwerty");

        RestAssured.given().contentType("application/json")
                .body(modelDto)
                .and()
                .post("/model/")
                .then()
                .statusCode(200);

        RestAssured.given().contentType("application/json")
                .and()
                .get("/model/1")
                .then()
                .body("id", Matchers.is(1))
                .body("text", Matchers.is("qwerty"))
                .statusCode(200);

        RestAssured.given().contentType("application/json")
                .get("/model/all")
                .then()
                .body("", Matchers.hasSize(2))
                .and()
                .statusCode(200);
    }

}
