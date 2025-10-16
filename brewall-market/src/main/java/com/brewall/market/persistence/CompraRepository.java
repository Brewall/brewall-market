package com.brewall.market.persistence;

import com.brewall.market.domain.Purchase;
import com.brewall.market.domain.repository.PurchaseRepository;
import com.brewall.market.persistence.crud.CompraCrudRepository;
import com.brewall.market.persistence.entity.Compra;
import com.brewall.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        System.out.println("Compra ID antes de guardar: " + compra.getIdCompra());

        // Asegura que la lista de productos no sea null
        compra.setProductos(Optional.ofNullable(compra.getProductos()).orElse(new ArrayList<>()));

        // Asocia los productos a la compra
        compra.getProductos().forEach(producto -> producto.setCompra(compra));

        // Hibernate persistirá la compra y generará el ID automáticamente
        Compra savedCompra = compraCrudRepository.save(compra);
        return mapper.toPurchases(savedCompra);
    }

}
