package com.will;

import com.will.models.Cliente;
import com.will.models.Producto;
import com.will.repository.ClienteRepository;
import com.will.repository.ProductoRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ProductoTest {
    @Autowired
    ProductoRepository productoRepository;

    @Test
    public void updateProductoTest(){
        Optional<Producto> producto_optional = productoRepository.findById(1L);
        if(producto_optional.isPresent()){
            Producto producto = producto_optional.get();
            producto.setStock(10);
            Producto nuevo = productoRepository.save(producto);
            Assertions.assertThat(nuevo.getStock()).isEqualTo(10);
        }
    }


}
