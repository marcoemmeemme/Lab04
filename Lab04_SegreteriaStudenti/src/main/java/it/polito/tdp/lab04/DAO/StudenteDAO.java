package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.MatricolaInesistenteException;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	public Studente getNomeCognome(int matricola) throws MatricolaInesistenteException 
	{
		Studente s=null;
		final String sql = "SELECT *\r\n" + 
				"FROM studente\r\n" + 
				"WHERE matricola=?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			if(!rs.next())
			{
				conn.close();
				throw new MatricolaInesistenteException();
			}
			else 
			{
				s=new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("cds"));
			}
			conn.close();
			return s;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}

			}

	public List<Corso> getCorsiDiStudente(int matricola) throws MatricolaInesistenteException
	{
		List <Corso> listaCorsi=new LinkedList<>();
		String sql = "SELECT c.codins, c.crediti, c.nome, c.pd\r\n" + 
			"FROM corso AS c, iscrizione AS i, studente AS s \r\n" + 
			"WHERE c.codins=i.codins\r\n" + 
			"AND s.matricola=i.matricola\r\n"+
			"AND s.matricola=?";
		try
		{
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			if(!rs.next())
			{
				conn.close();
				throw new MatricolaInesistenteException();
			}
			while(rs.next()) 
			{
				listaCorsi.add(new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd")));
			}
			conn.close();
			return listaCorsi;
		}	 
		catch(SQLException e) 	
		{
			throw new RuntimeException(e);
		}
	}
}
