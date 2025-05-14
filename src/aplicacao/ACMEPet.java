package aplicacao;
import dados.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

public class ACMEPet {
	private final Bicharada bicharada;
	private static final Path PATH_RELATORIO = Paths.get("relatorio.txt");
	private static final Path PATH_ENTRADA = Paths.get("dados.txt");

	public ACMEPet() {
		bicharada = new Bicharada();
	}

	public void executar() {
		inicializaRelatorio(); // Prepara arquivo do relatorio
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
            try {
                int codigo = Integer.parseInt(linha.get(0));
                String nome = linha.get(1);
                double valorBase = Double.parseDouble(linha.get(2));
                boolean voa = linha.get(3).equals("TRUE");
                Pet pet = new Ave(codigo, nome, valorBase, voa);
                if (bicharada.verificaCodigoUnico(codigo) && bicharada.create(pet)) {
                    escreveRelatorio("1: " + codigo + " - " + nome + " - " + String.format("%.2f", valorBase) + " - " + voa);
                } else {
                    escreveRelatorio("1: Erro: codigo repetido: " + codigo);
                }
            } catch (NumberFormatException e) {
				escreveRelatorio("1: Erro de leitura de aves.");
            }
        }
	}

	private void cadastraMamiferos() {
		ArrayList<ArrayList<String>> dados = leDados("mamiferos.csv");
		if (!dados.isEmpty()) {
			for (ArrayList<String> linha : dados) {
                try {
                    int codigo = Integer.parseInt(linha.get(0));
                    String nome = linha.get(1);
                    double valorBase = Double.parseDouble(linha.get(2));
                    double peso = Double.parseDouble(linha.get(3));
                    String pelo = linha.get(4);
                    Pelo peloEnum = pelo.equals("CURTO") ? Pelo.CURTO : pelo.equals("MEDIO") ? Pelo.MEDIO : pelo.equals("LONGO") ? Pelo.LONGO : null;
					if (peloEnum == null) {
						throw new NumberFormatException();
					}
                    Pet pet = new Mamifero(codigo, nome, valorBase, peso, peloEnum);
                    if (bicharada.verificaCodigoUnico(codigo) && bicharada.create(pet)) {
                        escreveRelatorio("2: " + codigo + " - " + nome + " - " + String.format("%.2f", valorBase) + " - " + peso + " - " + pelo);
                    } else {
                        escreveRelatorio("2: Erro: codigo repetido: " + codigo);
                    }
                } catch (NumberFormatException e) {
                    escreveRelatorio("2: Erro de leitura de mamiferos.");
                }
            }
		}
	}

	private void mostraValoresPets() {
		if (bicharada.isEmpty()) {
			escreveRelatorio("3: Nenhum pet encontrado.");
		} else {
			for (Double valor : bicharada.getValoresPets()) {
				escreveRelatorio("3: " + String.format("%.2f", valor));
			}
		}
	}

	private void maiorValorBase() {
		if (bicharada.isEmpty()) {
			escreveRelatorio("4: Nenhum pet encontrado.");
		} else {
			Pet maiorPet = bicharada.getMaiorValorPet();
			if (maiorPet instanceof Ave maior) {
                escreveRelatorio("4: " + maior.getCodigo() + " - " + maior.getNome() + " - " + String.format("%.2f", maior.getValorBase()) + " - " + maior.getVoa());
			} else if (maiorPet instanceof Mamifero maior) {
				escreveRelatorio("4: " + maior.getCodigo() + " - " + maior.getNome() + " - " + String.format("%.2f", maior.getValorBase()) + " - " + maior.getPeso() + " - " + maior.getPelo());
			}
		}
	}

	private void mostraDadosPet() {
		int codigo = leEntrada(0);
		Pet pet = (Pet) bicharada.retrieve(codigo);
		if (pet == null) {
			escreveRelatorio("5: Pet nao encontrado.");
		} else {
			if (pet instanceof Ave ave) {
				escreveRelatorio("5: " + ave.getCodigo() + " - " + ave.getNome() + " - " + String.format("%.2f", ave.getValorBase()) + " - " + ave.getVoa());
			} else if (pet instanceof Mamifero mam) {
				escreveRelatorio("5: " + mam.getCodigo() + " - " + mam.getNome() + " - " + String.format("%.2f", mam.getValorBase()) + " - " + mam.getPeso() + " - " + mam.getPelo());
			}
		}
	}

	private void removePet() {
		int codigo = leEntrada(1);
		Pet pet = (Pet) bicharada.retrieve(codigo);
		if (pet == null) {
			escreveRelatorio("6: Pet nao encontrado.");
		} else if (bicharada.delete(codigo)) {
			escreveRelatorio("6: Pet removido, codigo: " + codigo);
		}
	}

	private void qtdAvesVoam() {
		int cont = bicharada.qtdAvesVoam();
		escreveRelatorio("7: Quantidade de aves que voam: " + cont);
	}

	private void mostraMamPeloCurtoMaisPesado() {
		Mamifero mam = bicharada.mamiferoPeloCurtoMaisPesado();
		if (mam == null) {
			escreveRelatorio("8: Nenhum mamifero de pelo curto encontrado.");
		} else {
			escreveRelatorio("8: " + mam.getCodigo() + " - " + mam.getNome() + " - " + mam.getValorBase() + " - " + mam.getPeso() + " - " + mam.getPelo());
		}
	}

	private void mostraPeloMaiorQtd() {
		if (bicharada.isEmpty()) escreveRelatorio("ERRO!");

		int curto = bicharada.contPelo(Pelo.CURTO);
		int medio = bicharada.contPelo(Pelo.MEDIO);
		int longo = bicharada.contPelo(Pelo.LONGO);

		if (curto == 0 && medio == 0 && longo == 0) {
			escreveRelatorio("9: Nenhum mamifero cadastrado.");
		} else {
			if (curto > medio && curto > longo) escreveRelatorio("9: CURTO - " + curto);
			else if (medio > curto && medio > longo) escreveRelatorio("9: MEDIO - " + medio);
			else escreveRelatorio("9: LONGO - " + longo);
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

	private int leEntrada(int linha) {
		int entrada = 0;
		try (BufferedReader reader = Files.newBufferedReader(PATH_ENTRADA, Charset.defaultCharset())) {
			String[] line = new String[2];
			line[0] = reader.readLine();
			line[1] = reader.readLine();
			if (linha == 0) {
                try {
                    entrada = Integer.parseInt(line[0]);
                } catch (NumberFormatException e) {
					escreveRelatorio("5: Erro: leitura do arquivo de dados.");
                    entrada = -1;
                }
            } else if (linha == 1) {
                try {
                    entrada = Integer.parseInt(line[1]);
                } catch (NumberFormatException e) {
					escreveRelatorio("6: Erro: leitura do arquivo de dados.");
                    entrada = -1;
                }
            }
		}
		catch (IOException e) {
			System.err.format("Erro de E/S: %s%n", e);
		}
		return entrada;
	}

	private void inicializaRelatorio() {
		try {
			Files.write(PATH_RELATORIO, new byte[0]);
		}
		catch (IOException e) {
			System.err.format("Erro de E/S: %s%n", e);
		}
	}

	private void escreveRelatorio(String linha) {
		try (BufferedWriter writer = Files.newBufferedWriter(PATH_RELATORIO, Charset.defaultCharset(), StandardOpenOption.APPEND)) {
			writer.write(linha);
			writer.newLine();
		}
		catch (IOException e) {
			System.err.format("Erro de E/S: %s%n", e);
		}
	}
}
