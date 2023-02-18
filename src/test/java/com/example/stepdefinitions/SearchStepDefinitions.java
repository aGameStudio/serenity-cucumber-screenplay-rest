package com.example.stepdefinitions;

import com.example.search.CallSearchAPI;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.*;

public class SearchStepDefinitions {

    private static final String RESTAPI_BASE_URL = "restapi.baseurl";

    private EnvironmentVariables environmentVariables;
    private String theRestApiBaseUrl;

    @Before
    public void configureBaseUrl() {
        theRestApiBaseUrl = environmentVariables.optionalProperty(RESTAPI_BASE_URL)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Given("{actor} can call the API")
    public void actorCan(Actor actor) {
        actor.whoCan(CallAnApi.at(theRestApiBaseUrl));
    }

    @When("{actor} searches for an {string}")
    public void searchesFor(Actor actor, String keyword) {
        actor.attemptsTo(
                CallSearchAPI.withKeyword(keyword)
        );
    }

    @Then("{actor} sees the results displayed for an {string}")
    public void heSeesResultFor(Actor actor, String item) {
        actor.should(
                seeThatResponse(String.format("all the search result titles should contain %s keyword", item),
                        response -> response.statusCode(200)
                                .body("title", everyItem(containsStringIgnoringCase(item))))
        );
    }

    @Then("{actor} does not see the results")
    public void heDoesNotSeeTheResults(Actor actor) {
        actor.should(
                seeThatResponse("",
                        response -> response.statusCode(404)
                                .body("detail.error", is(true))));
    }
}
