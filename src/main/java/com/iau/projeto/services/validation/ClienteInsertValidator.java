package com.iau.projeto.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.iau.projeto.domain.Cliente;
import com.iau.projeto.domain.enums.TipoCliente;
import com.iau.projeto.dto.ClienteNewDTO;
import com.iau.projeto.repositories.ClienteRepository;
import com.iau.projeto.resources.exception.FieldMessage;
import com.iau.projeto.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository cliente_repository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if(objDto.getTipo_cliente().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCPF(objDto.getCpf_ou_cnjp())) {
			list.add(new FieldMessage("cpf_ou_cnpj", "CPF invalido"));
		}
		
		if(objDto.getTipo_cliente().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(objDto.getCpf_ou_cnjp())) {
			list.add(new FieldMessage("cpf_ou_cnpj", "CNPJ invalido"));
		}
		
		Cliente aux = cliente_repository.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "Email ja existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getFieldname()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}