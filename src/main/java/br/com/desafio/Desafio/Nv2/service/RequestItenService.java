package br.com.desafio.Desafio.Nv2.service;

import br.com.desafio.Desafio.Nv2.entities.Dto.RequestItenDTO;
import br.com.desafio.Desafio.Nv2.entities.Product;
import br.com.desafio.Desafio.Nv2.entities.Request;
import br.com.desafio.Desafio.Nv2.entities.RequestIten;
import br.com.desafio.Desafio.Nv2.repository.ProductRepository;
import br.com.desafio.Desafio.Nv2.repository.RequestItenRepository;
import br.com.desafio.Desafio.Nv2.repository.RequestRepository;
import br.com.desafio.Desafio.Nv2.service.Exceptions.DatabaseExceptions;
import br.com.desafio.Desafio.Nv2.service.Exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class RequestItenService {

    @Autowired
    RequestItenRepository requestItenRepository;
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    ProductRepository productRepository;

    public Double totalValue(){

        return null;
    }

    @Transactional
    public RequestIten insert(RequestIten obj) throws Exception {
        if (!obj.getProduct().getProductAtive()){
            throw new IllegalArgumentException("Produto não está ativo");
        }else{
            requestItenRepository.save(obj);
            return obj;
        }
    }

    public Page<RequestIten> findPage(Integer page, Integer linesPerPage){
        PageRequest pageRequest =  PageRequest.of(page,linesPerPage);
        return requestItenRepository.findAll(pageRequest);
    }

    public RequestIten findById(UUID idRequestIten){
        Optional<RequestIten> obj = Optional.ofNullable(requestItenRepository.findByIdRequestIten(idRequestIten));
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + idRequestIten + ", Tipo: " + RequestIten.class.getName()));
    }

    public RequestIten update(UUID idRequest,RequestIten requestIten){
        try {
            RequestIten entity = requestItenRepository.getReferenceById(idRequest);
            updateData(entity, requestIten);
            return requestItenRepository.save(entity);
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + idRequest + ", Tipo: " + RequestIten.class.getName());
        }
    }

    private void updateData(RequestIten entity, RequestIten requestIten) {
        if(requestIten.getProduct() != null) {
            entity.setProduct(requestIten.getProduct());
        }
        if(requestIten.getidRequest() != null) {
            entity.setidRequest(requestIten.getidRequest());
        }
        if(requestIten.getItenSequence() != null) {
            entity.setItenSequence(requestIten.getItenSequence());
        }
        if(requestIten.getAmount() != null) {
            entity.setAmount(requestIten.getAmount());
        }
    }

    public void delete(UUID idRequest){
        try {
            requestItenRepository.deleteById(idRequest);
        }catch (EmptyResultDataAccessException e){
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + idRequest + ", Tipo: " + RequestIten.class.getName());
        }catch (DataIntegrityViolationException e){
            throw new DatabaseExceptions("Não é possível excluir existe entidades relacionadas");
        }
    }

    public RequestIten fromDTO(RequestItenDTO requestItenDTO){
       Product pro = productRepository.findByIdProduct(requestItenDTO.getIdProduct());
       Request request = requestRepository.findByIdRequest(requestItenDTO.getIdRequest());

        RequestIten requestIten = new RequestIten(null,requestItenDTO.getItenSequence(),pro,
                pro.getPriceProduct(),requestItenDTO.getAmount(),request);
        return requestIten;
    }

}
