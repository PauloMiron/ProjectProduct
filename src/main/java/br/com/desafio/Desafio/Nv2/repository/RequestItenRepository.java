package br.com.desafio.Desafio.Nv2.repository;

import br.com.desafio.Desafio.Nv2.entities.Request;
import br.com.desafio.Desafio.Nv2.entities.RequestIten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RequestItenRepository extends JpaRepository<RequestIten, UUID> {

    RequestIten findByIdRequestIten(UUID idRequestIten);

}
