package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import metier.Serialisation;

/**
 * @author Thibaut MASSELIN
 * @date 15/11/2016
 */
@XStreamAlias("Connexion")
public class Connexion extends ChaineDeCommande{
	//Attribute
	@XStreamAlias("identifiant")
	private String identifiant;
	@XStreamAlias("motDePasse")
	private String motDePasse;
	
	//Constructor
	public Connexion(){
		this.identifiant = new String();
		this.motDePasse = new String();
	}
	
	//Method
	/**
	 * Traitement sp�cifique dans la chaine pour cette classe 
	 * @return vrai(la bonne classe) faux(la mauvaise classe)
	 */
	@Override
	public boolean operationSpec(String xml) {
		deserialisation = new Serialisation<Connexion>();
		deserialisation.deserialisation(xml);
		if(deserialisation.getT() != null){
			//si c'est l� bonne classe pour la lecture
			if(this.getClass().isAssignableFrom(deserialisation.getT().getClass())){
				return true;
			}			
		}
		return false;
	}

	@Override
	public ChaineDeCommande creer(Map<String, Object> contenuMap, String des, String exp) throws IllegalArgumentException{
		
		for(Entry<String, Object> obj : contenuMap.entrySet()){
			if(obj.getKey().equals("identifiant"))
				this.setIdentifiant(obj.getValue().toString());
			if(obj.getKey().equals("motDePasse"))
				this.setMotDePasse(obj.getValue().toString());
		}
		this.setExpediteur(this.getIdentifiant());
		this.setDestinataire("Serveur");
		
		return this;
	}

	@Override
	public Map<String, Object> getValeurs() {
		Map<String,Object> maps = new HashMap<>();
		maps.put("identifiant", identifiant);
		maps.put("motDePasse", motDePasse);
		return maps;
	}
	//Getter - Setter
	
	@Override
	public String getExpediteur() {
		return expediteur;
	}
	public void setExpediteur(String expediteur) {
		this.expediteur = expediteur;
	}
	@Override
	public String getDestinataire() {
		return destinataire;
	}
	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	
}
