package com.ruth.servicex.pagamento.service;

import com.ruth.servicex.pagamento.domain.PagamentoBoleto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BoletoService {

    public void preencherBoleto(PagamentoBoleto pagamento, LocalDate instantePedido) {
        pagamento.setDataVencimento(instantePedido.plusDays(7));
    }
}