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
import java.util.Locale;
import java.util.Scanner;

public class ACMEPet {
	private final Bicharada bicharada;
	private final Scanner in;

	public ACMEPet() {
		bicharada = new Bicharada();
		in = new Scanner(System.in);
		Locale.setDefault(Locale.US);
	}

	public void executar() {
		cadastraAves(); // 1. Cadastrar aves
		cadastraMamiferos(); // 2. Cadastrar mamíferos
		mostraValoresPets(); // 3. Mostrar valores de todos os pets
		maiorValorBase(); // 4. Mostrar pet com maior valor base
		mostraDadosPet(); // 5. Mostrar os dados de um determinado pet
		removePet(); // 6. Remover um determinado pet
		qtdAvesVoam(); // 7. Mostrar a quantidade de aves que voam
		mostraMamPeloCurtoMaisPesado(); // 8. Mostrar mamífero com pelo curto mais pesado
		mostraPeloMaiorQtd(); // Extra: Mostrar qual tipo de pelo possui mais mamíferos cadastrados
	}

	private void cadastraAves() {
		ArrayList<ArrayList<String>> dados = leDados("aves.csv");
		for (ArrayList<String> linha : dados) {
			int codigo = Integer.parseInt(linha.get(0));
			String nome = linha.get(1);
			double valorBase = Double.parseDouble(linha.get(2));
			boolean voa = linha.get(3).equals("TRUE");
			if (bicharada.verificaCodigoUnico(codigo)) {
				Pet pet = new Ave(codigo, nome, valorBase, voa);
				bicharada.create(pet);
				//TODO substituir por impressao no arquivo
				System.out.println("1: " + codigo + " – " + nome + " – " + String.format("%.2f", valorBase) + " - " + voa);
			} else {
				//TODO substituir por impressao no arquivo
				System.out.println("1: Erro: codigo repetido: " + codigo);
			}
		}
	}

	private void cadastraMamiferos() {
		ArrayList<ArrayList<String>> dados = leDados("mamiferos.csv");
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
					System.out.println("2: " + codigo + " – " + nome + " – " + String.format("%.2f", valorBase) + " - " + peso + " - " + pelo);
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
				System.out.println("3: " + String.format("%.2f", valor));
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
                System.out.println("4: " + maior.getCodigo() + " - " + maior.getNome() + " - " + String.format("%.2f", maior.getValorBase()) + " - " + maior.getVoa());
			} else if (maiorPet instanceof Mamifero maior) {
				//TODO substituir por impressao no arquivo
				System.out.println("4: " + maior.getCodigo() + " - " + maior.getNome() + " - " + String.format("%.2f", maior.getValorBase()) + " - " + maior.getPeso() + " - " + maior.getPeloString());
			}
		}
	}

	private void mostraDadosPet() {
		int codigo = leEntrada("dados.txt", 0);
		Pet pet = (Pet) bicharada.retrieve(codigo);
		if (pet == null) {
			//TODO substituir por impressao no arquivo
			System.out.println("5: Pet nao encontrado.");
		} else {
			if (pet instanceof Ave ave) {
				//TODO substituir por impressao no arquivo
				System.out.println("5: " + ave.getCodigo() + " - " + ave.getNome() + " - " + String.format("%.2f", ave.getValorBase()) + " - " + ave.getVoa());
			} else if (pet instanceof Mamifero mam) {
				//TODO substituir por impressao no arquivo
				System.out.println("5: " + mam.getCodigo() + " - " + mam.getNome() + " - " + String.format("%.2f", mam.getValorBase()) + " - " + mam.getPeso() + " - " + mam.getPeloString());
			}
		}
	}

	private void removePet() {
		int codigo = leEntrada("dados.txt", 1);
		Pet pet = (Pet) bicharada.retrieve(codigo);
		if (pet == null) {
			//TODO substituir por impressao no arquivo
			System.out.println("6: Pet nao encontrado.");
		} else {
			bicharada.delete(codigo);
			//TODO substituir por impressao no arquivo
			System.out.println("6: Pet removido, codigo: " + codigo);
		}
	}

	private void qtdAvesVoam() {
		int cont = bicharada.qtdAvesVoam();
		//TODO substituir por impressao no arquivo
		System.out.println("7: Quantidade de aves que voam: " + cont);
	}

	private void mostraMamPeloCurtoMaisPesado() {
		Mamifero mam = bicharada.mamiferoPeloCurtoMaisPesado();
		if (mam == null) {
			//TODO substituir por impressao no arquivo
			System.out.println("8: Nenhum mamifero de pelo curto encontrado.");
		} else {
			//TODO substituir por impressao no arquivo
			System.out.println("8: " + mam.getCodigo() + " - " + mam.getNome() + " - " + mam.getValorBase() + " - " + mam.getPeso() + " - " + mam.getPeloString());
		}
	}

	private void mostraPeloMaiorQtd() {
		//TODO substituir por impressao no arquivo
		if (bicharada.isEmpty()) System.out.println("ERRO!");

		int curto = bicharada.contPelo(Pelo.CURTO);
		int medio = bicharada.contPelo(Pelo.MEDIO);
		int longo = bicharada.contPelo(Pelo.LONGO);

		if (curto == 0 && medio == 0 && longo == 0) {
			System.out.println("9: Nenhum mamifero cadastrado.");
		} else {
			if (curto > medio && curto > longo) System.out.println("9: CURTO - " + curto);
			else if (medio > curto && medio > longo) System.out.println("9: MEDIO - " + medio);
			else System.out.println("9: LONGO - " + longo);
		}
	}

	private ArrayList<ArrayList<String>> leDados(String arquivo) {
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

	private int leEntrada(String arquivo, int linha) {
		Path path = Paths.get(arquivo);
		int entrada = 0;
		try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
			String[] line = new String[2];
			line[0] = reader.readLine();
			line[1] = reader.readLine();
			if (linha == 0) entrada = Integer.parseInt(line[0]);
			else if (linha == 1) entrada = Integer.parseInt(line[1]);

		}
		catch (IOException e) {
			System.err.format("Erro de E/S: %s%n", e);
		}
		return entrada;
	}

}
