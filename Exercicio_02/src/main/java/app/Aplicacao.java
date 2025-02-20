package app;

import java.util.List;
import java.util.Scanner;
import dao.XDAO;
import model.X;

public class Aplicacao {
    public static void main(String[] args) {
        XDAO usuarioDAO = new XDAO();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n======= MENU =======");
            System.out.println("1 - Inserir usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("3 - Excluir usuário");
            System.out.println("4 - Atualizar senha");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            
            int option = scanner.nextInt();
            scanner.nextLine(); 
            
            if (option == 0) break;
            
            switch (option) {
                case 1:
                    System.out.print("Código: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Login: ");
                    String login = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();
                    System.out.print("Sexo (M/F): ");
                    char sexo = scanner.nextLine().charAt(0);
                    
                    X novoUsuario = new X(codigo, login, senha, sexo);
                    if (usuarioDAO.insert(novoUsuario)) {
                        System.out.println("Usuário inserido com sucesso!");
                    } else {
                        System.out.println("Erro ao inserir usuário.");
                    }
                    break;
                
                case 2:
                    System.out.println("\nLista de usuários:");
                    List<X> usuarios = usuarioDAO.get();
                    if (usuarios.isEmpty()) {
                        System.out.println("Nenhum usuário encontrado.");
                    } else {
                        for (X u : usuarios) {
                            System.out.println(u);
                        }
                    }
                    break;
                
                case 3:
                    System.out.print("Código do usuário a excluir: ");
                    int codigoExcluir = scanner.nextInt();
                    if (usuarioDAO.delete(codigoExcluir)) {
                        System.out.println("Usuário excluído com sucesso!");
                    } else {
                        System.out.println("Erro ao excluir usuário.");
                    }
                    break;
                
                case 4:
                    System.out.print("Código do usuário: ");
                    int codigoAtualizar = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Nova senha: ");
                    String novaSenha = scanner.nextLine();
                    
                    X usuario = usuarioDAO.get(codigoAtualizar);
                    if (usuario != null) {
                        usuario.setSenha(novaSenha);
                        if (usuarioDAO.update(usuario)) {
                            System.out.println("Senha atualizada com sucesso!");
                        } else {
                            System.out.println("Erro ao atualizar senha.");
                        }
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        
        scanner.close();
        System.out.println("Programa encerrado.");
    }
}
