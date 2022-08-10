package br.com.desafio.Desafio.Nv2.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class RequestIten {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idRequestIten;
    @NotNull
    private Integer itenSequence;
    @NotNull
    private Double unitaryValue;
    @NotNull
    private Integer amount;

    @ManyToOne
    private Product product;

    @ManyToOne
    @JoinColumn(name = "Request", nullable = false)
    private Request idRequest;

    public RequestIten() {
    }

    public RequestIten(UUID idRequestIten, Integer itenSequence, Product product, Double unitaryValue, Integer amount, Request idRequest) {
        this.idRequestIten = idRequestIten;
        this.itenSequence = itenSequence;
        this.product = product;
        this.unitaryValue = product.getPriceProduct();
        this.amount = amount;
        this.idRequest = idRequest;
    }
    public Double getSubTotal(){
        return unitaryValue * amount;
    }

    public Integer getItenSequence() {
        return itenSequence;
    }

    public void setItenSequence(Integer itenSequence) {
        this.itenSequence = itenSequence;
    }

    public Double getUnitaryValue() {
        return unitaryValue;
    }

    public void setUnitaryValue(Double unitaryValue) {
        this.unitaryValue = unitaryValue;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UUID getIdRequestIten() {
        return idRequestIten;
    }

    public void setIdRequestIten(UUID idRequestIten) {
        this.idRequestIten = idRequestIten;
    }

    @JsonBackReference
    public Request getidRequest() {return idRequest;}

    public void setidRequest(Request idRequest) {this.idRequest = idRequest;}
}
