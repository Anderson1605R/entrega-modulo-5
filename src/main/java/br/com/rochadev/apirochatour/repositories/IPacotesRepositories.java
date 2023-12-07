package br.com.rochadev.apirochatour.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.rochadev.apirochatour.models.PacoteModel;

public interface IPacotesRepositories extends MongoRepository<PacoteModel, String> {
  
}
