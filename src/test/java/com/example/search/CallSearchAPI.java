package com.example.search;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class CallSearchAPI {

    private static final String PATH = "/api/v1/search/demo/{itemName}";

    public static Performable withKeyword(String itemName) {
        return Task.where("{0} call search an item API with keyword " + itemName,
                Get.resource(PATH)
                        .with(request -> request.pathParam("itemName", itemName))
        );
    }
}
