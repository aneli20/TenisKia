package org.teniskia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teniskia.entity.Venta;

public interface VentasRepository  extends JpaRepository<Venta, Integer>{

}
