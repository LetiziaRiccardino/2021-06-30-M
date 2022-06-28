package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private GenesDao dao;
	private Graph <Integer, DefaultWeightedEdge> grafo;
	List<Adiacenza> archi;
	
	public Model(){
		dao= new GenesDao();
	}
	
	public void creaGrafo() {
		this.grafo= new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		//VERTICI
		List<Integer> vertici= dao.getChromosome();
		Graphs.addAllVertices(this.grafo, vertici);
		System.out.println("#vertici "+vertici.size() );
		//ARCHI
		this.archi= dao.getArchiEPeso();
		System.out.println("Lista archi "+archi.size());
		int i=0;
		for(Adiacenza a: archi) {
			Graphs.addEdge(this.grafo, a.getC1(), a.getC2(), a.getPeso());
			i++;
		}
		System.out.println("#archi "+grafo.edgeSet().size() +" i "+i);
	}
	public Integer getNVertici() {
		return this.grafo.vertexSet().size();
	}
	public Integer getNArchi() {
		return this.grafo.edgeSet().size();
	}
	public Double getPesoMin() {
		Double min=100000000.0;
		for(DefaultWeightedEdge e: this.grafo.edgeSet()) {
			if(this.grafo.getEdgeWeight(e)<min)
				min=this.grafo.getEdgeWeight(e);
		}
		return min;
	}
	public Double getPesoMax() {
		Double max=0.0;
		for(DefaultWeightedEdge e: this.grafo.edgeSet()) {
			if(this.grafo.getEdgeWeight(e)>max)
				max=this.grafo.getEdgeWeight(e);
		}
		return max;
	}
	
	public List<Adiacenza> getContaArchiMaggiore(Double S){
		List<Adiacenza> contaArchiMaggiore= new ArrayList<>();
		for(Adiacenza a: this.archi) {
			if(a.getPeso()>S)
				contaArchiMaggiore.add(a);
		}
		return contaArchiMaggiore;
	}
	public List<Adiacenza> getContaArchiMinore(Double S){
		List<Adiacenza> contaArchiMinore= new ArrayList<>();
		for(Adiacenza a: this.archi) {if(a.getPeso()<S)
				contaArchiMinore.add(a);
		}
		return contaArchiMinore;
	}
	
	

}