package br.com.tech.springtech.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tech.springtech.domain.model.Carteira;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

}
