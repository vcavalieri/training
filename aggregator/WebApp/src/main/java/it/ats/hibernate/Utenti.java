package it.ats.hibernate;
// Generated Apr 5, 2019 12:53:10 PM by Hibernate Tools 5.1.10.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;



/**
 * Utenti generated by hbm2java
 */


public class Utenti implements java.io.Serializable {

	private int ndg;
	private String nome;
	private String cognome;
	private Date dataNascita;
	private String codFiscale;
	private Set utentiAssociazioneContos = new HashSet(0);
   
	
	public Utenti() {
	}

	public Utenti(int ndg) {
		this.ndg = ndg;
	}

	public Utenti(int ndg, String nome, String cognome, Date dataNascita, String codFiscale,
			Set utentiAssociazioneContos) {
		this.ndg = ndg;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.codFiscale = codFiscale;
		this.utentiAssociazioneContos = utentiAssociazioneContos;
	}

	public int getNdg() {
		return this.ndg;
	}

	public void setNdg(int ndg) {
		this.ndg = ndg;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return this.dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getCodFiscale() {
		return this.codFiscale;
	}

	public void setCodFiscale(String codFiscale) {
		this.codFiscale = codFiscale;
	}

	public Set getUtentiAssociazioneContos() {
		return this.utentiAssociazioneContos;
	}

	public void setUtentiAssociazioneContos(Set utentiAssociazioneContos) {
		this.utentiAssociazioneContos = utentiAssociazioneContos;
	}

}
