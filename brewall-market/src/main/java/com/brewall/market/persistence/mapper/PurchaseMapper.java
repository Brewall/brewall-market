package com.brewall.market.persistence.mapper;

import com.brewall.market.domain.Purchase;
import com.brewall.market.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {

    @Mappings({
            @Mapping(source = "idCompra", target = "purchaseId"),
            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario", target = "comment"),
            @Mapping(source = "estado", target = "state"),
            @Mapping(source = "productos", target = "items")
    })
    Purchase toPurchases(Compra compra);

    List<Purchase> toPurchases(List<Compra> compras);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(source = "purchaseId", target = "idCompra"),
            @Mapping(source = "clientId", target = "idCliente"),
            @Mapping(source = "date", target = "fecha"),
            @Mapping(source = "paymentMethod", target = "medioPago"),
            @Mapping(source = "comment", target = "comentario"),
            @Mapping(source = "state", target = "estado"),
            @Mapping(source = "items", target = "productos"),
            @Mapping(target = "cliente", ignore = true)
    })
    Compra toCompra(Purchase purchase);
}
