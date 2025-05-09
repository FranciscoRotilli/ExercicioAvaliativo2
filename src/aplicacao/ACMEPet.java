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
import java.util.Scanner;

public class ACMEPet {
	private final Bicharada bicharada;
	private final Scanner in;

	public ACMEPet() {
		bicharada = new Bicharada();
		in = new Scanner(System.in);
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
			if (bicharada.verificaCodigoUnico(codigo)) {
				Pet pet = new Ave(codigo, nome, valorBase, voa);
				bicharada.create(pet);
				//TODO substituir por impressao no arquivo
				System.out.println("1: " + codigo + " – " + nome + " – " + valorBase + " - " + voa);
			} else {
				//TODO substituir por impressao no arquivo
				System.out.println("1: Erro: codigo repetido: " + codigo);
			}
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
				if (bicharada.verificaCodigoUnico(codigo)) {
					Pet pet = new Mamifero(codigo, nome, valorBase, peso, peloEnum);
					bicharada.create(pet);
					//TODO substituir por impressao no arquivo
					System.out.println("2: " + codigo + " – " + nome + " – " + valorBase + " - " + peso + " - " + pelo);
				} else {
					//TODO substituir por impressao no arquivo
					System.out.println("2: Erro: codigo repetido: " + codigo);
				}
			}
		}
	}

	private void mostraValoresPets() {
		if (bicharada.isEmpty()) {
			//TODO substituir por impressao no arquivo
			System.out.println("3: Nenhum pet encontrado.");
		} else {
			for (Double valor : bicharada.getValoresPets()) {
				//TODO substituir por impressao no arquivo
				System.out.println("3: " + valor);
			}
		}
	}

	private void maiorValorBase() {
		if (bicharada.isEmpty()) {
			//TODO substituir por impressao no arquivo
			System.out.println("4: Nenhum pet encontrado.");
		} else {
			Pet maiorPet = bicharada.getMaiorValorPet();
			if (maiorPet instanceof Ave maior) {
				//TODO substituir por impressao no arquivo
                System.out.println("4: " + maior.getCodigo() + " - " + maior.getNome() + " - " + maior.getValorBase() + " - " + maior.getVoa());
			} else if (maiorPet instanceof Mamifero maior) {
				//TODO substituir por impressao no arquivo
				System.out.println("4: " + maior.getCodigo() + " - " + maior.getNome() + " - " + maior.getValorBase() + " - " + maior.getPeso() + " - " + maior.getPelo());
			}
		}
	}

	private void mostraDadosPets() {
		int codigo = in.nextInt();
		Pet pet = (Pet) bicharada.retrieve(codigo);
		if (pet == null) {
			//TODO substituir por impressao no arquivo
			System.out.println("5: Pet nao encontrado.");
		} else {
			if (pet instanceof Ave ave) {
				//TODO substituir por impressao no arquivo
				System.out.println("5: " + ave.getCodigo() + " - " + ave.getNome() + " - " + ave.getValorBase() + " - " + ave.getVoa());
			} else if (pet instanceof Mamifero mam) {
				//TODO substituir por impressao no arquivo
				System.out.println("5: " + mam.getCodigo() + " - " + mam.getNome() + " - " + mam.getValorBase() + " - " + mam.getPeso() + " - " + mam.getPelo());
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
