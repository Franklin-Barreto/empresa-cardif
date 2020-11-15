package br.com.empresa.models.dtos;

public class ChefeDepartamentoDto {

	private Long idChefe;
	
	public ChefeDepartamentoDto(Long idChefe) {
		this.idChefe = idChefe;
	}
	
	public ChefeDepartamentoDto() {
	}
	
	public Long getIdChefe() {
		return idChefe;
	}
}
