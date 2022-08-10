package br.com.desafio.Desafio.Nv2.service;

import br.com.desafio.Desafio.Nv2.entities.Dto.ProductDTO;
import br.com.desafio.Desafio.Nv2.entities.Dto.RequestItenDTO;
import br.com.desafio.Desafio.Nv2.entities.Product;
import br.com.desafio.Desafio.Nv2.entities.Request;
import br.com.desafio.Desafio.Nv2.entities.RequestIten;
import br.com.desafio.Desafio.Nv2.repository.ProductRepository;
import br.com.desafio.Desafio.Nv2.repository.RequestItenRepository;
import br.com.desafio.Desafio.Nv2.service.Exceptions.DatabaseExceptions;
import br.com.desafio.Desafio.Nv2.service.Exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    RequestItenRepository requestItenRepository;

    @Transactional
    public Product insert(Product obj){
        productRepository.save(obj);
        return obj;
    }

    public Product findById(UUID idProduct){
        Optional<Product> obj = Optional.ofNullable(productRepository.findByIdProduct(idProduct));
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + idProduct + ", Tipo: " + Product.class.getName()));
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Page<Product> findPage(Integer page,Integer linesPerPage){
        PageRequest pageRequest =  PageRequest.of(page,linesPerPage);
        return productRepository.findAll(pageRequest);
    }

    public Product update(UUID idProduct,Product product){
        try {
            Product entity = productRepository.getReferenceById(idProduct);
            updateData(entity, product);
            return productRepository.save(entity);
        }catch (EntityNotFoundException e){
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + idProduct + ", Tipo: " + Product.class.getName());
        }
    }

    private void updateData(Product entity, Product product) {
        if(product.getService() != null) {
            entity.setService(product.getService());
        }
        if(product.getProductAtive() != null) {
            entity.setProductAtive(product.getProductAtive());
        }
        if(product.getPriceProduct() != null) {
            entity.setPriceProduct(product.getPriceProduct());
        }
        if(product.getNameProduct() != null) {
            entity.setNameProduct(product.getNameProduct());
        }
    }

    public void delete(UUID idProduct){
        try {
            productRepository.deleteById(idProduct);
        }catch (EmptyResultDataAccessException e){
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + idProduct + ", Tipo: " + Product.class.getName());
        }catch (DataIntegrityViolationException e){
            throw new DatabaseExceptions("Não é possível excluir existe entidades relacionadas");
        }
    }

    public Product fromDTO(ProductDTO productDTO){
        Product product = new Product(null,productDTO.getNameProduct(),
                productDTO.getPriceProduct(),productDTO.getProductAtive(),productDTO.getService());
        return product;
    }
}
