package ar.com.taller2.papers.libraries;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.ext.ComponentAttributeProvider;

import ar.com.taller2.papers.model.Arista;

public class VertexAttributeProvider implements ComponentAttributeProvider<Arista> {

	public Map getComponentAttributes(Arista arista) {
		Map<String,String> atributos = new HashMap<String,String>();
		atributos.put("weight", arista.getPeso().toString());
		return atributos;
	}

}
