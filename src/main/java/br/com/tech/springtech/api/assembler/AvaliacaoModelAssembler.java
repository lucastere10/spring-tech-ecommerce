package br.com.tech.springtech.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tech.springtech.api.dto.model.AvaliacaoModel;
import br.com.tech.springtech.domain.model.Avaliacao;

@Component
public class AvaliacaoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public AvaliacaoModel toModel(Avaliacao avaliacao) {
        return modelMapper.map(avaliacao, AvaliacaoModel.class);
    }

    public List<AvaliacaoModel> toCollectionModel(List<Avaliacao> avaliacoes) {
        return avaliacoes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
