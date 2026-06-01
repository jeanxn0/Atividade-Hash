import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

    private static void carregarCSV(String arquivo, HashEndecamentoAberto<String> hashTable) {
        File file = new File(arquivo);
        try (Scanner fscan = new Scanner(file)) {
            while (fscan.hasNextLine()) {
                String line = fscan.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",", 2);
                if (parts.length < 2) continue;
                try {
                    long chave = Long.parseLong(parts[0].trim());
                    String nome = parts[1].trim();
                    hashTable.put(chave, nome);
                } catch (NumberFormatException e) {
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado: " + arquivo);
        }
    }

  private static int menu(Scanner scanner) {
        System.out.println("\t\t*** IFSULDEMINAS - CAMPUS MACHADO ***");
        System.out.println("\t\t*** Estrutura de Dados I ***");
        System.out.println("\t\t*** HASH OPEN ADDRESSING ***");
        System.out.println("1-Inserir");
        System.out.println("2-Remover");
        System.out.println("3-Buscar");
        System.out.println("4-Alterar");
        System.out.println("0-Sair");
        System.out.print("Escolha uma opcao: ");
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashEndecamentoAberto<String> hashTable = new HashEndecamentoAberto<>();
        System.out.print("Arquivo CSV para carregar (enter para pular): ");
        String arquivo = scanner.nextLine().trim();
        if (!arquivo.isEmpty()) {
            carregarCSV(arquivo, hashTable);
        }
        int op;
        do {
            hashTable.printHash();
            op = menu(scanner);
            switch (op) {
                case 1:
                    System.out.print("Entre com a chave: ");
                    long chave = scanner.nextLong();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("Entre com o objeto: ");
                    String nome = scanner.nextLine();
                    hashTable.put(chave, nome);
                    break;

                case 2:
                    System.out.print("Chave para remover: ");
                    chave = scanner.nextLong();
                    scanner.nextLine(); // Limpar o buffer
                    boolean removeu = hashTable.deleteHash(chave);
                    if (!removeu) {
                        System.out.println("Chave nao existente para remocao");
                    } else {
                        System.out.println("Chave removida com sucesso! :)");
                    }
                    break;

                case 3:
                    // CRIE A CHAMADA PARA BUSCAR UM ELEMENTO E IMPRIMIR SE FOI ENCONTRADO OU NAO
                        System.out.print("Chave para buscar: ");
                        chave = scanner.nextLong();
                        scanner.nextLine(); // Limpar o buffer
                        boolean encontrado = hashTable.containsKey(chave);
                        if (encontrado) {
                            System.out.println("Chave encontrada! :)");
                        } else {
                            System.out.println("Chave nao encontrada! :(");
                        }
                    break;

                case 4:
                    System.out.print("Chave para alterar: ");
                    chave = scanner.nextLong();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("Novo valor: ");
                    String novoValor = scanner.nextLine();
                    //criar funcao alterar e chamar
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
            try {
                Thread.sleep(1000); // Pausa para simular o getch()
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("\033[H\033[2J"); // Limpar a tela (ANSI escape code)
            System.out.flush();
        } while (op != 0);

        scanner.close();
    }
}
