package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	public Model()
	{
		corsoDAO=new CorsoDAO();
		studenteDAO=new StudenteDAO();
	}
	
	public List<Corso> getTuttiCorsi()
	{
		return corsoDAO.getTuttiICorsi();
	}
	
	public Corso getCorso(String codIns)
	{
		return corsoDAO.getCorso(codIns);
	}
	
	public List<Studente> getStudenti(Corso corso)
	{
		return corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	public Studente getStudente(int matricola) throws MatricolaInesistenteException
	{
		return studenteDAO.getNomeCognome(matricola);
	}
	
	public List<Corso> getCorsiIscritto(int matricola) throws MatricolaInesistenteException
	{
		return studenteDAO.getCorsiDiStudente(matricola);
	}
	
	public boolean checkStudenteCorso(Studente studente, Corso corso)
	{
		return corsoDAO.inscriviStudenteACorso(studente, corso);
	}
}
