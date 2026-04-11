import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;


public class LoggingFilter implements Filter {
    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        // Step 1 — Log request
        System.out.println("Request Method  : " + requestSpec.getMethod());
        System.out.println("Request URI     : " + requestSpec.getURI());
        System.out.println("Request Headers : " + requestSpec.getHeaders());
        System.out.println("Request Body    : " + requestSpec.getBody());

        // Step 2 — Send request and get response
        Response response = ctx.next(requestSpec, responseSpec);

        // Step 3 — Log response
        System.out.println("Response Status : " + response.getStatusCode());
        System.out.println("Response Body   : " + response.getBody().asString());

        // Step 4 — Return response
        return response;
    }
}
