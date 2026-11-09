
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner entrada = new Scanner(System.in);
    static Random random = new Random();
    static int pontuacao = 0;
    static ArrayList<Jogador> ranking = new ArrayList<>();

    static class Jogador {
        String nome;
        int pontos;

        Jogador(String nome, int pontos) {
            this.nome = nome;
            this.pontos = pontos;
        }
    }

    static class Pergunta {
        String enunciado;
        String[] alternativas;
        int respostaCorreta;

        Pergunta(String enunciado, String a, String b, String c, int respostaCorreta) {
            this.enunciado = enunciado;
            this.alternativas = new String[]{a, b, c};
            this.respostaCorreta = respostaCorreta;
        }
    }

    public static void main(String[] args) {

        int jogarNovamente = 1;

        System.out.println("=================================");
        System.out.println("       JOGO DE 3 FASES");
        System.out.println("=================================");

        while (jogarNovamente == 1) {

            pontuacao = 0;

            System.out.print("\nDigite o nome do jogador: ");
            String nome = entrada.nextLine();

            System.out.println("\nBem-vindo(a), " + nome + "!");

            if (fase1()) {
                if (fase2()) {
                    fase3();
                }
            }

            ranking.add(new Jogador(nome, pontuacao));

            System.out.println("\n=================================");
            System.out.println("       RESULTADO FINAL");
            System.out.println("=================================");
            System.out.println("Jogador: " + nome);
            System.out.println("Pontuação: " + pontuacao);

            exibirRanking();

            System.out.println("\nDeseja jogar novamente?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");

            jogarNovamente = lerNumero(1, 2);
        }

        System.out.println("\n=================================");
        System.out.println("       JOGO ENCERRADO!");
        System.out.println("=================================");

        entrada.close();
    }

    public static boolean fase1() {

        System.out.println("\n========== FASE 1 ==========");
        System.out.println("PEDRA, PAPEL E TESOURA");
        System.out.println("Melhor de 3 rodadas!");
        System.out.println("Cada vitória vale 15 pontos.");

        int vitoriasJogador = 0;
        int vitoriasComputador = 0;

        String[] opcoes = {"Pedra", "Papel", "Tesoura"};

        while (vitoriasJogador < 2 && vitoriasComputador < 2) {

            System.out.println("\nEscolha:");
            System.out.println("1 - Pedra");
            System.out.println("2 - Papel");
            System.out.println("3 - Tesoura");

            int escolhaJogador = lerNumero(1, 3);
            int escolhaComputador = random.nextInt(3) + 1;

            System.out.println("\nVocê escolheu: " + opcoes[escolhaJogador - 1]);
            System.out.println("Computador escolheu: " + opcoes[escolhaComputador - 1]);

            if (escolhaJogador == escolhaComputador) {

                System.out.println("EMPATE! Jogue novamente.");

            } else if (
                (escolhaJogador == 1 && escolhaComputador == 3) ||
                (escolhaJogador == 2 && escolhaComputador == 1) ||
                (escolhaJogador == 3 && escolhaComputador == 2)
            ) {

                System.out.println("Você venceu a rodada!");
                vitoriasJogador++;
                pontuacao += 15;

            } else {

                System.out.println("Você perdeu a rodada!");
                vitoriasComputador++;
            }

            System.out.println("\nPlacar:");
            System.out.println("Jogador: " + vitoriasJogador);
            System.out.println("Computador: " + vitoriasComputador);
            System.out.println("Pontuação: " + pontuacao);
        }

        if (vitoriasJogador == 2) {

            System.out.println("\nPARABÉNS! Você passou para a Fase 2!");
            return true;

        } else {

            System.out.println("\nVocê perdeu a Fase 1!");
            System.out.println("Fim de jogo!");
            return false;
        }
    }

    public static boolean fase2() {

        System.out.println("\n========== FASE 2 ==========");
        System.out.println("PERGUNTAS SOBRE JAVA");
        System.out.println("5 perguntas, cada uma vale 15 pontos.");
        System.out.println("Você precisa de 60 pontos para avançar.");

        Pergunta[] perguntas = {

            new Pergunta(
                "Qual palavra-chave é utilizada para criar uma classe em Java?",
                "function", "class", "define", 2),

            new Pergunta(
                "Qual método é o ponto de entrada de um programa Java?",
                "main()", "start()", "run()", 1),

            new Pergunta(
                "Qual tipo de dado armazena números inteiros?",
                "String", "boolean", "int", 3),

            new Pergunta(
                "Qual comando exibe informações no console?",
                "System.out.println()", "Scanner()", "Console.read()", 1),

            new Pergunta(
                "Qual estrutura é utilizada para repetir comandos?",
                "if", "for", "switch", 2)
        };

        int acertos = 0;

        for (int i = 0; i < perguntas.length; i++) {

            System.out.println("\nPergunta " + (i + 1) + ":");

            if (fazerPergunta(perguntas[i])) {

                System.out.println("Resposta correta! +15 pontos.");
                pontuacao += 15;
                acertos++;

            } else {

                System.out.println("Resposta incorreta!");
            }

            System.out.println("Pontuação atual: " + pontuacao);
        }

        System.out.println("\nVocê acertou " + acertos + " de 5 perguntas.");

        if (acertos >= 4) {

            System.out.println("Parabéns! Você passou para a Fase 3!");
            return true;

        } else {

            System.out.println("Você não alcançou os 60 pontos necessários.");
            System.out.println("Fim de jogo!");
            return false;
        }
    }

    public static void fase3() {

        System.out.println("\n========== FASE 3 ==========");
        System.out.println("ESCOLHA SEU ASSUNTO PREFERIDO!");

        System.out.println("\n1 - Tecnologia");
        System.out.println("2 - Esportes");
        System.out.println("3 - Ciências");

        int tema = lerNumero(1, 3);

        Pergunta[] perguntas;

        switch (tema) {

            case 1:
                System.out.println("\nTema escolhido: TECNOLOGIA");
                perguntas = perguntasTecnologia();
                break;

            case 2:
                System.out.println("\nTema escolhido: ESPORTES");
                perguntas = perguntasEsportes();
                break;

            default:
                System.out.println("\nTema escolhido: CIÊNCIAS");
                perguntas = perguntasCiencias();
                break;
        }

        System.out.println("\nSerão 10 perguntas.");
        System.out.println("Cada acerto vale 15 pontos.");

        for (int i = 0; i < perguntas.length; i++) {

            System.out.println("\nPergunta " + (i + 1) + " de 10:");

            if (fazerPergunta(perguntas[i])) {

                System.out.println("Correto! +15 pontos.");
                pontuacao += 15;

            } else {

                System.out.println("Resposta incorreta!");
            }

            System.out.println("Pontuação atual: " + pontuacao);
        }

        System.out.println("\nVocê concluiu todas as fases!");
    }

    public static Pergunta[] perguntasTecnologia() {

        return new Pergunta[] {

            new Pergunta("O que significa CPU?",
                "Central Processing Unit",
                "Computer Personal User",
                "Control Program Utility", 1),

            new Pergunta("Qual empresa desenvolveu o Java?",
                "Microsoft", "Sun Microsystems", "Apple", 2),

            new Pergunta("Qual linguagem é muito utilizada no desenvolvimento web?",
                "HTML", "SQL", "CSS", 1),

            new Pergunta("O que significa RAM?",
                "Random Access Memory",
                "Read Automatic Machine",
                "Real Application Method", 1),

            new Pergunta("Qual sistema operacional é de código aberto?",
                "Windows", "Linux", "macOS", 2),

            new Pergunta("Qual é a função de um banco de dados?",
                "Editar imagens",
                "Armazenar informações",
                "Aumentar a velocidade da internet", 2),

            new Pergunta("Qual destas opções é uma linguagem de programação?",
                "Python", "Photoshop", "Google", 1),

            new Pergunta("O que significa HTML?",
                "HyperText Markup Language",
                "High Technology Machine Language",
                "Home Tool Management Link", 1),

            new Pergunta("Qual dispositivo é utilizado para conectar redes?",
                "Roteador", "Monitor", "Teclado", 1),

            new Pergunta("O que é um algoritmo?",
                "Um tipo de computador",
                "Uma sequência de instruções",
                "Um sistema operacional", 2)
        };
    }

    public static Pergunta[] perguntasEsportes() {

        return new Pergunta[] {

            new Pergunta("Quantos jogadores um time de vôlei tem em quadra?",
                "5", "6", "7", 2),

            new Pergunta("Qual é o tempo normal de uma partida de futebol?",
                "Dois tempos de 45 minutos",
                "Três tempos de 30 minutos",
                "Quatro tempos de 15 minutos", 1),

            new Pergunta("Em qual esporte existe o termo 'ace'?",
                "Vôlei", "Natação", "Ciclismo", 1),

            new Pergunta("Quantos pontos vale uma cesta comum no basquete?",
                "1", "2", "3", 2),

            new Pergunta("Qual país é conhecido como o país do futebol?",
                "Brasil", "Japão", "Canadá", 1),

            new Pergunta("Em qual esporte é utilizado um volante?",
                "Tênis", "Badminton", "Vôlei", 2),

            new Pergunta("Quantos sets um time precisa vencer para ganhar uma partida oficial de vôlei de quadra?",
                "2", "3", "4", 2),

            new Pergunta("Qual destes esportes é praticado em uma piscina?",
                "Handebol", "Natação", "Rúgbi", 2),

            new Pergunta("Qual é o principal objetivo do bloqueio no vôlei?",
                "Impedir ou dificultar o ataque adversário",
                "Marcar o saque",
                "Substituir o levantador", 1),

            new Pergunta("Quantos jogadores formam um time de futebol em campo?",
                "10", "11", "12", 2)
        };
    }

    public static Pergunta[] perguntasCiencias() {

        return new Pergunta[] {

            new Pergunta("Qual é o planeta conhecido como planeta vermelho?",
                "Vênus", "Marte", "Júpiter", 2),

            new Pergunta("Qual é a fórmula química da água?",
                "CO2", "H2O", "O2", 2),

            new Pergunta("Qual órgão bombeia o sangue pelo corpo?",
                "Pulmão", "Cérebro", "Coração", 3),

            new Pergunta("Qual é o maior planeta do Sistema Solar?",
                "Terra", "Júpiter", "Saturno", 2),

            new Pergunta("Qual gás as plantas absorvem durante a fotossíntese?",
                "Oxigênio", "Nitrogênio", "Dióxido de carbono", 3),

            new Pergunta("Qual é a unidade básica da vida?",
                "Célula", "Átomo", "Molécula", 1),

            new Pergunta("Quantos ossos possui aproximadamente um adulto?",
                "106", "206", "306", 2),

            new Pergunta("Qual força mantém os planetas em órbita ao redor do Sol?",
                "Atrito", "Gravidade", "Magnetismo", 2),

            new Pergunta("Qual é o símbolo químico do ouro?",
                "Ag", "Au", "Fe", 2),

            new Pergunta("Qual parte da célula contém a maior parte do DNA?",
                "Núcleo", "Membrana", "Citoplasma", 1)
        };
    }

    public static boolean fazerPergunta(Pergunta pergunta) {

        System.out.println(pergunta.enunciado);

        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + " - " + pergunta.alternativas[i]);
        }

        int resposta = lerNumero(1, 3);

        return resposta == pergunta.respostaCorreta;
    }

    public static int lerNumero(int minimo, int maximo) {

        while (true) {

            System.out.print("Digite sua opção: ");

            String valor = entrada.nextLine();

            try {

                int numero = Integer.parseInt(valor);

                if (numero >= minimo && numero <= maximo) {
                    return numero;
                }

                System.out.println("Opção inválida! Tente novamente.");

            } catch (NumberFormatException e) {

                System.out.println("Digite apenas números!");
            }
        }
    }

    public static void exibirRanking() {

        System.out.println("\n=================================");
        System.out.println("             RANKING");
        System.out.println("=================================");

        Collections.sort(ranking,
            Comparator.comparingInt((Jogador j) -> j.pontos).reversed());

        for (int i = 0; i < ranking.size(); i++) {

            Jogador jogador = ranking.get(i);

            System.out.println((i + 1) + "º lugar - "
                    + jogador.nome + " | "
                    + jogador.pontos + " pontos");
        }

        System.out.println("=================================");
    }
}
