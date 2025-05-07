package br.com.ulbra.apiaula.services;

import br.com.ulbra.apiaula.models.User;
import br.com.ulbra.apiaula.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository; //Atributo do tipo UserRepository

    //Injetando a dependência no construtor UserService
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Delega a busca do usuário para o repositório
    public User getUser(Long id){
        return this.userRepository.getUser(id);
    }

    //Delega a busca de uma lista com todos usuários para o repositório
    public List<User> getUsers(){
        return this.userRepository.getUsers();
    }

    //Cria um novo usuário no repositório
    public User createUser(User user){
        return this.userRepository.createUser(user);
    }

    //Delega a atualização para o repositório
    public User updateUser(Long id,  User userData){
        if (userData == null) {
            throw new IllegalArgumentException("Dados do usuário não podem ser nulos.");
        }
        //Verifica a existência do usuário
        User existingUser = getUser(id);

        //Atualiza os dados do usuário
        userData.setId(id);
        return userRepository.updateUser(userData);
    }

    //Delega a exclusão do usuário para o repositório
    public void deleteUser(Long id){
        this.userRepository.deleteUser(id);
    }
}
