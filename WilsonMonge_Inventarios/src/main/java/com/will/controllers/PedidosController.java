package com.will.controllers;

import com.will.models.*;
import com.will.repository.ClienteRepository;
import com.will.repository.ProductoRepository;
import com.will.repository.RegistroRepository;
import com.will.repository.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    TiendaRepository tiendaRepository;
    @Autowired
    RegistroRepository registroRepository;

    @PostMapping("/addRegistro")
    public ResponseEntity<String> addRegistro(@RequestBody Pedido pedido) {
        for (Registro registro : pedido.getRegistros()) {
            Optional<Cliente> cliente_optional = clienteRepository.findById(registro.getId_cliente());
            if(!cliente_optional.isPresent()){
                return new ResponseEntity<>("CLIENTE NO EXISTE", HttpStatus.OK);
            }
            Optional<Tienda> tienda_optional = tiendaRepository.findById(registro.getCodigo_tienda());
            if(!tienda_optional.isPresent()){
                return new ResponseEntity<>("TIENDA NO EXISTE", HttpStatus.OK);
            }
            Optional<Producto> producto_optional = productoRepository.findById(registro.getId_producto());
            if (producto_optional.isPresent()) {
                Producto producto = producto_optional.get();
                int validar_stock = producto.getStock() - registro.getCantidad();
                int nuevo_stock = validar_stock;
                if (validar_stock < -10) {
                    return new ResponseEntity<>("UNIDADES NO DISPONIBLES (>10)", HttpStatus.OK);
                }
                if (validar_stock < -5 && validar_stock >= -10) {
                    String fakeApi = "https://mocki.io/v1/a8434bde-4ce3-4359-94a6-151b281542d7";
                    RestTemplate restTemplate = new RestTemplate();
                    Stock stock = restTemplate.getForObject(fakeApi, Stock.class);
                    if (stock != null) {
                        nuevo_stock = (producto.getStock() + stock.getStock()) - registro.getCantidad();
                    }
                }
                if (validar_stock < 0 && validar_stock >= 5) { //CAMBIAR A UN SERVICI ASYNC
                    String fakeApi = "https://mocki.io/v1/cc5a03f3-72d5-4acc-8854-5ecd6df714ad";
                    RestTemplate restTemplate = new RestTemplate();
                    Stock stock = restTemplate.getForObject(fakeApi, Stock.class);
                    if (stock != null) {
                        nuevo_stock = (producto.getStock() + stock.getStock()) - registro.getCantidad();
                    }
                }
                producto.setStock(nuevo_stock);
                productoRepository.save(producto);
                registroRepository.save(registro);
            }
        }
        return new ResponseEntity<>("PEDIDO REALIZADO CORRECTAMENTE", HttpStatus.OK);
    }
}
