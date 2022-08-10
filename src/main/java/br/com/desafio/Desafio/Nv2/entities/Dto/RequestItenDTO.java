package br.com.desafio.Desafio.Nv2.entities.Dto;

import br.com.desafio.Desafio.Nv2.entities.Product;
import br.com.desafio.Desafio.Nv2.entities.Request;
import br.com.desafio.Desafio.Nv2.entities.RequestIten;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public class RequestItenDTO implements Serializable {


    @NotNull
    private UUID idProduct;
    @NotNull
    private UUID idRequest;
    @NotNull
    private Integer itenSequence;
    @NotNull
    private Integer amount;

    public RequestItenDTO() {
    }

    public RequestItenDTO(RequestIten requestIten){
        this.itenSequence = requestIten.getItenSequence();
        this.amount = requestIten.getAmount();
        this.idRequest = requestIten.getidRequest().getIdRequest();
        this.idProduct = requestIten.getProduct().getIdProduct();
    }

    public UUID getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(UUID idProduct) {
        this.idProduct = idProduct;
    }

    public UUID getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(UUID idRequest) {
        this.idRequest = idRequest;
    }

    public Integer getItenSequence() {
        return itenSequence;
    }

    public void setItenSequence(Integer itenSequence) {
        this.itenSequence = itenSequence;
    }


    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
