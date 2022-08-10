package br.com.desafio.Desafio.Nv2.entities;

import br.com.desafio.Desafio.Nv2.entities.Enum.StatusOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idRequest;
    @NotNull
    private Integer requestNumber;
    @NotNull
    private Date dateOfExpedition;
    private StatusOrder statusOrder;
    private Double discountPercentage;

    @OneToMany
    @JoinColumn(name = "Request")
    private List<RequestIten> listItens;

    public Request() {
    }

    public Request(UUID idRequest, Integer requestNumber, Date dateOfExpedition, StatusOrder statusOrder, Double discountPercentage, Double totalRequest, List<RequestIten> listItens) {
        this.idRequest = idRequest;
        this.requestNumber = requestNumber;
        this.dateOfExpedition = dateOfExpedition;
        this.statusOrder = statusOrder;
        this.discountPercentage = discountPercentage;
        this.listItens = listItens;
    }


    public double getValorTotal() throws Exception {
        if (listItens != null) {
            if (statusOrder == StatusOrder.Finished) {
                Double valorTotal = 0.00;
                Double valorService = 0.00;
                for (RequestIten itens : listItens) {
                    if (!itens.getProduct().getService()) {
                        valorTotal += itens.getSubTotal();
                    } else {
                        valorService += itens.getSubTotal();
                    }
                }
                valorTotal = valorService + valorTotal;
                return valorTotal;
            }
        }
            if(listItens != null){
                if (statusOrder == StatusOrder.Pending){
                Double valorTotal = 0.00;
                Double valorService = 0.00;
                for (RequestIten itens : listItens) {
                    if (!itens.getProduct().getService()) {
                        valorTotal += itens.getSubTotal();
                    } else {
                        valorService += itens.getSubTotal();
                    }
                }
                valorTotal = valorTotal - (valorTotal / 100) * discountPercentage;
                valorTotal = valorService + valorTotal;
                return valorTotal;
            }
        }
        return 0;
    }


    public UUID getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(UUID idRequest) {
        this.idRequest = idRequest;
    }

    public Integer getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(Integer requestNumber) {
        this.requestNumber = requestNumber;
    }

    @JsonManagedReference
    public List<RequestIten> getListItens() {
        return listItens;
    }

    public void setListItens(List<RequestIten> listItens) {
        this.listItens = listItens;
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
