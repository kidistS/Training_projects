/*
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

public class PatchRequestTest {

    @BeforeClass
    public void setBaseUri (){

        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    public void updateUsingPatch(){

        Posts post = new Posts();
        post.setId ("3");
        post.setTitle ("Hello Vietnam");

        give().body (post)
                .when ()
                .contentType ( ContentType.JSON)
                .patch ("/posts/3");

    }

}

*/