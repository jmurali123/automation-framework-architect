package interfaces;

import filters.LoggingFilter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.ConfigReader;

public interface ApiCapable {

    // Interface variable — static final — created once!
    RequestSpecification API_SPEC = new RequestSpecBuilder()
            .setBaseUri(ConfigReader.get("jsonplaceholder.base.uri"))
            .setContentType(ContentType.JSON)
            .addFilter(new LoggingFilter())
            .build();

    default RequestSpecification getApiSpec(){
        return API_SPEC;
    }
}