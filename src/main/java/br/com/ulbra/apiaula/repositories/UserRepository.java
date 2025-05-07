package br.com.ulbra.apiaula.repositories;

import br.com.ulbra.apiaula.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class UserRepository {
    private List<User> lista = new ArrayList<>(); //Lista de usuários

    //Criando construtor que inicializa uma lista de usuários com dados
    public UserRepository(){
        lista.add(new User(1L, "Maria"));
        lista.add(new User(2L, "João"));
        lista.add(new User(3L, "Daniel"));
    }

    //Função para retornar a lista de todos os usuários
    public List<User> getUsers(){
        return lista;
    }

    //Função para retornar um usuário específico com base no ID fornecido
    public User getUser(Long id){
        return lista
                .stream() //transforma a lista em uma stream, permitindo encadear e filtrar chamadas
                .filter(item -> Objects.equals(item.getId(), id)) //Filtra os elementos usando uma lambda expressio
                .findFirst()//Obtém o primeiro elemento que corresponde ao filtro
                .orElseThrow();
    }

    //Função para criar
    public User createUser(User user){
        lista.add(user);
        return user;
    }

    //Função para atualizar
    public User updateUser(User user){
        User existingUser = getUser(user.getId());
        int index = lista.indexOf(existingUser);
        lista.set(index, user);
        return user;
    }


    //Função para deletar
    public void deleteUser(Long id){
        User user = lista
                .stream() //transforma a lista em uma stream, permitindo encadear e filtrar chamadas
                .filter(item -> Objects.equals(item.getId(), id)) //Filtra os elementos usando uma lambda expressio
                .findFirst()//Obtém o primeiro elemento que corresponde ao filtro
                .orElseThrow();

        lista.remove(user);
    }
}
