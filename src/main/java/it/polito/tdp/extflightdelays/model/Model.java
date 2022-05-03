package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private Graph<Airport, DefaultWeightedEdge> grafo;
	
	public void creaGrafo(int distanza_minima)
	{
		this.grafo = new SimpleWeightedGraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		Map<Integer, Airport> ALLaeroporti = new HashMap<Integer, Airport>();
		Set<Airport> aeroporti = new HashSet<Airport>();
		
		for(Airport a:dao.loadAllAirports())
		{
			ALLaeroporti.put(a.getId(), a);
		}
		
		
		List<Flight> voli = dao.loadAllFlights();
		
		List<CoppiaId> fermateColl = dao.getAllAeroportiConnessi(distanza_minima);
		for(CoppiaId c:fermateColl)	
		{
			this.grafo.setEdgeWeight(ALLaeroporti.get(c.getIdp()), ALLaeroporti.get(c.getIda()), c.d);
			
		}
		
		Graphs.addAllVertices(grafo, dao.loadAllAirports());
		
		System.out.println("Aeroporti: " + grafo.vertexSet().size());
		System.out.println("Archi: " + grafo.edgeSet().size());
		
	}

}
