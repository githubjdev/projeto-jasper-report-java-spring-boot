package projeto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "report_jasper")
public class ReportJasper {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private byte[] jasper;

	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getJasper() {
		return jasper;
	}

	public void setJasper(byte[] jasper) {
		this.jasper = jasper;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
