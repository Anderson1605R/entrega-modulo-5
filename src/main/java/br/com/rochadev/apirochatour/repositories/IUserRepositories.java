package br.com.rochadev.apirochatour.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.rochadev.apirochatour.models.UserModel;

public interface IUserRepositories extends MongoRepository<UserModel,String> {
  
}
