package br.com.desafio.Desafio.Nv2.Controllers;

import br.com.desafio.Desafio.Nv2.entities.Enum.StatusOrder;
import br.com.desafio.Desafio.Nv2.entities.Product;
import br.com.desafio.Desafio.Nv2.entities.Request;
import br.com.desafio.Desafio.Nv2.entities.RequestIten;
import br.com.desafio.Desafio.Nv2.resources.RequestItenResource;
import br.com.desafio.Desafio.Nv2.resources.RequestResource;
import br.com.desafio.Desafio.Nv2.service.ProductService;
import br.com.desafio.Desafio.Nv2.service.RequestItenService;
import br.com.desafio.Desafio.Nv2.service.RequestService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

@WebMvcTest
public class RequestController {

    @Autowired
    private RequestResource requestResource;

    @MockBean
    private ProductService productService;
    @MockBean
    private RequestItenService requestItenService;
    @MockBean
    private RequestService requestService;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.requestResource);
    }

    @Test
    public void deveRetornarSucesso_QuandoBuscarRequest() {

        Product product = new Product(UUID.fromString("85412568-6f78-4b51-826d-8325f3e9588d"),
                "Cimento",2.0,true,false);

        Request request = new Request(UUID.fromString("85412568-6f78-4b51-826d-8325f3e9588d"),2,
                new Date(), StatusOrder.Pending,10.0,2.0,null);

       RequestIten requestIten = new RequestIten(UUID.fromString("85412568-6f78-4b51-826d-8325f3e9588d"),2,product,2.0,1,request);

        List<RequestIten> list = new ArrayList<>();
        list.add(requestIten);

        when(this.requestService.findById(UUID.fromString("85412568-6f78-4b51-826d-8325f3e9588d")))
                .thenReturn(new Request(UUID.fromString("85412568-6f78-4b51-826d-8325f3e9588d"),2,null ,StatusOrder.Pending,2.0, 10.0,
                        list));

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/request/{idRequest}", UUID.fromString("85412568-6f78-4b51-826d-8325f3e9588d"))
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}