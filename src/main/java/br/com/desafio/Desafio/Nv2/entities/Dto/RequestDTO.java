package br.com.desafio.Desafio.Nv2.entities.Dto;

import br.com.desafio.Desafio.Nv2.entities.Enum.StatusOrder;
import br.com.desafio.Desafio.Nv2.entities.Request;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class RequestDTO implements Serializable {


    @NotNull
    private Integer requestNumber;
    @NotNull
    private Date dateOfExpedition;
    private StatusOrder statusOrder;
    private Double discountPercentage;

    public RequestDTO() {
    }

    public RequestDTO(Request request) {
        this.requestNumber = request.getRequestNumber();
        this.dateOfExpedition = request.getDateOfExpedition();
        this.statusOrder = request.getStatusOrder();
        this.discountPercentage = request.getDiscountPercentage();
    }

    public Integer getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(Integer requestNumber) {
        this.requestNumber = requestNumber;
    }

    public Date getDateOfExpedition() {
        return dateOfExpedition;
    }

    public void setDateOfExpedition(Date dateOfExpedition) {
        this.dateOfExpedition = dateOfExpedition;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
