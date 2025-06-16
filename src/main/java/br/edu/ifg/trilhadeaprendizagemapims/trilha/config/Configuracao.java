package br.edu.ifg.trilhadeaprendizagemapims.trilha.config;

import br.edu.ifg.trilhadeaprendizagemapims.trilha.dto.CategoriaDTO;
import br.edu.ifg.trilhadeaprendizagemapims.trilha.model.ECategoria;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracao {
    @Bean
    public ModelMapper obterModelMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(CategoriaDTO.class, ECategoria.class).addMappings(m -> m.skip(ECategoria::setId));
        return mapper;
    }
}