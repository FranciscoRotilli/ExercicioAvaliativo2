package aplicacao;
import dados.Bicharada;
import dados.Pet;

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
