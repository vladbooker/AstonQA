import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class PostmanEchoTest {

    @Test
    public void testGetMethod() {
        Response response = RestAssured.get("https://postman-echo.com/get?foo1=bar1&foo2=bar2");
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("bar1", response.jsonPath().getString("args.foo1"));
        Assert.assertEquals("bar2", response.jsonPath().getString("args.foo2"));
    }

    @Test
    public void testPostMethod() {
        Response response = RestAssured.given()
                .post("https://postman-echo.com/post");
        Assert.assertEquals(500, response.getStatusCode());
    }

    @Test
    public void testPutMethod() {
        Response response = RestAssured.given()
                .put("https://postman-echo.com/put");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testPatchMethod() {
        Response response = RestAssured.given()
                .patch("https://postman-echo.com/patch");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testDeleteMethod() {
        Response response = RestAssured.delete("https://postman-echo.com/delete");
        Assert.assertEquals(200, response.getStatusCode());
    }
}