package br.com.desafio.Desafio.Nv2.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduct;
    @NotNull
    @Size(max = 200)
    private String nameProduct;
    @NotNull
    private Double priceProduct;
    @NotNull
    private Boolean productAtive;
    @NotNull
    private Boolean service;

    public Product() {
    }

    public Product(UUID idProduct, String nameProduct, Double priceProduct, Boolean productAtive, Boolean service) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.productAtive = productAtive;
        this.service = service;
    }


    public UUID getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(UUID idProduct) {
        this.idProduct = idProduct;
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
