package aplicacao;
import dados.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class ACMEPet {
	private Bicharada bicharada;

	public ACMEPet() {
		bicharada = new Bicharada();
	}

	public void executar() {

	}

	private void cadastraAves() {
		ArrayList<ArrayList<String>> dados = lerDados("aves.csv");
		for (ArrayList<String> linha : dados) {
			int codigo = Integer.parseInt(linha.get(0));
			String nome = linha.get(1);
			double valorBase = Double.parseDouble(linha.get(2));
			boolean voa = linha.get(3).equals("TRUE");
			//TODO verificações e impressao do resultado no arquivo
			Pet pet = new Ave(codigo, nome, valorBase, voa);
			bicharada.create(pet);
		}
	}

	private void cadastraMamiferos() {
		ArrayList<ArrayList<String>> dados = lerDados("mamiferos.csv");
		if (!dados.isEmpty()) {
			for (ArrayList<String> linha : dados) {
				int codigo = Integer.parseInt(linha.get(0));
				String nome = linha.get(1);
				double valorBase = Double.parseDouble(linha.get(2));
				double peso = Double.parseDouble(linha.get(3));
				String pelo = linha.get(4);
				Pelo peloEnum = pelo.equals("CURTO") ? Pelo.CURTO : pelo.equals("MEDIO") ? Pelo.MEDIO : pelo.equals("LONGO") ? Pelo.LONGO : null;
				//TODO verificações e impressao do resultado no arquivo
				Pet pet = new Mamifero(codigo, nome, valorBase, peso, peloEnum);
				bicharada.create(pet);
			}
		}
	}

	private ArrayList<ArrayList<String>> lerDados(String arquivo) {
		Path path = Paths.get(arquivo);
		ArrayList<ArrayList<String>> dados = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] valores = line.split(",");
				ArrayList<String> linha = new ArrayList<>(Arrays.asList(valores));
				dados.add(linha);
			}
		}
		catch (IOException e) {
			System.err.format("Erro de E/S: %s%n", e);
		}
		return dados;
	}

}
