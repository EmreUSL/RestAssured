package api.test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    Faker faker;
    User userPayload;

    @BeforeClass
    public void setUp() {
       faker = new Faker();
       userPayload = new User();
       userPayload.setId(faker.idNumber().hashCode());
       userPayload.setFirstName(faker.name().firstName());
       userPayload.setLastName(faker.name().lastName());
       userPayload.setEmail(faker.internet().emailAddress());
       userPayload.setPassword(faker.internet().password());
       userPayload.setPhone(faker.phoneNumber().phoneNumber());
    }

    @Test
    public void testPostUser() {
        Response response = UserEndpoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
