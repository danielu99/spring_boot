package com.formaciondbi.springboot.app.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;


public interface ProductoDao extends CrudRepository<Producto,Long>{ // Generic=T,ID=Type Of Key <T,ID>
	
}
