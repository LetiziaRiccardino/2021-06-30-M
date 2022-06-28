package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Adiacenza;
import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Interactions;


public class GenesDao {
	
	public List<Genes> getAllGenes(){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}
	
	public List<Integer> getChromosome(){
		String sql="SELECT DISTINCT Chromosome "
				+ "FROM genes "
				+ "WHERE Chromosome<>0 ";
		List<Integer> ch= new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				ch.add(res.getInt("Chromosome"));
			}
			res.close();
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
		return ch;
	}
	
	public List<Adiacenza> getArchiEPeso(){
		final String sql = "SELECT distinct g1.Chromosome as cr1,g2.Chromosome as cr2,SUM(distinct i.Expression_Corr) as weight  "
				+ "FROM genes g1,genes g2, interactions i "
				+ "WHERE g1.Chromosome!=g2.Chromosome AND g1.Chromosome!=0 AND g2.Chromosome!=0 AND g1.GeneID=i.GeneID1 AND g2.GeneID=i.GeneID2  "
				+ "GROUP BY g1.Chromosome,g2.Chromosome "
				+ "ORDER BY g1.Chromosome,g2.Chromosome";
		List<Adiacenza> ad= new ArrayList<Adiacenza>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet res = st.executeQuery();
			while (res.next()) {
				ad.add(new Adiacenza(res.getInt("cr1"), res.getInt("cr2"), res.getDouble("weight")));
			}
			res.close();
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
//		System.out.print("data.."+ad.size());
		return ad;
	}
	


	
}
