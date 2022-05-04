package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;
public class Model {
	
	private Graph<Airport, DefaultWeightedEdge> grafo;
	Map<DefaultWeightedEdge,Double> archi_distanza;
	
	public String creaGrafo(int distanza_minima)
	{
		this.grafo = new SimpleWeightedGraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		archi_distanza = new HashMap<DefaultWeightedEdge,Double>();
		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		Map<Integer, Airport> ALLaeroporti = new HashMap<Integer, Airport>();
		
		for(Airport a:dao.loadAllAirports())
		{
			ALLaeroporti.put(a.getId(), a);
		}
		
		
		Graphs.addAllVertices(grafo, dao.loadAllAirports());
		List<CoppiaId> fermateColl = dao.getAllAeroportiConnessi(distanza_minima);
		for(CoppiaId c:fermateColl)	
		{
			DefaultWeightedEdge e = Graphs.addEdge(grafo, ALLaeroporti.get(c.Idp), ALLaeroporti.get(c.Ida), c.d);
			archi_distanza.put(e, c.d);
			System.out.println(e + " " + c.d);
			
			
		}
		
		
		
	
		System.out.println("Vertici: " + grafo.vertexSet().size());
		System.out.println("Archi: " + grafo.edgeSet().size());
		
		return "Vertici: " + grafo.vertexSet().size() + "\n" + "Archi: " + grafo.edgeSet().size() + "\n";
		
	}

	public Map<DefaultWeightedEdge, Double> getArchi_distanza() {
		return archi_distanza;
	}
	
	
	

}
