package br.unipar.devbackend.agendaenderecos.dao;
import br.unipar.devbackend.agendaenderecos.Cliente;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ClienteDAO {

    private final EntityManager em;

    public ClienteDAO(EntityManager em) {
        this.em = em;
    }

    public void gravar(Cliente cliente) {
        em.persist(cliente);
    }

    public Cliente editar(Cliente cliente) {
        return em.merge(cliente);
    }

    public void delete(Long id) {
        Cliente clienteParaRemover = em.find(Cliente.class, id);
        if (clienteParaRemover != null) {
            em.remove(clienteParaRemover);
        }
    }

    public Cliente findById(Long id) {
        return em.find(Cliente.class, id);
    }

    public List<Cliente> findAll() {
        return em.createQuery("SELECT d FROM Cliente c", Cliente.class).getResultList();
    }
}