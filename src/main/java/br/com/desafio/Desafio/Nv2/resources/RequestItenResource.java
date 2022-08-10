package br.com.desafio.Desafio.Nv2.resources;

import br.com.desafio.Desafio.Nv2.entities.Dto.RequestItenDTO;
import br.com.desafio.Desafio.Nv2.entities.Product;
import br.com.desafio.Desafio.Nv2.entities.RequestIten;
import br.com.desafio.Desafio.Nv2.service.RequestItenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/requestIten")
public class RequestItenResource {


    @Autowired
    RequestItenService requestItenService;

    @PostMapping
    public ResponseEntity<RequestIten> insertProduct(@RequestBody RequestItenDTO requestItenDTO) throws Exception {
        RequestIten requestIten = requestItenService.fromDTO(requestItenDTO);
        requestItenService.insert(requestIten);
        return new ResponseEntity<>(requestIten, HttpStatus.CREATED);
    }

    @GetMapping(value="{idRequestIten}")
    public ResponseEntity<RequestIten> findById(@PathVariable UUID idRequestIten){
        RequestIten request = requestItenService.findById(idRequestIten);
        return ResponseEntity.ok().body(request);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<RequestIten>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "10")Integer linesPerPage)
    {
        Page<RequestIten> list = requestItenService.findPage(page,linesPerPage);
        return ResponseEntity.ok().body(list);
    }

    @PutMapping(value = "/{idRequestIten}")
    public ResponseEntity<RequestIten> update(@PathVariable UUID idRequestIten,@RequestBody RequestItenDTO requestItenDTO){
        RequestIten requestIten = requestItenService.fromDTO(requestItenDTO);
        requestIten = requestItenService.update(idRequestIten,requestIten);
        return ResponseEntity.ok().body(requestIten);
    }

    @DeleteMapping(value = "/{idRequest}")
    public ResponseEntity<Void> delete(@PathVariable UUID idRequest){
        requestItenService.delete(idRequest);
        return ResponseEntity.noContent().build();
    }


}
