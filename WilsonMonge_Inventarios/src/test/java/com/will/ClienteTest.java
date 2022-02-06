package com.will;

import static org.assertj.core.api.Assertions.assertThat;

import com.will.models.Cliente;
import com.will.repository.ClienteRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ClienteTest {
    @Autowired
    ClienteRepository clienteRepository;

    @Test
    public void addClienteTest(){
        Cliente cliente = new Cliente();
        cliente.setIdentificacion("01010001234");
        cliente.setNombre("prueba unitaria");
        cliente.setFoto("");
        Cliente nuevo = clienteRepository.save(cliente);
        Assertions.assertThat(nuevo.getIdentificacion()).isEqualTo("01010001234");
    }

    @Test
    public void updateClienteTest(){
        Optional<Cliente> cliente_optional = clienteRepository.findById("0104112347");
        if(cliente_optional.isPresent()){
            Cliente cliente = cliente_optional.get();
            cliente.setNombre("aaaaaaa");
            Cliente nuevo = clienteRepository.save(cliente);
            Assertions.assertThat(nuevo.getNombre()).isEqualTo("aaaaaaa");
        }
    }

    @Test
    public void deleteClienteTest(){
        Optional<Cliente> cliente_optional = clienteRepository.findById("0104112347");
        if(cliente_optional.isPresent()){
            Cliente cliente = cliente_optional.get();
            clienteRepository.delete(cliente);
            Optional<Cliente> cliente_optional2 = clienteRepository.findById("0104112347");
            cliente = null;
            if(cliente_optional2.isPresent()){
                cliente = cliente_optional2.get();
            }
            Assertions.assertThat(cliente).isNull();
        }
    }
}
