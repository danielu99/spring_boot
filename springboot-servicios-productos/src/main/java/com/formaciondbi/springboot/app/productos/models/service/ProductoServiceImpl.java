package com.formaciondbi.springboot.app.productos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import com.formaciondbi.springboot.app.productos.models.dao.ProductoDao;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired //Este se inyecta por defecto del contenedor, ya está hecho el Bean
	private ProductoDao productoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return (List<Producto>)productoDao.findAll(); //Retorna un iterable y por eso casteamos
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return productoDao.findById(id).orElse(null); //Regresa un optional, por si no se encontró en la BD.
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return productoDao.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		productoDao.deleteById(id);
	}

}
