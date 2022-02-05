package com.will;

import com.will.models.Catalogo;
import com.will.models.Producto;
import com.will.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class WilsonMongeInventariosApplication {

	@Autowired
	ProductoRepository productoRepository;

	public static void main(String[] args) {
		SpringApplication.run(WilsonMongeInventariosApplication.class, args);
	}

	@PostConstruct
	public void loadProductos(){
		System.out.println("--------------INGRESA A INICIALIZAR LAS TIENDAS-------------------");
		String fakeApi = "https://mocki.io/v1/f9d8878a-51d2-45e9-bb50-c5b2da01dc02";
		RestTemplate restTemplate = new RestTemplate();
		Catalogo catalogo = restTemplate.getForObject(fakeApi, Catalogo.class);
		List<Producto> productoList = catalogo.getProds();
		System.out.println("producto 0 >>>>>>>>>>>>>>>>>>>.."+productoList.get(0).getCod());
		if(productoRepository.count() == 0){
			productoRepository.saveAll(productoList);
		}
	}


}
