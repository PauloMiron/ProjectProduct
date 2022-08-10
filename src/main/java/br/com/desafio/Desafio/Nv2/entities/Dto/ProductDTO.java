package br.com.desafio.Desafio.Nv2.entities.Dto;

import br.com.desafio.Desafio.Nv2.entities.Product;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

public class ProductDTO implements Serializable {

    @NotNull
    @Size(max = 200)
    private String nameProduct;
    @NotNull
    private Double priceProduct;
    @NotNull
    private Boolean productAtive;
    @NotNull
    private Boolean service;

    public ProductDTO() {
    }

    public ProductDTO(Product product){
        nameProduct = product.getNameProduct();
        priceProduct = product.getPriceProduct();
        productAtive = product.getProductAtive();
        service = product.getService();
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(Double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public Boolean getProductAtive() {
        return productAtive;
    }

    public void setProductAtive(Boolean productAtive) {
        this.productAtive = productAtive;
    }

    public Boolean getService() {
        return service;
    }

    public void setService(Boolean service) {
        this.service = service;
    }
}
