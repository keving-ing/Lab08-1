package it.polito.tdp.extflightdelays.model;

public class CoppiaId {
	
	int Idp;
	int Ida;
	double d;
	
	
	public CoppiaId(int idp, int ida, double d) {
		Idp = idp;
		Ida = ida;
		this.d = d;
	}


	public int getIdp() {
		return Idp;
	}


	public void setIdp(int idp) {
		Idp = idp;
	}


	public int getIda() {
		return Ida;
	}


	public void setIda(int ida) {
		Ida = ida;
	}
	
	
	
	

}
