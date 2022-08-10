package br.com.desafio.Desafio.Nv2.resources;

import br.com.desafio.Desafio.Nv2.entities.Dto.ProductDTO;
import br.com.desafio.Desafio.Nv2.entities.Product;
import br.com.desafio.Desafio.Nv2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/product")
public class ProductResource {


    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Product> insertProduct(@RequestBody ProductDTO productDTO){
        Product product = productService.fromDTO(productDTO);
        productService.insert(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping(value="{idProduct}")
    public ResponseEntity<Product> findById(@PathVariable UUID idProduct){
        Product product = productService.findById(idProduct);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> list = productService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<Product>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "10")Integer linesPerPage)
    {
            Page<Product> list = productService.findPage(page,linesPerPage);
            return ResponseEntity.ok().body(list);
    }


    @PutMapping(value = "/{idProduct}")
    public ResponseEntity<Product> update(@PathVariable UUID idProduct,@RequestBody ProductDTO productDTO){
        Product product = productService.fromDTO(productDTO);
        product = productService.update(idProduct,product);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping(value = "/{idProduct}")
    public ResponseEntity<Void> delete(@PathVariable UUID idProduct){
        productService.delete(idProduct);
        return ResponseEntity.noContent().build();
    }


}
