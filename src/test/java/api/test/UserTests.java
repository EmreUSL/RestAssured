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
       userPayload.setUsername(faker.name().username());
       userPayload.setFirstName(faker.name().firstName());
       userPayload.setLastName(faker.name().lastName());
       userPayload.setEmail(faker.internet().emailAddress());
       userPayload.setPassword(faker.internet().password());
       userPayload.setPhone(faker.phoneNumber().phoneNumber());
    }

    @Test(priority = 1)
    public void testPostUser() {
        Response response = UserEndpoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testGetUserByName() {
        Response response = UserEndpoints.readUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void testUpdateUserByName() {
        //update data using payload
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().body();

        Assert.assertEquals(response.getStatusCode(), 200);

        //Checking data after update
        Response responseAfterUpdate = UserEndpoints.readUser(this.userPayload.getUsername());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
    }

    @Test(priority = 4)
    public void testDeleteUserByName() {
        Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
