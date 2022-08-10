package br.com.desafio.Desafio.Nv2.resources;

import br.com.desafio.Desafio.Nv2.entities.Dto.RequestDTO;
import br.com.desafio.Desafio.Nv2.entities.Request;
import br.com.desafio.Desafio.Nv2.entities.RequestIten;
import br.com.desafio.Desafio.Nv2.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/request")
public class RequestResource {


    @Autowired
    RequestService requestService;

    @PostMapping
    public ResponseEntity<Request> insert(@RequestBody RequestDTO requestDTO){
        Request request = requestService.fromDTO(requestDTO);
        requestService.insert(request);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @GetMapping(value="{idRequest}")
    public ResponseEntity<Request> findById(@PathVariable UUID idRequest){
        Request request = requestService.findById(idRequest);
        return ResponseEntity.ok().body(request);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<Request>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "10")Integer linesPerPage)
    {
        Page<Request> list = requestService.findPage(page,linesPerPage);
        return ResponseEntity.ok().body(list);
    }

    @PutMapping(value = "/{idRequest}")
    public ResponseEntity<Request> update(@PathVariable UUID idRequest,@RequestBody RequestDTO requestDTO){
        Request request = requestService.fromDTO(requestDTO);
        request = requestService.update(idRequest,request);
        return ResponseEntity.ok().body(request);
    }

    @DeleteMapping(value = "/{idRquest}")
    public ResponseEntity<Void> delete(@PathVariable UUID idRquest){
        requestService.delete(idRquest);
        return ResponseEntity.noContent().build();
    }


}
