package br.com.rochadev.apirochatour.services;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import br.com.rochadev.apirochatour.models.PacoteModel;
import br.com.rochadev.apirochatour.models.UserModel;
import br.com.rochadev.apirochatour.repositories.IPacotesRepositories;
import br.com.rochadev.apirochatour.repositories.IUserRepositories;

@Service
public class UserService {

    private final IUserRepositories userRepo;
    private final IPacotesRepositories pacoteRepo;

    public UserService(IUserRepositories userRepo, IPacotesRepositories pacoteRepo) {
        this.userRepo = userRepo;
        this.pacoteRepo = pacoteRepo;
    }

    /**
     * Salva um novo usuário.
     *
     * @param user O usuário a ser salvo
     * @return O usuário salvo
     * @throws IllegalArgumentException Se o usuário for nulo
     */
    public UserModel createUser(UserModel user) {
        if (user == null) {
            throw new IllegalArgumentException("O usuário não pode ser nulo");
        }
        return userRepo.save(user);
    }

    /**
     * Retorna uma lista de todos os usuários.
     *
     * @return Lista de usuários
     */
    public List<UserModel> getAllUsers() {
        return userRepo.findAll();
    }

    /**
     * Deleta um usuário pelo ID.
     *
     * @param userId ID do usuário a ser deletado
     * @throws IllegalArgumentException Se o ID for nulo ou vazio
     */
    public void deleteUserById(String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("ID do usuário não pode ser nulo ou vazio");
        }
        userRepo.deleteById(userId);
    }

    /**
     * Atualiza um usuário existente.
     *
     * @param updatedUser O usuário atualizado
     * @throws IllegalArgumentException Se o usuário for nulo ou não existir no banco de dados
     */
    public UserModel updateUser(UserModel updatedUser) {
        if (updatedUser == null || updatedUser.getId() == null) {
            throw new IllegalArgumentException("Usuário ou ID inválido para atualização");
        }

        Optional<UserModel> existingUser = userRepo.findById(updatedUser.getId());
        if (existingUser.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado para atualização");
        }

        return userRepo.save(updatedUser);
    }

    public UserModel buyPacote(String userId, String pacoteId) {
        
        try {
            Optional<UserModel> existingUserOptional = userRepo.findById(userId);
            Optional<PacoteModel> existingPacoteOptional = pacoteRepo.findById(pacoteId);

            if (existingUserOptional.isPresent() && existingPacoteOptional.isPresent()) {
                UserModel existingUser = existingUserOptional.get();
                PacoteModel existingPacote = existingPacoteOptional.get();
               

                existingUser.addPacote(existingPacote);
                return userRepo.save(existingUser);
            } else {
                throw new IllegalArgumentException("Usuário ou Pacote não encontrado");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Falha ao realizar a compra", e);
        }
    }
    }

