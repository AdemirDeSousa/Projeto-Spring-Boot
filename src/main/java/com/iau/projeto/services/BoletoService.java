package com.iau.projeto.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.iau.projeto.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagamento, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagamento.setData_vencimento(cal.getTime());
	}
}
