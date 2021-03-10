package it.polito.tdp.parole.model;

import java.util.*;

public abstract class Parole 
{
	private List<String> paroleInserite;
	
	public Parole(List<String> listaParole) 
	{
		this.paroleInserite = listaParole;
	}
	
	public boolean addParola(String nuovaParola) 
	{
		if(this.paroleInserite.contains(nuovaParola))
			return false;
		else
		{
			this.paroleInserite.add(nuovaParola);
			this.paroleInserite.sort(String::compareTo);
			return true;
		}
	}
	
	public List<String> getElenco() //in ordine alfabetico
	{
		return this.paroleInserite;
	}
	
	public void reset() 
	{
		this.paroleInserite.clear();
	}

	public boolean deleteParola(String parolaInserita)
	{
		return this.paroleInserite.remove(parolaInserita);
	}

}
