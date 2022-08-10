package br.com.desafio.Desafio.Nv2.Controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.*;


import br.com.desafio.Desafio.Nv2.entities.Product;
import br.com.desafio.Desafio.Nv2.entities.RequestIten;
import br.com.desafio.Desafio.Nv2.resources.ProductResource;
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
import java.util.List;
import java.util.UUID;

@WebMvcTest
public class ProductController {

    @Autowired
    private ProductResource productResource;

    @MockBean
    private ProductService productService;
    @MockBean
    private RequestItenService requestItenService;
    @MockBean
    private RequestService requestService;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.productResource);
    }

    @Test
    public void deveRetornarSucesso_QuandoBuscarProduto() {

        when(this.productService.findById(UUID.fromString("85412568-6f78-4b51-826d-8325f3e9588d")))
                .thenReturn(new Product(UUID.fromString("85412568-6f78-4b51-826d-8325f3e9588d"),
                        "Cimento", 1.00, true, false));

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/product/{idProduct}", UUID.fromString("85412568-6f78-4b51-826d-8325f3e9588d"))
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
