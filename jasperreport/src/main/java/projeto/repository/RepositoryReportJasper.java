package projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import projeto.model.ReportJasper;

@Repository
public interface RepositoryReportJasper extends JpaRepository<ReportJasper, Long> {
	
	
	@Query("select r from ReportJasper r where nome = ?1")
	ReportJasper buscaByName(String nome);

}
