package br.com.desafio.Desafio.Nv2.service;

import br.com.desafio.Desafio.Nv2.entities.Dto.RequestDTO;
import br.com.desafio.Desafio.Nv2.entities.Enum.StatusOrder;
import br.com.desafio.Desafio.Nv2.entities.Product;
import br.com.desafio.Desafio.Nv2.entities.Request;
import br.com.desafio.Desafio.Nv2.entities.RequestIten;
import br.com.desafio.Desafio.Nv2.repository.ProductRepository;
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
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Transactional
    public Request insert(Request obj){
        requestRepository.save(obj);
        return obj;
    }


    public Request findById(UUID idRequest){
        Optional<Request> obj = Optional.ofNullable(requestRepository.findByIdRequest(idRequest));
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + idRequest + ", Tipo: " + Request.class.getName()));
    }

    public Page<Request> findPage(Integer page, Integer linesPerPage){
        PageRequest pageRequest =  PageRequest.of(page,linesPerPage);
        return requestRepository.findAll(pageRequest);
    }

    public Request update(UUID idRequest,Request request){
        try {
            Request entity = requestRepository.getReferenceById(idRequest);
            updateData(entity, request);
            return requestRepository.save(entity);
        }catch (EntityNotFoundException e){
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + idRequest + ", Tipo: " + Request.class.getName());
        }
    }

    private void updateData(Request entity, Request request) {
        if(request.getDateOfExpedition() != null) {
            entity.setDateOfExpedition(request.getDateOfExpedition());
        }
        if(request.getDiscountPercentage() != null) {
            entity.setDiscountPercentage(request.getDiscountPercentage());
        }
        if(request.getRequestNumber() != null) {
            entity.setRequestNumber(request.getRequestNumber());
        }
        if(request.getStatusOrder() != null) {
            entity.setStatusOrder(request.getStatusOrder());
        }

    }

    public void delete(UUID idRequest){
        try {
            requestRepository.deleteById(idRequest);
        }catch (EmptyResultDataAccessException e){
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + idRequest + ", Tipo: " + Request.class.getName());
        }catch (DataIntegrityViolationException e){
            throw new DatabaseExceptions("Não é possível excluir existe entidades relacionadas");
        }
    }

    public Request fromDTO(RequestDTO requestDTO){
        Request request = new Request(null,requestDTO.getRequestNumber(),requestDTO.getDateOfExpedition(),requestDTO.getStatusOrder(),
                requestDTO.getDiscountPercentage(),null,null);
        return request;
    }
}
